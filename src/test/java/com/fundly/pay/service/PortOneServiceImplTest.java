package com.fundly.pay.service;

import com.fundly.pay.dto.billkey.BillKeyRequestDto;
import com.fundly.pay.dto.billkey.BillKeyResponseDto;
import com.fundly.pay.dto.payment.PaymentRequestDto;
import com.fundly.pay.dto.payment.PaymentResponseDto;
import com.fundly.pay.dto.schedule.ScheduledPayRequestDto;
import com.fundly.pay.dto.schedule.ScheduledPayResponseDto;
import com.fundly.pay.dto.token.TokenResponseDto;
import com.fundly.pay.model.PayMeansDao;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
class PortOneServiceImplTest {

    @Autowired
    PortOneService portOneService;

    @Autowired
    PayMeansDao payMeansDao;

    @Value("${PAY_CARD_NO}")
    private String PAY_CARD_NO;

    @Value("${PAY_EXPIRY}")
    private String PAY_EXPIRY;

    @Value("${PAY_BIRTH}")
    private String PAY_BIRTH;

    @Value("${PAY_PWD_2DIGIT}")
    private String PAY_PWD_2DIGIT;

    String authToken = "";
    String userId = "test";
    String payMeansId = "";
    PaymentRequestDto paymentRequestDto;
    BillKeyRequestDto billKeyRequestDto;

    @BeforeEach
    @SneakyThrows
    @DisplayName("테스트 수행 전 초기화")
    void init() {
        payMeansId = payMeansDao.selectPayMeansId(userId);

        // 빌링키 발급 & 삭제 RequestDto 생성
        billKeyRequestDto = BillKeyRequestDto.builder()
                .cardNo(PAY_CARD_NO)
                .expiry(PAY_EXPIRY)
                .pg("nice_v2")
                .birth(PAY_BIRTH)
                .pwd2digit(PAY_PWD_2DIGIT)
                .customer_uid(payMeansId)
                .build();

        // 결제 & 결제취소 RequestDto 생성
        paymentRequestDto = PaymentRequestDto.builder()
                .customer_uid(payMeansId)
                .amount(BigInteger.valueOf(100))
                .name("test")
                .build();

        // PortOne 인증 토큰 발급
        ResponseEntity<TokenResponseDto> tokenResponseDto = portOneService.getToken();
        assertEquals(tokenResponseDto.getStatusCodeValue(), 200);
        assertEquals(tokenResponseDto.getBody().getCode(), 0);
        authToken = tokenResponseDto.getBody().getResponse().getAccess_token();

        // 빌링키 발급
        ResponseEntity<BillKeyResponseDto> billKeyResponseDto = portOneService.getBillKey(billKeyRequestDto, authToken);
        assertEquals(billKeyResponseDto.getStatusCodeValue(), 200);
        assertEquals(billKeyResponseDto.getBody().getCode(), 0);
    }

    @Test
    @DisplayName("결제")
    void requestPayTest() {
        paymentRequestDto.setMerchant_uid("requestPayTest_99999");

        ResponseEntity<PaymentResponseDto> paymentResponseDto = portOneService.requestPay(paymentRequestDto, authToken);
        assertEquals(paymentResponseDto.getStatusCodeValue(), 200);
        assertEquals(paymentResponseDto.getBody().getCode(), 0);
    }

    @Test
    @DisplayName("결제 취소")
    void cancelPayTest() {
        paymentRequestDto.setMerchant_uid("cancelPayTest_99999");

        // 결제
        ResponseEntity<PaymentResponseDto> paymentResponseDto = portOneService.requestPay(paymentRequestDto, authToken);
        assertEquals(paymentResponseDto.getStatusCodeValue(), 200);
        assertEquals(paymentResponseDto.getBody().getCode(), 0);

        // 결제 취소
        paymentResponseDto = portOneService.cancelPay(paymentRequestDto, authToken);
        assertEquals(paymentResponseDto.getStatusCodeValue(), 200);
        assertEquals(paymentResponseDto.getBody().getCode(), 0);
    }

    @Test
    @DisplayName("빌링키 삭제")
    void removeBillKeyTest() {
        // 빌링키 삭제
        ResponseEntity<BillKeyResponseDto> billKeyResponseDto = portOneService.removeBillKey(billKeyRequestDto, authToken);
        assertEquals(billKeyResponseDto.getStatusCodeValue(), 200);
        assertEquals(billKeyResponseDto.getBody().getCode(), 0);
    }

    @Test
    @DisplayName("예약된 결제 내역 조회")
    void getScheduledPayTest() {
        long currentUnixTime = System.currentTimeMillis() / 1000; // 현재 UTC Time
        long threeMonthsAgoUnixTime = currentUnixTime - (3L * 30L * 24L * 60L * 60L); // 3개월 전 UTC Time
        ScheduledPayRequestDto scheduledPayRequestDto = ScheduledPayRequestDto.builder()
                .customer_uid(payMeansId)
                .schedule_status("scheduled")
                .from(threeMonthsAgoUnixTime)
                .to(currentUnixTime)
                .build();

        // 예약된 결제 내역 조회
        ResponseEntity<ScheduledPayResponseDto> scheduledPayResponseDto = portOneService.getScheduledPay(scheduledPayRequestDto, authToken);
        assertEquals(scheduledPayResponseDto.getStatusCodeValue(), 200);
        assertEquals(scheduledPayResponseDto.getBody().getCode(), 0);
        assertEquals(scheduledPayResponseDto.getBody().getResponse().getTotal(), 0); // 예약된 결제 내역 0건
    }

//    @Test
//    @DisplayName("빌링키 발급")
//    void getBillKeyTest() {
//        ResponseEntity<BillKeyResponseDto> billKeyResponseDto = portOneService.getBillKey(billKeyRequestDto, authToken);
//        assertEquals(billKeyResponseDto.getStatusCodeValue(), 200);
//        assertEquals(billKeyResponseDto.getBody().getCode(), 0);
//    }

//    @Test
//    @DisplayName("인증 토큰 발급")
//    void getTokenTest() {
//        ResponseEntity<TokenResponseDto> tokenResponseDto = portOneService.getToken();
//        assertEquals(tokenResponseDto.getStatusCodeValue(), 200);
//        assertEquals(tokenResponseDto.getBody().getCode(), 0);
//    }
}
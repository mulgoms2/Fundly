package com.fundly.pay.service;

import com.fundly.pay.dto.billkey.BillKeyRequestDto;
import com.fundly.pay.dto.billkey.BillKeyResponseDto;
import com.fundly.pay.dto.schedule.ScheduledPayRequestDto;
import com.fundly.pay.dto.schedule.ScheduledPayResponseDto;
import com.fundly.pay.model.PayMeansDao;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    @SneakyThrows
    @DisplayName("빌링키 삭제")
    void removeBillKeyTest() {
        assertEquals(portOneService.getToken().getBody().getCode(), 0, "[removeBillKeyTest] getToken 테스트 실패");
        String authToken = portOneService.getToken().getBody().getResponse().getAccess_token();

        String userId = "test";
        String payMeansId = payMeansDao.selectPayMeansId(userId);
        BillKeyRequestDto billKeyRequestDto = new BillKeyRequestDto(PAY_CARD_NO, PAY_EXPIRY, PAY_BIRTH, PAY_PWD_2DIGIT, payMeansId);
        ResponseEntity<BillKeyResponseDto> billKeyResponseDto = portOneService.getBillKey(billKeyRequestDto, authToken);
        assertEquals(billKeyResponseDto.getBody().getCode(), 0, "[removeBillKeyTest] getBillKey 테스트 실패");

        // 결제예약내역이 없는지 확인 후 삭제
        long currentUnixTime = System.currentTimeMillis() / 1000;
        long threeMonthsAgoUnixTime = currentUnixTime - (3L * 30L * 24L * 60L * 60L);
        ScheduledPayRequestDto scheduledPayRequestDto = new ScheduledPayRequestDto(billKeyRequestDto.getCustomer_uid(), "scheduled", threeMonthsAgoUnixTime, currentUnixTime);
        ResponseEntity<ScheduledPayResponseDto> scheduledPayResponseDto = portOneService.getScheduledPay(scheduledPayRequestDto, authToken);
        assertEquals(scheduledPayResponseDto.getBody().getCode(), 0, "[removeBillKeyTest] getScheduledPay 테스트 실패");
        assertEquals(scheduledPayResponseDto.getBody().getResponse().getTotal(), 0, "[removeBillKeyTest] getScheduledPay Total 테스트 실패");

        billKeyResponseDto = portOneService.removeBillKey(billKeyRequestDto, authToken);
        assertEquals(billKeyResponseDto.getBody().getCode(), 0, "[removeBillKeyTest] removeBillKeyTest 테스트 실패");
    }

    @Test
    @SneakyThrows
    @DisplayName("결제 예약 내역 조회")
    void getScheduledPayTest() {
        // TODO: 결제예약요청 메서드 만들어지면 total 카운트로 검증해보기

        assertEquals(portOneService.getToken().getBody().getCode(), 0,  "[getScheduledPayTest] getToken 테스트 실패");
        String authToken = portOneService.getToken().getBody().getResponse().getAccess_token();

        String userId = "test";
        String payMeansId = payMeansDao.selectPayMeansId(userId);
        BillKeyRequestDto billKeyRequestDto = new BillKeyRequestDto(PAY_CARD_NO, PAY_EXPIRY, PAY_BIRTH, PAY_PWD_2DIGIT, payMeansId);
        ResponseEntity<BillKeyResponseDto> billKeyResponseDto = portOneService.getBillKey(billKeyRequestDto, authToken);
        assertEquals(billKeyResponseDto.getBody().getCode(), 0, "[getScheduledPayTest] getBillKey 테스트 실패");

        long currentUnixTime = System.currentTimeMillis() / 1000;
        long threeMonthsAgoUnixTime = currentUnixTime - (3L * 30L * 24L * 60L * 60L);
        ScheduledPayRequestDto scheduledPayRequestDto = new ScheduledPayRequestDto(billKeyRequestDto.getCustomer_uid(), "scheduled", threeMonthsAgoUnixTime, currentUnixTime);
        ResponseEntity<ScheduledPayResponseDto> scheduledPayResponseDto = portOneService.getScheduledPay(scheduledPayRequestDto, authToken);
        assertEquals(scheduledPayResponseDto.getBody().getCode(), 0, "[getScheduledPayTest] getScheduledPay 테스트 실패");
    }

    @Test
    @SneakyThrows
    @DisplayName("빌링키 발급")
    void getBillKeyTest() {
        assertEquals(portOneService.getToken().getBody().getCode(), 0, "[getBillKeyTest] getToken 테스트 실패");
        String authToken = portOneService.getToken().getBody().getResponse().getAccess_token();

        String userId = "test";
        String payMeansId = payMeansDao.selectPayMeansId(userId);
        BillKeyRequestDto billKeyRequestDto = new BillKeyRequestDto("1111111111111111", "2027-04", "970101", "11", payMeansId);
        ResponseEntity<BillKeyResponseDto> billKeyResponseDto = portOneService.getBillKey(billKeyRequestDto, authToken);
        assertTrue(billKeyResponseDto.getBody().getCode() != 0);

        billKeyRequestDto = new BillKeyRequestDto(PAY_CARD_NO, PAY_EXPIRY, PAY_BIRTH, PAY_PWD_2DIGIT, payMeansId);
        billKeyResponseDto = portOneService.getBillKey(billKeyRequestDto, authToken);
        assertEquals(billKeyResponseDto.getBody().getCode(), 0, "[getBillKeyTest] getBillKey 테스트 실패");
    }

    @Test
    @SneakyThrows
    @DisplayName("인증 토큰 발급")
    void getTokenTest() {
        assertEquals(portOneService.getToken().getStatusCodeValue(), 200, "[getTokenTest] getToken 테스트 실패");
        assertEquals(portOneService.getToken().getBody().getCode(), 0, "[getTokenTest] getToken 테스트 실패");

        // Exception 처리 확인
//        try {
//            log.info("portOneService.getToken().getStatusCode(): " + portOneService.getToken().getStatusCode());
//            log.info("portOneService.getToken().getStatusCodeValue(): " + portOneService.getToken().getStatusCodeValue());
//            log.info("portOneService.getToken() authToken: " + Objects.requireNonNull(portOneService.getToken().getBody()).getResponse().getAccess_token());
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.info("에러가 발생했어요 !!!" + e.getMessage());
//        }
    }
}
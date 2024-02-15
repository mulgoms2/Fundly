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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    void removeBillKeyTest() {
        assertEquals(portOneService.getToken().getCode(), 0, "[removeBillKeyTest] getToken 테스트 실패");
        String authToken = portOneService.getToken().getResponse().getAccess_token();

        String userId = "test";
        String payMeansId = payMeansDao.selectPayMeansId(userId);
        BillKeyRequestDto billKeyRequestDto = new BillKeyRequestDto(PAY_CARD_NO, PAY_EXPIRY, PAY_BIRTH, PAY_PWD_2DIGIT, payMeansId);
        BillKeyResponseDto billKeyResponseDto = portOneService.getBillKey(billKeyRequestDto, authToken);
        assertEquals(0, billKeyResponseDto.getCode(), "[removeBillKeyTest] getBillKey 테스트 실패");

        // 결제예약내역이 없는지 확인 후 삭제
        long currentUnixTime = System.currentTimeMillis() / 1000;
        long threeMonthsAgoUnixTime = currentUnixTime - (3L * 30L * 24L * 60L * 60L);
        ScheduledPayRequestDto scheduledPayRequestDto = new ScheduledPayRequestDto(billKeyRequestDto.getCustomer_uid(), "scheduled", threeMonthsAgoUnixTime, currentUnixTime);
        ScheduledPayResponseDto scheduledPayResponseDto = portOneService.getScheduledPay(scheduledPayRequestDto, authToken);
        assertEquals(0, scheduledPayResponseDto.getCode(), "[removeBillKeyTest] getScheduledPay 테스트 실패");
        assertEquals(0, scheduledPayResponseDto.getResponse().getTotal(), "[removeBillKeyTest] getScheduledPay Total 테스트 실패");

        billKeyResponseDto = portOneService.removeBillKey(billKeyRequestDto, authToken);
        assertEquals(0, billKeyResponseDto.getCode(), "[removeBillKeyTest] removeBillKeyTest 테스트 실패");
    }

    @Test
    @SneakyThrows
    void getScheduledPayTest() {
        // TODO: 결제예약요청 메서드 만들어지면 total 카운트로 검증해보기

        assertEquals(portOneService.getToken().getCode(), 0, "[getScheduledPayTest] getToken 테스트 실패");
        String authToken = portOneService.getToken().getResponse().getAccess_token();

        String userId = "test";
        String payMeansId = payMeansDao.selectPayMeansId(userId);
        BillKeyRequestDto billKeyRequestDto = new BillKeyRequestDto(PAY_CARD_NO, PAY_EXPIRY, PAY_BIRTH, PAY_PWD_2DIGIT, payMeansId);
        BillKeyResponseDto billKeyResponseDto = portOneService.getBillKey(billKeyRequestDto, authToken);
        assertEquals(0, billKeyResponseDto.getCode(), "[getScheduledPayTest] getBillKey 테스트 실패");

        long currentUnixTime = System.currentTimeMillis() / 1000;
        long threeMonthsAgoUnixTime = currentUnixTime - (3L * 30L * 24L * 60L * 60L);
        ScheduledPayRequestDto scheduledPayRequestDto = new ScheduledPayRequestDto(billKeyRequestDto.getCustomer_uid(), "scheduled", threeMonthsAgoUnixTime, currentUnixTime);
        ScheduledPayResponseDto scheduledPayResponseDto = portOneService.getScheduledPay(scheduledPayRequestDto, authToken);
        assertEquals(0, scheduledPayResponseDto.getCode(), "[getScheduledPayTest] getScheduledPay 테스트 실패");
    }

    @Test
    @SneakyThrows
    void getBillKeyTest() {
        assertEquals(portOneService.getToken().getCode(), 0, "[getBillKeyTest] getToken 테스트 실패");
        String authToken = portOneService.getToken().getResponse().getAccess_token();

        String userId = "test";
        String payMeansId = payMeansDao.selectPayMeansId(userId);
        BillKeyRequestDto billKeyRequestDto = new BillKeyRequestDto("1111111111111111", "2027-04", "970101", "11", payMeansId);
        BillKeyResponseDto billKeyResponseDto = portOneService.getBillKey(billKeyRequestDto, authToken);
        assertTrue(billKeyResponseDto.getCode() != 0);

        billKeyRequestDto = new BillKeyRequestDto(PAY_CARD_NO, PAY_EXPIRY, PAY_BIRTH, PAY_PWD_2DIGIT, payMeansId);
        billKeyResponseDto = portOneService.getBillKey(billKeyRequestDto, authToken);
        assertEquals(0, billKeyResponseDto.getCode(), "[getBillKeyTest] getBillKey 테스트 실패");
    }

    @Test
    @SneakyThrows
    void getTokenTest() {
        assertEquals(0, portOneService.getToken().getCode(), "[getTokenTest] getToken 테스트 실패");
    }
}
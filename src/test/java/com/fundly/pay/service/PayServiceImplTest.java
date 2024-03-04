package com.fundly.pay.service;

import com.fundly.pay.dto.token.TokenData;
import com.fundly.pay.dto.token.TokenResponseDto;
import com.fundly.pay.model.PayDao;
import com.persistence.dto.PayDto;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.math.BigInteger;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Slf4j
@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
@ExtendWith(MockitoExtension.class)
class PayServiceImplTest {

    @Mock private PayDao payDao;
    @Mock private PortOneServiceImpl portOneService;
    @InjectMocks private PayServiceImpl payService;

    @Test
    @SneakyThrows
    @DisplayName("결제테이블 데이터 생성")
    void createPayRecordFromOrder() {
        // given
        // 가짜 주문 데이터 생성
        String fakeOrderListId = "fakeOrderListId";
        PayDto fakeOrder = PayDto.builder()
                .order_list_id(fakeOrderListId)
                .order_status("주문완료")
                .build();

        given(payDao.selectCompletedOrder()).willReturn(Arrays.asList(fakeOrder));

        // when
        payService.createPayRecordFromOrder();

        // then
        verify(payDao, times(1)).selectPayId(anyString());
        verify(payDao, times(1)).insert(fakeOrder);
        verify(payDao, times(1)).updatePayInsertedToY(fakeOrderListId);
    }

    @Test
    @SneakyThrows
    @DisplayName("결제")
    void executePayment() {
        // given
        String userId = "test";
        String fakePayId = "fakePayId";
        PayDto fakePayment = PayDto.builder()
                .pay_id(fakePayId)
                .pay_means_id("fakePayMeansId")
                .order_list_id("fakeOrderListId")
                .order_pay_money(BigInteger.valueOf(100))
                .pay_status("미결제")
                .build();
        String fakeAccessToken = "fakeAccessToken";

        given(payDao.selectPayTarget()).willReturn(Arrays.asList(fakePayment));
        given(portOneService.getToken()).willReturn(ResponseEntity.ok(
                TokenResponseDto.builder().response(TokenData.builder().access_token(fakeAccessToken).build())
                        .build()));

        // when
        payService.executePayment();

        // then
        verify(portOneService, times(1)).requestPay(any(), eq(fakeAccessToken));
        verify(payDao, times(1)).updatePayStatus(fakePayment);
    }

    @Test
    @DisplayName("재결제")
    void retryPayment() {
        // given

        // when
        payService.retryPayment();

        // then

    }
}
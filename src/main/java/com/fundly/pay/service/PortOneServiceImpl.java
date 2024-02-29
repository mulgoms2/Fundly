package com.fundly.pay.service;

import com.fundly.pay.dto.billkey.BillKeyRequestDto;
import com.fundly.pay.dto.billkey.BillKeyResponseDto;
import com.fundly.pay.dto.payment.PaymentRequestDto;
import com.fundly.pay.dto.payment.PaymentResponseDto;
import com.fundly.pay.dto.schedule.ScheduledPayRequestDto;
import com.fundly.pay.dto.schedule.ScheduledPayResponseDto;
import com.fundly.pay.dto.token.TokenResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Service
@Slf4j
public class PortOneServiceImpl implements PortOneService {
    @Autowired
    WebClient webClient;

    @Value("${PORTONE_API_KEY}")
    private String PORTONE_API_KEY;

    @Value("${PORTONE_API_SECRET}")
    private String PORTONE_API_SECRET;

    public ResponseEntity<PaymentResponseDto> cancelPay(PaymentRequestDto paymentRequestDto, String authToken) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("merchant_uid", paymentRequestDto.getMerchant_uid());

        String requestUrl = "/payments/cancel";

        return webClient.post()
                .uri(requestUrl)
                .header("Authorization", "Bearer " + authToken)
                .body(BodyInserters.fromFormData(map))
                .retrieve()
                .toEntity(PaymentResponseDto.class)
                .doOnSuccess(res -> { // 200 OK
                    log.info("cancelPay 요청 성공");
                    // response의 code 값이 0이어야만 정상적인 조회이므로, 0이 아닌 경우 RuntimeException 발생시킨다.
                    if (Objects.requireNonNull(res.getBody()).getCode() != 0) {
                        throw new RuntimeException("PortOneService cancelPay() ERROR");
                    }
                })
                .doOnError(e -> { // ERROR
                    log.error("ResponseEntity<PaymentResponseDto> cancelPay(PaymentRequestDto paymentRequestDto, String authToken) : {}\n {}\n", e.getMessage(), e.getStackTrace());
                    throw new RuntimeException("PortOneService cancelPay() ERROR");
                })
                .block();
    }

    public ResponseEntity<PaymentResponseDto> requestPay(PaymentRequestDto paymentRequestDto, String authToken) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("customer_uid", paymentRequestDto.getCustomer_uid());
        map.add("merchant_uid", paymentRequestDto.getMerchant_uid());
        map.add("amount", paymentRequestDto.getAmount().toString());
        map.add("name", paymentRequestDto.getName());

        String requestUrl = "/subscribe/payments/again";

        return webClient.post()
                .uri(requestUrl)
                .header("Authorization", "Bearer " + authToken)
                .body(BodyInserters.fromFormData(map))
                .retrieve()
                .toEntity(PaymentResponseDto.class)
                .doOnSuccess(res -> { // 200 OK
                    log.info("requestPay 요청 성공");
                    // response의 code 값이 0이어야만 정상적인 조회이므로, 0이 아닌 경우 RuntimeException 발생시킨다.
                    if (Objects.requireNonNull(res.getBody()).getCode() != 0) {
                        throw new RuntimeException("PortOneService requestPay() ERROR");
                    }
                })
                .doOnError(e -> { // ERROR
                    log.error("ResponseEntity<PaymentResponseDto> requestPay(PaymentRequestDto paymentRequestDto, String authToken) : {}\n {}\n", e.getMessage(), e.getStackTrace());
                    throw new RuntimeException("PortOneService requestPay() ERROR");
                })
                .block();
    }

    public ResponseEntity<BillKeyResponseDto> removeBillKey(BillKeyRequestDto billKeyRequestDto, String authToken) {
        String requestUrl = "/subscribe/customers/";

        return webClient.delete()
                .uri(uriBuilder -> uriBuilder
                        .path(requestUrl + "{customer_uid}")
                        .build(billKeyRequestDto.getCustomer_uid()))
                .header("Authorization", "Bearer " + authToken)
                .retrieve()
                .toEntity(BillKeyResponseDto.class)
                .doOnSuccess(res -> { // 200 OK
                    log.info("removeBillKey 요청 성공");
                    // response의 code 값이 0이어야만 정상적인 조회이므로, 0이 아닌 경우 RuntimeException 발생시킨다.
                    if (Objects.requireNonNull(res.getBody()).getCode() != 0) {
                        throw new RuntimeException("PortOneService removeBillKey() ERROR");
                    }
                })
                .doOnError(e -> { // ERROR
                    log.error("ResponseEntity<BillKeyResponseDto> removeBillKey(BillKeyRequestDto billKeyRequestDto, String authToken) : {}\n {}\n", e.getMessage(), e.getStackTrace());
                    throw new RuntimeException("PortOneService removeBillKey() ERROR");
                })
                .block();
    }

    public ResponseEntity<ScheduledPayResponseDto> getScheduledPay(ScheduledPayRequestDto scheduledPayRequestDto, String authToken) {
        String requestUrl = "/subscribe/customers/";

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(requestUrl + "{customer_uid}" + "/schedules")
                        .queryParam("from", scheduledPayRequestDto.getFrom())
                        .queryParam("to", scheduledPayRequestDto.getTo())
                        .queryParam("schedule-status", scheduledPayRequestDto.getSchedule_status())
                        .build(scheduledPayRequestDto.getCustomer_uid()))
                .header("Authorization", "Bearer " + authToken)
                .retrieve()
                .toEntity(ScheduledPayResponseDto.class)
                .doOnSuccess(res -> { // 200 OK
                    log.info("getScheduledPay 요청 성공");
                    // response의 code 값이 0이어야만 정상적인 조회이므로, 0이 아닌 경우 RuntimeException 발생시킨다.
                    if (Objects.requireNonNull(res.getBody()).getCode() != 0) {
                        throw new RuntimeException("PortOneService getScheduledPay() ERROR");
                    }
                })
                .doOnError(e -> { // ERROR
                    log.error("ResponseEntity<ScheduledPayResponseDto> getScheduledPay(ScheduledPayRequestDto scheduledPayRequestDto, String authToken) : {}\n {}\n", e.getMessage(), e.getStackTrace());
                    throw new RuntimeException("PortOneService getScheduledPay() ERROR");
                })
                .block();
    }

    // 2. 결제사에 카드 등록: getBillKey() -> registerPayMeans()
    public ResponseEntity<BillKeyResponseDto> getBillKey(BillKeyRequestDto billKeyRequestDto, String authToken) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("card_number", billKeyRequestDto.getCardNo());
        map.add("expiry", billKeyRequestDto.getExpiry());
        map.add("pg", billKeyRequestDto.getPg());
        map.add("birth", billKeyRequestDto.getBirth());
        map.add("pwd_2digit", billKeyRequestDto.getPwd2digit());

        String requestUrl = "/subscribe/customers/";

        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(requestUrl+"{customer_uid}")
                        .build(billKeyRequestDto.getCustomer_uid()))
                .header("Authorization", "Bearer " + authToken)
                .body(BodyInserters.fromFormData(map))
                .retrieve()
                .toEntity(BillKeyResponseDto.class)
                .doOnSuccess(res -> { // 200 OK
                    log.info("getBillKey 요청 성공");
                    // response의 code 값이 0이어야만 정상적인 조회이므로, 0이 아닌 경우 RuntimeException 발생시킨다.
                    if (Objects.requireNonNull(res.getBody()).getCode() != 0) {
                        throw new RuntimeException("PortOneService getBillKey() ERROR");
                    }
                })
                .doOnError(e -> { // ERROR
                    log.error("ResponseEntity<BillKeyResponseDto> getBillKey(BillKeyRequestDto billKeyRequestDto, String authToken) : {}\n {}\n", e.getMessage(), e.getStackTrace());
                    throw new RuntimeException("PortOneService getBillKey() ERROR");
                })
                .block();
    }

    // 1. 포트원 API 호출 전, 모든 함수 공통 호출: getToken()
    public ResponseEntity<TokenResponseDto> getToken() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("imp_key", PORTONE_API_KEY);
        map.add("imp_secret", PORTONE_API_SECRET);

        String requestUrl = "/users/getToken";

        return webClient.post()
            .uri(requestUrl)
            .body(BodyInserters.fromFormData(map)) // set body value
                .retrieve() // 요청 전송
                .toEntity(TokenResponseDto.class)
                .doOnSuccess(res -> { // 200 OK
                    log.info("getToken 요청 성공");
                    // response의 code 값이 0이어야만 정상적인 조회이므로, 0이 아닌 경우 RuntimeException 발생시킨다.
                    if (Objects.requireNonNull(res.getBody()).getCode() != 0) {
                        throw new RuntimeException("PortOneService getToken() ERROR");
                    }
                })
                .doOnError(e -> { // ERROR
                    log.error("ResponseEntity<TokenResponseDto> getToken() : {}\n {}\n", e.getMessage(), e.getStackTrace());
                    throw new RuntimeException("PortOneService getToken() ERROR");
                })
                .block(); // 동기
    }
}

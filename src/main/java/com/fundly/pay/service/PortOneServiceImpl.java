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

import static com.fundly.pay.util.ExceptionHandlerUtil.handleException;


@Service
@Slf4j
public class PortOneServiceImpl implements PortOneService {
    @Autowired
    WebClient webClient;

    @Value("${PORTONE_API_KEY}")
    private String PORTONE_API_KEY;

    @Value("${PORTONE_API_SECRET}")
    private String PORTONE_API_SECRET;

    // 결제 취소
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
                        throw new RuntimeException("PortOneService cancelPay() ERROR" + res.getBody().getMessage());
                    }
                    if (!Objects.requireNonNull(res.getBody()).getResponse().getStatus().equals("cancelled")) {
                        throw new RuntimeException("PortOneService cancelPay() ERROR: " + res.getBody().getResponse().getFail_reason());
                    }
                })
                .doOnError(e -> { // ERROR
                    handleException("ResponseEntity<PaymentResponseDto> cancelPay(PaymentRequestDto paymentRequestDto, String authToken)", (Exception) e);
                })
                .block();
    }

    // 결제
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
                    // response의 code 값이 0이 아니거나, 0이더라도 status가 paid가 아니면 에외를 발생시킨다.
                    if (Objects.requireNonNull(res.getBody()).getCode() != 0) {
                        throw new RuntimeException("PortOneService requestPay() ERROR: " + res.getBody().getMessage());
                    }
                    if (!Objects.requireNonNull(res.getBody()).getResponse().getStatus().equals("paid")) {
                        throw new RuntimeException("PortOneService requestPay() ERROR: " + res.getBody().getResponse().getFail_reason());
                    }
                })
                .doOnError(e -> { // ERROR
                    log.info("requestPay 요청 실패");
                    handleException("requestPay(PaymentRequestDto paymentRequestDto, String authToken)", (Exception) e);
                })
                .block();
    }

    // 결제수단 삭제 (빌링키 삭제)
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
                        throw new RuntimeException("PortOneService removeBillKey() ERROR : " + res.getBody().getMessage());
                    }
                })
                .doOnError(e -> { // ERROR
                    handleException("removeBillKey(BillKeyRequestDto billKeyRequestDto, String authToken)", (Exception) e);
                })
                .block();
    }

    // 예약된 결제 내역 조회
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
                        throw new RuntimeException("PortOneService getScheduledPay() ERROR : " + res.getBody().getMessage());
                    }
                })
                .doOnError(e -> { // ERROR
                    handleException("getScheduledPay(ScheduledPayRequestDto scheduledPayRequestDto, String authToken)", (Exception) e);
                })
                .block();
    }

    // 결제수단 등록 (빌링키 발급)
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
                        throw new RuntimeException("PortOneService getBillKey() ERROR : " + res.getBody().getMessage());
                    }
                })
                .doOnError(e -> { // ERROR
                    handleException("getBillKey(BillKeyRequestDto billKeyRequestDto, String authToken)", (Exception) e);
                })
                .block();
    }

    // PortOne 인증 토큰 발급 (포트원 API 호출 전, 모든 메서드에서 공통 호출)
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
                        throw new RuntimeException("PortOneService getToken() ERROR : " + res.getBody().getMessage() );
                    }
                })
                .doOnError(e -> { // ERROR
                    handleException("getToken()", (Exception) e);
                })
                .block(); // 동기
    }
}

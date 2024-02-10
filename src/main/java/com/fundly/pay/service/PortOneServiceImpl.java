package com.fundly.pay.service;

import com.fundly.pay.dto.BillKeyRequestDto;
import com.fundly.pay.dto.BillKeyResponseDto;
import com.fundly.pay.dto.TokenResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@PropertySource(value = "/WEB-INF/config/pay.properties")
@Slf4j
public class PortOneServiceImpl implements PortOneService {

    @Autowired
    WebClient webClient;

    @Value("${PORTONE_API_KEY}")
    private String PORTONE_API_KEY;

    @Value("${PORTONE_API_SECRET}")
    private String PORTONE_API_SECRET;

    // 2. 결제사에 카드 등록: getBillKey() -> registerPayMeans()
    public BillKeyResponseDto getBillKey(BillKeyRequestDto billKeyRequestDto, String authToken) throws Exception {
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
                .bodyToMono(BillKeyResponseDto.class)
//                .map(BillKeyResponseDto::getBillKey)
                .doOnSuccess(res -> log.info("getBillKey 요청 성공"))
                .doOnError(res -> log.info("getBillKey 요청 실패"))
                .block();
    }

    // 1. 포트원 API 호출 전, 모든 함수 공통 호출: getToken()
    public String getToken() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("imp_key", PORTONE_API_KEY);
        map.add("imp_secret", PORTONE_API_SECRET);
        String requestUrl = "/users/getToken";

        return webClient.post()
                .uri(requestUrl)
                .body(BodyInserters.fromFormData(map)) // set body value
                .retrieve() // 요청 전송
                .bodyToMono(TokenResponseDto.class) // response를 Mono로 변환
                .map(TokenResponseDto::getAccess_token)
                .doOnSuccess(res -> log.info("getToken 요청 성공"))
                .doOnError(res -> log.info("getToken 요청 실패"))
                .block();
    }

}

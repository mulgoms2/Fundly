package com.fundly.pay.dto.payment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class PaymentResponseDto {
    private Integer code; // 응답코드. 0이면 정상적인 조회
    private String message; // code != 0 일때 오류 메세지
    private PaymentData response; // 응답 상세데이터
}

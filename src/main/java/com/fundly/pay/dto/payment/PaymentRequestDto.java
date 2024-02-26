package com.fundly.pay.dto.payment;

import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentRequestDto {
    // required
    private String customer_uid; // 결제수단 ID
    private String merchant_uid; // 주문 ID
    private BigInteger amount; // 결제금액
    private String name; // 상품명
}

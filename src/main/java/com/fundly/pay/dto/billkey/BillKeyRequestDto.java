package com.fundly.pay.dto.billkey;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BillKeyRequestDto {
    private String cardNo;
    private String expiry;
    private String pg = "nice_v2";
    private String birth;
    private String pwd2digit;
    private String customer_uid; // pay_means_id

    public BillKeyRequestDto(String cardNo, String expiry, String birth, String pwd2digit, String customer_uid) {
        this.cardNo = cardNo;
        this.expiry = expiry;
        this.birth = birth;
        this.pwd2digit = pwd2digit;
        this.customer_uid = customer_uid;
    }
}

package com.fundly.pay.dto.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ScheduledPay {
    private String customer_uid;
    private String merchant_uid;
    private String imp_uid;
    private String customer_id;
    private long schedule_at;
    private long executed_at;
    private long revoked_at;
    private Long amount;
    private String name;
    private String buyer_name;
    private String buyer_tel;
    private String buyer_email;
    private String buyer_addr;
    private String buyer_postcode;
    private String schedule_status;
    private String payment_status;
    private String fail_reason;
    private String custom_data;
    private String currency;
}
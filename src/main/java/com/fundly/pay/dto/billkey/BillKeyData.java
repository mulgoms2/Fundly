package com.fundly.pay.dto.billkey;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@ToString
public class BillKeyData {
    private String card_code;
    private String card_issuer_code;
    private String card_issuer_name;
    private String card_name;
    private String card_number;
    private String card_publisher_code;
    private String card_publisher_name;
    private Integer card_type;
    private String customer_addr;
    private String customer_email;
    private String customer_id;
    private String customer_name;
    private String customer_postcode;
    private String customer_tel;
    private String customer_uid;
    private Timestamp inserted;
    private String pg_id;
    private String pg_provider;
    private Timestamp updated;
}
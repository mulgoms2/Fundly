package com.fundly.pay.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@ToString
public class BillKeyResponseDto {
    private Integer code;
    private String message;
    private BillKeyData response;
    public String getBillKey() {
        return response != null ? response.getCustomer_id() : null;
    }
}

@Getter
@NoArgsConstructor
@ToString
class BillKeyData {
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
    private Timestamp inserted;
    private String pg_id;
    private String pg_provider;
    private Timestamp updated;
}

//"response": {
//        "card_code": "381",
//        "card_issuer_code": "381",
//        "card_issuer_name": "KB국민카드",
//        "card_name": "KB국민카드",
//        "card_number": "53651019****3392",
//        "card_publisher_code": "090",
//        "card_publisher_name": "카카오뱅크",
//        "card_type": 1,
//        "customer_addr": null,
//        "customer_email": null,
//        "customer_id": "customer-id-1d453314-0eae-4c96-99e0-239ba3c98f05",
//        "customer_name": null,
//        "customer_postcode": null,
//        "customer_tel": null,
//        "customer_uid": ":customer_uid",
//        "inserted": 1707307868,
//        "pg_id": "iamport01m",
//        "pg_provider": "nice_v2",
//        "updated": 1707319533
//        }
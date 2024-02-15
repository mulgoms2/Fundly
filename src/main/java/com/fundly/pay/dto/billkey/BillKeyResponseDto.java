package com.fundly.pay.dto.billkey;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class BillKeyResponseDto {
    private Integer code;
    private String message;
    private BillKeyData response;
}
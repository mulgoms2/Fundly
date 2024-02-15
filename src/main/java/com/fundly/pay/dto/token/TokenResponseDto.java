package com.fundly.pay.dto.token;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class TokenResponseDto {
    private Integer code;
    private String message;
    private TokenData response;
}
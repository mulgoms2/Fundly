package com.fundly.pay.dto.token;

import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class TokenResponseDto {
    private Integer code;
    private String message;
    private TokenData response;
}
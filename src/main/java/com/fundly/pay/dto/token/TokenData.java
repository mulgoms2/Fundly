package com.fundly.pay.dto.token;

import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class TokenData {
    private String access_token;
    private long now;
    private long expired_at;
}
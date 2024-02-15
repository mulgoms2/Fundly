package com.fundly.pay.dto.token;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
@Getter
@NoArgsConstructor
@ToString
public class TokenData {
    private String access_token;
    private Timestamp now;
    private Timestamp expired_at;
}
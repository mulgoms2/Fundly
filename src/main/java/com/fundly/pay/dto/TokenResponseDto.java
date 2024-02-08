package com.fundly.pay.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@ToString
public class TokenResponseDto {
    private Integer code;
//    private String message;
    private TokenData response;

    public String getAccess_token() {
        return response != null ? response.getAccess_token() : null;
    }

    // TODO: UTC time to Timestamp
    public Timestamp getNow() {
        return response != null ? response.getNow() : null;
    }

    public Timestamp getExpired_at() {
        return response != null ? response.getExpired_at() : null;
    }

}

@Getter
@NoArgsConstructor
@ToString
class TokenData {
    private String access_token;
    private Timestamp now;
    private Timestamp expired_at;
}

//"response": {
//        "access_token": "1a9761b86ca7bd579230bf99a90fb0a3e3deedc4",
//        "now": 1707295105,
//        "expired_at": 1707296905
//        }
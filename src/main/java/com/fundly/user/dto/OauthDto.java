package com.fundly.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
//@ToString
@Builder
public class OauthDto {
    private String snsId;
    private String user_email;
    private String refresh_token;
    private String access_token;
    private String user_name;
}
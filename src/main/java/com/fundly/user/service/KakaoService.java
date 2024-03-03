package com.fundly.user.service;

import java.io.IOException;
import java.util.Map;

public interface KakaoService {
    //getAccessToken
    String[] getToken(String code) throws IOException;

    // logout
    String logout(String access_token) throws IOException;

    //연결 끊기
    String unlink(String access_token) throws IOException;

    // 유효시간 확인
    Map<String, Object> getTokenTime(String access_token) throws Exception;

    Map<String, Object> getUserInfo(String access_token) throws IOException;

    Map<String, Object> getservice_terms(String access_token) throws IOException;
}

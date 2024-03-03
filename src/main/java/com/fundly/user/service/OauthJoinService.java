package com.fundly.user.service;

import com.fundly.user.dto.OauthDto;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface OauthJoinService {
    int check(OauthDto oauthDto) throws Exception;

    OauthDto OauthInfo(OauthDto oauthDto) throws Exception;

    //    @Transactional(rollbackFor = SQLException.class)
    @Transactional(rollbackFor = Exception.class)
    int userOauthJoin(OauthDto oauthDto, HttpSession session, HttpServletResponse response) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    int userOauthUpdate(OauthDto oauthDto, HttpSession session, HttpServletResponse response) throws Exception;

    // cookie key/value setting
    Cookie setCookie(String cookieKey, String cookieValue, int maxAge, String path);
}

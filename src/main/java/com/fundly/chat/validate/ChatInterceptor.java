package com.fundly.chat.validate;


import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChatInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("user_email") == null) {

            System.out.println("로그인 된 사용자만 채팅에 참여할 수 있습니다.");

            return true;
        }

        return true;
    }
}

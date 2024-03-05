package com.fundly.project.validate;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userEmail = (String) request.getSession().getAttribute("user_email");

        if (userEmail == null || userEmail.isEmpty()) {
//            로그인 안된 유저는 로그인창으로 이동시킨다.
            response.sendRedirect(request.getContextPath() + "/login/login");
            return false;
        }
        // 유저 이메일이 채팅방의 식별자와 같을때만 접속할 수 있다.

        return true;
    }
}

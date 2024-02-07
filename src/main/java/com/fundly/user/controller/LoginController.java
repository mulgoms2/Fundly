package com.fundly.user.controller;

import com.fundly.user.model.UserLoginDao;
import com.fundly.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    UserLoginDao userLoginDao;


    @Autowired
    LoginService loginService;


    /*
    * 1. 로그인 get , post
    * 2. 쿠키, 세션
    *
    * */
    @GetMapping("/login")
    public String login(){ return "user/login";}

    @PostMapping("/login")
    public String login(String user_email) {

        try {
             userLoginDao.emailCheck(user_email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "index";
    }
}

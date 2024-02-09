package com.fundly.user.controller;

import com.fundly.user.model.UserLoginDao;
import com.fundly.user.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
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
    public String login(String user_email,String user_pwd) {


        /*
        * 1. 쿠키 요청 있는지 확인
        * 2. 유저 데이터 정보 확인
        * 3. 에러가 있으면(아이디/비번 입력 안된경우) 리다이렉트객체에 추가 후 뷰 전달
        * 사용자 로그인 시도하면 db정보 조회
        *
        * */
        try {



//             userLoginDao.emailCheck(user_email);









        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "index";
    }
}

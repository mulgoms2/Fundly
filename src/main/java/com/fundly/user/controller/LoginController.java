package com.fundly.user.controller;

import com.fundly.user.model.UserLoginDao;
import com.fundly.user.service.LoginService;
import com.persistence.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /*
    * 1. 로그인 get , post
    * 2. 쿠키, 세션
    *
    * */

    @GetMapping("/login")
    public String login(){ return "user/login";}

    @PostMapping("/login")
    public String login(String user_email, String user_pwd, HttpSession session, RedirectAttributes rattr) throws Exception {

        /*
            1. 기존 로그인 정보가 있는가? 있다면 자동으로 로그인 된 값을 가져 와서 화면에 보여준다.
                - 추후 소셜로그인 정보와 함께 고민
            2. 로그인 된 정보가 없다면 로그인을 한다.
             2-1 가입된 정보를 확인 후 정보가 있으면 로그인
             2-2 가입된 정보가 없다면 id/pwd 확인 요청
        * */

        UserDto userInfo = loginService.Login(user_email, user_pwd, session);

//        log.error("\n\n\n\n\n\n\n\n email , pwd = " + user_email + " , " + user_pwd );
//        log.error("userInfo = " + userInfo);
//        log.error("user_email = " + session.getAttribute("user_email"));

        try {
            // 2-1
            if(userInfo != null){

            } else {
                // 2-2
                rattr.addFlashAttribute("msg", "Fundly에 등록되지 않은 이메일주소 또는 비밀번호가 일치 하지 않습니다. ");
                return "redirect:/user/login";
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "tiles.index";
    }

    @GetMapping("/forgetPwd")
    public String forgetPwd(){
        return "user/forgetpwd";
    }
}

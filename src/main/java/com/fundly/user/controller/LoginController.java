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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {
    private LoginService loginService;

    /*
    * 1. 로그인 get , post
    * 2. 쿠키, 세션
    *
    * */

    public LoginController(){}
    @Autowired
    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String login(){ return "user/login";}

    @PostMapping("/login")
    public String login(UserDto userDto, HttpSession session, RedirectAttributes rattr) throws Exception {
        /*
            1. 기존 로그인 정보가 있는가? 있다면 자동으로 로그인 된 값을 가져 와서 화면에 보여준다.
                - 추후 소셜로그인 정보와 함께 고민
            2. 로그인 된 정보가 없다면 로그인을 한다.
             2-1 가입된 정보를 확인 후 정보가 있으면 로그인
             2-2 가입된 정보가 없다면 id/pwd 확인 요청
        * */
        try {
            UserDto userInfo = loginService.Login(userDto, session);
//        log.error("\n\n\n\n\n\n\n\n email , pwd = " + user_email + " , " + user_pwd );
//        log.error("userInfo = " + userInfo);
//        log.error("user_email = " + session.getAttribute("user_email"));

            // 2-2
            if(userInfo == null){
                rattr.addFlashAttribute("msg", "Fundly에 등록되지 않은 이메일주소 또는 비밀번호가 일치 하지 않습니다. ");
                return "redirect:/login/login";
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "redirect:/";
    }

    @GetMapping("/forgetPwd")
    public String forgetPwd(){
        return "user/forgetpwd";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) throws Exception {
        session.invalidate();
        Cookie user_profile_img_url_cookie = new Cookie("user_profile_img_url", null);
        user_profile_img_url_cookie.setPath("/");
        user_profile_img_url_cookie.setMaxAge(0);
        response.addCookie(user_profile_img_url_cookie);
        return "redirect:/";
    }
}

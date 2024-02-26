package com.fundly.user.controller;

import com.fundly.user.dto.UserLoginDto;
import com.fundly.user.model.UserLoginDao;
import com.fundly.user.service.LoginService;
import com.persistence.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;

    /*
    * 1. 로그인 get , post
    * 2. 쿠키, 세션
    *
    * */

    @Autowired
    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("userLoginDto")UserLoginDto userLoginDto){ return "user/login";}

    @PostMapping("/login")
    public String login(@Valid UserLoginDto userLoginDto, BindingResult bindingResult, HttpSession session,
                        HttpServletResponse response, RedirectAttributes rattr) throws Exception {
        /*
            1. 기존 로그인 정보가 있는가? 있다면 자동으로 로그인 된 값을 가져 와서 화면에 보여준다.
                - 추후 소셜로그인 정보와 함께 고민
            2. 로그인 된 정보가 없다면 로그인을 한다.
             2-1 가입된 정보를 확인 후 정보가 있으면 로그인
             2-2 가입된 정보가 없다면 id/pwd 확인 요청
        * */


        /* userLoginDto valid */
        if(bindingResult.hasErrors()) {

            /*  error value*/
            Map<String, String> validResult = validateHandling(bindingResult);

            for (String key : validResult.keySet()) {
                rattr.addFlashAttribute(key, validResult.get(key));
            }

            log.info("\n\n\n" + userLoginDto +"\n\n\n");

            rattr.addFlashAttribute(userLoginDto);
            return "redirect:/login/login";
        }

        try {
            loginService.Login(userLoginDto, session, response);
//            String user_email

            // 2-2
//            if(userInfo == null){
//                rattr.addFlashAttribute("msg", "Fundly에 등록되지 않은 이메일주소 또는 비밀번호가 일치 하지 않습니다. ");
//                return "redirect:/login/login";
//            }

        } catch (Exception e) {
//            throw new RuntimeException(e);
            rattr.addFlashAttribute("errmsg", e.getMessage().split(" ",2)[1]);
            rattr.addFlashAttribute(userLoginDto);
            return "redirect:/login/login";
        }

        return "redirect:/";
    }

    @GetMapping("/forgetPwd")
    public String forgetPwd(){
        return "user/forgetpwd";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response, HttpServletRequest request, RedirectAttributes rattr) throws Exception {

        try {
            String user_email = (String)(session.getAttribute("user_email"));
            String access_token = getCookieValue(request,"kat");

            log.error(user_email);
            log.error(access_token);

            log.info("user_email = " + user_email);
            if(user_email!="") {
                session.invalidate();

                response.addCookie(setCookie("user_email","",0,"/"));
                response.addCookie(setCookie("user_name","",0,"/"));
            }
    //        profile img
//            response.addCookie(setCookie("user_profile_img_url","null",0,"/"));

            if(access_token!=null){

                response.addCookie(setCookie("kat","",0,"/"));
                return "redirect:/oauth/logout";
            }

//            rattr.addFlashAttribute("errmsg", "LOGOUT");
            response.getWriter().println("<script>alert('로그 아웃되었습니다.');</script>");
            return "redirect:/";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 쿠키 값
    public String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();

        // 쿠키 배열이 null이 아니고, 각 쿠키를 확인하여 원하는 쿠키의 값을 찾음
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }

    // 유효성 체크
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    // cookie key/value setting
    public Cookie setCookie(String cookieKey, String cookieValue, int maxAge, String path){
        Cookie cookie = new Cookie(cookieKey,cookieValue);
        cookie.setMaxAge(maxAge); // 쿠키를 삭제
        cookie.setPath(path);

        return cookie;
    }

    /* RuntimeException.class, SQLException.class,IllegalArgumentException.class에 따른 에러들 처리 */
    @ExceptionHandler({RuntimeException.class, SQLException.class,IllegalArgumentException.class})
    public String handleException()
    {
        return "user/error";
    }

}

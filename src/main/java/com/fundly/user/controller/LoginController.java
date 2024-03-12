package com.fundly.user.controller;

import com.fundly.user.dto.UserLoginDto;
import com.fundly.user.exception.UserJoinFailException;
import com.fundly.user.exception.UserLoinFailException;
import com.fundly.user.model.UserLoginDao;
import com.fundly.user.service.LoginService;
import com.fundly.user.service.UserProfileService;
import com.persistence.dto.FileDto;
import com.persistence.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    /*
     * 1. 로그인 get , post
     * 2. 쿠키, 세션
     * */

    private final LoginService loginService;
    private final UserProfileService userProfileService;

    @Autowired
    public LoginController(LoginService loginService,UserProfileService userProfileService){
        this.loginService = loginService;
        this.userProfileService = userProfileService;
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("userLoginDto")UserLoginDto userLoginDto){ return "user/login";}

    @PostMapping("/login")
    public String login(@Valid UserLoginDto userLoginDto, BindingResult bindingResult, HttpSession session,
                        HttpServletResponse response, RedirectAttributes rattr)   {
        /*
            1. 기존 로그인 정보가 있는가? 있다면 자동으로 로그인 된 값을 가져 와서 화면에 보여준다.
                - 추후 소셜로그인 정보와 함께 고민
            2. 로그인 된 정보가 없다면 로그인을 한다.
             2-1 가입된 정보를 확인 후 정보가 있으면 로그인
             2-2 가입된 정보가 없다면 id/pwd 확인 요청
        * */

        /* userLoginDto valid */
        if(bindingResult.hasErrors()) {
            Map<String, String> validResult = validateHandling(bindingResult);

            for (String key : validResult.keySet()) {
                rattr.addFlashAttribute(key, validResult.get(key));
            }

            rattr.addFlashAttribute(userLoginDto);
            return "redirect:/login/login";
        }

        try {

            loginService.Login(userLoginDto, session, response);
            String profileImg = userProfileService.getUserProfileImg(FileDto.builder().table_name("user_info").table_key(userLoginDto.getUser_email()).build());

            if(profileImg !=null){
                /* cookie add , profile img split... */

                log.error("프로필이미지 값은 " + profileImg);
                response.addCookie(setCookie("user_profileImg",profileImg.split("/")[5],-1,"/"));
            }

        } catch (Exception e) {
            rattr.addFlashAttribute("errmsg", e.getMessage());
            rattr.addFlashAttribute(userLoginDto);
            return "redirect:/login/login";
        }

        return "redirect:/";
    }

    @GetMapping("/forgetPwd") //TODO : 비밀번호 찾기 할것인가 ?
    public String forgetPwd(){
        return "user/forgetpwd";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response, HttpServletRequest request) {

        try {
            String user_email = (String)(session.getAttribute("user_email"));
            String access_token = getCookieValue(request,"kat");

            if(!Objects.equals(user_email, "")) {
                response.addCookie(setCookie("user_email","",0,"/"));
                response.addCookie(setCookie("user_name","",0,"/"));
                response.addCookie(setCookie("user_profileImg","",0,"/"));

                session.invalidate();
            }

            if(access_token!=null){
                return "redirect:/oauth/logout";
            }

//            response.getWriter().println("<script>alert('로그 아웃되었습니다.');</script>");
            return "redirect:/";
        } catch (Exception e) {
            log.error("Exception error : " + e.getMessage());
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
                    if(cookie.getName().equals(cookieName) && cookieName.equals("user_profileImg"))
                    {
                        try {
                            return URLDecoder.decode(cookie.getValue(),"UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            throw new RuntimeException(e);
                        }
                    }else{
                        return cookie.getValue();
                    }
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
        String encodedValue = null;
        try { encodedValue = URLEncoder.encode(cookieValue, "UTF-8"); }
        catch (UnsupportedEncodingException e) { throw new RuntimeException(e); }
        Cookie cookie = new Cookie(cookieKey,encodedValue);
        cookie.setMaxAge(maxAge); // 쿠키를 삭제
        cookie.setPath(path);

        return cookie;
    }

    /* Exception */
    @ExceptionHandler({UserLoinFailException.class})
    public String UserLoinFailException(Model model, UserLoinFailException e){
        log.error("UserLoinFailException {\n " + e.getMessage() +" }\n ");
        model.addAttribute("errorMsg",e.getMessage());
        return "user/error";
    }

    @ExceptionHandler({RuntimeException.class})
    public String RuntimeException(Model model,RuntimeException e) {
        log.error("RuntimeException {\n " + e.getMessage() +" }\n ");
        model.addAttribute("errorMsg","잘못된 접근입니다. 관리자에게 문의해주세요.");
        return "user/error";
    }
    @ExceptionHandler({Exception.class})
    public String Exception(Model model, Exception e) {
        log.error("Exception {\n " + e.getMessage() +" }\n ");
        model.addAttribute("errorMsg","잘못된 접근입니다. 관리자에게 문의해주세요.");
        return "user/error";
    }
}

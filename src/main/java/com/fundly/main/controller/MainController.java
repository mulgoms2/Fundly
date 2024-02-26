package com.fundly.main.controller;

import com.fundly.user.dto.UserLoginDto;
import com.fundly.user.service.UserInfoService;
import com.persistence.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class MainController {

    @Autowired
    private UserInfoService userInfoService;


    @GetMapping({"/"})
    public String main(HttpSession session, Model model, HttpServletRequest request) {

        String user_email = getCookieValue(request,"user_email");
        String sessionemail = (String)(session.getAttribute("user_email"));

//        log.info("user_email = " + user_email);
//        log.info("sessionemail = " + sessionemail);

        if(sessionemail== null && user_email != null){
            session.setAttribute("user_email",user_email);
            // 세션의 유효시간 (30분)
            session.setMaxInactiveInterval(1800);
        }

        UserDto dto = UserDto.builder().user_email(user_email).build();
        UserDto userInfo = userInfoService.userInfo(dto);
        model.addAttribute("userInfo",userInfo);
        return "main.index";
    }

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
}

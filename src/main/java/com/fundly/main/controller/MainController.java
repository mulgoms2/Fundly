package com.fundly.main.controller;

import com.fundly.project.service.ProjectService;
import com.fundly.project.service.ProjectServiceImpl;
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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Slf4j
@Controller
public class MainController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private ProjectService projectService;
    @GetMapping({"/"})
    public String main(HttpSession session, Model model, HttpServletRequest request) {

        String user_email = getCookieValue(request,"user_email");
        String sessionemail = (String)(session.getAttribute("user_email"));
        String user_profileImg = getCookieValue(request,"user_profileImg");

        if(sessionemail== null && user_email != null){
            session.setAttribute("user_email",user_email);
            // 세션의 유효시간 (30분)
            session.setMaxInactiveInterval(1800);
        }

        UserDto userInfo = userInfoService.userInfo(UserDto.builder().user_email(user_email).build());
        model.addAttribute("userInfo",userInfo);
        model.addAttribute("user_profileImg",user_profileImg);

        return "main.index";
    }


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
}

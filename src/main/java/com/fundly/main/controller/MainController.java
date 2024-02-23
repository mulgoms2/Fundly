package com.fundly.main.controller;

import com.fundly.user.model.OauthDao;
import com.fundly.user.model.UserLoginDao;
import com.fundly.user.service.LoginService;
import com.fundly.user.service.UserInfoService;
import com.persistence.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class MainController {

    @Autowired
    private UserInfoService userInfoService;


    @GetMapping({"/"})
    public String main(HttpSession session, Model model, HttpServletResponse response) {

        String user_email = (String)(session.getAttribute("user_email"));// "helloworld@abc.com";
        String kat = (String)(session.getAttribute("kat"));// "helloworld@abc.com";
        UserDto dto;
        if(user_email == null){
            return "main.index";
        }

        if(kat != ""){




            dto = UserDto.builder().user_email(user_email).build();

        }else{
            dto = UserDto.builder().user_email(user_email).build();
        }

        UserDto userInfo = userInfoService.userInfo(dto);
        model.addAttribute("userInfo",userInfo);

        return "main.index";
    }
}

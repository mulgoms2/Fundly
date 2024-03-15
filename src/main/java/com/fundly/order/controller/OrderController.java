package com.fundly.order.controller;

import com.fundly.user.model.UserInfoDao;
import com.fundly.user.service.LikeService;
import com.fundly.user.service.UserInfoService;
import com.persistence.dto.LikeDto;
import com.persistence.dto.ProjectDto;
import com.persistence.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
public class OrderController {

    @GetMapping("/order")
    public String order(Model model, HttpSession session) {

        //로그인 안되어 있으면 로그인 페이지
        String user_email = (String)(session.getAttribute("user_email"));

        if(user_email == null || user_email.isEmpty()){
//            model.addAttribute("errmsg","USER_ERROR");
            return "user/login";
        }

        return "order/order";

    }
}
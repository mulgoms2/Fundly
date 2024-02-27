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
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
public class OrderController {

    @GetMapping("/order")
    public String order() {
        return "order/order";
    }
}
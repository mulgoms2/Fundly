package com.fundly.admin.controller;

import com.fundly.admin.service.AdminService;
import com.persistence.dto.AdminDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/adminLogin")
public class AdminLoginController {
    @Autowired
    AdminService adminService;

    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(AdminDto dto, HttpSession session){

        try {
            String loginInfo = adminService.loginUser(dto);
            if(loginInfo!=null){
                session.setAttribute("admin_id", dto.getAdmin_id());
                session.setAttribute("admin_name",dto.getAdmin_name());
                session.setMaxInactiveInterval(60*30);
            }
        }catch (Exception e){ throw new RuntimeException(e);}

        return "redirect:/admin/list";
    }

}

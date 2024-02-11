package com.fundly.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/mypage")
public class MyPageController {

    @GetMapping("/mypage")
    public String mypagemain(){
        return "user/profile";
    }
}

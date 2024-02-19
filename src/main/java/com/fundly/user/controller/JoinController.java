package com.fundly.user.controller;

import com.fundly.user.service.JoinService;
import com.fundly.user.service.MailSendService;
import com.persistence.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Slf4j
@Controller
@RequestMapping("/join")
public class JoinController {
    private JoinService joinService;
//    private MailSendService mailSendService;

    public JoinController(){}
    @Autowired
    public JoinController(JoinService joinService){
        this.joinService = joinService;
    }

    @GetMapping("/add")
    public String join(){ return "user/join";}

    @PostMapping("/add")
    public String joinsave( UserDto userDto) throws Exception {

        try {

            joinService.userJoin(userDto);

        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
//        회원가입 후 로그인 화면으로 갈것인가 ? 메인으로 갈것인가 ?
        return "user/login";
    }

//    // 메일 인증
//    @PostMapping("/mailCheck")
//    public String mailCheck(String email) throws Exception {
//        try {
//            mailSendService.joinEmail(email);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        return "/add/add";
//    }
}

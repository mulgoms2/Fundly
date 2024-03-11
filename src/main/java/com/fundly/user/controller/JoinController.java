package com.fundly.user.controller;

import com.fundly.user.dto.UserJoinDto;
import com.fundly.user.exception.UserJoinFailException;
import com.fundly.user.service.JoinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/join")
public class JoinController {

    /* 회원가입
     *  회원가입버튼클릭 - 회원가입폼 - 값 입력 - 버튼 클릭
     *  error : valid로 값 비교한 후에 맞지 않으면 error msg  */

    private final JoinService joinService;

//    private MailSendService mailSendService;

    @Autowired
    public JoinController(JoinService joinService){
        this.joinService = joinService;
    }

    @GetMapping("/add")
    public String join(@ModelAttribute("userJoinDto")UserJoinDto userJoinDto){ return "user/join";}

    @PostMapping( "/add")
    public String joinsave(@Validated UserJoinDto userJoinDto, BindingResult bindingResult, RedirectAttributes rattr) {

        /* userjoindto valid */
        if(bindingResult.hasErrors()) {
            Map<String, String> validResult = validateHandling(bindingResult);

            for (String key : validResult.keySet()) {
                rattr.addFlashAttribute(key, validResult.get(key));
            }

            rattr.addFlashAttribute(userJoinDto);
            return "redirect:/join/add";
        }

        try {
            joinService.userJoin(userJoinDto);
        } catch (Exception e) {
            rattr.addFlashAttribute("errmsg", e.getMessage().split(" ",2)[1]);
            rattr.addFlashAttribute(userJoinDto);
            return "redirect:/join/add";
        }
        return "/user/login";
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

    /* Exception */
    @ExceptionHandler({UserJoinFailException.class})
    public String UserJoinFailException(Model model, UserJoinFailException e){
        log.error("UserJoinFailException {\n " + e.getMessage() +" }\n ");
        model.addAttribute("errorMsg",e.getMessage());
        return "user/error";
    }
    @ExceptionHandler({SQLException.class})
    public String SQLException(Model model, SQLException e) {
        log.error("SQLException {\n " + e.getMessage() +" }\n ");
        model.addAttribute("errorMsg","서버 오류입니다. 관리자에게 문의해주세요.");
        return "user/error";
    }
    @ExceptionHandler({RuntimeException.class})
    public String handleException(Model model,RuntimeException e) {
        log.error("RuntimeException {\n " + e.getMessage() +" }\n ");
        model.addAttribute("errorMsg","잘못된 접근입니다. 관리자에게 문의해주세요.");
        return "user/error";
    }
    @ExceptionHandler({Exception.class})
    public String handleException(Model model, Exception e) {
        log.error("Exception {\n " + e.getMessage() +" }\n ");
        model.addAttribute("errorMsg","잘못된 접근입니다. 관리자에게 문의해주세요.");
        return "user/error";
    }

//    // 메일 인증
//    @PostMapping("/mailCheck")
//    public String mailCheck(String email) throws Exception {
//        try {
//            mailSendService.joinEmail(email);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return "/add/add";
//    }
}

package com.fundly.user.controller;

import com.fundly.user.dto.UserJoinDto;
import com.fundly.user.service.JoinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
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

    @PostMapping("/add")
    public String joinsave(@Valid @RequestBody UserJoinDto userJoinDto, BindingResult bindingResult, RedirectAttributes rattr) {

        /*
        binding으로 value 값 확인
        Exception을 이용한
        * */

        /* userjoindto valid */
        if(bindingResult.hasErrors()) {
//            throw new RuntimeException("BingindResult Error : 관리자에게 문의해주세요.\n\n\n"+ bindingResult);

            /*  error value*/
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
            // 이미 가입되어 있을 때 기존 값을 보여줄것인가 ? 그냥 초기화 할것인가 ? : 초기화
//            rattr.addFlashAttribute(userJoinDto);
            return "redirect:/join/add";

            //*예외 처리하기(어디로 갈것인가), 디스패처서블릿 인지 확인하기 ,핸들러 어댑터,
            //개발 순서(우선순위) 개발->화면
            //서버에서도 각 dto에서 , valid 에 따른 입력값 재 확인 요구(화면 유지하면서 해당 vaild 체크 하기)
            // 화면간의 이동에 따른 주석
        }

//        회원가입 후 로그인 화면으로 갈것인가 ? 메인으로 갈것인가 ? :  로그인 화면
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

    /* RuntimeException.class, SQLException.class,IllegalArgumentException.class에 따른 에러들 처리 */
    @ExceptionHandler({RuntimeException.class, SQLException.class,IllegalArgumentException.class})
    public String handleException()
    {
        return "user/error";
    }

//    public String Exception(){
//    }

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

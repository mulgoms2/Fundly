package com.fundly.user.controller;

import com.fundly.project.controller.ErrorResult;
import com.fundly.user.dto.UserJoinDto;
import com.fundly.user.dto.UserProfileDto;
import com.fundly.user.service.JoinService;
import com.fundly.user.service.UserHistService;
import com.fundly.user.service.UserInfoService;
import com.fundly.user.service.UserProfileService;
import com.persistence.dto.ItemDto;
import com.persistence.dto.PayMeansDto;
import com.persistence.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserProfileController {

    private final UserInfoService userInfoService;

    private final UserProfileService userProfileService;

    private final UserHistService userHistService;

    MessageSource messageSource;
    @Autowired
    public UserProfileController(UserInfoService userInfoService,UserProfileService userProfileService,
                                 UserHistService userHistService){
        this.userInfoService = userInfoService;
        this.userProfileService = userProfileService;
        this.userHistService = userHistService;
    }

    /* 설정 - 프로필 */
    @GetMapping("/profileBasic")
    public String settingProfile(HttpSession session, Model model){
//        String user_email = (String)session

        String user_email = (String)(session.getAttribute("user_email"));

        log.info("user_email = " + user_email + "\n\n");

        UserDto dto = UserDto.builder().user_email(user_email).build();
        UserDto userInfo = null;
        try {
            userInfo = userInfoService.userInfo(dto);

            log.info("userInfo = " + userInfo + "\n\n");

        } catch (Exception e) {

            log.info(e.getMessage());
        }

        model.addAttribute("userInfo",userInfo);
        return "user/settingProfile";
    }

    /* 설정 - 계정 */
    @GetMapping("/account")
    public String settingAccount(HttpSession session, Model model){

        String user_email = (String)(session.getAttribute("user_email"));

        log.info("user_email = " + user_email + "\n\n");

        UserDto dto = UserDto.builder().user_email(user_email).build();
        UserDto userInfo = null;
        try {
            userInfo = userInfoService.userInfo(dto);

            log.info("userInfo = " + userInfo + "\n\n");

        } catch (Exception e) {

            log.info(e.getMessage());
        }

        model.addAttribute("userInfo",userInfo);
        return "user/settingAccount";
    }

    @PostMapping("/update")
    @ResponseBody
    @Transactional(rollbackFor = SQLException.class)
    public String nameSave(@RequestBody UserDto userDto) {

        int cnt = 0;

        try {
            /* userdto 가 정상 적으로 값이 오면 ?
             * 그 이후 try , catch 방식은 ?
             * */
            log.info("어디서부터");
            UserProfileDto  userProfileInfo = userProfileService.userProfileInfo(userDto);
            log.info("안되는건가");
            if(userDto.getUser_name()!=null) userProfileInfo.setUser_name(userDto.getUser_name());
            if(userDto.getUser_intro()!=null) userProfileInfo.setUser_intro(userDto.getUser_intro());

            log.info("userProfileInfo ===== " + userProfileInfo + "\n\n");

            UserProfileDto userProfileDto = userProfileService.userUpdate(userProfileInfo);

            cnt = userHistService.userHistinsert(userProfileDto);


            if (cnt == 1) {
                log.info("성공");
                return "성공";
            }else{
                log.info("실패");
                return "실패";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* RuntimeException.class, SQLException.class,IllegalArgumentException.class에 따른 에러들 처리 */
    @ExceptionHandler({RuntimeException.class, SQLException.class,IllegalArgumentException.class,Exception.class})
    public String handleException()
    {
        return "user/error";
    }
}

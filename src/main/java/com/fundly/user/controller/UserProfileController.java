package com.fundly.user.controller;

import com.fundly.chat.service.ChatService;
import com.fundly.project.controller.ErrorResult;
import com.fundly.user.dto.UserJoinDto;
import com.fundly.user.dto.UserProfileDto;
import com.fundly.user.service.JoinService;
import com.fundly.user.service.UserHistService;
import com.fundly.user.service.UserInfoService;
import com.fundly.user.service.UserProfileService;
import com.persistence.dto.FileDto;
import com.persistence.dto.ItemDto;
import com.persistence.dto.PayMeansDto;
import com.persistence.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.io.IOUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserProfileController {
    String IMG_SAVE_LOCATION = "/Users/USER/fundly/img/";

    private final UserInfoService userInfoService;
    private final UserProfileService userProfileService;
    private final UserHistService userHistService;

    @Autowired
    public UserProfileController(UserInfoService userInfoService,UserProfileService userProfileService,
                                 UserHistService userHistService){
        this.userInfoService = userInfoService;
        this.userProfileService = userProfileService;
        this.userHistService = userHistService;
    }

    /* 설정 - 프로필 */
    @GetMapping("/profileBasic")
    public String settingProfile(HttpSession session, Model model,HttpServletRequest request,HttpServletResponse response){
//        String user_email = (String)session

        String user_email = (String)(session.getAttribute("user_email"));
        String user_profileImg = getCookieValue(request,"user_profileImg");

        log.info("user_email = " + user_email + "\n\n");
        log.info("user_profileImg = " + user_profileImg + "\n\n");

        UserDto userInfo = null;
        try {
            userInfo = userInfoService.userInfo(UserDto.builder().user_email(user_email).build());

            log.info("userInfo = " + userInfo + "\n\n");

        } catch (Exception e) {

            log.info(e.getMessage());
        }

        model.addAttribute("userInfo",userInfo);
        model.addAttribute("user_profileImg",user_profileImg);
        log.error(" 이미지 주소 값은 ? : "+user_profileImg);
        return "user/settingProfile";
    }

    /* 설정 - 계정 */
    @GetMapping("/account")
    public String settingAccount(HttpSession session, Model model){

        String user_email = (String)(session.getAttribute("user_email"));

        UserDto userInfo = null;
        try {
            userInfo = userInfoService.userInfo(UserDto.builder().user_email(user_email).build());
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        model.addAttribute("userInfo",userInfo);
        return "user/settingAccount";
    }

    @PostMapping("/imgupdate")
    @ResponseBody
    public ResponseEntity<?>  imgFile(@RequestParam("file") MultipartFile file, @SessionAttribute String user_email, HttpServletResponse response){

        String originFileName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String saveImgUrl = IMG_SAVE_LOCATION + uuid + originFileName;
        String imgname = uuid + originFileName;
        MultipartFile imgFile = file;

        try{
            imgFile.transferTo(new File(saveImgUrl));
            FileDto uploadFile = FileDto.builder().file(file).file_saved_url(saveImgUrl).file_origin_url(originFileName).build();
            userProfileService.saveImg(uploadFile,user_email,response);

            /* cookie add */
            response.addCookie(setCookie("user_profileImg",imgname,-1,"/"));

            return new ResponseEntity<>(new String[]{"user_profileImg",imgname}, HttpStatus.OK);
        } catch (IOException e){
//            log.error("errrrrrrrrrrrrrrrrrrrorrrrrrrrrrrrrrr");
            throw new RuntimeException(e);
        }
    }

    public Cookie setCookie(String cookieKey, String cookieValue, int maxAge, String path){
        log.info("coo = " + cookieKey + ", value = " + cookieValue + ", maxage = " + maxAge + ", path = " + path);
        Cookie cookie = new Cookie(cookieKey,cookieValue);
        cookie.setMaxAge(maxAge); // 쿠키를 삭제
        cookie.setPath(path);

        return cookie;
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<?> updateSave(@RequestBody @Valid UserDto userDto, BindingResult result) {

        // dto binding error
        result.getAllErrors().stream().forEach(
                error -> Arrays.stream(error.getCodes()).forEach(System.out::println)
        );

        int cnt = 0;

        try {
            /* userdto 가 정상 적으로 값이 오면 ?
             * 그 이후 try , catch 방식은 ?
             * 1. user 기본정보
             * 2. user 프로필 사진
             * */

            UserProfileDto userProfileInfo = userProfileService.userProfileInfo(userDto);
            if(userDto.getUser_name()!=null) userProfileInfo.setUser_name(userDto.getUser_name());
            if(userDto.getUser_intro()!=null) userProfileInfo.setUser_intro(userDto.getUser_intro());

//            log.info("userProfileInfo ===== " + userProfileInfo + "\n\n");

            UserProfileDto userProfileDto = userProfileService.userUpdate(userProfileInfo);
            cnt = userHistService.userHistinsert(userProfileDto);

//            log.info("cnt = " + cnt);

            if (cnt != 1) {
                throw new Exception("update userinfo failed");
            }

            UserDto userList = userInfoService.userInfo(userDto);
            return new ResponseEntity<UserDto>(userList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("ddd");
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();

        // 쿠키 배열이 null이 아니고, 각 쿠키를 확인하여 원하는 쿠키의 값을 찾음
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /* RuntimeException.class, SQLException.class,IllegalArgumentException.class에 따른 에러들 처리 */
    @ExceptionHandler({RuntimeException.class, SQLException.class,IllegalArgumentException.class,Exception.class})
    public String handleException()
    {
        return "user/error";
    }
}

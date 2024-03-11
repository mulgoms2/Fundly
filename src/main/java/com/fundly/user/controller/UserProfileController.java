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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserProfileController {
    String IMG_SAVE_LOCATION = "/Users/bada/desktop/나JAVA봐라/";
//    String IMG_SAVE_LOCATION = "/Users/USER/fundly/img/";
//    String IMG_SAVE_LOCATION = "/fundly/img/";
//    String IMG_SAVE_LOCATION = "/static/";

    private final UserInfoService userInfoService;
    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserInfoService userInfoService,UserProfileService userProfileService){
        this.userInfoService = userInfoService;
        this.userProfileService = userProfileService;
    }

    /* 설정 - 프로필 */
    @GetMapping("/profileBasic")
    public String settingProfile(HttpSession session, Model model,HttpServletRequest request,HttpServletResponse response){
//        String user_email = (String)session

        String user_email = (String)(session.getAttribute("user_email"));
        String user_profileImg = getCookieValue(request,"user_profileImg");

//        log.info("user_email = " + user_email + "\n\n");
//        log.info("user_profileImg = " + user_profileImg + "\n\n");

        UserDto userInfo = null;
        try {
            userInfo = userInfoService.userInfo(UserDto.builder().user_email(user_email).build());

            log.info("userInfo = " + userInfo + "\n\n");

        } catch (Exception e) {

            log.info(e.getMessage());
        }

        model.addAttribute("userInfo",userInfo);
        model.addAttribute("user_profileImg",user_profileImg);

        log.error("유저 정보는 ? " + userInfo);

        log.error(" 이미지 주소 값은 ? : "+user_profileImg);
        return "user/settingProfile";
    }

    /* 설정 - 계정 */
    @GetMapping("/account")
    public String settingAccount(HttpSession session, Model model){

        String user_email = (String)(session.getAttribute("user_email"));
        String lastPwdDate = "";
        UserDto userInfo = null;

        try {
            userInfo = userInfoService.userInfo(UserDto.builder().user_email(user_email).build());
            lastPwdDate = userProfileService.getlastPwdDate(user_email);
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        model.addAttribute("userInfo",userInfo);
        model.addAttribute("lastPwdDate",lastPwdDate);
        return "user/settingAccount";
    }

    @PostMapping("/imgupdate")
    @ResponseBody
    public ResponseEntity<?> imgFile(@RequestParam("file") MultipartFile file, @SessionAttribute String user_email, HttpServletResponse response){

        String originFileName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String saveImgUrl = IMG_SAVE_LOCATION + uuid + originFileName;
        String imgname = uuid + originFileName;
        MultipartFile imgFile = file;

        System.out.println("saveImgUrl = " + saveImgUrl);
//        Path root = Paths.get(System.getProperty("user.home"),"upload");
//        Path target = root.resolve(saveImgUrl);
//        Files.copy(inputStream,target);
//        String projectPath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/static/img";

        try{
//            imgFile.transferTo(new File(projectPath,saveImgUrl));
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

    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<?> updateSave(@RequestBody @Valid UserDto userDto, BindingResult result) {

//        // dto binding error msg view
//        result.getAllErrors().stream().forEach(
//                error -> Arrays.stream(error.getCodes()).forEach(System.out::println)
//        );

        int cnt = 0;

        try {
            /* userdto 가 정상 적으로 값이 오면 ?
             * 그 이후 try , catch 방식은 ?
             * 1. user 기본정보
             * 2. user 프로필 사진
             * */

            UserProfileDto userProfileDto = userProfileService.userUpdate(userDto);
//            log.info("cnt = " + cnt);
            UserDto userList = userInfoService.userInfo(UserDto.builder().user_email(userProfileDto.getUser_email()).build());
            return new ResponseEntity<UserDto>(userList, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Cookie setCookie(String cookieKey, String cookieValue, int maxAge, String path){
        log.info("coo = " + cookieKey + ", value = " + cookieValue + ", maxage = " + maxAge + ", path = " + path);
        Cookie cookie = new Cookie(cookieKey,cookieValue);
        cookie.setMaxAge(maxAge); // 쿠키를 삭제
        cookie.setPath(path);

        return cookie;
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

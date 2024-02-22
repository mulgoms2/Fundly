package com.fundly.user.controller;

import com.fundly.user.service.LikeService;
import com.fundly.user.service.UserInfoService;
import com.persistence.dto.LikeDto;
import com.persistence.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/mypage")
public class MyPageController {

    /* final : 멤버변수로 사용했고, 변수 초기화 후 다른 객체로 변경 할수 없다.
     코드의 안정성을 높이고 객체의 불변성을 유지하기 위한 처리*/
    private final UserInfoService userInfoService;
    private final LikeService likeservice;

    @Autowired
    public MyPageController(UserInfoService userInfoService, LikeService likeservice) {
        this.userInfoService = userInfoService;
        this.likeservice = likeservice;
    }

    /* 프로필 */
    @GetMapping("/profile")
    public String mypageprofile(HttpSession session, Model model) {
        return selViewPage("main.index","user.profile",session,model);
    }

    /* 응원권 */
    @GetMapping("/coupon")
    public String mypagecoupon(HttpSession session, Model model) {
        return selViewPage("main.index","user.coupon",session,model);
    }

    /* 후원한 프로젝트 */
    @GetMapping("/fundingProject")
    public String mypageOrder(HttpSession session,Model model){
        return selViewPage("main.index","user.fundingProject",session,model);
    }

    /* 관심 프로젝트 */
    @GetMapping("/likes")
    public String mypageLikes(HttpSession session,Model model){
        return selViewPage("main.index","user.likes",session,model);
    }

    /* 알림 */
    @GetMapping("/alarm")
    public String mypageAlarm(HttpSession session,Model model){
        return selViewPage("main.index","user.alarm",session,model);
    }

    /* 메시지 */
    @GetMapping("/message")
    public String mypageMessage(HttpSession session,Model model){
        return selViewPage("main.index","user.message",session,model);
    }

    /* 내가 만든 프로젝트 */
    @GetMapping("/makeProject")
    public String mypageMakeProject(HttpSession session,Model model){
        return selViewPage("main.index","user.makeProject",session,model);
    }

    /* 셋팅 */
    @GetMapping("/setting")
    public String mypageSetting(HttpSession session,Model model){
        return selViewPage("main.index","user.setting",session,model);
    }

    public String selViewPage(String mainView, String moveView, HttpSession session, Model model){

        try {
            String user_email = (String)(session.getAttribute("user_email"));// "helloworld@abc.com";

            if(user_email == null || user_email == ""){
//                throw new RuntimeException("USER_ERROR");
//                log.error("로그인되지 않았습니다");
                model.addAttribute("errmsg","USER_ERROR");
                /* session email 없다면 메인 화면으로 가면서 로그인 정보 확인 요청 ..
                * url 부분은 어떻게 가리는가 ? */
                return mainView;
            }

            LikeDto likedto = new LikeDto(user_email,"");
            List<LikeDto> likes = likeservice.getLikeList(likedto);

            UserDto dto = UserDto.builder().user_email(user_email).build();

            UserDto userInfo = userInfoService.userInfo(dto);

            model.addAttribute("userInfo",userInfo);
            model.addAttribute("likes", likes);

        } catch (Exception e) {

            /* exception 가능 ? */
//            throw new RuntimeException(e);

        }
        return moveView;
    }

    /* RuntimeException.class, SQLException.class,IllegalArgumentException.class에 따른 에러들 처리 */
    @ExceptionHandler({RuntimeException.class, SQLException.class,IllegalArgumentException.class})
    public String handleException()
    {
        return "user/error";
    }
}

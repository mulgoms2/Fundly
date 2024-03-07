package com.fundly.user.controller;

import com.fundly.user.dto.LikeProjectDto;
import com.fundly.user.service.LikeService;
import com.fundly.user.service.UserInfoService;
import com.persistence.dto.LikeDto;
import com.persistence.dto.ProjectDto;
import com.persistence.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
    public String mypageprofile(HttpSession session, HttpServletRequest request, Model model) {
        return selViewPage("main.index","user.profile", session, request, model);
    }

    /* 응원권 */
    @GetMapping("/coupon")
    public String mypagecoupon(HttpSession session, HttpServletRequest request, Model model) {
        return selViewPage("main.index","user.coupon", session, request, model);
    }

    /* 후원한 프로젝트 */
    @GetMapping("/fundingProject")
    public String mypageOrder(HttpSession session, HttpServletRequest request,Model model){
        return selViewPage("main.index","user.fundingProject", session, request, model);
    }

    /* 관심 프로젝트 */
    @GetMapping("/likes")
    public String mypageLikes(HttpSession session, HttpServletRequest request,Model model){
        return selViewPage("main.index","user.likes", session, request, model);
    }

    /* 알림 */
    @GetMapping("/alarm")
    public String mypageAlarm(HttpSession session, HttpServletRequest request,Model model){
        return selViewPage("main.index","user.alarm", session, request, model);
    }

    /* 메시지 */
    @GetMapping("/message")
    public String mypageMessage(HttpSession session, HttpServletRequest request, Model model){
        return selViewPage("main.index","user.message", session, request, model);
    }

    /* 내가 만든 프로젝트 */
    @GetMapping("/makeProject")
    public String mypageMakeProject(HttpSession session, HttpServletRequest request, Model model){
        return selViewPage("main.index","user.makeProject", session, request, model);
    }

    /* 셋팅 */
    @GetMapping("/setting")
    public String mypageSetting(HttpSession session, HttpServletRequest request, Model model){
        return selViewPage("main.index","user.setting", session, request, model);
    }

    public String selViewPage(String mainView, String moveView, HttpSession session, HttpServletRequest request, Model model){

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

            UserDto dto = UserDto.builder().user_email(user_email).user_id(user_email).build();
            UserDto userInfo = userInfoService.userInfo(dto);
            String userjoindateView = userInfoService.userJoindate(dto);
            String user_profileImg = getCookieValue(request,"user_profileImg");

            model.addAttribute("userInfo",userInfo);
            model.addAttribute("userjoindateView",userjoindateView);
            model.addAttribute("user_profileImg",user_profileImg);

            // 유저 아이디 통해 좋아요 목록 불러오기 (디폴트 : 진행중인 프로젝트)
            List<LikeProjectDto> likes = likeservice.getLikeList(userInfo.getUser_id());
            model.addAttribute("likes", likes);
            log.error("\n\n\n" + likes);

        } catch (Exception e) {

            log.error(""+ e.getStackTrace());
            /* exception 가능 ? */
//            throw new RuntimeException(e);

        }
        return moveView;
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
    @ExceptionHandler({RuntimeException.class, SQLException.class,IllegalArgumentException.class})
    public String handleException()
    {
        return "user/error";
    }
}

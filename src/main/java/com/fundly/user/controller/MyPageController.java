package com.fundly.user.controller;

import com.fundly.user.service.LikeService;
import com.fundly.user.service.UserInfoService;
import com.persistence.dto.LikeDto;
import com.persistence.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller

@RequestMapping("/mypage")
public class MyPageController {

    private UserInfoService userInfoService;
    private LikeService likeservice;

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

        log.error("\n\n" + selViewPage("tiles.index","user.fundingProject",session,model) + "\n\n");

        return selViewPage("main.index","user.fundingProject",session,model);
    }

    /* 관심 프로젝트 */
    @GetMapping("/likes")
    public String mypageLikes(HttpSession session,Model model){

        log.error("\n\n" + selViewPage("tiles.index","user.likes",session,model) + "\n\n");

        return selViewPage("main.index","user.likes",session,model);
    }

    /* 알림 */
    @GetMapping("/alarm")
    public String mypageAlarm(HttpSession session,Model model){

        log.error("\n\n" + selViewPage("tiles.index","user.alarm",session,model) + "\n\n");

        return selViewPage("main.index","user.alarm",session,model);
    }

    /* 메시지 */
    @GetMapping("/message")
    public String mypageMessage(HttpSession session,Model model){

        log.error("\n\n" + selViewPage("tiles.index","user.message",session,model) + "\n\n");

        return selViewPage("main.index","user.message",session,model);
    }

    /* 내가 만든 프로젝트 */
    @GetMapping("/makeProject")
    public String mypageMakeProject(HttpSession session,Model model){

        log.error("\n\n" + selViewPage("tiles.index","user.makeProject",session,model) + "\n\n");

        return selViewPage("main.index","user.makeProject",session,model);
    }

    /* 셋팅 */
    @GetMapping("/setting")
    public String mypageSetting(HttpSession session,Model model){

        log.error("\n\n" + selViewPage("main.index","user.setting",session,model) + "\n\n");

        return selViewPage("main.index","user.setting",session,model);
    }

    public String selViewPage(String mainView, String moveView, HttpSession session, Model model){

        try {
            String user_email = (String)(session.getAttribute("user_email"));// "helloworld@abc.com";

            log.error("\n\n user_email = " + user_email + "\n\n");

            if(user_email == null){
                return mainView;
            }

            LikeDto likedto = new LikeDto("bada@gamil.com","");
            List<LikeDto> likes = likeservice.getLikeList(likedto);

//            UserDto userInfo = userInfoService.userInfo(user_email);
//
//            String user_name = userInfo.getUser_name();
//
//            model.addAttribute("userInfo",userInfo);
//            model.addAttribute("user_name",user_name);
//            model.addAttribute("user_email",user_email);

            model.addAttribute("likes", likes);

        } catch (Exception e) {

            throw new RuntimeException(e);

        }
        return moveView;
    }
}

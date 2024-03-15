package com.fundly.product.detail.controller;

import com.fundly.project.controller.GiftForm;
import com.fundly.project.service.GiftService;
import com.fundly.project.service.ProjectService;
import com.fundly.user.service.UserInfoService;
import com.persistence.dto.ProjectDto;
import com.persistence.dto.UserDto;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Controller
public class detailController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserInfoService userinfoService;
    @Autowired
    private GiftService giftService;


    @GetMapping("/detail")
    public String detail() {
        return "product/detail";
    }

    @GetMapping("/detail/{pj_id}")
    public String showDetailView(@PathVariable String pj_id, Model model, HttpSession session, HttpServletRequest request, String gift_id) {
        ProjectDto pj = projectService.get(pj_id);

        try {
            // 유저정보 가져오기
            String user_profileImg = getCookieValue(request, "user_profileImg");
            String user_email = (String) (session.getAttribute("user_email"));
            UserDto userdto = UserDto.builder()
                    .user_email(user_email)
                    .user_id(user_email)
                    .build();

            UserDto userinfo = userinfoService.userInfo(userdto);
            model.addAttribute("user", userinfo);
            model.addAttribute("user_profileImg", user_profileImg);

            List<GiftForm> giftServe = giftService.getAllGiftList(pj_id);

//            List<GiftItemDetailDto> itemDt = giftService.getGift(gift_id);
//            GiftForm gf = giftService.getGift(gift_id);
            model.addAttribute("giftServe", giftServe);
//            System.out.println("giftFormaaaaaaaaa" + gf);
        } catch (Exception e) {
            e.printStackTrace();
            return "errorPage";
        }


        model.addAttribute("pj", pj);

        return "product/detail";
    }

    // 유저 프로필이미지 가져오기
    public String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();

        // 쿠키 배열이 null이 아니고, 각 쿠키를 확인하여 원하는 쿠키의 값을 찾음
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName()
                        .equals(cookieName)) {
                    if (cookie.getName()
                            .equals(cookieName) && cookieName.equals("user_profileImg")) {
                        try {
                            return URLDecoder.decode(cookie.getValue(), "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        return cookie.getValue();
                    }
                }
            }
        }

        return null;
    }

    // 창작자 컨트롤러
    @GetMapping("/creation")
    public String creator() {
        return "product/creation";
    }
}

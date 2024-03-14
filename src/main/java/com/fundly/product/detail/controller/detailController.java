package com.fundly.product.detail.controller;

import com.fundly.project.controller.GiftForm;
import com.fundly.project.service.GiftService;
import com.fundly.project.service.ProjectService;
import com.persistence.dto.ProjectDto;
import com.persistence.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class detailController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private GiftService giftService;


    @GetMapping("/detail")
    public String detail() {
        return "product/detail";
    }

    @GetMapping("/detail/{pj_id}")
    public String showDetailView(@PathVariable String pj_id, Model model, HttpSession session, String gift_id) {
        ProjectDto pj = projectService.get(pj_id);

        try {
            // 유저정보 가져오기
            String user_email = (String) (session.getAttribute("user_email"));
            UserDto userdto = UserDto.builder()
                    .user_email(user_email)
                    .user_id(user_email)
                    .build();
            model.addAttribute("user", userdto);

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

    // 창작자 컨트롤러
    @GetMapping("/creation")
    public String creator() {
        return "product/creation";
    }
}

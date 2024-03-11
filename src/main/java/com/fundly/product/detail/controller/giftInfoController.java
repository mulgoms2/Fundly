package com.fundly.product.detail.controller;

import com.fundly.project.controller.GiftForm;
import com.fundly.project.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class giftInfoController {
    @Autowired
    private GiftService giftService;

    @GetMapping("/detail/{pj_id}/{gift_id}")
    public String detailGiftlist(@PathVariable String pj_id, @PathVariable String gift_id, Model model) {

        List<GiftForm> giftServe = null;
        try {
            giftServe = giftService.getAllGiftList(pj_id);
        } catch (Exception e) {
            e.printStackTrace();
            return "errorPage";
//            throw new RuntimeException(e);
        }

        model.addAttribute("giftServe", giftServe);

        return "product/detail";
    }
}

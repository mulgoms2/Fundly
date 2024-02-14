package com.fundly.user.controller;

import com.fundly.user.service.LikeService;
import com.persistence.dto.LikeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class LikeController {

    @Autowired
    LikeService likeservice;

    @PostMapping("/like")
    @ResponseBody
    public LikeDto handleLike(@RequestBody LikeDto likedto) throws Exception {
        // 좋아요 상태 확인
        int isLike =  likeservice.checkLike(likedto);
        likedto.setLike_status(isLike);
        return likeservice.getList(likedto);
    }

//    @RequestMapping(value="/like")
//    public String like(HttpServletRequest req, Model model) throws Exception {
//        String pj_id = req.getParameter("pj_id");
//        String user_id = req.getParameter("user_id");
//
//        LikeDto likedto = new LikeDto();
//        likedto.setPj_id(pj_id);
//        likedto.setUser_id(user_id);
//
//        model.addAttribute("pj_id", pj_id);
//        model.addAttribute("user_id", user_id);
//        model.addAttribute("like_status", likeservice.checkLike(likedto));
//        model.addAttribute("list", likeservice.getList(likedto));
//        return "user/like";
//    }
}

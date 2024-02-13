package com.fundly.user.controller;

import com.fundly.user.service.LikeService;
import com.persistence.dto.ItemDto;
import com.persistence.dto.LikeDto;
import com.persistence.dto.ProjectDto;
import com.persistence.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class LikeController {

    @Autowired
    LikeService likeservice;

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

    @PostMapping("/like2")
    @ResponseBody
    public List<LikeDto> addLikePj(@RequestBody LikeDto likedto) throws Exception {
        // 좋아요 상태 확인
        int isLike =  likeservice.checkLike(likedto);
        likedto.setLike_status(isLike);
        return likeservice.getList(likedto);
    }
}

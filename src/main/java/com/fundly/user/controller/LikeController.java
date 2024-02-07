package com.fundly.user.controller;

import com.fundly.user.model.LikeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class LikeController {
    @Autowired
    LikeDao likeDao;

    //상세에서 좋아요 누르면 관심프로젝트 뷰로 이동.
    @GetMapping("like")
    public String detailLike() {
        return "user/like";
    }

    //찜상태 확인하기


//    //상세페이지 찜하기
//    @PostMapping("/like")
//    public String selectLike() {
//
//        //로그인 유무 확인
//        //안했으면 로그인창
////        return "user/login";
//        return "product/detail";
//
//    }

    //전체페이지 찜하기
}

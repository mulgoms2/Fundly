//package com.fundly.user.controller;
//
//import com.fundly.user.service.LikeService;
//import com.persistence.dto.LikeDto;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@Slf4j
//@RequestMapping("/like")
//public class LikeController {
//
//    private final LikeService likeservice;
//
//    @Autowired
//    public LikeController(LikeService likeservice) {
//        this.likeservice = likeservice;
//    }
//
//    @PostMapping
//    public ResponseEntity<LikeDto> clickLike(@RequestBody LikeDto likedto) {
//
//        // 좋아요 상태 바꾸고 가져오기
//        try {
//
//            likeservice.changeLike(likedto);
//
//            return ResponseEntity.ok(likeservice.getLike(likedto));
//
//        } catch (Exception e) {
//
//            throw new RuntimeException(e);
//
//        }
//    }
//
//    @PostMapping("/status")
//    public ResponseEntity<LikeDto> IsLike(@RequestBody LikeDto likedto) {
//
//        // 현재 좋아요 상태 가져오기
//        try {
//
//            return ResponseEntity.ok(likeservice.getLike(likedto));
//
//        } catch (Exception e) {
//
//            throw new RuntimeException(e);
//
//        }
//    }
//
//    @GetMapping("/likes")
//    public String getLikeList(int page, int pageSize, Model m) {
//
//        try {
//            Map map = new HashMap();
//            map.put("offset",(page-1)*pageSize);
//            map.put("pageSize", pageSize);
//
//            List<LikeDto> likes = likeservice.getPage(map);
//            m.addAttribute("likes", likes);
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return "user/likes2";
//    }
//}

package com.fundly.user.controller;

import com.fundly.user.service.LikeService;
import com.persistence.dto.LikeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class LikeController {

    @Autowired
    LikeService likeservice;

    @PostMapping("/like")
    public ResponseEntity<LikeDto> clickLike(@RequestBody LikeDto likedto) {

        try {

            // 변경된 좋아요 상태 가져오기
            int isLike =  likeservice.changeLike(likedto);
            likedto.setLike_status(isLike);

            return ResponseEntity.ok(likeservice.getLike(likedto));

        } catch (Exception e) {

            throw new RuntimeException(e);

        }
    }

    @PostMapping("/like/status")
    public ResponseEntity<LikeDto> IsLike(@RequestBody LikeDto likedto) {
        try {

            // 현재 좋아요 상태 가져오기
            return ResponseEntity.ok(likeservice.getLike(likedto));

        } catch (Exception e) {

            throw new RuntimeException(e);

        }
    }

}

package com.fundly.user.controller;

import com.fundly.project.service.ProjectService;
import com.fundly.user.dto.LikeRequestDto;
import com.fundly.user.dto.LikeResponseDto;
import com.fundly.user.service.LikeService;
import com.persistence.dto.LikeDto;
import com.persistence.dto.ProjectDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/like")
public class LikeController {

   LikeService likeservice;

    @Autowired
    public LikeController(LikeService likeservice) {
        this.likeservice = likeservice;
    }

    @PostMapping
    public ResponseEntity<LikeResponseDto> clickLike(@RequestBody LikeRequestDto likereq) {

        try {

            log.error("\n\n\n [click]요청한 likedto:" + likereq + "\n\n\n");

            LikeDto likedto = LikeDto.builder().pj_id(likereq.getPj_id()).user_id(likereq.getUser_id()).build();
            ProjectDto pjdto = ProjectDto.builder().pj_id(likereq.getPj_id()).curr_pj_like_cnt(likereq.getCurr_pj_like_cnt()).build();

            // 좋아요 상태,좋아요 수 업데이트
            likeservice.changeLike(likedto,pjdto);

            //업데이트 된 데이터 가져오기`
            LikeDto updatedLikeDto = likeservice.getupdatedLike(likedto);
            ProjectDto updatedPjDto = likeservice.getupdatedPj(pjdto);

            log.error("\n\n\n응답 likedto:" + updatedLikeDto + "\n응답 pjdto:" + updatedPjDto + "\n\n\n");

            LikeResponseDto response = new LikeResponseDto();
            response.setLikedto(updatedLikeDto);
            response.setPjdto(updatedPjDto);

            log.error("\n\n\n응답객체 response:" + response + "\n\n\n\n");

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            throw new RuntimeException(e);

        }
    }

    @PostMapping("/status")
    public ResponseEntity<LikeDto> IsLike(@RequestBody LikeRequestDto likereq) {

        // 현재 좋아요 상태 가져오기
        try {
            log.error("\n\n\n [load]요청한 likedto:" + likereq + "\n\n\n");

            LikeDto likedto = LikeDto.builder().pj_id(likereq.getPj_id()).user_id(likereq.getUser_id()).build();

            log.error("\n\n\n [load]응답한 likedto:" + likeservice.getupdatedLike(likedto) + "\n\n\n");
            return ResponseEntity.ok(likeservice.getupdatedLike(likedto));

        } catch (Exception e) {

            throw new RuntimeException(e);

        }
    }

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
}

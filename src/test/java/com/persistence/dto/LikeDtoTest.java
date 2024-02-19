//package com.persistence.dto;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//
//import static java.time.LocalDateTime.now;
//
//@Slf4j
//class LikeDtoTest {
//    @Test
//    void reqbuilderTest() {
//
//        LikeDto.Request req = LikeDto.Request.builder()
//                .user_id("user")
//                .pj_id("P5040")
//                .like_dtm(now())
//                .build();
//        log.error("\n\n\n" + req);
//    }
//
//    @Test
//    void resbuilderTest() {
//        LikeDto.Response res = LikeDto.Response.builder()
//                .like_status(1)
//                .build();
//        log.error("\n\n\n" + res);
//    }
//
//    @Test
//    void dtobuilderTest() {
////        LikeDto likedto = LikeDto.builder().build();
//    }
//}
//package com.fundly.user.service;
//
//import com.fundly.user.model.LikeDao;
//import com.persistence.dto.LikeDto;
//import config.RootContext;
//import config.ServletContext;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
//
//import java.util.Date;
//import java.text.SimpleDateFormat;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@Slf4j
//@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
//class LikeServiceImplTest {
//
//    @Autowired
//    LikeDao likedao;
//    @Autowired
//    LikeService likeservice;
//    LikeDto likedto;
//
//    SimpleDateFormat date = new SimpleDateFormat("yyy-mm-dd hh:mm:ss");
//
//    @Test
//    @SneakyThrows
//    @DisplayName("처음좋아요")
//    void checkLikeTestWhenListIsEmpty() {
//        Date date2 = (Date) date.parse("2024-05-30 18:00:00");
//
//        likedto = new LikeDto("bada","P001", dateString);
//        assertTrue(likeservice.changeLike(likedto)==1);
//    }
//
//    @Test
//    @SneakyThrows
//    @DisplayName("좋아요취소")
//    void checkLikeTestWhenListIsNotEmpty() {
//        Date date2 = (Date) date.parse("2024-05-30 18:00:00");
//
//        likedto = new LikeDto("bada","P001", (java.sql.Date) date2);
//        assertTrue(likeservice.changeLike(likedto)==0);
//    }
//
//    @Test
//    @SneakyThrows
//    @DisplayName("다시 좋아요")
//    void checkLike() {
//        Date date2 = (Date) date.parse("2024-05-30 18:00:00");
//
//        likedto = new LikeDto("bada","P001", (java.sql.Date) date2);
//        assertTrue(likeservice.changeLike(likedto)==1);
//    }
//}
package com.fundly.user.service;

import com.fundly.user.model.LikeDao;
import com.persistence.dto.LikeDto;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
class LikeServiceImplTest {

    @Autowired
    LikeDao likedao;
    @Autowired
    LikeService likeservice;
    LikeDto likedto;

    @Test
    @SneakyThrows
    @DisplayName("처음좋아요")
    void checkLikeTestWhenListIsEmpty() {
        assertTrue(likeservice.checkLike(likedto)==1);
    }

    @Test
    @SneakyThrows
    @DisplayName("좋아요취소")
    void checkLikeTestWhenListIsNotEmpty() {
        likedto = new LikeDto("bada","P001");
        likedao.insertLike(likedto);
        assertTrue(likeservice.checkLike(likedto)==0);
    }
}
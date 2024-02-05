package com.fundly.user.model;

import com.persistence.dto.UserDto;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
class LikeDaoTest {

    @Autowired
    LikeDao likedao;
    UserDto userdto;

    @Test
    @BeforeEach
    @SneakyThrows
    void start() {
        Map map = new HashMap();
        String user_id = "bada";
        String pj_id = "P001";
        map.put("user_id", user_id);
        map.put("pj_id", pj_id);
//        LikeDao.insertLike(map);
        System.out.println("map = " + map);
    }
    @AfterEach
    @SneakyThrows
    void deleteLike() {
//        LikeDao.deleteLike();
    }

    @Test
    @SneakyThrows
    @DisplayName("좋아요상태확인")
    void checkLikeTest() {
//        LikeDao.checkLike(userdto);
    }

    @Test
    @SneakyThrows
    @DisplayName("처음좋아요")
    void insertLikeTest() {
        assertTrue(true);
    }

    @Test
    @SneakyThrows
    @DisplayName("안좋아요")
    void cancelLikeTest() {
    }

    @Test
    @SneakyThrows
    @DisplayName("다시좋아요")
    void reLikeTest() {
    }
}
package com.fundly.user.model;

import com.persistence.dto.LikeDto;
import com.persistence.dto.ProjectDto;
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
import java.util.Date;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
class LikeDaoTest {

    @Autowired
    LikeDao likedao;

    @Test
    @SneakyThrows
    @DisplayName("처음좋아요")
    void insertLikeTest() {
        UserDto userdto = new UserDto("bada","바다","1234");
        ProjectDto pjdto = new ProjectDto("P001");
        LikeDto likedto = new LikeDto(userdto.getUser_id(), pjdto.getPj_id());
        assertTrue(likedao.insertLike(likedto)==1);
    }

    @Test
    @SneakyThrows
    @DisplayName("좋아요리스트확인")
    void getLikeListTest() {
    }

    @Test
    @SneakyThrows
    @DisplayName("안좋아요")
    void cancelLikeTest() {
    }
}
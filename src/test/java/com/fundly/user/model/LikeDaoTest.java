package com.fundly.user.model;

import com.persistence.dto.LikeDto;
import com.persistence.dto.ProjectDto;
import com.persistence.dto.UserDto;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
class LikeDaoTest {

    @Autowired
    LikeDao likedao;
    UserDto userdto;
    ProjectDto pjdto;
    LikeDto likedto;

    @BeforeEach
    @SneakyThrows
    @DisplayName("DB에 데이터넣기")
    void start() {
        userdto = new UserDto("bada","바다","1234");
        pjdto = new ProjectDto("P001",0);
        likedto = new LikeDto(userdto.getUser_id(),pjdto.getPj_id());
    }

    @AfterEach
    @SneakyThrows
    @DisplayName("DB에 데이터삭제")
    void end() {
        int result = likedao.deleteAllLike();
        System.out.println("result = " + result);
    }

    @Test
    @SneakyThrows
    @DisplayName("좋아요목록전체")
    void getLikeListAllTest() {
        likedto = new LikeDto("user","P3030");
        List<LikeDto> likes = likedao.getLikeListAll(likedto);
        assertTrue(likes.size()==2);
    }

    @Test
    @SneakyThrows
    @DisplayName("좋아요목록")
    void getLikeTest() {
        LikeDto list = likedao.getLike(likedto);
//        assertTrue(list.size()==0);
        assertTrue(likedao.insertLike(likedto)==1);
        list = likedao.getLike(likedto);
//        assertTrue(list.size()==1);
        System.out.println("list = " + list);
        userdto = new UserDto("bada","바다","1234");
        pjdto = new ProjectDto("P002",0);
        likedto = new LikeDto(userdto.getUser_id(),pjdto.getPj_id());
        assertTrue(likedao.insertLike(likedto)==1);
        list = likedao.getLike(likedto);
//        assertTrue(list.size()==1);
        System.out.println("list = " + list);
    }

    @Test
    @SneakyThrows
    @DisplayName("처음좋아요")
    void insertLikeTest() {
        assertTrue(likedao.insertLike(likedto)==1);
        userdto = new UserDto("bada","바다","1234");
        pjdto = new ProjectDto("P002",0);
        likedto = new LikeDto(userdto.getUser_id(),pjdto.getPj_id());
        assertTrue(likedao.insertLike(likedto)==1);
    }

    @Test
    @SneakyThrows
    @DisplayName("좋아요취소")
    void cancelLikeTest() {
        assertTrue(likedao.insertLike(likedto)==1);
        //좋아요목록 불러와서
        likedao.getLike(likedto);
        //좋아요 상태가 1이면
        assertTrue(likedao.cancelLike(likedto)==1);
        //likedao.cancelLike()
    }


    @Test
    @SneakyThrows
    @DisplayName("다시좋아요")
    void reLikeTest() {
        //좋아요목록 불러와서
        assertTrue(likedao.insertLike(likedto)==1);
        //좋아요 상태가 0이면
        likedao.getLike(likedto);
        assertTrue(likedao.cancelLike(likedto)==1);
        //likedao.reLike()
        assertTrue(likedao.reLike(likedto)==1);
    }
}
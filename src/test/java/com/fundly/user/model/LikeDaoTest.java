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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.text.SimpleDateFormat;

import static java.time.LocalTime.now;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
@Transactional
class LikeDaoTest {

    @Autowired
    LikeDao likedao;
    UserDto userdto;
    ProjectDto pjdto;
    LikeDto likedto;

    // String -> LocalDateTime
    // str = js에서 const curr = new Date();로 현재시간 가져온 값
//    String str = "2024-02-16T12:25:12.004Z";
//    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//    LocalDateTime strtoDatetime = LocalDateTime.parse(str,format);
//
//    // LocalDateTime -> String
//    String nowtoString = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    @BeforeEach
    @SneakyThrows
    @DisplayName("DB에 데이터넣기")
    void start() {

//        userdto = new UserDto("user","바다","1234");
//        pjdto = new ProjectDto("P5040",0);
        likedto = new LikeDto(userdto.getUser_id(),pjdto.getPj_id());
        log.error("\n\n\n" + likedto);

    }
    @Test
    @SneakyThrows
    @DisplayName("좋아요목록삭제")
    void deleteAllLikeTest() {
        int result = likedao.deleteAllLike();
        System.out.println("result = " + result);
    }

    @Test
    @SneakyThrows
    @DisplayName("좋아요목록전체")
    void AllLikeListTest() {

        assertTrue(likedao.insertLike(likedto)==1);

        List<LikeDto> likes = likedao.AllLikeList(likedto);
        assertTrue(likes.size()==1);
    }

    @Test
    @SneakyThrows
    @DisplayName("좋아요목록추가순")
    void LikeListByTime() {

        assertTrue(likedao.insertLike(likedto)==1);

        // 두번째 좋아요한 프로젝트
        String str2 = "2021-11-05 13:47:13";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime strtoDatetime2 = LocalDateTime.parse(str2,format);

        LikeDto likedto2 = new LikeDto (userdto.getUser_id(),"P1010");
        assertTrue(likedao.insertLike(likedto2)==1);

        List<LikeDto> likes = likedao.AllLikeList(likedto);
        assertTrue(likes.size()==2);
        log.error("\n\n\n" + likes);

        // 첫번째 좋아요 취소 후 다시 좋아요
        assertTrue(likedao.cancelLike(likedto)==1);
        Thread.sleep(2*1000);
        assertTrue(likedao.reLike(likedto)==1);

        // 다시 좋아요한 프로젝트가 먼저인지 순서 확인
        List<LikeDto> likes2 = likedao.AllLikeList(likedto);
        assertTrue(likes.size()==2);
        log.error("\n\n\n" + likes2);


    }

    @Test
    @SneakyThrows
    @DisplayName("좋아요확인")
    void getLikeTest() {

        assertTrue(likedao.insertLike(likedto)==1);
        LikeDto result = likedao.getLike(likedto);
        log.error("\n\n\n" + result);

    }

    @Test
    @SneakyThrows
    @DisplayName("처음좋아요")
    void insertLikeTest() {

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
        //likedao.cancelLike()
        assertTrue(likedao.cancelLike(likedto)==1);
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
    
    @Test
    @SneakyThrows
    void selectPageTest() {
        Map map = new HashMap();
        map.put("offset",1);
        map.put("pageSize", 10);
        List<LikeDto> result = likedao.selectPage(map);
        log.error("\n\n\n" + result);
    }
}
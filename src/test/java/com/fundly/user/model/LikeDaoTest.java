package com.fundly.user.model;

import com.fundly.user.dto.LikeProjectDto;
import com.fundly.user.dto.LikeResponseDto;
import com.persistence.dto.LikeDto;
import com.persistence.dto.ProjectDto;
import com.persistence.dto.UserDto;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        userdto = UserDto.builder().user_id("bada").build();
        pjdto = ProjectDto.builder().pj_id("P1010").build();
        likedto = LikeDto.builder().user_id(userdto.getUser_id()).pj_id(pjdto.getPj_id()).build();
        log.error("\n\n\n" + likedto);

    }
    @Test
    @SneakyThrows
    @DisplayName("좋아요목록삭제")
    void deleteAllLikeTest() {
        int result = likedao.deleteAllLike(likedto);
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

        LikeDto likedto2 = LikeDto.builder().user_id(userdto.getUser_id()).pj_id("P2020").build();
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
    void checkLikeTest() {

        assertTrue(likedao.insertLike(likedto)==1);
        LikeDto result = likedao.checkLike(likedto);
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
        likedao.checkLike(likedto);
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
        likedao.checkLike(likedto);
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

    @Test
    @SneakyThrows
    @DisplayName("프로젝트-좋아요테이블 조인테스트")
    void test() {
        assertTrue(likedao.insertLike(likedto)==1);

        List<LikeProjectDto> res = likedao.AllLikeListWithPj(likedto.getUser_id());
        System.out.println("[[[[조인테스트 출력]]]] " + res);
        
        int like_status = res.get(0).getLike_status();
        System.out.println("res_like_status = " + like_status);

        String pj_id = res.get(0).getPj_id();
        System.out.println("res_pj_id = " + pj_id);
    }
}
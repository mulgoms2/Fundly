//package com.fundly.user.service;
//
//import com.fundly.project.model.ProjectMapper;
//import com.fundly.user.model.LikeDao;
//import com.persistence.dto.LikeDto;
//import com.persistence.dto.ProjectDto;
//import config.RootContext;
//import config.ServletContext;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(MockitoExtension.class)
//@Slf4j
//@Transactional
//@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
//class LikeServiceImplTest {
//
//    //    @Mock
//    LikeDao likedao;
//
//    ProjectMapper pjdao;
//
//    //    @InjectMocks
//    LikeService likeservice;
//    ProjectDto pjdto, pjdto2, pjdto3, pjdto4;
//    LikeDto likedto, likedto2, likedto3, likedto4;
//
//
//    @Autowired
//    public LikeServiceImplTest(LikeDao likedao, ProjectMapper pjdao) {
//
//        this.likedao = likedao;
//        this.pjdao = pjdao;
//
//    }
//
//    @Test
//    @SneakyThrows
//    @BeforeEach
//    void set() {
//
//        String userId = "user";
//
//        pjdto = ProjectDto.builder().pj_id("1").pj_status("ing").curr_pj_like_cnt(10).build();
//        pjdto2 = ProjectDto.builder().pj_id("2").pj_status("ing").curr_pj_like_cnt(10).build();
//        pjdto3 = ProjectDto.builder().pj_id("3").pj_status("end").curr_pj_like_cnt(10).build();
//        pjdto4 = ProjectDto.builder().pj_id("4").pj_status("end").curr_pj_like_cnt(10).build();
//
//        likedto = LikeDto.builder().user_id(userId).pj_id(pjdto.getPj_id()).build();
//        assertTrue(pjdao.insert(pjdto) == 1);
//        assertTrue(likedao.insertLike(likedto) == 1);
//
//        likedto2 = LikeDto.builder().user_id(userId).pj_id(pjdto2.getPj_id()).build();
//        assertTrue(pjdao.insert(pjdto2) == 1);
//        assertTrue(likedao.insertLike(likedto2) == 1);
//
//        likedto3 = LikeDto.builder().user_id(userId).pj_id(pjdto3.getPj_id()).build();
//        assertTrue(pjdao.insert(pjdto3) == 1);
//        assertTrue(likedao.insertLike(likedto3) == 1);
//
//        // 4번은 좋아요한 적이 없는 프로젝트
//        likedto4 = LikeDto.builder().user_id(userId).pj_id(pjdto4.getPj_id()).build();
//        assertTrue(pjdao.insert(pjdto4) == 1);
//    }
//
//    @Test
//    @SneakyThrows
//    @DisplayName("좋아요목록에 있는지 확인 후 좋아요 on/off")
//    void changeLike() {
//
//        // 이미 리스트에 있는 프로젝트 정보
//        LikeDto likes = likedao.getLike(likedto);
//        assertEquals(1, likes.getLike_status());
//        assertEquals(1, likedao.cancelLike(likedto));
//        assertEquals(1, pjdao.downLikeCnt(pjdto));
//        assertEquals(9, pjdto.getCurr_pj_like_cnt());
//
//        // 리스트에 없는 프로젝트 정보
//        LikeDto likes2 = likedao.getLike(likedto4);
//        assertEquals(null, likes2);
//        assertEquals(1, likedao.insertLike(likedto4));
//        assertEquals(1, pjdao.upLikeCnt(pjdto4));
//        assertEquals(11, pjdto4.getCurr_pj_like_cnt());
//
//    }
//
//    @Test
//    @SneakyThrows
//    @DisplayName("좋아요목록에 있는지 확인")
//    void getLike() {
//
//        //좋아요 목록에 있어서 상태1을 반환하는지 확인
//        LikeDto likes = likedao.getLike(likedto);
//        assertTrue(likes.getLike_status() == 1);
//
//        // 좋아요 목록에 없을 때 null 반환하는지 확인
//        LikeDto likes2 = likedao.getLike(likedto4);
//        assertEquals(null, likes2);
//    }
//
//    @Test
//    @SneakyThrows
//    @DisplayName("좋아요 목록 중 진행중인 프로젝트만 불러오기")
//    void getListWithPjStatusTest() {
//
//        // 좋아요 목록
//        List<LikeDto> likes = likedao.AllLikeList(likedto);
//        assertEquals(3, likes.size());
//
//        // 프로젝트 정보 담을 리스트
//        List<ProjectDto> projectList = new ArrayList<>();
//
//        // 좋아요 목록에 있는 프로젝트 이름 순서대로 프로젝트 정보를 리스트에 담기
//        for (LikeDto like : likes) {
//            ProjectDto projectdto = pjdao.selectByStatus(like.getPj_id(), "ing");
//            log.error("\n\n" + projectdto + "\n\n");
//            if (projectdto != null) {
//                projectList.add(projectdto);
//            }
//        }
//        assertEquals(2, projectList.size());
//    }
//
//    @Test
//    @SneakyThrows
//    @DisplayName("좋아요 목록 중 완료된 프로젝트만 불러오기")
//    void getListWithPjStatusTest2() {
//
//        // 좋아요 목록
//        List<LikeDto> likes = likedao.AllLikeList(likedto);
//        assertEquals(3, likes.size());
//
//        // 프로젝트 정보 담을 리스트
//        List<ProjectDto> projectList = new ArrayList<>();
//
//        // 좋아요 목록에 있는 프로젝트 이름 순서대로 프로젝트 정보를 리스트에 담기
//        for (LikeDto like : likes) {
//            ProjectDto projectdto = pjdao.selectByStatus(like.getPj_id(), "end");
//            log.error("\n\n" + projectdto + "\n\n");
//            if (projectdto != null) {
//                projectList.add(projectdto);
//            }
//        }
//        assertEquals(1, projectList.size());
//    }
//
//    @Test
//    @SneakyThrows
//    @DisplayName("증가된 좋아요수불어오는 테스트")
//    void upLikeCntTest() {
//
//        int updatedRows = pjdao.upLikeCnt(pjdto);
////        int likeCnt = pjdao.selectLikeCnt(pjdto.getPj_id());
//        assertEquals(1, updatedRows);
//
//        // 좋아요 수 증가 성공 시 업데이트된 좋아요 수를 반환
////        assertEquals(11, likeCnt);
//
//    }
//
//    @Test
//    @SneakyThrows
//    @DisplayName("감소된 좋아요수불어오는 테스트")
//    void downLikeCntTest() {
//        int updatedRows = pjdao.downLikeCnt(pjdto);
////        int likeCnt = pjdao.selectLikeCnt(pjdto.getPj_id());
//        assertEquals(1, updatedRows);
//
//        // 좋아요 수 증가 성공 시 업데이트된 좋아요 수를 반환
////        assertEquals(9, likeCnt);
//    }
//
////    @Test
////    @SneakyThrows
////    @DisplayName("좋아요 on/off 시 좋아요수 업데이트하기 테스트")
////    void
//}
//

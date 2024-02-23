package com.fundly.user.service;

import com.fundly.project.model.ProjectMapper;
import com.fundly.user.model.LikeDao;
import com.persistence.dto.LikeDto;
import com.persistence.dto.ProjectDto;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@Slf4j
@Transactional
@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
class LikeServiceImplTest {

    //    @Mock
    LikeDao likedao;

    ProjectMapper pjdao;

    //    @InjectMocks
    LikeService likeservice;


    @Test
    @SneakyThrows
    void transJasonTest() {

        LikeDto likedto = new LikeDto();

//        Mockito.when(likedao.getLike(likedto)).thenReturn("ok");

        //TODO : 가져온 데이터에서 datetime인 like_dtm을 jason.parse 하는 로직 필요


    }

    @Test
    void changeLike() {
    }

    @Test
    void getLike() {
    }

    @Test
    @SneakyThrows
    void getLikeListWithPjTest() {

        LikeDto likedto = new LikeDto("yyy", "P5040");
        likedao.insertLike(likedto);
        LikeDto likedto2 = new LikeDto("yyy", "P111");
        likedao.insertLike(likedto2);

        log.error("\n\n 좋아요목록 널체크 = " + likedto + "\n\n");

        // 좋아요 목록
        List<LikeDto> likes = likedao.AllLikeList(likedto);

        // 프로젝트 정보 담을 리스트
        List<ProjectDto> projectList = new ArrayList<>();

        // 좋아요 목록에 있는 프로젝트 이름 순서대로 프로젝트 정보를 리스트에 담기
        for (LikeDto like : likes) {
            ProjectDto projectdto = pjdao.selectByStatus(like.getPj_id(), "ing");
            if (projectdto != null) {
                projectList.add(projectdto);
            }
        }

        log.error("\n\n 좋아요프로젝트리스트 = " + likes + "\n\n");
    }
}


package com.fundly.project.service;

import com.fundly.project.model.ProjectDao;
import com.fundly.project.model.ProjectMapper;
import com.persistence.dto.ProjectDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {
    @Mock
    ProjectMapper projectMapper;
    @InjectMocks
    ProjectServiceImpl projectServiceImpl;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("프로젝트 추가")
    public void add() {

        projectServiceImpl.add()
    }

    @Test
    @DisplayName("프로젝트 템플릿 가져오기")
    void getPjTemplate() {
//        프로젝트 뷰에 뿌려줄 템플릿을 만든다.

//        조건
//        테이블에 프로젝트 정보가 존재, 대표이미지는 파일 테이블에 저장되어있다.
        String pj_id = "1";
        ProjectDto willGivenDto = ProjectDto.builder().pj_id("1").pj_sel_id("mulgom").pj_long_title("최고의 선물").build();
        given(projectMapper.getByPjId("1")).willReturn(willGivenDto);

        ProjectDto projectDto = projectServiceImpl.getPjTemplate(pj_id);

        assertEquals(willGivenDto.getPj_id(), projectDto.getPj_id());


//        행위
//        프로젝트 아이디를 인자로 전달하며 메서드를 호출한다.

//        결과
//        프로젝트 한개에 대한 템플릿을 반환한다.
    }
}
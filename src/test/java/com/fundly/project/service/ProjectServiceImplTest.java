package com.fundly.project.service;

import com.fundly.project.exception.ProjectNofFoundException;
import com.fundly.project.exception.ProjectUpdateFailureException;
import com.fundly.project.model.ProjectMapper;
import com.persistence.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@Slf4j
@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {
    @Mock
    ProjectMapper projectMapper;
    @InjectMocks
    ProjectServiceImpl projectServiceImpl;

    ProjectAddRequest 프로젝트생성요청;
    private ProjectDto projectDto;

    @BeforeEach
    void setUp() {
        프로젝트생성요청 = ProjectAddRequest.builder().user_id("dbswoi123").build();
        projectDto = ProjectDto.builder().build();
    }

    @Test
    @DisplayName("add(ProjectAddRequest) 최소한의 동작 테스트")
    public void addPj() {
//        테스트 대상 메서드 실행
        given(projectMapper.getByPjId(any())).willReturn(projectDto);
        ProjectDto result = projectServiceImpl.add(프로젝트생성요청);
//        테스트 결과 확인
        assertNotNull(result);
    }

    @Test
    @DisplayName("add(ProjectAddRequest) 프로젝트 추가")
    public void add() {
//       컨트롤러로부터 프로젝트 추가 요청을 받아 프로젝트 추가후. 저장된 프로젝트를 리턴한다.
        ProjectDto 프로젝트 = 프로젝트생성요청.toProject();
//        프로젝트 추가요청을 받아 응답객체를 돌려준다.
        given(projectMapper.getByPjId(any())).willReturn(프로젝트);
        ProjectDto projectDto = projectServiceImpl.add(프로젝트생성요청);


//        프로젝트 응답객체에는 판매자 아이디, 프로젝트번호, 및 유저 정보가 들어있어야 한다.
        assertThat(projectDto).isNotNull();
        assertThat(projectDto.getPj_sel_id()).isEqualTo(프로젝트생성요청.getUser_id());
        assertThat(프로젝트.getPj_id()).isNotEmpty();
    }

    @Test
    @DisplayName("add() 프로젝트 추가시 키 중복되는 경우, 다시 시도")
    void duplicateKey() {
//        ProjectDto pj = 프로젝트생성요청.toProject();
        given(projectMapper.insert(any())).willThrow(DuplicateKeyException.class).willReturn(1);
        given(projectMapper.getByPjId(any())).willReturn(projectDto);
//        프로젝트를 추가하려는데. 생성된 프로젝트의 키가 데이터베이스에 이미 존재한다.
        ProjectDto projectAddResponse = assertDoesNotThrow(() -> projectServiceImpl.add(프로젝트생성요청));

//        그러면 프로젝트 객체에게 새로운 키를 만들 것을 요청한다? 아니면 프로젝트 요청을 통해 새로운 프로젝트 객체를 생성한다?

//        둘다 가능함
        verify(projectMapper, times(2)).insert(any());
    }

    @Test
    @DisplayName("getById() 프로젝트 템플릿 가져오기")
    void get() {
        String pj_id = "pj01";
        ProjectDto pj = ProjectDto.builder().pj_id(pj_id).pj_sel_id("mulgom").pj_short_title("한윤재의 프로젝트").build();
        given(projectMapper.getByPjId(pj_id)).willReturn(pj);

        ProjectDto pjTemplate = projectServiceImpl.get(pj_id);
        assertThat(pjTemplate).isNotNull();

        verify(projectMapper).getByPjId(pj_id);
    }

    @Test
    @DisplayName("getById() 존재하지 않는 프로젝트가 조회되면 예외를 발생시킨다.")
    void getByNonExistId() {
        given(projectMapper.getByPjId(any())).willReturn(null);

        assertThatExceptionOfType(ProjectNofFoundException.class).isThrownBy(() -> projectServiceImpl.get("hello"));
    }

    @Test
    @DisplayName("getEditingProject() 편집중인 프로젝트가 없다.")
    void get_editing_project_dosent_exists() {
//        특정 유저가 편집중인 프로젝트를 가져오려는데, 편집중인 프로젝트가 존재하지 않는다.
        String user_email = "dbswoi123@naver.com";
//        빈 리스트가 반환되었다.
        List<ProjectDto> emptyList = new ArrayList<>();
        given(projectMapper.getListByUserId(any())).willReturn(emptyList);

        assertThatExceptionOfType(ProjectNofFoundException.class).isThrownBy(() -> projectServiceImpl.getEditingProject(user_email));

//        작성중인 상태의 프로젝트가 존재하지 않으면 예외를 발생시킨다.
//        assertThat(pj_id).isNull();
    }

    @Test
    @DisplayName("getEditingProject() 편집중인 프로젝트를 반환한다.")
    void get_editing_project() {
        List<ProjectDto> pjList = new ArrayList<>();
        String user_email = "dbwswoi123@naver.com";

        String result_pj_id = "01";
        ProjectDto 작성중인프로젝트 = ProjectDto.builder().pj_id(result_pj_id).pj_status("작성중").build();
        ProjectDto pj1 = ProjectDto.builder().pj_id("02").pj_status("진행중").build();
        ProjectDto pj2 = ProjectDto.builder().pj_id("03").pj_status("진행중").build();
        ProjectDto pj3 = ProjectDto.builder().pj_id("04").pj_status("승인반려").build();

        pjList.add(pj1);
        pjList.add(pj2);
        pjList.add(작성중인프로젝트);
        pjList.add(pj3);

        given(projectMapper.getListByUserId(user_email)).willReturn(pjList);

        ProjectDto project = projectServiceImpl.getEditingProject(user_email);

        assertThat(project).isNotNull();
        assertThat(project.getPj_id()).isEqualTo(result_pj_id);
    }

    @Test
    @DisplayName("updatePjInfo() 프로젝트를 업데이트 한다.")
    void updateProject() {
        String pj_id = "01";
        ProjectDto pj = ProjectDto
                .builder()
                .pj_id(pj_id)
                .ctg("가전제품")
                .sub_ctg("밥솥")
                .pj_short_title("맛있는밥")
                .pj_long_title("매일 매일 만들어먹는 집밥")
                .build();
        ProjectInfoUpdateRequest pjInfoUpdate = ProjectInfoUpdateRequest
                .builder()
                .pj_id(pj_id)
                .ctg("반려동물")
                .sub_ctg("사료")
                .pj_short_intro("맛있는 츄르")
                .pj_long_title("우리집 고양이 츄르를 좋아해")
                .build();

        given(projectMapper.getByPjId(pjInfoUpdate.getPj_id())).willReturn(pj);

        ProjectBasicInfo resp =  projectServiceImpl.updatePjInfo(pjInfoUpdate);

        assertThat(resp).isNotNull();
        assertThat(resp.getCtg()).isEqualTo(pjInfoUpdate.getCtg());
        assertThat(resp.getSub_ctg()).isEqualTo(pjInfoUpdate.getSub_ctg());
        assertThat(resp.getPj_short_title()).isEqualTo(pjInfoUpdate.getPj_short_title());
        assertThat(resp.getPj_long_title()).isEqualTo(pjInfoUpdate.getPj_long_title());
    }

    @Test
    @DisplayName("getProjectBasicInfo() 조회되는 프로젝트가 없을때")
    void project_not_found() {
        String pj_id = "01";

//        해당 프로젝트 아이디로 조회되는 프로젝트가 없는 예외상황
        given(projectMapper.getByPjId(pj_id)).willReturn(null);

        assertThatExceptionOfType(ProjectNofFoundException.class).isThrownBy(() -> projectServiceImpl.getProjectBasicInfo(pj_id));
    }

    @Test
    @DisplayName("getProjectBasicInfo() 조회되는 프로젝트가 있을때")
    void get_project_basicInfo() {

        String pj_id = "01";
        given(projectMapper.getByPjId(any())).willReturn(ProjectDto.builder().pj_id(pj_id).pj_status("작성중").build());

        ProjectBasicInfo projectBasicInfo = projectServiceImpl.getProjectBasicInfo(pj_id);

        assertThat(projectBasicInfo.getPj_id()).isEqualTo(pj_id);
        log.debug(""+projectBasicInfo);

    }

    @Test
    @DisplayName("update() 업데이트 대상 프로젝트가 존재하지 않는다.")
    void updateFaileur() {
//        업데이트를 시도했으나 영향받은 행이 없다.
        given(projectMapper.update(any())).willReturn(0);

        assertThatExceptionOfType(ProjectUpdateFailureException.class).isThrownBy(() -> projectServiceImpl.update(projectDto));
    }

    @Test
    @DisplayName("update(ProjectDto) 프로젝트 전체를 업데이트한다")
    void update() {
        projectDto.setPj_id("01");
        projectDto.setPj_status("작성중");

        given(projectMapper.update(any())).willReturn(1);
        given(projectMapper.getByPjId(projectDto.getPj_id())).willReturn(projectDto);


        ProjectDto savedProject = projectServiceImpl.update(projectDto);

        assertThat(savedProject.getPj_id()).isEqualTo(projectDto.getPj_id());
        log.debug(""+savedProject);
    }
}
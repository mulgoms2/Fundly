package com.fundly.project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fundly.project.exception.ImageSaveFailureException;
import com.fundly.project.exception.ProjectAddFailureException;
import com.fundly.project.exception.ProjectNofFoundException;
import com.fundly.project.exception.ProjectUpdateFailureException;
import com.fundly.project.service.ProjectService;
import com.fundly.project.util.FileUploader;
import com.persistence.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class ProjectBasicInfoControllerTest {
    @Mock
    ProjectService service;
    @InjectMocks
    ProjectBasicInfoController projectBasicInfoController;
    MockMvc mockMvc;
    ObjectMapper objectMapper;
    private ProjectInfoUpdateRequest request;
    private ProjectInfoUpdateResponse response;
    private String pj_id;
    private byte[] requestJson;
    private String user_id;
    private String editingProject;
    private ProjectDto projectDto;

    public static final String BASE_URL = "/project/editor/";



    @BeforeEach
    void setUp() throws JsonProcessingException {
        mockMvc = MockMvcBuilders.standaloneSetup(projectBasicInfoController).build();
        objectMapper = new ObjectMapper();

        pj_id = "01";
        user_id = "mulgom";
        request = ProjectInfoUpdateRequest.builder().pj_id(pj_id).build();
        response = ProjectInfoUpdateResponse.builder().pj_id(pj_id).build();
        requestJson = objectMapper.writeValueAsBytes(request);
        editingProject = "editingProject";

//        given(service.getEditingProject(any())).willReturn(ProjectDto.builder().pj_id(pj_id).build());
    }


//    @Test
//    @DisplayName("getStart() 비로그인 유저가 프로젝트 에디터에 진입하면 지금 시작하기 버튼이 로그인창으로 이동시킨다.")
//    void unLoginedUser_start_editing() throws Exception {
////        로그인 하지 않은 유저가 프로젝트 에디터 시작페이지에 들어오면 로그인 페이지로 이동시킨다.
//
//        mockMvc.perform(get("/editor/start"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(model().attributeDoesNotExist(editingProject))
//                .andExpect(redirectedUrl("/login/login"))
//                .andDo(print());
//
//        verify(service, never()).getEditingProject(any());
//    }

    @Test
    @DisplayName("getStart() 작성중인 프로젝트가 존재하는 유저가 프로젝트 에디터 시작페이지를 요청한다.")
    void get_start_page() throws Exception {
//        eferje

        projectDto = ProjectDto.builder().pj_id(pj_id).build();

        given(service.getEditingProject(user_id)).willReturn(projectDto);

        mockMvc.perform(get( BASE_URL + "/start").sessionAttr("user_email", user_id))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("projectDto"))
                .andExpect(forwardedUrl("project/start"))
                .andDo(print());

        verify(service).getEditingProject(user_id);
    }

//    @Test
//    @DisplayName("startPage() 이어서 작성하기시 pj_id를 세션에 저장한다.")
//    void save_pjId_onSession() throws Exception {
//        projectDto = ProjectDto.builder().pj_id(pj_id).build();
//
//        given(service.getEditingProject(user_id)).willReturn(projectDto);
//
//        ResultMatcher sessionChecker = getSessionChecker("pj_id", pj_id);
//        mockMvc.perform(get("/editor/start").sessionAttr("user_email", user_id))
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("pj_id"))
//                .andExpect(forwardedUrl("project/start"))
//                .andExpect(sessionChecker)
//                .andDo(print());
//
//        verify(service).getEditingProject(user_id);
//    }

    @Test
    @DisplayName("getStart() 프로젝트 올리기 페이지에 작성중인 프로젝트가 존재하지 않는다.")
    void get_start_with_project() throws Exception {
        ProjectStarter pjStarter = ProjectStarter.builder().build();
        given(service.getEditingProject(user_id)).willThrow(ProjectNofFoundException.class);

        mockMvc.perform(get(BASE_URL + "/start").sessionAttr("user_email", user_id))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("pj_id"))
                .andExpect(forwardedUrl("project/start"))
                .andDo(print());

        verify(service).getEditingProject(user_id);
    }

//    @Test
//    @DisplayName("getInfo(pj_id) 프로젝트 아이디가 공백일때")
//    void getInfoInputEmpty() throws Exception {
//        mockMvc.perform(get("/editor/info").param("pj_id", ""))
//                .andExpect(status().isOk())
//                .andExpect(forwardedUrl("project/clientError"))
//                .andExpect(model().attributeExists("errorMsg"))
//                .andDo(print());
//    }

//    @Test
//    @DisplayName("getInfo(pj_id) 프로젝트 아이디가 null")
//    void getInfoInputNull() throws Exception {
//
//        given(service.getEditingProject(any())).willReturn(ProjectDto.builder().pj_id(null).build());
//
//        mockMvc.perform(get("/editor/info"))
//                .andExpect(status().isOk())
//                .andExpect(forwardedUrl("project/clientError"))
//                .andExpect(model().attributeExists("errorMsg"))
//                .andDo(print());
//    }

//    @Test
//    @DisplayName("getInfo(pj_id) pj_id로 조회되는 프로젝트가 없을때")
//    void project_not_find() throws Exception {
//        이어서 작성하기를 눌렀는데 조회되는 프로젝트가 없는 경우.
//        프로젝트 아이디로 조회되는 프로젝트가 없다.(비정상)
//        given(service.getProjectBasicInfo(any())).willThrow(ProjectNofFoundException.class);
//
//        mockMvc.perform(get("/editor/info").sessionAttr("pj_id",pj_id))
//                .andExpect(status().isOk())
//                .andExpect(forwardedUrl("project/clientError"))
//                .andExpect(model().attributeExists("errorMsg"))
//                .andDo(print());
//    }

    @Test
    @DisplayName("getInfo() 유저가 편집중인 프로젝트를 가져온다.")
    void getEditingProject() throws Exception {
        given(service.getEditingProject(any())).willReturn(ProjectDto.builder().pj_id(pj_id).build());


        mockMvc.perform(get(BASE_URL+ "/info").sessionAttr("user_email", "dbswoi"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("basicInfo"))
                .andExpect(forwardedUrl("project.basicInfo"))
                .andDo(print());
    }

    @Test
    @DisplayName("makeProject() 비로그인 유저가 지금 시작하기 버튼 클릭")
    void unLogined_new_project() throws Exception {
        mockMvc.perform(post(BASE_URL + "/info"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("project/clientError"))
                .andExpect(model().attributeExists("errorMsg"))
                .andDo(print());
    }

    @Test
    @DisplayName("makeProject() 새로운 프로젝트 생성을 실패했다.")
    void makeProject() throws Exception {
        ProjectAddRequest addRequest = ProjectAddRequest.builder().user_email(user_id).build();

        given(service.add(addRequest)).willThrow(ProjectAddFailureException.class);
        given(service.getEditingProject(any())).willReturn(ProjectDto.builder().pj_id(pj_id).build());

        mockMvc.perform(post(BASE_URL + "/info").sessionAttr("user_email", user_id))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("errorMsg"))
                .andExpect(forwardedUrl("project/clientError"))
                .andDo(print());

    }

    @Test
    @DisplayName("makeProject() 새로운 프로젝트 생성 성공")
    void new_project_success() throws Exception {
        ProjectAddRequest addRequest = ProjectAddRequest.builder().user_email(user_id).build();

        projectDto = ProjectDto.builder().pj_id(pj_id).build();
        given(service.add(addRequest)).willReturn(projectDto);
        given(service.getEditingProject(any())).willReturn(ProjectDto.builder().pj_id(pj_id).build());

        mockMvc.perform(post(BASE_URL + "/info").param("user_email", user_id).sessionAttr("user_email", user_id))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/project/editor/info"))
                .andDo(print());
    }

    @Test
    @DisplayName("makeProject() 프로젝트 생성 후 세션에 pj_id가 저장된다.")
    void sessionCheck() throws Exception {

        projectDto = ProjectDto.builder().pj_id("01").build();

        given(service.add(any())).willReturn(projectDto);
        given(service.getEditingProject(any())).willReturn(ProjectDto.builder().pj_id(pj_id).build());

        ResultMatcher rm = getSessionChecker("pj_id", pj_id);

        mockMvc.perform(post(BASE_URL + "/info").param("user_email", "dbswo").sessionAttr("user_email", user_id))
                .andExpect(status().is3xxRedirection())
                .andExpect(rm)
                .andDo(print());
    }

    private ResultMatcher getSessionChecker(String key, String value) {
        ResultMatcher rm = new ResultMatcher() {
            @Override
            public void match(MvcResult result) throws Exception {
                HttpSession session = result.getRequest().getSession(false);
                String value = (String) session.getAttribute(key);
                // 세션에 있는 값 검증
                assertThat(value).isEqualTo(value);
            }
        };
        return rm;
    }

    @Test
    @DisplayName("updateBasicInfo() 업데이트 요청에 프로젝트아이디가 없다.")
    void 업데이트요청에프로젝트아이디가없다() throws Exception {
        mockMvc.perform(multipart(BASE_URL + "/infoUpdate")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .param("pj_id", ""))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("errorMsg"))
                .andExpect(view().name("project/clientError"))
                .andDo(print());
    }

    @Test
    @DisplayName("updateBasicInfo() 정상적으로 업데이트가 수행됐을경우")
    void 업데이트성공() throws Exception {

        given(service.getEditingProject(any())).willReturn(ProjectDto.builder().pj_id(pj_id).build());

        given(service.update(any())).willReturn(any());

        mockMvc.perform(multipart(BASE_URL + "/infoUpdate")
                        .sessionAttr("user_email", "dbswo")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .param("pj_id", "01"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"))
                .andDo(print());
    }

    @Test
    @DisplayName("updateBasicInfo() 업데이트 대상 프로젝트를 찾지 못했다.")
    void 업데이트실패() throws Exception {
        given(service.getEditingProject(any())).willReturn(ProjectDto.builder().pj_id(pj_id).build());
        given(service.update(any())).willThrow(ProjectUpdateFailureException.class);

        ProjectInfoUpdateRequest updateRequest = ProjectInfoUpdateRequest.builder().pj_id("01").build();
        byte[] json = objectMapper.writeValueAsBytes(updateRequest);

        mockMvc.perform(multipart(BASE_URL + "/infoUpdate")
                        .sessionAttr("user_email", "dbswo")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .param("pj_id", pj_id)
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("false"))
                .andDo(print());
    }

    @Test
    @DisplayName("이미지 파일 업로드 테스트")
    void imgSaveFailureExceptionTest() throws Exception {
//        given(FileUploader.uploadFile(any())).willThrow(ImageSaveFailureException.class);
        given(service.getEditingProject(any())).willReturn(ProjectDto.builder().pj_id(pj_id).build());

        byte[] mockImg = new byte[10];

        MockMultipartFile file = new MockMultipartFile("image", "스크린샷_2012.png", "image/png", mockImg);

        mockMvc.perform(multipart("/project/editor/info/image").file(file).sessionAttr("user_email","dbswo")).andDo(print());

    }
}

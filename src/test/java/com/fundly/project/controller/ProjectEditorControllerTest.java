package com.fundly.project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fundly.project.service.ProjectService;
import com.persistence.dto.ProjectDto;
import com.persistence.dto.ProjectInfo;
import com.persistence.dto.ProjectInfoUpdateRequest;
import com.persistence.dto.ProjectInfoUpdateResponse;
import config.RootContext;
import config.ServletContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class ProjectEditorControllerTest {
    @Mock
    ProjectService service;
    @InjectMocks
    ProjectEditorController projectEditorController;
    MockMvc mockMvc;
    ObjectMapper objectMapper;
    private ProjectInfoUpdateRequest request;
    private ProjectInfoUpdateResponse response;
    private String pj_id;
    private byte[] requestJson;
    private String user_id;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        mockMvc = MockMvcBuilders.standaloneSetup(projectEditorController).build();
        objectMapper = new ObjectMapper();

        pj_id = "01";
        user_id = "mulgom";
        request = ProjectInfoUpdateRequest.builder().pj_id(pj_id).build();
        response = ProjectInfoUpdateResponse.builder().pj_id(pj_id).build();
        requestJson = objectMapper.writeValueAsBytes(request);
    }

    @Test
    void contextLoad() {
    }

    @Test
    @DisplayName("브라우저로부터 info update 요청받기")
    void info() throws Exception {

        given(service.updatePjInfo(any())).willReturn(response);
        mockMvc.perform(patch("/editor/info").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(status().isOk()).andExpect(jsonPath("$.pj_id").value(pj_id)).andDo(print());
    }

    @Test
    @DisplayName("업데이트 요청에 프로젝트 아이디가 포함되어있지 않다")
    void validationNull() throws Exception {
        request.setPj_id(null);
        requestJson = objectMapper.writeValueAsBytes(request);

        mockMvc.perform(patch("/editor/info").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(status().isBadRequest()).andDo(print());
    }
    @Test
    @DisplayName("편집중인 프로젝트 기본정보 탭을 불러온다.")
    void get_basicInfo() throws Exception {
        mockMvc.perform(get("/editor/info").param("user_id",""))
//                .andExpect(model().hasErrors())
                .andExpect(forwardedUrl("project.errorPage"))
                .andExpect(model().attributeExists("error"))
                .andDo(print());
    }

//    @Test
//    @DisplayName("유저가 편집중인 프로젝트를 가져온다.")
//    void getEditingProject() throws Exception {
////        유저 아이디를 받는다.
////        유저의 편집중인 프로젝트를 가져온다.
//        ProjectInfo projectInfo = ProjectInfo.builder().pj_id(pj_id).build();
//
//        given(service.getProjectInfo(user_id)).willReturn(projectInfo);
////        해당 프로젝트를 리턴한다.
//
//        mockMvc.perform(get("/editor/info").param("user_id", user_id))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.pj_id").value(pj_id))
//                .andDo(print());
//    }
//    @Test
//    @DisplayName("유저가 편집중인 프로젝트가 존재하지 않는다.")
//    void user_hasNo_editing_project() throws Exception {
//        mockMvc.perform(get("/editor/info").param("user_id", user_id))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.pj_id").value(pj_id))
//                .andDo(print());
//    }

//    @Test
//    @DisplayName("로그인 안한 유저가 프로젝트 에디터에 접속한다.")
//    void unloginedUserJoinEditor() throws Exception {
//        mockMvc.perform(get("/editor/info"))
//                .andExpect(status().isBadRequest())
//                .andDo(print());
//
//        mockMvc.perform(get("/editor/info").param("user_id", ""))
//                .andExpect(status().isBadRequest())
//                .andDo(print());
//    }


}

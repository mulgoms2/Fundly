package com.fundly.project.controller;

import com.fundly.project.service.ProjectService;
import com.persistence.dto.ProjectDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Slf4j
@ExtendWith(MockitoExtension.class)
class ProjectMainControllerTest {

    @Mock
    ProjectService projectService;
    @InjectMocks
    ProjectMainController projectMainController;

    MockMvc mockMvc;
    public static final String endPoint = "/project";

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(projectMainController)
                .build();
    }

    @Test
    void test() throws Exception {
        mockMvc.perform(get(endPoint + "/test"))
                .andExpect(content().string("test"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("프로젝트를 카테고리별로 받아온다.")
    void getPjByCategory() throws Exception {
        List<ProjectDto> pjList = new ArrayList<>();

        ProjectDto pj1 = ProjectDto.builder()
                .pj_id("01")
                .pj_intro("helo")
                .fund_goal_money(BigInteger.valueOf(30000000L))
                .curr_fund_money(BigInteger.valueOf(300000000L))
                .build();

        pjList.add(pj1);
        given(projectService.getListByCategory(any())).willReturn(pjList);

        mockMvc.perform(get(endPoint + "/helo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isArray())
                .andDo(print());
    }
}
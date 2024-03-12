package com.fundly.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fundly.user.dto.UserJoinDto;
import com.fundly.user.service.JoinService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class JoinControllerTest {
    private MockMvc mockMvc;

    @Mock
    private JoinService joinService;
    @InjectMocks
    private JoinController joinController;

    private ObjectMapper objectMapper;

    @BeforeEach
    @DisplayName("유저 컨트롤 테스트 설정")
    public void setMockMvc(){
        this.objectMapper = new ObjectMapper();
        this.mockMvc = MockMvcBuilders.standaloneSetup(joinController).build();
    }

    @Test
    @SneakyThrows
    @DisplayName("유저 회원가입 화면 이동 테스트")
    void 유저_회원가입_화면_이동_테스트() {
        // when
        mockMvc.perform(get("/join/add"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("user/join"));
    }

    @Test
    @SneakyThrows
    @DisplayName("유저 회원가입 입력값 오류")
    void 유저_회원가입_입력값_오류(){

        //given
        UserJoinDto userJoinDto = UserJoinDto.builder()
                .user_email("invalidemail")
                .build();

        //when - then
        mockMvc.perform(post("/join/add")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("userJoinDto",objectMapper.writeValueAsString(userJoinDto)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/join/add"))
                .andDo(print());
    }

    @Test
    @SneakyThrows
    @DisplayName("유저 회원가입 테스트")
    void 유저_회원가입_테스트(){

        // when- then
        mockMvc.perform(post("/join/add")
                .contentType(MediaType.APPLICATION_JSON)
                // given
                .param("user_name", "초코")
                .param("user_id","1")
                .param("user_join_date","2024-01-01")
                .param("user_email", "123@123.com")
                .param("user_pwd","1234.qwe!")
                .param("user_pwdConfirm","1234.qwe!")
                .param("p_Info_agree_yn","g")
                .param("p_info_oth_agree_yn","d")
                .param("m_info_rcv_agree_yn","da")
                .param("user_status","ㅁ")
                .param("age_agree_yn","b")
                .param("site_term_agree_yn","e"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("/user/login"))
                .andDo(print());
    }
}
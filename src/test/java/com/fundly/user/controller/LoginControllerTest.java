package com.fundly.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fundly.user.dto.UserJoinDto;
import com.fundly.user.dto.UserLoginDto;
import com.fundly.user.exception.UserLoinFailException;
import com.fundly.user.service.JoinService;
import com.fundly.user.service.LoginService;
import com.fundly.user.service.UserProfileService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import reactor.netty.http.server.HttpServerRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class LoginControllerTest {
    private MockMvc mockMvc;
    @Mock
    private LoginService loginService;
    @Mock
    private UserProfileService userProfileService;

    @Mock
    private JoinService joinService;
    @InjectMocks
    private LoginController loginController;

    private ObjectMapper objectMapper;
    private UserJoinDto userJoinDto1;

    String cookieKey ;
    String cookieValue ;


    @BeforeEach
    @DisplayName("login controller test setting")
    public void setMockMvc(){
        this.objectMapper = new ObjectMapper();
        this.mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
        this.cookieKey = "testCookie";
        this.cookieValue = "testValue";
        this.userJoinDto1 = UserJoinDto.builder()
                .user_id("abc123@test.com")
                .user_pwd("1234qwer!")
                .user_name("홍길동")
                .user_email("abc123@test.com")
                .user_join_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .site_term_agree_yn("Y")
                .p_Info_agree_yn("Y")
                .age_agree_yn("Y")
                .p_info_oth_agree_yn("Y")
                .m_info_rcv_agree_yn("Y")
                .user_status("A").build();
    }

    @Test
    @SneakyThrows
    @DisplayName("로그인 화면 이동 테스트")
    void 로그인_화면_이동_테스트() {
        //when
        mockMvc.perform(get("/login/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/login"))
                .andDo(print());
    }

    @Test
    @SneakyThrows
    @DisplayName("로그인 입력값 오류")
    void 로그인_입력값_오류() {
        //given
        UserLoginDto userLoginDto = UserLoginDto.builder().user_email("invalid").build();

        //when - then
        mockMvc.perform(post("/login/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("userLoginDto", objectMapper.writeValueAsString(userLoginDto)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login/login"))
                .andDo(print());
    }

    @Test
    @SneakyThrows
    @DisplayName("로그인 테스트")
    void 로그인_테스트(){
        // POST 요청 시, 이메일과 비밀번호를 JSON 형식으로 전달
        mockMvc.perform(post("/login/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("user_email","test@test.com")
                        .param("user_pwd","1234qqqqq.!"))
                        .andExpect(status().is3xxRedirection())
                        .andExpect(redirectedUrl("/"))
                        .andDo(print());

    }

    // TODO : 보류
//    @Test
//    void forgetPwd() {
//    }

    @Test
    @SneakyThrows
    @DisplayName("로그 아웃 테스트")
    void 로그_아웃_테스트() {
        mockMvc.perform(get("/login/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @SneakyThrows
    @DisplayName("쿠키 set 테스트")
    void 쿠키_set_테스트() {

        int maxAge = -1;
        String path = "/";

       Cookie cookie = loginController.setCookie(cookieKey,cookieValue,maxAge,path);

        assertEquals(cookieKey, cookie.getName());
        assertEquals(cookieValue, cookie.getValue());
        assertEquals(maxAge, cookie.getMaxAge());
        assertEquals(path, cookie.getPath());
    }

    @Test
    @SneakyThrows
    @DisplayName("쿠키 get 테스트")
    void 쿠키_get_테스트() {

        HttpServletRequest request = mock(HttpServletRequest.class);
        Cookie cookie = new Cookie(cookieKey ,cookieValue);

        when(request.getCookies()).thenReturn(new Cookie[]{cookie});
        String cookieValue = loginController.getCookieValue(request, cookieKey);

        assertEquals("testValue", cookieValue);
    }

    @Test
    @SneakyThrows
    @DisplayName("쿠키 get null 테스트")
    void 쿠키_get_null_테스트() {

        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getCookies()).thenReturn(null);
        String cookieValue = loginController.getCookieValue(request, "nonExistentCookie");

        assertEquals(null, cookieValue);
    }

    @Test
    @DisplayName("전체 Exception 테스트")
    void 에러_전체_테스트() {

        try{
            throw new UserLoinFailException("비밀번호를 확인해 주세요.");
//            throw new UserLoinFailException("가입된 이메일 정보가 없습니다.");
//            throw new UserLoinFailException("회원상태가 사용할수 없는 상태입니다.");
//            throw new RuntimeException("RuntimeException 에러 발생");
//            throw new Exception("Exception 에러 발생");
        }catch (UserLoinFailException ue){
            log.debug("UserLoinFailException.getMessage() = " + ue.getMessage());
            ue.printStackTrace();
        }catch (RuntimeException re){
            log.debug("RuntimeException.getMessage() = " + re.getMessage());
            re.printStackTrace();
        }catch (Exception e){
            log.debug("Exception.getMessage() = " + e.getMessage());
            e.printStackTrace();
        }
    }
}
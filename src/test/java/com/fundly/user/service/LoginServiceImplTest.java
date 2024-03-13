package com.fundly.user.service;

import com.fundly.user.dto.UserLoginDto;
import com.fundly.user.model.UserLoginDao;
import com.persistence.dto.UserDto;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.PrivilegedAction;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
@ExtendWith(MockitoExtension.class)
class LoginServiceImplTest {

//    @Autowired
//    private UserLoginDao userLoginDao;
    @Autowired
    private LoginService loginService;
    @Mock
    private HttpSession session;

    @Mock
    private HttpServletResponse response;

    @Test
//    @SneakyThrows
    @DisplayName("로그인 서비스 & 세션 만들기(로그인)")
    void login(){
//        UserLoginDto userLoginDto = UserLoginDto.builder()
//                .user_pwd("12345q!")
//                .user_email("ab12345@test.com")
//                .build();
//
//        try {
//            UserLoginDto userinfo = loginService.Login(userLoginDto,session,response);
//
//            assertEquals(userLoginDto.getUser_email(),userinfo.getUser_email());
//        } catch (Exception e) {
////            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
    }
}
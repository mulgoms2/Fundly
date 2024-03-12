package com.fundly.user.service;

import com.fundly.user.dto.UserJoinDto;
import com.fundly.user.model.UserJoinDao;
import config.RootContext;
import config.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@Slf4j
//@Transactional
@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
class JoinServiceImplTest {
    private  JoinService joinService;
    private final UserJoinDao userJoinDao = mock(UserJoinDao.class);
    private final BCryptPasswordEncoder bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);

    @BeforeEach
    void setUp(){
        joinService = new JoinServiceImpl(userJoinDao,bCryptPasswordEncoder);
    }
    @Test
//    @SneakyThrows
    @DisplayName("이메일 중복 확인")
    void 이메일_중복_확인(){

        int value = 1;
        //이메일 체크
            UserJoinDto userJoinDto = UserJoinDto.builder().user_email("asdfd@asdf.com").build();
            given(userJoinDao.emailCheck(userJoinDto)).willReturn(value);

            int cnt = joinService.emailCheck(userJoinDto);
            assertThat(cnt).isEqualTo(value);

            verify(userJoinDao).emailCheck(userJoinDto);
    }
    @Test
    @DisplayName("회원가입 서비스 테스트")
    void 회원가입_서비스_테스트()   {

        int value = 1;

        UserJoinDto userJoinDto = UserJoinDto.builder().user_email("a12345@test.com").build();
        given(userJoinDao.insert(userJoinDto)).willReturn(value);

        int cnt = joinService.userJoin(userJoinDto);
        assertThat(cnt).isEqualTo(value);

        verify(userJoinDao).insert(userJoinDto);
    }
}
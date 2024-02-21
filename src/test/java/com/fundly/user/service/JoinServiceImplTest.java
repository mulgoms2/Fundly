package com.fundly.user.service;

import com.fundly.user.dto.UserJoinDto;
import config.RootContext;
import config.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
class JoinServiceImplTest {

//    @Autowired
//    private  UserJoinDao userJoinDao;
//    @Autowired
//    private  BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JoinService joinService;

    @Test
//    @SneakyThrows
    @DisplayName("이메일 중복 확인")
    void emailcheck(){
        UserJoinDto userJoinDto = UserJoinDto.builder()
                .user_pwd("qwerrr123!")
//                .user_email("a12345@test.com")
                .user_email("ab33c@test.com")
                .build();
        try {
            int cnt = joinService.emailCheck(userJoinDto);
            assertEquals(1,cnt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("회원가입 service 체크")
//    @SneakyThrows
    @Transactional
//    @Rollback
    void insert()   {
        UserJoinDto userJoinDto = UserJoinDto.builder()
                .user_id("a12345@test.com")
                .user_pwd("qwerrr123!")
                .user_name("테스터")
                .user_email("a123")
                .user_join_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .site_term_agree_yn("Y")
                .p_Info_agree_yn("Y")
                .age_agree_yn("Y")
                .p_info_oth_agree_yn("Y")
                .m_info_rcv_agree_yn("Y")
                .user_status("A")
                .dba_reg_id("a12345@test.com")
                .build();

        try {
            int cnt = joinService.userJoin(userJoinDto);
            assertEquals(1,cnt);
            System.out.println("cnt = " + cnt);

            userJoinDto.setUser_email("a@test.com");
            int cnt2 = joinService.emailCheck(userJoinDto);
            assertEquals(0,cnt2);
            System.out.println("cnt2 = " + cnt2);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
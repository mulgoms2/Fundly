package com.fundly.user.service;

import com.fundly.user.model.UserJoinDao;
import com.persistence.dto.UserDto;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
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
        UserDto userdto = UserDto.builder()
                .user_pwd("qwerrr123!")
//                .user_email("a12345@test.com")
                .user_email("ab33c@test.com")
                .build();
        try {
            int cnt = joinService.emailCheck(userdto);
            assertEquals(1,cnt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("회원가입 service 체크")
//    @SneakyThrows
//    @Transactional
//    @Rollback
    void insert()   {
        UserDto userdto = UserDto.builder()
                .user_id("a12345@test.com")
                .user_pwd("qwerrr123!")
                .user_name("테스터")
                .user_email("a12345@test.com")
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
            int cnt = joinService.userJoin(userdto);
            assertEquals(1,cnt);
            System.out.println("cnt = " + cnt);

            userdto.setUser_email("a@test.com");
            int cnt2 = joinService.emailCheck(userdto);
            assertEquals(1,cnt2);
            System.out.println("cnt2 = " + cnt2);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
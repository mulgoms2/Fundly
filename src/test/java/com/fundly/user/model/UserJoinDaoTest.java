package com.fundly.user.model;

import com.fundly.user.dto.UserJoinDto;
import config.RootContext;
import config.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
class UserJoinDaoTest {

    private final UserJoinDao userJoinDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserJoinDaoTest(UserJoinDao userJoinDao, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userJoinDao = userJoinDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Test
//    @SneakyThrows
    @DisplayName("회원 이메일 중복 체크")
    void emailCheck() {
        try {
//            UserJoinDto userJoinDto = UserJoinDto.builder().user_email("111@111.com").build();
            UserJoinDto userJoinDto = UserJoinDto.builder().user_email(null).build();

            int cnt = userJoinDao.emailCheck(userJoinDto);

//            assertEquals(1,cnt);
            assertEquals(0,cnt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
//    @SneakyThrows
    @DisplayName("회원가입")
    @Transactional
//    @Rollback(true)
    void insert() {
        try {
            UserJoinDto userJoinDto = UserJoinDto.builder()
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
                    .user_status("A")
//                    .dba_reg_id("abc123@test.com")
                    .build();

            String userInPwd = userJoinDto.getUser_pwd();
            String encoderPwd = bCryptPasswordEncoder.encode(userInPwd);
//            log.error("encoderPwd = " +encoderPwd);
//            log.error("\n\n");

            userJoinDto.setUser_pwd(encoderPwd);

            int cnt = userJoinDao.insert(userJoinDto);

            assertEquals(1, cnt);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
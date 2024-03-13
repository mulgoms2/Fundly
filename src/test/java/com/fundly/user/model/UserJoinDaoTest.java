package com.fundly.user.model;

import com.fundly.user.dto.UserJoinDto;
import com.fundly.user.exception.UserLoinFailException;
import config.RootContext;
import config.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Transactional
@SpringJUnitWebConfig(RootContext.class)
class UserJoinDaoTest {

    private final UserJoinDao userJoinDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    UserJoinDto userJoinDto1;
    UserJoinDto userJoinDto2;
    UserJoinDto userJoinDto3;

    @Autowired
    public UserJoinDaoTest(UserJoinDao userJoinDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userJoinDao = userJoinDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @BeforeEach
    @DisplayName("테스트 세팅")
    void setUp() {
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

        this.userJoinDto2 = UserJoinDto.builder().user_email("1233@123.com").user_pwd("test1").build();
        this.userJoinDto3 = UserJoinDto.builder().user_email("abc123@test.com").build();
    }

    @Test
//    @SneakyThrows
    @DisplayName("회원 이메일 중복 체크")
    void 회원_이메일_중복_체크() {

        userJoinDao.insert(userJoinDto1);

        assertEquals(1, userJoinDao.emailCheck(userJoinDto1));
        assertEquals(0, userJoinDao.emailCheck(userJoinDto2));
        assertEquals(1, userJoinDao.emailCheck(userJoinDto3));
    }

    @Test
//    @SneakyThrows
    @DisplayName("회원가입 테스트")
    void 회원가입_테스트() {

        String userInPwd = userJoinDto1.getUser_pwd();
        String encoderPwd = bCryptPasswordEncoder.encode(userInPwd);

        userJoinDto1.setUser_pwd(encoderPwd);
        assertEquals(1, userJoinDao.insert(userJoinDto1));
    }

    @Test
//    @SneakyThrows
    @DisplayName("회원가입 테스트 null")
    void 회원가입_테스트_null() {
        assertThrows(DataAccessException.class, () -> userJoinDao.insert(null));
    }

    @Test
//    @SneakyThrows
    @DisplayName("회원가입 테스트 dto값 null검사")
    void 회원가입_테스트_dto값_null_검사() {
        assertThrowsExactly(DataIntegrityViolationException.class, () -> userJoinDao.insert(userJoinDto2));
    }
}
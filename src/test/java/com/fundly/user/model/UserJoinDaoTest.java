package com.fundly.user.model;

import com.persistence.dto.SelBuyMsgDetailsDto;
import com.persistence.dto.UserDto;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

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
            UserDto userDto = UserDto.builder().user_email("111@111.com").build();

            int cnt = userJoinDao.emailCheck(userDto);

            assertEquals(1,cnt);
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
            UserDto userdto = UserDto.builder()
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
                    .dba_reg_id("abc123@test.com")
                    .build();

            String userInPwd = userdto.getUser_pwd();
            String encoderPwd = bCryptPasswordEncoder.encode(userInPwd);
//            log.error("encoderPwd = " +encoderPwd);
//            log.error("\n\n");

            userdto.setUser_pwd(encoderPwd);

            int cnt = userJoinDao.insert(userdto);

            assertEquals(1, cnt);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
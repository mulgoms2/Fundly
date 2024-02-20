package com.fundly.user.model;

import com.fundly.user.dto.UserLoginDto;
import com.persistence.dto.UserDto;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
class UserLoginDaoTest {

    private final UserLoginDao userLoginDao;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserLoginDaoTest(UserLoginDao userLoginDao, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userLoginDao = userLoginDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Test
//    @SneakyThrows
    @DisplayName("회원 정보 조회")
    void selectUser(){
        try {
            UserLoginDto userLoginDto = UserLoginDto.builder()
                    .user_email("abc@test.com")
                    .user_pwd("1234qwer!").build();

            UserLoginDto userinfo = userLoginDao.selectUser(userLoginDto);

            assertTrue(bCryptPasswordEncoder.matches(userLoginDto.getUser_pwd(),userinfo.getUser_pwd()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
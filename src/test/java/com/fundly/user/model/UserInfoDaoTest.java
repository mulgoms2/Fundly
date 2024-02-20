package com.fundly.user.model;

import com.persistence.dto.UserDto;
import config.RootContext;
import config.ServletContext;
import lombok.Builder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Builder
@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
class UserInfoDaoTest {

    private UserInfoDao userInfoDao;

//    public UserInfoDaoTest(){}
    @Autowired
    public UserInfoDaoTest(UserInfoDao userInfoDao){
        this.userInfoDao = userInfoDao;
    }

    @Test
//    @SneakyThrows
    @DisplayName("유저 정보 불러오기")
    void userInfo() {
        try {
            UserDto userDto = UserDto.builder().user_email("111@111.com").build();

            UserDto info = userInfoDao.userInfo(userDto);

            assertEquals(userDto.getUser_email(),info.getUser_email());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
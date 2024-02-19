package com.fundly.user.service;

import com.persistence.dto.UserDto;
import config.RootContext;
import config.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
class UserInfoServiceImplTest {

    @Autowired
    private UserInfoService userInfoService;

    @Test
    void userInfo() {
        UserDto userdto = UserDto.builder()
                .user_email("ab33c@test.com")
                .build();

        try {
            UserDto userinfo = userInfoService.userInfo(userdto);

            assertEquals(userdto.getUser_email(),userinfo.getUser_email());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
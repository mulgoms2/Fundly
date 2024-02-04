package com.fundly.user.model;

import com.persistence.dto.UserDto;
import config.RootContext;
import config.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
@Slf4j
class UserJoinDaoTest {

    @Autowired
    UserJoinDao userJoinDao;

    @Test
    void count() throws Exception {

        int cnt = userJoinDao.count();
        assertTrue(cnt==3);

    }

    @Test
    void emailCheck() throws Exception {
        int cnt = userJoinDao.emailCheck("asdf@asdf.com");
        assertTrue(cnt==1);
    }

    @Test
    void insert() throws Exception {
        for(int i = 0; i <10; i++) {
            String uuid_user_id = UUID.randomUUID().toString();
            String user_status = "A"; // 활동중

            UserDto userDto = new UserDto();
            userDto.setUser_id(uuid_user_id);
            userDto.setUser_pwd("1111");
            userDto.setUser_name("호랑이");
            userDto.setUser_email("aaa" + i + "@abc.com");
            userDto.setUser_status(user_status);
            userDto.setDba_reg_id(uuid_user_id);

            System.out.println("userDto = " + userDto);

            int cnt = userJoinDao.insert(userDto);
            System.out.println("cnt = " + cnt);
        }
    }
}
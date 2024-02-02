package com.fundly.user.model;

import com.persistence.dto.UserDto;
import config.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
@Slf4j
@Transactional
class userDaoImplTest {

    @Autowired
    private userDao userDao;


    @Test
    public void emailCheck() throws Exception {

//        System.out.println("userDao.emailCheck(\"asdf@asdf.com\") = " + userDao.emailCheck("asdf@asdf.com"));
//        int cnt = userDao.emailCheck("asdf@asdf.com");
//        log.error("cnt = " + String.valueOf(cnt));

//        assertTrue(cnt==0);
    }

    @Test
    public void joinUser(UserDto userDto) throws Exception {

        String uuid_user_id = UUID.randomUUID().toString();
        String user_status = "A"; // 활동중

        userDto.setUser_id(uuid_user_id);
        userDto.getUser_pwd();
        userDto.getUser_name();
        userDto.getUser_email();
        userDto.setUser_status(user_status);
        userDto.setDba_reg_id(uuid_user_id);

//        int cnt = userDao.insert(userDto);
//        System.out.println("cnt = " + cnt);
        System.out.println("userDto = " + userDto);

    }
}
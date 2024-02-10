package com.fundly.user.service;

import com.fundly.user.model.UserLoginDao;
import config.RootContext;
import config.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
class LoginServiceImplTest {

    @Autowired
    UserLoginDao userLoginDao;

    @Test
    void count() throws Exception {

        int cnt = userLoginDao.count();

        log.error("cnt = " +cnt);
//        assertTrue(cnt==3);
    }

//    @Test
//    void userCheck() throws Exception {
//
//        int cnt = userLoginDao.userCheck("23@23","1111");
//
//        log.info("cnt = " + cnt);
//
//        assertTrue(cnt==1);
//    }






}
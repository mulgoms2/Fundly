package com.fundly.user.model;

import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringJUnitWebConfig(classes = {RootContext.class, ServletContext.class})
class userDaoImplTest {

    @Autowired
    UserDao userDao;
    @Test
    @SneakyThrows
    void count() {
<<<<<<< HEAD
//        assertTrue(true);
=======
        userDao.count();
        assertTrue(true);
>>>>>>> 53b0c10775ed877753b13808143a39d14c26a207
    }

    @Test
    void insert() {
    }
}
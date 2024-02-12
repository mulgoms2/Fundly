package com.fundly.user.model;

import config.RootContext;
import config.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
class UserLoginDaoTest {

    @Test
    void count() {
    }

    @Test
    void emailCheck() {

    }

    @Test
    void LoginCheck() {

    }

    @Test
    void idCheck() {
    }
}
package com.fundly.user.model;

import config.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringJUnitWebConfig(classes = {RootContext.class, ServletContext.class})
class userDaoImplTest {



    @Test
    void count() {
        assertTrue(true);
    }

    @Test
    void insert() {
    }
}
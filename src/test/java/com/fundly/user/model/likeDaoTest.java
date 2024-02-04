package com.fundly.user.model;

import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
@Slf4j
class likeDaoTest {
    @Autowired
    likeDao likeDao;

    @Test
    @SneakyThrows
    @DisplayName("테스트코드")
    void test1() {
        likeDao.test();
        assertTrue(true);
    }
}
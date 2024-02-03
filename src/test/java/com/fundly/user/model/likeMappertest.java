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

@Slf4j
@SpringJUnitWebConfig(classes = {RootContext.class, ServletContext.class})
class likeMappertest {
    @Autowired
    likeMapper likeMapper;

    @Test
    @SneakyThrows
    @DisplayName("매퍼읽어오는테스트")
    void test() {
        likeMapper.test();
        assertTrue(true);
    }
}
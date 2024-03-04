package com.fundly.project.service;

import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

@Slf4j
@SpringJUnitWebConfig(classes = {RootContext.class, ServletContext.class})
class temp {
    @Autowired
    GiftService giftService;

    @Test
    @SneakyThrows
    void test1() {
        int rowCnt = giftService.test("86027cb9-2701-4110-8245-46f3f63eb289");
        log.error("rowCnt={}",rowCnt);

    }

}
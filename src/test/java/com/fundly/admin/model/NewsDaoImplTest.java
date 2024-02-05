package com.fundly.admin.model;

import config.RootContext;
import config.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringJUnitWebConfig(classes = {RootContext.class, ServletContext.class})
class NewsDaoImplTest {

    @Autowired
    NewsDao newsDao;

    @Test
    void selectAll() throws Exception {

        newsDao.selectAll();
    }
}
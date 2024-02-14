package com.fundly.admin.model;

import com.fundly.admin.service.NewsService;
import config.RootContext;
import config.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringJUnitWebConfig(classes = {RootContext.class, ServletContext.class})

class NewsDaoTest {

    @Autowired
    NewsDao newsDao;

//
//    @Autowired
//    NewsDao NewsDao;

    @Test
    void count()throws Exception {

//        System.out.println("newsDao = " + newsDao);
        log.error(String.valueOf(newsDao.count()));

    }
}
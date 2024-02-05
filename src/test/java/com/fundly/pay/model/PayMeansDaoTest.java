package com.fundly.pay.model;

import config.RootContext;
import config.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

@Slf4j
@SpringJUnitWebConfig(classes = { RootContext.class, ServletContext.class })
class PayMeansDaoTest {

    @Autowired PayMeansDao payMeansDao;

    @Test
    void count() throws Exception {
        payMeansDao.count();
        log.error(""+payMeansDao.count());
    }
}
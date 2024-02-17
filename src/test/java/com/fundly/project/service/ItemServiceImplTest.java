package com.fundly.project.service;

import config.RootContext;
import config.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

@Slf4j
@SpringJUnitWebConfig(classes = {RootContext.class, ServletContext.class})
class ItemServiceImplTest {
    @Autowired
    ItemService itemService;



}
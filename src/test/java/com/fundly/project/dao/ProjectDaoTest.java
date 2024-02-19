package com.fundly.project.dao;

import com.fundly.project.model.ProjectDao;
import config.RootContext;
import config.ServletContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

@SpringJUnitWebConfig(classes = {RootContext.class})
public class ProjectDaoTest {

    @Autowired
    ProjectDao dao;

    @BeforeEach
    void setUp() {

    }
}

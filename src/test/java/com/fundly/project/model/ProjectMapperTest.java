package com.fundly.project.model;

import config.RootContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitWebConfig(classes = RootContext.class)
class ProjectMapperTest {
    @Autowired
    ProjectMapper projectMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("전체 삭제 검사")
    void delete() {
//        전체 행을 삭제한다.
//        projectMapper.deleteAll();
    }
}
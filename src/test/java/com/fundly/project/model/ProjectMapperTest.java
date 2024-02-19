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
        int rowCount = projectMapper.deleteAll();

        assertEquals(0, rowCount);
    }

    @Test
    @DisplayName("프로젝트 하나를 생성한다.")
    void insert() {
        projectMapper.insert();
    }

}
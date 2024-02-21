package com.fundly.project.model;

import com.persistence.dto.ProjectDto;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
@Transactional
class ProjectMapperTest {

    @Autowired
    ProjectMapper pjdao;
    ProjectDto pjdto;

    @Test
    @SneakyThrows
    void getForLikesTest() {
        String pj_id = "P5040";
        pjdao.getForLikes(pj_id);
    }

    @Test
    @SneakyThrows
    void changeLikeCntTest() {
//        pjdto = new ProjectDto("P5040");
        pjdao.changeLikeCnt(pjdto);
    }
}
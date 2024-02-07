package com.fundly.user.service;

import com.fundly.user.model.LikeDao;
import com.persistence.dto.LikeDto;
import com.persistence.dto.ProjectDto;
import com.persistence.dto.UserDto;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
class likeServiceImplTest{
    @Autowired
    LikeDao likedao;
    UserDto userdto;
    ProjectDto pjdto;

    @Test
    @SneakyThrows
    void insertLikeTest(Map map) {
//        likedto = new LikeDto("bada","P001");
//        likedto.setUser_id("bada");
//        likedto.setPj_id("P001");
//        map.put("user_id", userdto.getUser_id());
//        map.put("pj_id", pjdto.getPj_id());
//        int result = likedao.insertLike(map);
//        assertTrue(result==1);
    }
}
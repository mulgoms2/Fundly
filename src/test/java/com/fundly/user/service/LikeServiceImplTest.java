package com.fundly.user.service;

import com.fundly.user.model.LikeDao;
//import com.persistence.dto.ChatRoomDto;
import com.persistence.dto.LikeDto;
import config.RootContext;
import config.ServletContext;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@Slf4j
@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
class LikeServiceImplTest {

    @Mock
    LikeDao likedao;
    @InjectMocks
    LikeService likeservice;


    @Test
    @SneakyThrows
    void transJasonTest() {

        LikeDto likedto = new LikeDto();

//        Mockito.when(likedao.getLike(likedto)).thenReturn("ok");

        //TODO : 가져온 데이터에서 datetime인 like_dtm을 jason.parse 하는 로직 필요


    }

    @Test
    void changeLike() {
    }

    @Test
    void getLike() {
    }

    @Test
    void getLikeList() {
    }
}
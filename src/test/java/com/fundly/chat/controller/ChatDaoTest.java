package com.fundly.chat.controller;


import com.fundly.chat.model.ChatRoomDao;
import config.RootContext;
import config.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Transactional
@SpringJUnitWebConfig(classes = {RootContext.class, ServletContext.class})
public class ChatDaoTest {
    @Autowired
    ChatRoomDao chatRoomDao;

    @Test
    @BeforeEach
    public void testSetting() {
//        chatRoomDao.
    }


}
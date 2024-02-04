package com.fundly.chat.controller;

import com.fundly.chat.model.ChatRoomDao;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

@Slf4j
@SpringJUnitWebConfig(classes = {RootContext.class, ServletContext.class})
class ChatControllerTest {

    @Autowired
    ChatRoomDao chatRoomDao;

    @Test
    @SneakyThrows
    void greeting() {
        chatRoomDao.selectChatRoom("test", "testPj");

//        chatRoomDao.test("test");
//       log.error("\n\n\n" + chatRoomDto);


    }
}
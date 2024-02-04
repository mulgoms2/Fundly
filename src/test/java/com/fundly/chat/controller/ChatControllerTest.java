<<<<<<< HEAD:src/test/java/com/fundly/user/model/userDaoImplTest.java
//
//package com.fundly.user.model;
//
//import config.RootContext;
//import config.ServletContext;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@Slf4j
//@SpringJUnitWebConfig(classes = {RootContext.class, ServletContext.class})
//class userDaoImplTest {
//
//    @Autowired
//    userDao userDao;
//
//    @Test
//    @SneakyThrows
//    void count() {
//        userDao.count();
//        assertTrue(true);
//    }
//
//    @Test
//    void insert() {
//    }
//}
=======
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
>>>>>>> ecafd709e797faacfbd4198e7c4d856eb914677b:src/test/java/com/fundly/chat/controller/ChatControllerTest.java

package com.fundly.chat.model;

import com.fundly.chat.model.SelBuyMsgDao;
import com.persistence.dto.ChatRequest;
import com.persistence.dto.ChatRequest.ChatRequestBuilder;
import com.persistence.dto.ChatRoomDto;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@SpringJUnitWebConfig(classes = {RootContext.class, ServletContext.class})
public class SelBuyMsgDaoTest {

    @Autowired
    SelBuyMsgDao selBuyMsgDao;

    ChatRequest chatRequest;

    @SneakyThrows
    @BeforeEach
    public void setTest() {
        selBuyMsgDao.deleteAllChatRoom();

//         this.chatRequest = new ChatRequest();




//        ChatRequest chatRequest1 = ChatRequest.builder()
//                .helo(231)
//                .pj_id("123")
//                .user_id("hehe")
//                .build();

        chatRequest.setUser_id("asdf");
        chatRequest.setPj_id("1234");

        assert (selBuyMsgDao.makeChatRoom(chatRequest) == 1);
    }

    @SneakyThrows
    @Test
    @DisplayName("채팅방불러오기")
    public void selectChatRoomTest() {
//        given

        ChatRoomDto chatRoomDto = selBuyMsgDao.selectChatRoom(chatRequest);

        assert (chatRoomDto != null);

//        when

//        then

    }
}

package com.fundly.chat.model;

import com.fundly.chat.entity.SelbuyMsgEntity;
import com.persistence.dto.ChatRequest;
import com.persistence.dto.SelBuyMsgDto;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

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

        assertEquals(0, selBuyMsgDao.count());

        chatRequest = ChatRequest.builder()
                .pj_id("asdf")
                .buy_id("123")
                .build();

        assertEquals(1, selBuyMsgDao.makeChatRoom(chatRequest));
    }

    @SneakyThrows
    @Test
    @DisplayName("채팅방불러오기")
    public void selectChatRoomTest() {
//        given
//        when
        SelbuyMsgEntity selbuyMsgEntity = selBuyMsgDao.selectChatRoom(chatRequest);
//        then

        assertNotNull(selbuyMsgEntity);

        SelBuyMsgDto selBuyMsgDto = selbuyMsgEntity.toSelBuyMsgDto();

        assertEquals(selBuyMsgDto.getBuy_id(), "123");
        assertEquals(selBuyMsgDto.getPj_id(), "asdf");
    }
}

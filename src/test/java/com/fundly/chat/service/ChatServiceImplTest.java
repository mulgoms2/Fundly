package com.fundly.chat.service;

import com.fundly.chat.model.SelBuyMsgDao;
import com.persistence.dto.ChatRequest;
import com.persistence.dto.ChatRoomDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;


class ChatServiceImplTest {
    SelBuyMsgDao selBuyMsgDao;

    ChatService chatService;
    @SneakyThrows
    @BeforeEach
    void setMock() {
        ChatRequest chatRequest = ChatRequest.builder().pj_id("asdf").user_id("dbswo").build();

        ChatRoomDto chatRoomDto = ChatRoomDto.builder().room_num(1).user_id("dbswo").pj_id("asdf").build();

        this.selBuyMsgDao = Mockito.mock(SelBuyMsgDao.class);

        Mockito.when(selBuyMsgDao.selectChatRoom(chatRequest)).thenReturn(chatRoomDto);

        this.chatService = new ChatServiceImpl(selBuyMsgDao);
    }
    @Test
    void getChatRoom() {
        ChatRequest chatRequest = ChatRequest.builder().pj_id("asdf").user_id("dbswo").build();

         chatService.getChatRoom(chatRequest);
    }

    @Test
    void saveMessage() {
    }

    @Test
    void loadMessages() {
    }

    @Test
    void isFileAttached() {
    }

    @Test
    void saveImageFile() {
    }

    @Test
    void loadImgFile() {
    }
}
package com.fundly.chat.service;

import com.fundly.chat.model.SelBuyMsgDao;
import com.fundly.chat.model.SelBuyMsgDetailsDao;
import com.persistence.dto.ChatRequest;
import com.persistence.dto.ChatRoomDto;
import com.persistence.dto.SelBuyMsgDetailsDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ChatServiceImplTest {
    @Mock
    SelBuyMsgDao selBuyMsgDao;
    @Mock
    SelBuyMsgDetailsDao selBuyMsgDetailsDao;
    @InjectMocks
    ChatServiceImpl chatService;
    @SneakyThrows
    @BeforeEach
    void setMock() {
        ChatRequest chatRequest = ChatRequest.builder().pj_id("asdf").user_id("dbswo").build();
        ChatRoomDto chatRoomDto = ChatRoomDto.builder().room_num(1).user_id("dbswo").pj_id("asdf").build();

        Mockito.when(selBuyMsgDao.selectChatRoom(chatRequest)).thenReturn(chatRoomDto);
//        Mockito.when(selBuyMsgDetailsDao.loadAllMessages(chatRoomDto)).thenReturn(new ArrayList<SelBuyMsgDetailsDto>());

//        this.chatService = new ChatServiceImpl(selBuyMsgDao);
//        this.
    }
    @Test
    void getChatRoom() {
        ChatRequest chatRequest = ChatRequest.builder().pj_id("asdf").user_id("dbswo").build();
//
        chatService.getChatRoom(chatRequest);
//
        Integer roomNum = chatRequest.getChatRoomDto().getRoom_num();
//
        assertEquals(1, roomNum);
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
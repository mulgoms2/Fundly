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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//@SpringJUnitWebConfig(classes = )
@ExtendWith(MockitoExtension.class)
class ChatServiceImplTest {
    @Mock
    SelBuyMsgDao selBuyMsgDao;
    @Mock
    SelBuyMsgDetailsDao selBuyMsgDetailsDao;
    @InjectMocks
    ChatServiceImpl chatService;

    @Test
    void getChatRoom() {
        this.chatService = new ChatServiceImpl();

        SelBuyMsgDao selBuy = Mockito.mock(SelBuyMsgDao.class);

        Mockito.when(selBuy.deleteAllChatRoom()).thenReturn(20);

        int i = selBuy.deleteAllChatRoom();

        System.out.println(i);
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
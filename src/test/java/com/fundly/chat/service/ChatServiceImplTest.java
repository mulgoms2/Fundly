package com.fundly.chat.service;

import com.fundly.chat.dao.SelBuyMsgDao;
import com.fundly.chat.repository.ChatRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

//@SpringJUnitWebConfig(classes = )
@ExtendWith(MockitoExtension.class)
class ChatServiceImplTest {

    @Mock
    ChatRepository chatRepository;

    @InjectMocks
    ChatServiceImpl chatService;

    @Test
    void getChatRoom() {

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
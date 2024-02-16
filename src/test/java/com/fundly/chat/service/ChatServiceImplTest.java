package com.fundly.chat.service;

import com.fundly.chat.model.SelBuyMsgDao;
import com.fundly.chat.model.SelBuyMsgDetailsDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

//@SpringJUnitWebConfig(classes = )
@ExtendWith(MockitoExtension.class)
class ChatServiceImplTest {

    @InjectMocks
    ChatServiceImpl chatService;

    @Test
    void getChatRoom() {

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
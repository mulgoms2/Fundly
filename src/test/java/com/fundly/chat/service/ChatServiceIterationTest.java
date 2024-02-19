package com.fundly.chat.service;

import com.fundly.chat.dao.SelBuyMsgDao;
import com.fundly.chat.dao.SelBuyMsgDetailsDao;
import com.fundly.chat.repository.ChatRepository;
import com.persistence.dao.FileDao;
import com.persistence.dto.ChatRequest;
import com.persistence.dto.FileDto;
import com.persistence.dto.SelBuyMsgDetailsDto;
import com.persistence.dto.SelBuyMsgDto;
import config.RootContext;
import config.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Transactional
@SpringJUnitWebConfig(classes = {ServletContext.class, RootContext.class})
class ChatServiceIterationTest {
    @Autowired
    FileDao fileDao;
    @Autowired
    SelBuyMsgDao selBuyMsgDao;
    @Autowired
    SelBuyMsgDetailsDao selBuyMsgDetailsDao;

    @Autowired
    ChatRepository chatRepository;
    @Autowired
    ChatService chatService;

    @BeforeEach
    void setUp() {
        fileDao.deleteAll();
        selBuyMsgDao.deleteAllChatRoom();
        selBuyMsgDetailsDao.deleteAll();

        assertEquals(0, fileDao.count());
        assertEquals(0, selBuyMsgDao.count());
        assertEquals(0, selBuyMsgDetailsDao.count());
    }

    @Test
    void joinChatRoom() {
        //given
        ChatRequest chatRequest1 = ChatRequest.builder().pj_id("asdf").buy_id("mulgom").build();
        ChatRequest chatRequest2 = ChatRequest.builder().pj_id("asdf").buy_id("mulgom").build();

        //when
        SelBuyMsgDto chatRoom1 = chatService.joinChatRoom(chatRequest1);
        SelBuyMsgDto chatRoom2 = chatService.joinChatRoom(chatRequest1);

        //then
        assertNotNull(chatRoom1);
        assertEquals(chatRoom1, chatRoom2);
    }

    @Test
    void saveMessage() {
        // given
        SelBuyMsgDetailsDto message1 = SelBuyMsgDetailsDto.builder().room_num(1L).msg_cont("hello").build();
        SelBuyMsgDetailsDto message2 = SelBuyMsgDetailsDto.builder().room_num(null).msg_cont("hi").build();

        //when
        boolean b = chatService.saveMessage(message1);
//        채팅방 번호가 없는 메시지가 넘겨지면 저장되지 않아야 한다.

        assertThrows(RuntimeException.class, () -> {
            chatService.saveMessage(message2);
        }, "방번호 null인 메시지를 저장하는데 예외가 발생하지 않았습니다.");

        //then
        assertEquals(true, b);
    }

    @Test
    void saveFileMessage() {
        //given
        MultipartFile file = (MultipartFile) new File("myFile");
        FileDto fileDto = FileDto.builder().file(file).build();
        SelBuyMsgDetailsDto message = SelBuyMsgDetailsDto.builder().room_num(1L).pj_id("asdf").buy_id("123").build();

        // when
        chatService.saveFileMessage(fileDto, message);


    }
}
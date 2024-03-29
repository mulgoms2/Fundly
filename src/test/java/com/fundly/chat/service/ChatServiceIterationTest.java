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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Transactional
@SpringJUnitWebConfig(classes = {RootContext.class})
class ChatServiceIterationTest {
    @Autowired
    ApplicationContext ac;

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

    FileDto file;
    SelBuyMsgDetailsDto message;

    @BeforeEach
    void setUp() {
        fileDao.deleteAll();
        selBuyMsgDao.deleteAllChatRoom();
        selBuyMsgDetailsDao.deleteAll();

        assertEquals(0, fileDao.count());
        assertEquals(0, selBuyMsgDao.count());
        assertEquals(0, selBuyMsgDetailsDao.count());

        this.file = FileDto.builder().table_name("MSG_TABLE").table_key("msg1").file_saved_url("url").build();
        this.message = SelBuyMsgDetailsDto.builder().room_num(1L).pj_id("asdf").buy_id("123").build();
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
    @DisplayName("파일 저장시 메세지에 파일 경로 세팅되는지 확인")
    void setFileUrlTest() {
        FileDto file1 = FileDto.builder().table_name("MSG_TABLE").table_key("msg1").file_saved_url("url").build();
        SelBuyMsgDetailsDto message1 = SelBuyMsgDetailsDto.builder().room_num(1L).pj_id("asdf").buy_id("123").build();

        chatService.saveFileMessage(file1, message1);

        String fileUrl = message1.getFile_url();
        assertEquals(message1.getFile_url(), fileUrl);
    }

    @Test
    @DisplayName("파일이 null일때 검사")
    void saveNullFile() {
        //given

        SelBuyMsgDetailsDto message = SelBuyMsgDetailsDto.builder().msg_cont("hello").room_num(1l).build();
//        매개변수가 널일때
        String errorMsg = assertThrows(NullPointerException.class,
                () -> {
                    chatService.saveFileMessage(null, message);
                }
        ).getMessage();

        assertEquals("is empty file", errorMsg);

        String errorMsg2 = assertThrows(NullPointerException.class,
                () -> chatService.saveFileMessage(file, null)
        ).getMessage();

        assertEquals("is empty message", errorMsg2);
    }

    @Test
    @DisplayName("파일 저장 테스트")
    void saveFileMessage() {
        //given
        FileDto file1 = FileDto.builder().table_name("MSG_TABLE").table_key("msg1").build();
        FileDto file2 = FileDto.builder().table_name("MSG_TABLE").table_key("msg1").build();
        SelBuyMsgDetailsDto message1 = SelBuyMsgDetailsDto.builder().room_num(1L).pj_id("asdf").buy_id("123").build();
        SelBuyMsgDetailsDto message2 = SelBuyMsgDetailsDto.builder().room_num(2L).pj_id("dddd").buy_id("333").build();

        // when
//        파일을 저장했다.
        chatService.saveFileMessage(file1, message1);
        chatService.saveFileMessage(file2, message2);

//        저장한 파일을 불러온다
    }
}
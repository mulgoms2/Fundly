package com.fundly.chat.repository;

import com.fundly.chat.model.SelBuyMsgDao;
import com.fundly.chat.model.SelBuyMsgDetailsDao;
import com.fundly.chat.service.ChatService;
import com.persistence.dto.ChatRequest;
import com.persistence.dto.SelBuyMsgDetailsDto;
import com.persistence.dto.SelBuyMsgDto;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Transactional
@SpringJUnitWebConfig(classes = {ServletContext.class, RootContext.class})
class ChatRepositoryImplTest {

    @Autowired
    SelBuyMsgDao selBuyMsgDao;
    @Autowired
    SelBuyMsgDetailsDao selBuyMsgDetailsDao;
    @Autowired
    ChatService chatService;
    @Autowired
    @Qualifier("chatRepositoryImpl")
    ChatRepository chatRepository;

    ChatRequest request;

    @BeforeEach
    @DisplayName("테스트 세팅")
    void testSetting() {
        selBuyMsgDao.deleteAllChatRoom();
        selBuyMsgDetailsDao.deleteAll();
        assertEquals(0, selBuyMsgDao.count());
        assertEquals(0, selBuyMsgDetailsDao.count());

        request = ChatRequest.builder().pj_id("123").buy_id("asdf").build();
    }

    @Test
    @SneakyThrows
    @DisplayName("채팅방 저장후 가져오기 검사")
    void getChatRoom() {
        //given

//        채팅방을 생성한다.
        SelBuyMsgDto selBuyMsgDto = chatService.joinChatRoom(request);

//        채팅방에 메시지를 저장한다.
        SelBuyMsgDetailsDto message = SelBuyMsgDetailsDto.builder().room_num(selBuyMsgDto.getRoom_num()).msg_cont("hello").build();
        chatService.saveMessage(message);
        //when
        SelBuyMsgDto chatRoom = chatRepository.getChatRoom(request);

        //then
        assertNotNull(chatRoom);
        assertEquals(false, chatRoom.getMessage_list().isEmpty());
    }

    @Test
    @DisplayName("채팅방 만들기 검사")
    void makeChatRoom() throws Exception {
        // given
        ChatRequest request = ChatRequest.builder().pj_id("123").buy_id("asdf").build();

//        when
        chatRepository.makeChatRoom(request);

//        then
        SelBuyMsgDto chatRoom = chatRepository.getChatRoom(request);

        assertEquals(request.getBuy_id(), chatRoom.getBuy_id());
    }

    @Test
    @DisplayName("채팅 메시지 불러오기")
    void loadAllMessages() throws Exception {
        //when
        SelBuyMsgDto selBuyMsgDto = chatRepository.makeChatRoom(request);

//        메시지 리스트를 만들어서 넣는다.

        for (int i = 0; i < 100; i++) {
            SelBuyMsgDetailsDto message = SelBuyMsgDetailsDto.builder().room_num(selBuyMsgDto.getRoom_num()).msg_cont("helo" + i).build();
            chatService.saveMessage(message);
        }

//        then
        List<SelBuyMsgDetailsDto> result = chatRepository.loadAllMessages(selBuyMsgDto);

        assertEquals(100, result.size());
    }

    @Test
    @DisplayName("메시지 저장 검사")
    void saveMessage() throws Exception {
        selBuyMsgDao.makeChatRoom(request);
        SelBuyMsgDto selBuyMsgDto = selBuyMsgDao.selectChatRoom(request);

        SelBuyMsgDetailsDto message = SelBuyMsgDetailsDto.builder().room_num(selBuyMsgDto.getRoom_num()).msg_cont("hello").build();

        int i = chatRepository.saveMessage(message);

        assertEquals(1, i);
    }

    @Test
    @DisplayName("메시지 로딩시 시간 매핑하기")
    void time() throws Exception {
//        사진만 첨부한 메시지도 정상적으로 시간이 불러와지는지 확인한다.
        //when
        SelBuyMsgDto selBuyMsgDto = chatRepository.makeChatRoom(request);
//        메시지 리스트를 만들어서 넣는다.

        for (int i = 0; i < 100; i++) {
            SelBuyMsgDetailsDto message = SelBuyMsgDetailsDto.builder().room_num(selBuyMsgDto.getRoom_num()).msg_cont("helo" + i).build();
            chatService.saveMessage(message);
        }

        List<SelBuyMsgDetailsDto> selBuyMsgDetailsDtos = chatRepository.loadAllMessages(selBuyMsgDto);

        boolean b = selBuyMsgDetailsDtos.stream().peek(e-> System.out.println(e+"\n\n")).anyMatch(message -> !message.getSvr_intime_string().isEmpty());

        assertEquals(true, b);
    }
}
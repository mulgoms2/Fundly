
package com.fundly.chat.controller;

import com.fundly.chat.model.ChatRoomDao;
import com.fundly.chat.service.ChatService;
import com.persistence.dto.ChatRoomDto;
import com.persistence.dto.SelBuyMsgDetailsDto;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.ArrayList;

@Slf4j
@SpringJUnitWebConfig(classes = { RootContext.class, ServletContext.class })
// @Transactional
class ChatControllerTest {

  @Autowired
  ChatRoomDao chatRoomDao;

  @Autowired
  ChatService chatService;

  @Test
  @SneakyThrows
  void greeting() {
    chatRoomDao.selectChatRoom("test", "testPj");

    System.out.println("chatService = " + chatService);

    ChatRoomDto room = chatService.findRoom("test", "testPj");

    assert (room != null);

  }

  @Test
  @DisplayName("메시지저장")
  void makeMessage() {
    SelBuyMsgDetailsDto selBuyMsgDetailsDto = new SelBuyMsgDetailsDto();
    selBuyMsgDetailsDto.setPj_id("testPj");
    selBuyMsgDetailsDto.setMsg_cont("안녕ㅇ하세요 ^^ ");

    selBuyMsgDetailsDto.setBuy_id("test");
    for (int i = 0; i < 20; i++) {
      chatService.saveMessage(selBuyMsgDetailsDto);
    }
  }

  @Test
  @DisplayName("메시지불러오기")
  @SneakyThrows
  void loadMsg() {
    ArrayList<SelBuyMsgDetailsDto> selBuyMsgDetailDtos = chatRoomDao.loadAllMessages("test", "testPj");

    System.out.println("selBuyMsgDetails = " + selBuyMsgDetailDtos);
  }
}

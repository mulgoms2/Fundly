
package com.fundly.chat.controller;

import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.http.HttpSession;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringJUnitWebConfig(classes = {ServletContext.class, RootContext.class})
class ChatControllerTest {

  @Autowired
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  @BeforeEach
  @DisplayName("테스트 사전설정")
  public void setMockMvc(@Autowired HttpSession session) {
    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter("UTF-8");

    mockMvc = MockMvcBuilders
            .webAppContextSetup(webApplicationContext)
            .addFilter(characterEncodingFilter, "/*")
            .build();

    session.setAttribute("user_email" , "dbswoi123@naver.com");
  }

  @Test
  @SneakyThrows
  @DisplayName("채팅방 get 메시지 수신 검사")
  void chatTest() {

    mockMvc.perform(get("/chat")
            .param("asdf", "1234"))
            .andExpect(status().isOk());

//    chatRoomDao.selectChatRoom("test", "testPj");

//    System.out.println("chatService = " + chatService);

//    ChatRoomDto room = chatService.findRoom("test", "testPj");
//
//    assert (room != null);

  }

  @Test
  @DisplayName("메시지저장")
  void makeMessage() {
//    SelBuyMsgDetailsDto selBuyMsgDetailsDto = new SelBuyMsgDetailsDto();
//    selBuyMsgDetailsDto.setPj_id("testPj");
//    selBuyMsgDetailsDto.setMsg_cont("안녕ㅇ하세요 ^^ ");
//
//    selBuyMsgDetailsDto.setBuy_id("test");
//    for (int i = 0; i < 20; i++) {
//      chatService.saveMessage(selBuyMsgDetailsDto);
//    }
  }

  @Test
  @DisplayName("메시지불러오기")
  @SneakyThrows
  void loadMsg() {
//    ArrayList<SelBuyMsgDetailsDto> selBuyMsgDetailDtos = chatRoomDao.loadAllMessages("test", "testPj");
//
//    System.out.println("selBuyMsgDetails = " + selBuyMsgDetailDtos);
  }
}

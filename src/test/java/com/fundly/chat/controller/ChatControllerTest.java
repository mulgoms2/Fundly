
package com.fundly.chat.controller;

import com.persistence.dto.ChatRequest;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.http.HttpSession;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

//    session.setAttribute("user_email" , "dbswoi123@naver.com");
  }

  @Test
  @SneakyThrows
  @DisplayName("채팅방 getChat메서드")
  void getChatRoom() {
    mockMvc.perform(get("/chat")
                    .param("mulgom", "1234"))
            .andExpect(status().isOk())
            .andDo(print());

  }

  @Test
  @DisplayName("게시글 업로드 : 게시글 업로드시 Image 파일은 필수입니다")
  void uploadPostImageTest() throws Exception {

    //given

    //when
    mockMvc.perform(multipart( "/chat/file")
                    .file("image", null)
            )
            .andExpect(status().isBadRequest())
            .andDo(print());

    //then
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


package com.fundly.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.http.HttpSession;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringJUnitWebConfig(classes = {ServletContext.class, RootContext.class})
class ChatControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    HttpSession session;

    @BeforeEach
    @DisplayName("테스트 사전설정")
    public void setMockMvc(@Autowired HttpSession session) {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter("UTF-8");
        this.objectMapper = new ObjectMapper();

        this.session = session;

        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(characterEncodingFilter, "/*")
                .build();

    }
    @Test
    @DisplayName("리턴값을 이용하여 테스트하는 방법")
    void getTestReturn() throws Exception {
        // api 요청
        MvcResult mvcResult = mockMvc
                .perform(
                        get("/api/mock/getTest") // url
                                .param("name", "myName") // parameter
                                .param("value", "myValue") // parameter
                )
                .andDo(print())
                .andReturn();

    }
    @Test
    @SneakyThrows
    @DisplayName("채팅방 getChat메서드")
    void getChatRoom() {

        // 세션에 로그인 정보가 존재해야 한다.
//    session.setAttribute("user_email", "mulgom");


        mockMvc.perform(
                        get("/chatPop")
                                .param("user_id", "mulgom")
                                .param("pj_id", "1234"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("로그인 안한 사용자가 채팅방을 접속할때 에러 페이지로 이동한다.")
    void unLoginedUserJoin() throws Exception {
        mockMvc.perform(get("/chatPop")
                        .param("buy_id", "")
                        .param("pj_id", "1234"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("게시글 업로드 : 게시글 업로드시 Image 파일은 필수입니다")
    void uploadPostImageTest() throws Exception {

        //given

        //when
//    mockMvc.perform(multipart( "/chat/file")
//                    .file("image", null)
//            )
//            .andExpect(status().isBadRequest())
//            .andDo(print());

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
    void loadMsg() {
//    ArrayList<SelBuyMsgDetailsDto> selBuyMsgDetailDtos = chatRoomDao.loadAllMessages("test", "testPj");
//
//    System.out.println("selBuyMsgDetails = " + selBuyMsgDetailDtos);
    }
}

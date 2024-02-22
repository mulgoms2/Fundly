
package com.fundly.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fundly.chat.service.ChatService;
import com.persistence.dto.SelBuyMsgDto;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ExtendWith(MockitoExtension.class)
@SpringJUnitWebConfig(classes = {ServletContext.class, RootContext.class})
class ChatControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @Mock
    private ChatService chatService;

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
    @SneakyThrows
    @DisplayName("채팅방 getChat메서드")
    void getChatRoom() {
//        조건 : Get요청의 파라미터로 구매자 id와 프로젝트id를 전달한다.
        String buy_id = "";
        String pj_id = "1234";
        SelBuyMsgDto chatRoom = SelBuyMsgDto.builder().buy_id(buy_id).pj_id(pj_id).build();

        when(chatService.joinChatRoom(any())).thenReturn(chatRoom);

        mockMvc.perform(
                        get("/chatPop")
                                .param("buy_id", null)
                                .param("pj_id", pj_id))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andDo(print());

    }

//    @Test
//    @DisplayName("로그인 안한 사용자가 채팅방을 접속할때 에러 페이지로 이동한다.")
//    void unLoginedUserJoin() throws Exception {
//
//        ChatRequest emptyRequest = ChatRequest.builder().buy_id("").pj_id("").build();
//
//        mockMvc.perform(get("/chatPop")
//                        .param("buy_id", "")
//                        .param("pj_id", "1234"))
//                .andExpect(status().isBadRequest())
//                .andDo(print());
//    }

//    @Test
//    @DisplayName("게시글 업로드 : 게시글 업로드시 Image 파일은 필수입니다")
//    void uploadPostImageTest() throws Exception {

        //given

        //when
//    mockMvc.perform(multipart( "/chat/file")
//                    .file("image", null)
//            )
//            .andExpect(status().isBadRequest())
//            .andDo(print());

        //then
//    }

//    @Test
//    @DisplayName("메시지저장")
//    void makeMessage() {
//    SelBuyMsgDetailsDto selBuyMsgDetailsDto = new SelBuyMsgDetailsDto();
//    selBuyMsgDetailsDto.setPj_id("testPj");
//    selBuyMsgDetailsDto.setMsg_cont("안녕ㅇ하세요 ^^ ");
//
//    selBuyMsgDetailsDto.setBuy_id("test");
//    for (int i = 0; i < 20; i++) {
//      chatService.saveMessage(selBuyMsgDetailsDto);
//    }
//    }

//    @Test
//    @DisplayName("메시지불러오기")
//    void loadMsg() {
////    ArrayList<SelBuyMsgDetailsDto> selBuyMsgDetailDtos = chatRoomDao.loadAllMessages("test", "testPj");
////
////    System.out.println("selBuyMsgDetails = " + selBuyMsgDetailDtos);
//    }
}

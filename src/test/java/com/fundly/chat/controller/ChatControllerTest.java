
package com.fundly.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fundly.chat.service.ChatService;
import com.fundly.user.model.UserLoginDao;
import com.persistence.dto.ChatRequest;
import com.persistence.dto.SelBuyMsgDto;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Slf4j
//@WebMvcTest(ChatController.class)
//@AutoConfigureMockMvc
//@SpringBootTest(classes = {ServletContext.class, RootContext.class})
@SpringJUnitWebConfig(classes = {ServletContext.class, RootContext.class})
class ChatControllerTest {
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    UserLoginDao dao;

//    @Autowired
//    private MockMvc mockMvc;

//    @MockBean
//    private ChatService chatService;
    private ObjectMapper objectMapper;


    @BeforeEach
    @DisplayName("테스트 사전설정")
    public void setMockMvc() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    @SneakyThrows
    @DisplayName("레스트 컨트롤러 요청 응답 테스트")
    void getChatRoom() {
//        채팅 요청을 보내면
        String pj_id = "asdf";
        String buy_id = "dbswo";
        long room_num = 1L;
        ChatRequest chatRequest = ChatRequest.builder().pj_id(pj_id).buy_id(buy_id).build();
        SelBuyMsgDto chatRoom = SelBuyMsgDto.builder().room_num(room_num).pj_id(pj_id).buy_id(buy_id).build();

//        채팅방을 응답 받는다
//        given(chatService.joinChatRoom(any())).willReturn(chatRoom);
//        when(chatService.joinChatRoom(any())).thenReturn(chatRoom);

//        mockMvc.perform(
//                        post("/chat/test")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsBytes(chatRequest))
//                )
//                .andExpect(jsonPath("$.name").value("mulgom"))
//                .andExpect(status().isOk())
//                .andDo(print());

//        mockMvc.perform(get("/chat/")
//                ).andExpect(content().string("chat/asyncAwait"));
    }

//    @Test
//    @DisplayName("로그인 안한 사용자가 채팅방을 접속할때 에러 페이지로 이동한다.")
//    void unLoginedUserJoin() throws Exception {
//
//        ChatRequest emptyRequest = ChatRequest.builder().buy_id("").pj_id("").build();
//        objectMapper.writeValueAsBytes(emptyRequest);
//
//        mockMvc.perform(get("/chatPop")
//                        .param("buy_id", "")
//                        .param("pj_id", ""))
//                .andExpect(status().isOk())
//                .andExpect(content().string("chat/error"))
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

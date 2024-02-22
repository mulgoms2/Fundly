
package com.fundly.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fundly.chat.service.ChatService;
import com.persistence.dto.ChatRequest;
import com.persistence.dto.SelBuyMsgDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class ChatControllerTest {
    private MockMvc mockMvc;
    @Mock
    ChatService chatService;
    @InjectMocks
    ChatController chatController;
    private ObjectMapper objectMapper;
    @BeforeEach
    @DisplayName("테스트 사전설정")
    public void setMockMvc() {
        this.objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(chatController).build();
    }

    @Test
    @SneakyThrows
    @DisplayName("레스트 컨트롤러 json 요청보내기. json 응답 받기")
    void test() {
//        채팅 요청을 보내면
        String pj_id = "aaaaaadf";
        String buy_id = "dbswo";
        long room_num = 3L;
        ChatRequest chatRequest = ChatRequest.builder().pj_id("hehe").buy_id(buy_id).build();
        SelBuyMsgDto chatRoom = SelBuyMsgDto.builder().room_num(room_num).pj_id(pj_id).buy_id(buy_id).build();

//        채팅방을 응답 받는다
        given(chatService.joinChatRoom(any())).willReturn(chatRoom);
//        when(chatService.joinChatRoom(any())).thenReturn(chatRoom);

        mockMvc.perform(
                        post("/chat/test")
                               .contentType(MediaType.APPLICATION_JSON)
//                                objectMapper는 json data binder이다. 객체를 Json 문자열로 직렬화 해주는 메서드를 호출해서 컨텐트로 제공했다.
                                .content(objectMapper.writeValueAsBytes(chatRequest))
                )
//                json 으로 넘겨받은 응답을 검증하기 위한 어썰션이다.
                .andExpect(status().isOk())
//                json 표현식에 대해서는 인터넷을 검색해보자.
                .andExpect(jsonPath("$.room_num").value(room_num))
                .andExpect(jsonPath("$.pj_id").value(pj_id))
                .andExpect(jsonPath("$.buy_id").value(buy_id))
                .andDo(print());
    }
    @Test
    @DisplayName("정상적으로 로그인된 유저가 채팅방에 접속한다.")
    void getChatRoom() throws Exception {
        ChatRequest chatRequest = ChatRequest.builder().buy_id("dbswo").pj_id("1234").build();
        SelBuyMsgDto chatRoom = SelBuyMsgDto.builder().room_num(1L).pj_id(chatRequest.getPj_id()).buy_id(chatRequest.getBuy_id()).build();

        mockMvc.perform(get("/chatPop")
                        .param("buy_id", chatRequest.getBuy_id())
                        .param("pj_id", chatRequest.getPj_id()))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("chat/chat"))
                .andDo(print());
    }

    @Test
    @DisplayName("로그인이 안된 유저가 채팅방에 접속하려고 한다.")
    void chatPop() throws Exception {
        ChatRequest unLoginedRequest = ChatRequest.builder().buy_id("").pj_id("").build();
        SelBuyMsgDto chatRoom = SelBuyMsgDto.builder().room_num(1L).pj_id(unLoginedRequest.getPj_id()).buy_id(unLoginedRequest.getBuy_id()).build();

        mockMvc.perform(get("/chatPop")
                        .param("buy_id", unLoginedRequest.getBuy_id())
                        .param("pj_id", unLoginedRequest.getPj_id()))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("chat/error"))
                .andDo(print());
    }
}

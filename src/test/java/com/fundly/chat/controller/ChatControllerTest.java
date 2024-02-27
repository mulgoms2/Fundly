
package com.fundly.chat.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
//@SpringJUnitWebConfig(classes = {ServletContext.class, RootContext.class})
class ChatControllerTest {
    StompSessionHandler sessionHandler;
    private MockMvc mockMvc;
    @Mock
    ChatService chatService;
    @InjectMocks
    ChatController chatController;
    private ObjectMapper objectMapper;
    ChatRequest chatRequest;
    SelBuyMsgDto chatRoom;

    @BeforeEach
    @DisplayName("테스트 사전설정")
    public void setMockMvc() {
        this.objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(chatController).build();
    }


    @SneakyThrows
    @DisplayName("레스트 컨트롤러 json 요청보내기. json 응답 받기")
    void test() {
//        채팅 요청을 보내면
        String pj_id = "aaaaaadf";
        String buy_id = "dbswo";
        long room_num = 3L;
        chatRequest = ChatRequest.builder().pj_id(pj_id).buy_id(buy_id).build();
        chatRoom = SelBuyMsgDto.builder().room_num(room_num).pj_id(pj_id).buy_id(buy_id).build();

//        채팅방을 응답 받는다
        given(chatService.joinChatRoom(any())).willReturn(chatRoom);

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
//        로그인한 유저가 채팅방에 접속한다.
        chatRequest = ChatRequest.builder().buy_id("dbswo").pj_id("1234").build();
        chatRoom = SelBuyMsgDto.builder().room_num(1L).pj_id(chatRequest.getPj_id()).buy_id(chatRequest.getBuy_id()).build();

//        서비스 계층에서 채팅방을 발급받는다.
        given(chatService.joinChatRoom(any())).willReturn(chatRoom);

//        채팅방을 채팅 요청 객체에 담는다.
        chatRequest.setSelBuyMsgDto(chatRoom);

//        모델로 전달한 채팅방을 확인한다.
        mockMvc.perform(get("/chatPop")
                        .param("buy_id", chatRequest.getBuy_id())
                        .param("pj_id", chatRequest.getPj_id()))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("chat/chat"))
                .andExpect(model().attributeExists())
                .andExpect(model().attribute("chatRequest", chatRequest))
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
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasErrors("chatRequest"))
                .andExpect(model().attributeHasFieldErrors("chatRequest"))
                .andExpect(forwardedUrl("chat/error"))
                .andDo(print());
    }

//    @Test
//    @DisplayName("채팅 입력받기")
//    void chat() throws Exception {
//        WebSocketStompClient stompClient = new WebSocketStompClient(new SockJsClient(
//                Collections.singletonList(new WebSocketTransport(new StandardWebSocketClient()))));
//
//        String wsEndpoint = "ws://localhost:8080/endPoint"; // Your WebSocket endpoint
//        ListenableFuture<StompSession> future = stompClient.connect(wsEndpoint, sessionHandler);
//
//        StompSession session = future.get(5, TimeUnit.SECONDS);
//
//        // Prepare your STOMP message here
//        String message = "{\"msg_cont\":\"Hello, world!\"}";
//
//        // Send your STOMP message
//        session.send("/chatPub/chat/1", message.getBytes());
//
//        // For example, if you expect HTTP status 200
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/chatSub/1"))
//                .andReturn();
//        assertEquals(200, result.getResponse().getStatus());
//    }

}

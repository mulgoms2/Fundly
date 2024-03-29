//package com.fundly.apiTest;
//
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.persistence.dto.SelBuyMsgDetailsDto;
//import com.persistence.dto.SelBuyMsgDto;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.*;
//import org.springframework.messaging.converter.MappingJackson2MessageConverter;
//import org.springframework.messaging.simp.stomp.*;
//import org.springframework.util.concurrent.ListenableFuture;
//import org.springframework.web.socket.client.standard.StandardWebSocketClient;
//import org.springframework.web.socket.messaging.WebSocketStompClient;
//
//import java.lang.reflect.Type;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeoutException;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@Slf4j
//public class StompClientTest {
//    /*
//     * 학습 목적의 테스트 코드입니다
//     * 스톰프 클라이언트 작성을 위한 테스트 코드
//     * */
//    WebSocketStompClient stompClient;
//    StompSessionHandler stompSessionHandler;
//    StompSession stompSession;
//    ObjectMapper objectMapper;
//
//    @BeforeEach
//    @DisplayName("스톰프 클라이언트 인스턴스 초기화")
//    public void setUp() throws ExecutionException, InterruptedException, TimeoutException {
//        this.stompClient = new WebSocketStompClient(new StandardWebSocketClient());
//
////        추상클래스 임시 구현체
//        this.stompSessionHandler = new StompSessionHandlerAdapter() {
//        };
//
//        String webSocketEndPoint = "ws://localhost:8080/endPoint";
//
//        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
//        this.objectMapper = messageConverter.getObjectMapper();
//        stompClient.setMessageConverter(messageConverter);
//
//        ListenableFuture<StompSession> connect = stompClient.connect(webSocketEndPoint, stompSessionHandler);
//
////        타임아웃 익셉션 적용
//        this.stompSession = connect.get(2, TimeUnit.SECONDS);
//    }
////    @AfterEach
////    public void closeSesion() {
////        stompClien
////    }
//
//    @Test
//    @DisplayName("토픽에 메시지 보내고 응답 받기")
//    public void stompClient() throws ExecutionException, InterruptedException, JsonProcessingException, TimeoutException {
//        SelBuyMsgDetailsDto message = SelBuyMsgDetailsDto.builder().room_num(1L).buy_id("dbswo").pj_id("123").msg_cont("hello").build();
//        boolean connected = stompSession.isConnected();
//        assertThat(connected).isEqualTo(true);
//
//        stompSession.send("/chatPub/chat/10", message);
//
//        SelBuyMsgDetailsDto responseMessage = getResponse();
//
//        assertThat(responseMessage.getMsg_cont()).isEqualTo(message.getMsg_cont());
//    }
//
//    private SelBuyMsgDetailsDto getResponse() throws ExecutionException, InterruptedException, TimeoutException {
//        CompletableFuture<SelBuyMsgDetailsDto> subscribeFuture = new CompletableFuture<>();
//        stompSession.subscribe("/chatSub/1", new StompFrameHandler() {
//            //            페이로드(내용물)가 역직렬화 될 타입을 지정한다.
//            @Override
//            public Type getPayloadType(StompHeaders headers) {
//                return SelBuyMsgDetailsDto.class;
//            }
//
//            //            메시지를 비동기적으로 받아 처리한다.
//            @Override
//            public void handleFrame(StompHeaders headers, Object payload) {
//                subscribeFuture.complete((SelBuyMsgDetailsDto) payload);
//            }
//        });
//
//        return subscribeFuture.get(3, TimeUnit.SECONDS);
//    }
//
//    @Test
//    @DisplayName("채팅방 번호가 없이 채팅을 발송한다.")
//    public void sendToEmptyRoom() throws ExecutionException, InterruptedException, TimeoutException {
//        SelBuyMsgDetailsDto message = SelBuyMsgDetailsDto.builder().room_num(1L).pj_id("123").buy_id("dbswo").build();
////        메시지에 채팅방 번호가 없다.
////        채팅방 번호가 없으면 실제로는 예외가 발생하지 않는다. 메시지와 매핑된 메서드가 없기 때문이다.
////        stompSession.send("/chatPub/chat/" + message.getRoom_num(), message);
//        stompSession.send("/chatPub/chat/", message);
//
//        Assertions.assertThrows(TimeoutException.class, () -> getResponse());
//    }
//
//    @Test
//    @DisplayName("채팅방 권한이 없는 유저가 메시지 발송시 예외발생")
//    public void sendUserValidation() {
//        SelBuyMsgDetailsDto message = SelBuyMsgDetailsDto.builder().pj_id("123").send_user_id("hi").msg_cont("hello").build();
//        stompSession.send("/chatPub/chat/1", message);
//    }
//}

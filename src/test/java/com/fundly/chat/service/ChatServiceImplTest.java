package com.fundly.chat.service;

import com.fundly.chat.dao.ChatRoomDao;
import com.fundly.chat.model.ChatRoom;
import com.fundly.chat.model.ChatRoomRequest;
import com.fundly.chat.model.ChatRoomResponse;
import org.apache.coyote.BadRequestException;
import org.apache.ibatis.javassist.NotFoundException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class ChatServiceImplTest {

    @Mock
    ChatRoomDao chatRoomDao;

    @InjectMocks
    ChatServiceTestImpl chatServiceTest;
    ChatRoomRequest chatRoomRequest;

    @BeforeEach
    void setUp() {
        chatRoomRequest = ChatRoomRequest.builder().build();
    }

    @Test
    @DisplayName("기존 채팅방이 존재하면 기존 채팅방을 리턴한다.")
    void getChatRoom2() {
        Long room_num = 1L;

        //given
        ChatRoomRequest request1 = ChatRoomRequest.builder().user_id("dbswo").to_user_id("mulgom").build();
        given(chatRoomDao.findRoomNum(request1.getUser_id(), request1.getTo_user_id())).willReturn(room_num);
        given(chatRoomDao.getRoom(any())).willReturn(ChatRoom.builder().room_num(room_num).build());
        //when
        ChatRoom chatRoom = chatServiceTest.getChatRoom(request1);
        //then
        assertNotNull(chatRoom);
        assertEquals(room_num, chatRoom.getRoom_num());
        room_num++;

        //given
        ChatRoomRequest request2 = ChatRoomRequest.builder().user_id("dbddddswo").to_user_id("maaulgom").build();
        given(chatRoomDao.findRoomNum(request2.getUser_id(), request2.getTo_user_id())).willReturn(room_num);
        given(chatRoomDao.getRoom(any())).willReturn(ChatRoom.builder().room_num(room_num).build());
        //when
        ChatRoom chatRoom2 = chatServiceTest.getChatRoom(request2);
        //then
        assertEquals(room_num, chatRoom2.getRoom_num());
    }

    @Test
    @DisplayName("채팅방이 없는 요청에 대해 새로운 채팅방을 리턴한다.")
    void getChatRoom1() {
        //     조건:   getChatRoom()이 호출될 때 dao가 채팅방 요청에 대해 null 을 리턴
        String fromUser = "mulgom";
        String toUser = "dbswo";
        ChatRoom savedChatRoom = ChatRoom.builder().room_num(1L).from_user_id(fromUser).to_user_id(toUser).build();
        chatRoomRequest.setUser_id(fromUser);
        chatRoomRequest.setTo_user_id(toUser);

//        채팅 방 번호가 없을때 getChatRoom 내부적으로 makeChatRoom 이 호출된다.
//        makeChatRoom 은 요청을 통해 새로운 채팅방을 만들고 db에 저장 후 반환한다.
        given(chatRoomDao.findRoomNum(any(), any())).willReturn(null);
        given(chatRoomDao.save(chatRoomRequest.toEntity())).willReturn(savedChatRoom);
        ChatRoom chatRoom = chatServiceTest.getChatRoom(chatRoomRequest);

//        새로운 채팅방은 항상 리턴되어야 하며 null 이어서는 안된다.
//        새로운 채팅방에도 채팅을 보내는이와 받는이에 대한 정보가 있어야한다.
        assertThat(chatRoom).isNotNull();
        assertThat(chatRoom.getFrom_user_id()).isEqualTo(chatRoomRequest.getUser_id());
        assertThat(chatRoom.getTo_user_id()).isEqualTo(chatRoomRequest.getTo_user_id());
    }

    @Test
    @DisplayName("부정조건 테스트")
    void nullInput() throws BadRequestException {
//        채팅방을 들어갈때 채팅요청이 유실되서 없다!
//        요청이 잘못되었다고 메시지를 전달해야한다.
        assertThatExceptionOfType(BadRequestException.class).isThrownBy(() -> chatServiceTest.enter(null)).withMessage("잘못 된 요청입니다. 다시 시도해주세요");
    }

    @Test
    @DisplayName("새롭게 만들어진 채팅방의 번호는 기존의 채팅방과 겹치지 않는다.")
    void getChatRoomTest() {
        //     조건:   getChatRoom()이 호출될 때 dao가 채팅방 요청에 대해 null 을 리턴할때 makeChatRoom() 이 내부적으로 호출된다.
        Long maxRoomNum = 4L;
        given(chatRoomDao.findRoomNum(any(), any())).willReturn(null);
//        makeChatRoom()가 호출될 때 기존의 채팅방 번호를 조회해 가장 큰 채팅방 번호를 리턴한다.
//        given(chatRoomDao.maxRoomNum()).willReturn(maxRoomNum);
        given(chatRoomDao.save(chatRoomRequest.toEntity())).willReturn(ChatRoom.builder().room_num(maxRoomNum + 1L).build());

        ChatRoom chatRoom = chatServiceTest.getChatRoom(chatRoomRequest);

//        dao.maxRoomNum(); 을 호출해서 + 1L 을 하면 새로운 방 번호가 만들어 질 것이다.
        assertEquals(maxRoomNum + 1L, chatRoom.getRoom_num());
    }

    @Test
    @DisplayName("유저가 채팅방에 접속한다.")
    void enter() throws BadRequestException {
//        유저로부터 채팅방 접속 요청이 들어온다.
//        조건 : 다음의 채팅방 요청을 받았을 때
        Long room_num = 3L;
        ChatRoomRequest chatRoomRequest = ChatRoomRequest.builder().user_id("mulgom").to_user_id("dbswo").build();
        ChatRoom chatRoom = ChatRoom.builder().room_num(room_num).from_user_id(chatRoomRequest.getUser_id()).to_user_id(chatRoomRequest.getTo_user_id()).build();
//        유저가 기존의 채팅방을 가지고있다.
        given(chatRoomDao.findRoomNum(chatRoomRequest.getUser_id(), chatRoomRequest.getTo_user_id())).willReturn(room_num);
        given(chatRoomDao.getRoom(room_num)).willReturn(chatRoom);
//        행위 : 채팅방에 접속하려고 하면
        ChatRoomResponse chatRoomResponse = chatServiceTest.enter(chatRoomRequest);

//        결과: 채팅방응답 객체를 리턴받는다.
//        해당 객체에는 room_num, from_user, to_user, 채팅방 정보가 담겨있어야 한다.
        Assert.assertNotNull(chatRoomResponse);
        assertEquals(room_num, chatRoomResponse.getRoom_num());
//        요청으로 받은 유저의 아이디와. 채팅방 응답객체의 fromUserId 가 일치해야한다.
        assertThat(chatRoomResponse.getFrom_user_id()).isEqualTo(chatRoomRequest.getUser_id());
//        요청으로 받은 받는이의 아이디와, 채팅방 응답객체의 to_user_id가 같아야한다.
        assertThat(chatRoomResponse.getTo_user_id()).isEqualTo(chatRoomRequest.getTo_user_id());

        verify(chatRoomDao).findRoomNum(chatRoomRequest.getUser_id(), chatRoomRequest.getTo_user_id());
        verify(chatRoomDao).getRoom(room_num);
    }

    @Test
    @DisplayName("makeChatRoom entity 테스트")
    public void entity() {
        ChatRoomRequest chatRoomRequest = ChatRoomRequest.builder().user_id("mulgom").to_user_id("dbswo").build();
        ChatRoom chatRoom = ChatRoom.builder().room_num(2L).from_user_id(chatRoomRequest.getUser_id()).to_user_id(chatRoomRequest.getTo_user_id()).build();

        given(chatRoomDao.save(chatRoomRequest.toEntity())).willReturn(chatRoom);
//        채팅방 요청을 전달받아 db에 채팅방을 insert하고 저장된 채팅방을 돌려준다.
        ChatRoom resultChatRoom = chatServiceTest.makeChatRoom(chatRoomRequest);

        assertThat(resultChatRoom).isEqualTo(chatRoom);
    }
}
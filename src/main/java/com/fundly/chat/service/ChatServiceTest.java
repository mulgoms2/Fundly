package com.fundly.chat.service;

import com.fundly.chat.model.ChatRoom;
import com.fundly.chat.model.ChatRoomRequest;
import com.fundly.chat.model.ChatRoomResponse;
import org.apache.coyote.BadRequestException;

public interface ChatServiceTest {
    ChatRoomResponse enter(ChatRoomRequest chatRoomRequest) throws BadRequestException;

    ChatRoom getChatRoom(ChatRoomRequest request);
}

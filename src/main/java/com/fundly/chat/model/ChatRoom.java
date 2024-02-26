package com.fundly.chat.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class ChatRoom {
    private Long room_num;
    private String from_user_id;
    private String to_user_id;
    private LocalDateTime dba_reg_dtm;

    public static ChatRoomResponse of(ChatRoom chatRoom) {
        return ChatRoomResponse.builder().room_num(chatRoom.room_num).from_user_id(chatRoom.from_user_id).to_user_id(chatRoom.to_user_id).build();
    }
}

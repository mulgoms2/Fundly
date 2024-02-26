package com.fundly.chat.model;


import lombok.*;

@Data
@Builder
public class ChatRoomResponse {
    private Long room_num;
    private String from_user_id;
    private String to_user_id;
}

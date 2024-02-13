package com.persistence.dto;

import lombok.Data;

@Data
public class ChatRequest {
   private ChatRoomDto chatRoomDto;
   private String user_id;
   private String pj_id;
}

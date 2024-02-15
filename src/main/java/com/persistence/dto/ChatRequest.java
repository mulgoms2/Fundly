package com.persistence.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatRequest {

   private ChatRoomDto chatRoomDto;

   private String user_id;

   private String pj_id;

}

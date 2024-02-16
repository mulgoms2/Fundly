package com.persistence.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class ChatRequest {

   private ChatRoomDto chatRoomDto;

   @NotNull
   private String user_id;

   @NotNull
   private String pj_id;

}

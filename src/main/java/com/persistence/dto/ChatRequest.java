package com.persistence.dto;

//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.NotNull;
import lombok.Data;

import javax.validation.constraints.NotNull;

//import javax.validation.constraints.NotNull;

@Data
public class ChatRequest {


   private ChatRoomDto chatRoomDto;

   private String user_id;

   private String pj_id;

   @NotNull
   private Integer helo;

}

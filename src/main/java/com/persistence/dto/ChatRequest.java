package com.persistence.dto;

//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.*;

//import javax.validation.constraints.NotNull;

@Data
public class ChatRequest {

   @Valid
   private ChatRoomDto chatRoomDto;

   @Email
   private String user_id;

   @Min(10)
   private String pj_id;

   @NotNull
   private Integer helo;

}

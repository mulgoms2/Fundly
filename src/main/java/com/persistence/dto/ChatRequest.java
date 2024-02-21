package com.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {

   private SelBuyMsgDto selBuyMsgDto;

   @NotEmpty(message = "로그인 후 이용해주세요.")
   private String buy_id;

   @NotNull()
   private String pj_id;

}

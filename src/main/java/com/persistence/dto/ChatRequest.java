package com.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {

   private SelBuyMsgDto selBuyMsgDto;

   @NotNull
   private String buy_id;

   @NotNull
   private String pj_id;

}

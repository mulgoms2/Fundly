package com.persistence.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class ChatRequest {

   private SelBuyMsgDto selBuyMsgDto;

   @NotNull
   private String buy_id;

   @NotNull
   private String pj_id;

}

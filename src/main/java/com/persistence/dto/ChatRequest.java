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

   @NotEmpty(message = "바보래요")
   private String buy_id;

   @NotNull(message = "바보보")
   private String pj_id;

}

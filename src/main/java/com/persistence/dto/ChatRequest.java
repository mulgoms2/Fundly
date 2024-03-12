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

    // 채팅방 접속 요청자의 id를 받는다.
    @NotEmpty(message = "로그인 후 이용해주세요.")
    private String buy_id;

    /* 프로젝트 아이디와 회원의 아이디를 통해 채팅방을 식별할 수 있다. */
    @NotNull()
    private String pj_id;

}

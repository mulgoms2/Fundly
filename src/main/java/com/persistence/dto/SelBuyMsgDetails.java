package com.persistence.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SelBuyMsgDetails {

    private String msgId;
    private String buyId;
    private String pjId;
    private Integer seq;
    private String sendUserId;
    private Date svrIntime;
    private String msgReadYn;
    private String msgCont;
    private Date dbaRegDtm;
    private String dbaRegId;
    private Date dbaModDtm;
    private String dbaModId;
    private Integer fileCnt;
}

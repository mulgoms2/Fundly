package com.persistence.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SelBuyMsgDetailsDto {
    private String msg_id;
    private String buy_id;
    private String pj_id;
    private Integer seq;
    private String send_user_id;
    private Date svr_intime;
    private String msg_read_yn;
    private String msg_cont;
    private Date dba_reg_dtm;
    private String dba_reg_id;
    private Date dba_mod_dtm;
    private String dba_mod_id;
    private int file_cnt;
    private String file_url;
}

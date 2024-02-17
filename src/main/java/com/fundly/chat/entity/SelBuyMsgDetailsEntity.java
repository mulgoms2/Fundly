package com.fundly.chat.entity;

import com.persistence.dto.SelBuyMsgDetailsDto;

import java.util.Date;

public class SelBuyMsgDetailsEntity {
    private Integer seq;
    private Long room_num;
    private String pj_id;
    private String buy_id;
    private String msg_id;
    private String send_user_id;
    private String msg_cont;
    private Date svr_intime;
    private String msg_read_yn;
    private int file_cnt;
    private Date dba_reg_dtm;
    private String dba_reg_id;
    private String dba_mod_id;
    private Date dba_mod_dtm;

    public SelBuyMsgDetailsDto toDto() {
        return SelBuyMsgDetailsDto.builder().pj_id(pj_id).buy_id(buy_id).seq(seq).room_num(room_num).file_cnt(file_cnt).build();
    }
}

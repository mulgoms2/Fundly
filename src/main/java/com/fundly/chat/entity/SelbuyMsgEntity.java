package com.fundly.chat.entity;

import com.persistence.dto.SelBuyMsgDto;

import java.util.Date;

public class SelbuyMsgEntity {
    private Integer room_num;
    private String buy_id;
    private String pj_id;
    private Date dba_reg_dtm;
    private String dba_reg_id;
    private Date dba_mod_dtm;
    private String dba_mod_id;

    public SelBuyMsgDto toSelBuyMsgDto() {
        return SelBuyMsgDto.builder().pj_id(pj_id).buy_id(buy_id).room_num(room_num).build();
    }
    public void update(SelBuyMsgDto room) {
        this.room_num = room.getRoom_num();
        this.pj_id = room.getPj_id();
        this.buy_id = room.getBuy_id();
    }
}
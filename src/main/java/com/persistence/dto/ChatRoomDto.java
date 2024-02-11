package com.persistence.dto;

import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;

@Data
public class ChatRoomDto {
    private Integer room_num;
    private ArrayList<SelBuyMsgDetailsDto> message_list;
    private String user_id;
    private String pj_id;
    private Date dba_reg_dtm;
    private String dba_reg_id;
    private Date dba_mod_dtm;
    private String dba_mod_id;
}

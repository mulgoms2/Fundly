package com.persistence.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
@Builder
public class SelBuyMsgDto {
    private Integer room_num;
    private ArrayList<SelBuyMsgDetailsDto> message_list;
    private String buy_id;
    private String pj_id;
    private Date dba_reg_dtm;
    private String dba_reg_id;
    private Date dba_mod_dtm;
    private String dba_mod_id;
}

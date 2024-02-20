package com.fundly.project.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class GiftForm {
    private Integer gift_id;
    private String gift_name;
    private String pj_id;
    private String gift_qty_lim_yn;
    private Integer gift_total_qty;
    private Integer gift_max_qty_per_person;
    private LocalDateTime gift_ship_due_date;
    private String gift_ship_need_yn;
    private Integer gift_money;
    private String dba_reg_id;
    //여기까진 GiftDto의 필드와 일치

    private LocalDateTime pj_pay_due_dtm;
    // Validator에서 gift_ship_due_date를 검증하기 위해 필요한 데이터

    private Integer[] item_id;
    private Integer[] item_qty;
    //GiftItemDetailDto에 바인딩할 필드

}

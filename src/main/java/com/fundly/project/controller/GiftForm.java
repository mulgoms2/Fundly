package com.fundly.project.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GiftForm {
    private String gift_id;
    private String gift_name;
    private String pj_id;
    private String gift_qty_lim_yn;
    private Integer gift_total_qty;
    private Integer gift_max_qty_per_person;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime gift_ship_due_date;
    private String gift_ship_need_yn;
    private Integer gift_money;
    private String dba_reg_id;
    //여기까진 GiftDto의 필드와 일치

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime pj_pay_due_dtm;
    // Validator에서 gift_ship_due_date를 검증하기 위해 필요한 데이터

    private Integer[] item_id;
    private Integer[] item_qty;
    //GiftItemDetailDto에 바인딩할 필드

}

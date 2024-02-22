package com.persistence.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GiftDto {
    private String gift_id; //UUID
    private String gift_name;
    private String pj_id;
    private String gift_qty_lim_yn;
    private Integer gift_total_qty;
    private Integer gift_max_qty_per_person;
    private LocalDateTime gift_ship_due_date;
    //private String gift_ship_need_yn;
    private Integer gift_money;
    private Integer gift_sold_qty;
    private Integer gift_curr_qty;
    private String gift_status; //insert시 "등록중"을 default값을 등록
    private LocalDateTime gift_reg_dtm;
    private LocalDateTime dba_reg_dtm;
    private String dba_reg_id;
    private LocalDateTime dba_mod_dtm;
    private String dba_mod_id;

    public GiftDto(String gift_name, String pj_id, String gift_qty_lim_yn, Integer gift_total_qty,
                   Integer gift_max_qty_per_person, LocalDateTime gift_ship_due_date, String gift_ship_need_yn,
                   Integer gift_money, Integer gift_curr_qty){
        this.gift_name = gift_name;
        this.pj_id = pj_id;
        this.gift_qty_lim_yn = gift_qty_lim_yn;
        this.gift_total_qty = gift_total_qty;
        this.gift_max_qty_per_person = gift_max_qty_per_person;
        this.gift_ship_due_date = gift_ship_due_date;
        //this.gift_ship_need_yn = gift_ship_need_yn;
        this.gift_money = gift_money;
        this.gift_curr_qty = gift_curr_qty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiftDto giftDto = (GiftDto) o;
        return Objects.equals(gift_id, giftDto.gift_id) && Objects.equals(gift_name, giftDto.gift_name) && Objects.equals(pj_id, giftDto.pj_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gift_id, pj_id, gift_name);
    }
}


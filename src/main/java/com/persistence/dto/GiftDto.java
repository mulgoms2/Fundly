package com.persistence.dto;

import lombok.*;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GiftDto {
    private String gift_id;
    private String pj_id;
    private String item_id;
    private Integer item_qty;
    private Integer seq;
    private String gift_name;
    private String gift_qty_lim_yn;
    private Integer gift_max_qty_per_person;
    private String gift_ship_due_date;
    private String gift_ship_need_yn;
    private BigInteger gift_money;
    private Integer gift_total_qty;
    private Integer gift_sold_qty;
    private Integer gift_curr_qty;
    private String gift_status;
    private Timestamp gift_reg_dtm;
    private Timestamp dba_reg_dtm;
    private String dba_reg_id;
    private Timestamp dba_mod_dtm;
    private String dba_mod_id;


    public GiftDto(String gift_name){
        this.gift_name = gift_name;
    }
    public GiftDto(String pj_id, String item_id, Integer item_qty, String gift_name,
                   String gift_qty_lim_yn, Integer gift_total_qty, Integer gift_max_qty_per_person,
                   String gift_ship_due_date, String gift_ship_need_yn, BigInteger gift_money,
                   Integer gift_sold_qty, Integer gift_curr_qty, String gift_status, String dba_reg_id){

        this.pj_id = pj_id;
        this.item_id = item_id;
        this.item_qty = item_qty;
        this.gift_name = gift_name;
        this.gift_qty_lim_yn = gift_qty_lim_yn;
        this.gift_total_qty = gift_total_qty;
        this.gift_max_qty_per_person = gift_max_qty_per_person;
        this.gift_ship_due_date = gift_ship_due_date;
        this.gift_ship_need_yn = gift_ship_need_yn;
        this.gift_money = gift_money;
        this.gift_sold_qty = gift_sold_qty;
        this.gift_curr_qty = gift_curr_qty;
        this.gift_status = gift_status;
        this.dba_reg_id = dba_reg_id;

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiftDto giftDto = (GiftDto) o;
        return Objects.equals(gift_id, giftDto.gift_id) && Objects.equals(pj_id, giftDto.pj_id) && Objects.equals(item_id, giftDto.item_id) && Objects.equals(seq, giftDto.seq) && Objects.equals(gift_name, giftDto.gift_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gift_id, pj_id, item_id, seq, gift_name);
    }
}


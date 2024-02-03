package com.persistence.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
public class giftDto {
    private Integer gift_id;
    private String pj_id;
    private Integer item_id;
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

    @Override
    public String toString() {
        return "giftDto{" +
                "gift_id=" + gift_id +
                ", pj_id='" + pj_id + '\'' +
                ", item_id=" + item_id +
                ", seq=" + seq +
                ", gift_name='" + gift_name + '\'' +
                ", gift_qty_lim_yn='" + gift_qty_lim_yn + '\'' +
                ", gift_max_qty_per_person=" + gift_max_qty_per_person +
                ", gift_ship_due_date='" + gift_ship_due_date + '\'' +
                ", gift_ship_need_yn='" + gift_ship_need_yn + '\'' +
                ", gift_money=" + gift_money +
                ", gift_total_qty=" + gift_total_qty +
                ", gift_sold_qty=" + gift_sold_qty +
                ", gift_curr_qty=" + gift_curr_qty +
                ", gift_status='" + gift_status + '\'' +
                ", gift_reg_dtm=" + gift_reg_dtm +
                '}';
    }

    public giftDto(String gift_name){
        this.gift_name = gift_name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        giftDto giftDto = (giftDto) o;
        return Objects.equals(gift_id, giftDto.gift_id) && Objects.equals(pj_id, giftDto.pj_id) && Objects.equals(item_id, giftDto.item_id) && Objects.equals(seq, giftDto.seq) && Objects.equals(gift_name, giftDto.gift_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gift_id, pj_id, item_id, seq, gift_name);
    }
}


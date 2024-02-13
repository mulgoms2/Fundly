package com.persistence.dto;


import lombok.*;

import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor //기본생성자 꼭 필요(맵핑)
public class ItemDto {
    private String item_id;
    private Integer seq;
    private String pj_id;
    private String item_name;
    private String item_option;
    private String item_option_type; //추가해야할 column
    private Timestamp item_reg_dtm;
    private Timestamp dba_reg_dtm;
    private String dba_reg_id;
    private Timestamp dba_mod_dtm;
    private String dba_mod_id;

    public ItemDto(String pj_id, String item_name, String item_option_type, String item_option){
        this.pj_id = pj_id;
        this.item_name = item_name;
        this.item_option_type = item_option_type;
        this.item_option = item_option;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDto itemDto = (ItemDto) o;
        return Objects.equals(item_id, itemDto.item_id) && Objects.equals(seq, itemDto.seq) && Objects.equals(pj_id, itemDto.pj_id) && Objects.equals(item_name, itemDto.item_name) && Objects.equals(item_option, itemDto.item_option);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item_id, seq, pj_id, item_name, item_option);
    }

}

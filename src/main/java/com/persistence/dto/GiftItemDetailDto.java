package com.persistence.dto;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class GiftItemDetailDto {
    private Integer gift_item_id;
    private String gift_id;
    private Integer item_id;
    private String item_name;
    private Integer item_qty;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiftItemDetailDto that = (GiftItemDetailDto) o;
        return Objects.equals(gift_id, that.gift_id) && Objects.equals(item_id, that.item_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gift_id, item_id);
    }
}



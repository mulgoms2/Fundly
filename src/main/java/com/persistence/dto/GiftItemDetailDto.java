package com.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GiftItemDetailDto {
    private Integer gift_item_id;
    private Integer gift_id;
    private Integer item_id;
    private Integer item_qty;
}

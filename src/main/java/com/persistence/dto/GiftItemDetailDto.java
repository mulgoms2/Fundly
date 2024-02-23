package com.persistence.dto;

import lombok.*;

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
}

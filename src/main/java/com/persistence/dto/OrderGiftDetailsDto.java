package com.persistence.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrderGiftDetailsDto {
    private String order_detail_seq;
    private String order_no;
    private int seq;
    private String gift_id;
    private String order_gift_name;
    private int order_gift_qty;
    @EqualsAndHashCode.Exclude
    private LocalDateTime gift_ship_due_date;
    private int gift_money;

    // DBA
    @EqualsAndHashCode.Exclude
    private LocalDateTime dba_reg_dtm; // 최초등록일시
    private String dba_reg_id; // 최초등록자식별번호
    @EqualsAndHashCode.Exclude
    private LocalDateTime dba_mod_dtm; // 최종수정일시
    private String dba_mod_id; // 최종수정자식별번호
}

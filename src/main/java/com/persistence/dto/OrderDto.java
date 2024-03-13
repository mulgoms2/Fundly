package com.persistence.dto;

import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderDto {
    @NonNull
    private String order_list_id;
    private int seq;
    private String adr_id;
    @NonNull
    private String user_id;
    @NonNull
    private String pj_id;
    private int cpn_seq;
    private BigInteger pay_money;
    private BigInteger order_money;
    @EqualsAndHashCode.Exclude
    private LocalDateTime order_dtm;
    private String order_status;
    private String pay_means_id;
    private String cancel_reason;
    @EqualsAndHashCode.Exclude
    private LocalDateTime order_ddln_dtm;
    @EqualsAndHashCode.Exclude
    private LocalDateTime pay_due_dtm;
    private char pay_inserted_yn;
    private String gift_ship_due_date; //상품 전달 예정일
    @EqualsAndHashCode.Exclude
    private LocalDateTime dba_reg_dtm;
    private String dba_reg_id;
    @EqualsAndHashCode.Exclude
    private LocalDateTime dba_mod_dtm;
    private String dba_mod_id;
}

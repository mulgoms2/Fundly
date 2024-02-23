package com.persistence.dto;

import lombok.*;

import java.sql.Date;

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
    private long order_pay_money;
    private long order_money;
    private Date order_dtm;
    private String order_status;
    private String pay_means;
    private String cancel_reason;
    private Date order_ddln_dtm;
    private Date pay_due_dtm;
    private Date gift_ship_due_date; // TODO: 주문마감일시 : 추가되어야 할 컬럼. (프로젝트 테이블에 존재) (update 24/02/23)
    private Date dba_reg_dtm;
    private String dba_reg_id;
    private Date dba_mod_dtm;
    private String dba_mod_id;
}

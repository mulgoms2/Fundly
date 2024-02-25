package com.persistence.dto;

import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderDto {
    @NonNull
    private String order_list_id; // TODO: DB 컬럼명 오타 수정 필요 order_ilst_id -> order_list_id (update 24/02/24)
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
    private String pay_means_id; // TODO: DB 컬럼명 변경 pay_means -> pay_means_id (update 24/02/24)
    private String cancel_reason;
    private Date order_ddln_dtm;
    private Date pay_due_dtm;
    private char pay_inserted_yn; // TODO: DB 컬럼 추가. 결제테이블에 insert 된 주문인지 여부 (update 24/02/24)
    private Date dba_reg_dtm;
    private String dba_reg_id;
    private Date dba_mod_dtm;
    private String dba_mod_id;
}

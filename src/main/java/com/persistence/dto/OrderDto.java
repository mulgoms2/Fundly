package com.persistence.dto;

import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;

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
    private LocalDateTime order_dtm;
    private String order_status;
    private String pay_means_id; // TODO: DB 컬럼명 변경 pay_means -> pay_means_id (update 24/02/24)
    private String cancel_reason;
    private LocalDateTime order_ddln_dtm;
    private LocalDateTime pay_due_dtm;
    private char pay_inserted_yn; // TODO: DB 컬럼 추가. 결제테이블에 insert 된 주문인지 여부 (update 24/02/24)
    private String gift_ship_due_date; //상품 전달 예정일 TODO: DB 컬럼 추가. 프로젝트 테이블에서 가져오기 (update 24/02/25)
    private LocalDateTime dba_reg_dtm;
    private String dba_reg_id;
    private LocalDateTime dba_mod_dtm;
    private String dba_mod_id;
}

package com.persistence.dto;


import lombok.*;

import java.math.BigInteger;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class PayDto {
    // 결제테이블
    private String pay_id; // 결제 ID
    private String pj_id; // 프로젝트 ID
    private String user_id; // 회원 ID
    private Integer seq; // auto increment
    private String order_list_id; // 주문번호
    private String pay_means_id; // 결제 수단
    private BigInteger order_pay_money; // 결제 금액
    private String pay_status; // 결제 상태
    private Timestamp pay_dtm; // 실제 결제일시
    private Timestamp pay_due_dtm; // 결제예정일시
    private Timestamp pay_ddln_dtm; // 결제마감일시 (결제예정일시 + 7일)
    private Timestamp gift_ship_due_date; // 상품전달예정일
    private String pay_co_resp_json; // PortOne 결제요청에 대한 응답 데이터

    // 주문테이블 only
    private String order_status; // 주문 상태
    private char pay_inserted_yn; // 결제테이블에 insert 된 주문인지 여부

    // dba
    private Timestamp dba_reg_dtm; // 최초등록일시
    private String dba_reg_id; // 최초등록자식별번호
    private Timestamp dba_mod_dtm; // 최종수정일시
    private String dba_mod_id; // 최종수정자식별번호
}

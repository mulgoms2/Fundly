package com.persistence.dto;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.time.LocalDateTime;

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
    private BigInteger pay_money; // 결제 금액
    private String pay_status; // 결제 상태
    @EqualsAndHashCode.Exclude
    private LocalDateTime pay_dtm; // 실제 결제일시
    @EqualsAndHashCode.Exclude
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime pay_due_dtm; // 결제예정일시
    @EqualsAndHashCode.Exclude
    private LocalDateTime pay_ddln_dtm; // 결제마감일시 (결제예정일시 + 7일)
    @EqualsAndHashCode.Exclude
    private LocalDateTime gift_ship_due_date; // 상품전달예정일
    private String pay_co_resp_json; // PortOne 결제요청에 대한 응답 데이터

    // 주문테이블 only
    private String order_status; // 주문 상태
    private char pay_inserted_yn; // 결제테이블에 insert 된 주문인지 여부
    private String adr_id; // 배송지 ID

    // 결제상태이력테이블 only
    private String pay_status_hist_id; // 결제상태이력 ID
    @EqualsAndHashCode.Exclude
    private LocalDateTime mod_dtm; // 결제상태 update 일시

    // dba
    @EqualsAndHashCode.Exclude
    private LocalDateTime dba_reg_dtm; // 최초등록일시
    private String dba_reg_id; // 최초등록자식별번호
    @EqualsAndHashCode.Exclude
    private LocalDateTime dba_mod_dtm; // 최종수정일시
    private String dba_mod_id; // 최종수정자식별번호
}

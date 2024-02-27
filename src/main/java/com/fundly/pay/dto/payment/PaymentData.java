package com.fundly.pay.dto.payment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigInteger;

@Getter
@NoArgsConstructor
@ToString
public class PaymentData {
    private String imp_uid; // 가맹점 식별키(펀들리 ID)
    private String merchant_uid; // 주문 ID
    private String pay_method; // 결제수단 구분코드 ex) card
    private String channel; // 결제환경 구분코드
    private String pg_provider; // PG사 구분코드
    private String emb_pg_provider; // 허브형결제 PG사 구분코드
    private String pg_tid; // PG사 거래번호
    private String pg_id; // PG사 상점아이디
    private boolean escrow; // 에스크로결제 여부
    private String apply_num; // 신용카드 승인번호

    // 실시간계좌이체
    private String bank_code; // 은행 표준코드
    private String bank_name; // 은행명

    // 카드
    private String card_code; // 카드사 표준코드 (카드)
    private String card_name; // 카드사명 (카드)
    private String card_quota; // 할부개월 수 (일시불은 0) (신용카드)
    private String card_number; // 카드번호
    private Integer card_type; // 카드 구분코드 (0: 신용카드, 1: 체크카드)

    // 가상계좌
    private String vbank_code; // 가상계좌 은행 표준코드
    private String vbank_name; // 가상계좌 은행명
    private String vbank_num; // 가상계좌 계좌번호
    private String vbank_holder; // 가상계좌 예금주
    private Integer vbank_date; // 가상계좌 입금기한
    private Integer vbank_issued_at; // 가상계좌 생성시각 unix time

    private String name; // 상품명
    private BigInteger amount; // 결제금액
    private BigInteger cancel_amount; // 취소금액
    private String currency; // 결제통화 구분코드
    private String buyer_name; // 주문자명
    private String buyer_email; // 주문자 Email 주소
    private String buyer_tel; // 주문자 전화번호
    private String buyer_addr; // 주문자 주소
    private String buyer_postcode; // 주문자 우편번호
    private String custom_data; // 결제 요청시 가맹점에서 전달한 추가정보 (JSON string으로 전달)
    private String user_agent; // 단말기의 UserAgent 문자열
    private String status; // 결제상태
    private Integer started_at; // 결제 요청시각. unix timestamp
    private Integer paid_at; // 결제 시각. 결제상태가 결제완료(paid)가 아닌 경우 0으로 표시
    private Integer failed_at; // 결제 실패시각. 결제상태가 결제실패(failed)가 아닌경우 0으로 표시
    private Integer cancelled_at; // 결제 취소시각. 결제상태가 결제취소(cancelled)가 아닐 경우 0으로 표시
    private String fail_reason; // 결제실패 사유
    private String cancel_reason; // 결제취소 사유
    private String receipt_url; // 매출전표 url
    private boolean cash_receipt_issued; // 현금영수증 발급 여부
    private String customer_uid; // 결제수단 ID
    private String customer_uid_usage; // 결제수단 ID 사용 구분코드
}

package com.persistence.dto;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class PayMeansDto {
    // 1. 카드 + 계좌 공통
    private String pay_means_id; // 결제수단 ID
    private String user_id; // 회원 ID
    private Integer seq;
    private String own_type; // 카드소유주/예금주 유형 (개인, 법인)
    private String own_birth; // 생년월일
    private String bill_key; // 포트원 결제수단별 등록키 TODO: DB 컬럼 새로 추가

// 2. 카드
    private String default_pay_means_yn; // 기본 결제수단 여부
    private String card_co_info_agree_yn; // 결제사 정보제공 동의 여부
    private String card_co_type; // 카드사 종류
//    private String card_own_kor_name; // 카드 소유주 한글이름
//    private String card_own_eng_name; // 카드 소유주 영문이름
    private String card_no; // 카드 번호 TODO: DB 컬럼 새로 추가
    private String card_pwd; // 카드 비밀번호 앞 2자리 TODO: DB 타입 변경 (Integer -> String)
    private String card_valid_date; // 카드 유효기간
//    private String card_valid_yn; // 카드 사용가능 여부

// 3. 계좌
//    private String acc_bank_name; // 결제은행
//    private String acc_no; // 계좌번호
//    private String acc_own_kor_name; // 예금주 한글이름
//    private String acc_own_eng_name; // 예금주 영문이름

    // 4. dba
    private Timestamp dba_reg_dtm;
    private String dba_reg_id;
    private Timestamp dba_mod_dtm;
    private String dba_mod_id;

    public PayMeansDto(String user_id, String own_type, String own_birth, String default_pay_means_yn, String card_co_info_agree_yn, String card_no, String card_pwd, String card_valid_date, String dba_reg_id, Timestamp dba_reg_dtm) {
        this.user_id = user_id;
        this.own_type = own_type;
        this.own_birth = own_birth;
        this.default_pay_means_yn = default_pay_means_yn;
        this.card_co_info_agree_yn = card_co_info_agree_yn;
        this.card_no = card_no;
        this.card_pwd = card_pwd;
        this.card_valid_date = card_valid_date;
        this.dba_reg_id = dba_reg_id;
        this.dba_reg_dtm = dba_reg_dtm;
    }

    public String getCard_co_info_agree_yn() {
        return card_co_info_agree_yn;
    }

    public void setCard_co_info_agree_yn(String card_co_info_agree_yn) {
        this.card_co_info_agree_yn = card_co_info_agree_yn;
    }

    public String getOwn_birth() {
        return own_birth;
    }

    public void setOwn_birth(String own_birth) {
        this.own_birth = own_birth;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getCard_pwd() {
        return card_pwd;
    }

    public void setCard_pwd(String card_pwd) {
        this.card_pwd = card_pwd;
    }

    public String getCard_valid_date() {
        return card_valid_date;
    }

    public void setCard_valid_date(String card_valid_date) {
        this.card_valid_date = card_valid_date;
    }

    public String getPay_means_id() {
        return pay_means_id;
    }

    public void setPay_means_id(String pay_means_id) {
        this.pay_means_id = pay_means_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getOwn_type() {
        return own_type;
    }

    public void setOwn_type(String own_type) {
        this.own_type = own_type;
    }

    public String getBill_key() {
        return bill_key;
    }

    public void setBill_key(String bill_key) {
        this.bill_key = bill_key;
    }

    public String getDefault_pay_means_yn() {
        return default_pay_means_yn;
    }

    public void setDefault_pay_means_yn(String default_pay_means_yn) {
        this.default_pay_means_yn = default_pay_means_yn;
    }

    public String getCard_co_type() {
        return card_co_type;
    }

    public void setCard_co_type(String card_co_type) {
        this.card_co_type = card_co_type;
    }

    public Timestamp getDba_reg_dtm() {
        return dba_reg_dtm;
    }

    public void setDba_reg_dtm(Timestamp dba_reg_dtm) {
        this.dba_reg_dtm = dba_reg_dtm;
    }

    public String getDba_reg_id() {
        return dba_reg_id;
    }

    public void setDba_reg_id(String dba_reg_id) {
        this.dba_reg_id = dba_reg_id;
    }

    public Timestamp getDba_mod_dtm() {
        return dba_mod_dtm;
    }

    public void setDba_mod_dtm(Timestamp dba_mod_dtm) {
        this.dba_mod_dtm = dba_mod_dtm;
    }

    public String getDba_mod_id() {
        return dba_mod_id;
    }

    public void setDba_mod_id(String dba_mod_id) {
        this.dba_mod_id = dba_mod_id;
    }
}

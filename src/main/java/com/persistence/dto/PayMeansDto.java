package com.persistence.dto;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class PayMeansDto {
    private String pay_means_id; // 결제수단 ID
    private String user_id; // 회원 ID
    private Integer seq;
    @NonNull
    private String own_type; // 카드소유주/예금주 유형 (개인, 법인)
    @NonNull
    private String own_birth; // 생년월일
    private String bill_key; // 포트원 결제수단별 등록키
    private String file_path; // 결제수단 이미지 파일 경로
    private String file_name; // 결제수단 이미지 파일명
    private String file_extension; // 결제수단 이미지 파일 확장자
    private String default_pay_means_yn; // 기본 결제수단 여부
    private String card_co_info_agree_yn; // 결제사 정보제공 동의 여부
    private String card_co_type; // 카드사 종류
    @NonNull
    private String card_no; // 카드 뒷자리 번호 4개
    @NonNull
    private String card_pwd; // 카드 비밀번호 앞 2자리
    @NonNull
    private String card_valid_date; // 카드 유효기간
    private Integer card_type; // 카드 타입 (신용카드 == 0, 체크카드 == 1)

    // dba
    @EqualsAndHashCode.Exclude
    private LocalDateTime dba_reg_dtm;
    private String dba_reg_id;
    @EqualsAndHashCode.Exclude
    private LocalDateTime dba_mod_dtm;
    private String dba_mod_id;

    public PayMeansDto(String user_id, String own_type, String bill_key, String default_pay_means_yn, String card_no, String dba_reg_id) {
        this.user_id = user_id;
        this.own_type = own_type;
        this.bill_key = bill_key;
        this.default_pay_means_yn = default_pay_means_yn;
        this.card_no = card_no;
        this.dba_reg_id = dba_reg_id;
    }

    public String getFile_path() {
        return file_path;
    }

    public String getFile_name() {
        return file_name;
    }

    public String getFile_extension() {
        return file_extension;
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

    public Integer getCard_type() {
        return card_type;
    }

    public void setCard_type(Integer card_type) {
        this.card_type = card_type;
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

    public LocalDateTime getDba_reg_dtm() {
        return dba_reg_dtm;
    }

    public void setDba_reg_dtm(LocalDateTime dba_reg_dtm) {
        this.dba_reg_dtm = dba_reg_dtm;
    }

    public String getDba_reg_id() {
        return dba_reg_id;
    }

    public void setDba_reg_id(String dba_reg_id) {
        this.dba_reg_id = dba_reg_id;
    }

    public LocalDateTime getDba_mod_dtm() {
        return dba_mod_dtm;
    }

    public void setDba_mod_dtm(LocalDateTime dba_mod_dtm) {
        this.dba_mod_dtm = dba_mod_dtm;
    }

    public String getDba_mod_id() {
        return dba_mod_id;
    }

    public void setDba_mod_id(String dba_mod_id) {
        this.dba_mod_id = dba_mod_id;
    }
}

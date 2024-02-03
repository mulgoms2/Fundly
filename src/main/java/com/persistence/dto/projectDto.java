package com.persistence.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
public class projectDto {
    private String pj_id;
    private Integer seq;
    private String pj_sel_id; //로그인 세션에서 가져오기.
    private String pj_url; //이건 없애기로 한거 아닌가.
    private String pj_long_title;
    private String pj_short_title;
    private String pj_intro; //프로젝트 소개(t.e에서 작성)
    private String pj_short_intro; //프로제트 요약(기본정보 탭에서 작성)
    private String pj_tag;
    private BigInteger pj_expct_money; //프로젝트 예산
    private Timestamp pj_pay_due_dtm; //후원 결제 예정일
    private String pj_sel_intro; //창작자 소개(t.e에서 작성)
    private String pj_gift_intro; //선물 설명(t.e에서 작성)
    private BigInteger fund_goal_money; //펀딩 목표금액
    private Timestamp fund_str_dtm;// 펀딩시작일시
    private Timestamp fund_end_dtm; //펀딩 종료일시
    private Timestamp fund_calc_due_dtm;//펀딩 정산예정일
    private String gift_ship_due_date; //상품 전달 예정일
    private String pj_status; //프로젝트 상태
    private String fund_result_status; //펀딩 결과 상태
    private Integer curr_pj_like_cnt; //프로젝트 좋아요 현재 집계
    private BigInteger curr_fund_money;//펀딩 모금액 현재 집계
    private Integer curr_buy_cnt; //후원자 수 현재 집계
    private Timestamp dba_reg_dtm;
    private String dba_reg_id;
    private Timestamp dba_mod_dtm;
    private String dba_mod_id;

    //추가된 column
    private String pj_sched; //(t.e에서 작성)
    private String pj_budget; //(t.e에서 작성)
    private String pj_sel_name; //창작자 정보 탭
    private String pj_sel_short_intro; //창작자 정보 탭


    //프로젝트 일정, 프로젝트 예산 (t.e에서 작성하는) column 추가해야함.
    //창작자 이름, 창작자 소개(짧은)

    //프로젝트 대표이미지, 프로필 이미지, t.e에서 첨부하는 이미지들은 파일테이블에서 관리?
    //입금계좌 등록 (-> 입금계좌 테이블에서)
    @Override
    public String toString() {
        return "projectDto{" +
                "pj_id='" + pj_id + '\'' +
                ", seq=" + seq +
                ", pj_sel_id='" + pj_sel_id + '\'' +
                ", pj_url='" + pj_url + '\'' +
                ", pj_long_title='" + pj_long_title + '\'' +
                ", pj_short_title='" + pj_short_title + '\'' +
                ", pj_intro='" + pj_intro + '\'' +
                ", pj_short_intro='" + pj_short_intro + '\'' +
                ", pj_tag='" + pj_tag + '\'' +
                ", pj_expct_money=" + pj_expct_money +
                ", pj_pay_due_dtm=" + pj_pay_due_dtm +
                ", pj_sel_intro='" + pj_sel_intro + '\'' +
                ", pj_gift_intro='" + pj_gift_intro + '\'' +
                ", fund_goal_money=" + fund_goal_money +
                ", fund_str_dtm=" + fund_str_dtm +
                ", fund_end_dtm=" + fund_end_dtm +
                ", fund_calc_due_dtm=" + fund_calc_due_dtm +
                ", gift_ship_due_date='" + gift_ship_due_date + '\'' +
                ", pj_status='" + pj_status + '\'' +
                ", fund_result_status='" + fund_result_status + '\'' +
                ", curr_pj_like_cnt=" + curr_pj_like_cnt +
                ", curr_fund_money=" + curr_fund_money +
                ", curr_buy_cnt=" + curr_buy_cnt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        projectDto that = (projectDto) o;
        return Objects.equals(pj_id, that.pj_id) && Objects.equals(seq, that.seq) && Objects.equals(pj_sel_id, that.pj_sel_id) && Objects.equals(pj_status, that.pj_status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pj_id, seq, pj_sel_id, pj_status);
    }
}

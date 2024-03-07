package com.fundly.user.dto;

import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class LikeProjectDto {

    // 좋아요 필드
    private String user_id;
    private String pj_id;
    private int like_status;
    private String like_dtm;
    @EqualsAndHashCode.Exclude
    private LocalDateTime dba_reg_dtm;
    private String dba_reg_id;
    @EqualsAndHashCode.Exclude
    private LocalDateTime dba_mod_dtm;
    private String dba_mod_id;

    // 프로젝트 필드
    private String pj_sel_id; //로그인 세션에서 가져오기.
    @EqualsAndHashCode.Exclude
    private LocalDateTime pj_reg_dtm;

    //프로젝트 기획 - 기본 정보
    private String ctg; //카테고리
    private String sub_ctg; //세부 카테고리
    private String pj_long_title; //프로젝트 긴 제목
    private String pj_short_title; //프로젝트 짧은 제목
    private String pj_thumbnail_url; // 프로젝트 대표이미지
    private String pj_short_intro; //프로제트 요약(기본정보 탭에서 작성)
    //    컴마로 구분된 통 문자열이 들어온다.
    private String pj_tag; // 검색 태그

    //프로젝트 기획 - 펀딩 계획
    private BigInteger fund_goal_money; //펀딩 목표금액 10
    //    @DateTimeFormat()
    private LocalDateTime fund_str_dtm;// 펀딩시작일시
    private LocalDateTime fund_end_dtm; //펀딩 종료일시
    private LocalDateTime pj_pay_due_dtm; //후원 결제 예정일
    private LocalDateTime fund_calc_due_dtm;//펀딩 정산예정일

    //프로젝트 기획 - 프로젝트 계획
    private String pj_intro; //프로젝트 소개(t.e에서 작성)
    //    예산이 실제로는 예산 사용 계획서 글이다.
    private String pj_budget; //프로젝트 예산(t.e에서 작성) ////!!!!테이블에서 컬럼명과 타입을 수정해야합니다!!!
    private String pj_sched; //프로젝트 일정(t.e에서 작성)
    private String pj_sel_intro; //창작자 또는 팀 소개(t.e에서 작성)
    private String pj_gift_intro; //선물 설명(t.e에서 작성)


    //프로젝트 기획 - 창작자 정보
    private String pj_sel_name; //창작자 정보 탭 20
    private String pj_sel_short_intro; //창작자 정보 탭
    private String pj_prof_image_url; //창작자 프로필 이미지
    // 입금계좌는 입금계좌 테이블에서 진행

    // 그 외
    private String gift_ship_due_date; //상품 전달 예정일
    private String pj_status; //프로젝트 상태
    private String fund_result_status; //펀딩 결과 상태

    //프로젝트 좋아요 및 후원자 관련
    private Integer curr_pj_like_cnt; //프로젝트 좋아요 현재 집계
    private BigInteger curr_fund_money;//펀딩 모금액 현재 집계
    private Integer curr_buy_cnt; //후원자 수 현재 집계 8
}

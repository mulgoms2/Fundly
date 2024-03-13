package com.persistence.dto;

import com.fundly.project.controller.FundingForm;
import com.fundly.project.controller.StoryForm;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Builder
@EqualsAndHashCode
public class ProjectDto {
    private static BigInteger curr_money;
    //    public enum PJ_STAUS {
//        심사대기(0,1), 진행중(1,2 ), 승인됨(2,3), 반려됨(3,4), 진행중단(4,5), 펀딩종료(5, 6),;
//        private final Integer step;
//        private final Integer nextStep;
//        PJ_STAUS(int step, int nextStep) {
//            this.step = step;
//            this.nextStep = nextStep;
//        }
//    }

    //    @NonNull
    private String pj_id; //uuid만들어서 그대로 집어넣으면?? // PK를 노출하는 것은 좋지 않다고....하는데..
    private String pj_sel_id; //로그인 세션에서 가져오기.

    @EqualsAndHashCode.Exclude
    private LocalDateTime pj_reg_dtm;

//    private PJ_STAUS status;

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
    //private LocalDateTime gift_ship_due_date; //상품 전달 예정일 -> 프로젝트 테이블에 필요없음.
    private String pj_status; //프로젝트 상태
    private String fund_result_status; //펀딩 결과 상태

    //프로젝트 좋아요 및 후원자 관련
//    @NonNull
    private Integer curr_pj_like_cnt; //프로젝트 좋아요 현재 집계
    private BigInteger curr_fund_money;//펀딩 모금액 현재 집계
    private Integer curr_buy_cnt; //후원자 수 현재 집계 8

    //이미지들에 대한 관리
    // 프로젝트 대표이미지, 프로필 이미지 -> 한개씩이니까 접근성 좋게 pj테이블에 column으로 관리?
    // t.e에서 첨부하는 이미지들은.... 파일 테이블 이용??

    public static ProjectAddResponse toResponseDto(ProjectDto pj) {
        return ProjectAddResponse.builder()
                                 .pj_id(pj.getPj_id())
                                 .sel_id(pj.getPj_sel_id())
                                 .build();
    }

    public static ProjectTemplate toTemplate(ProjectDto pj) {
        return ProjectTemplate.builder()
                              .build();
    }

    public static ProjectInfoUpdateResponse toInfoUpdateResponse(ProjectDto project) {
        return ProjectInfoUpdateResponse
                .builder()
                .pj_id(project.getPj_id())
                .ctg(project.getCtg())
                .sub_ctg(project.getSub_ctg())
                .pj_long_title(project.getPj_long_title())
                .pj_short_title(project.getPj_short_title())
                .pj_thumbnail_url(project.getPj_thumbnail_url())
                .build();
    }

    public static ProjectCreatorDto toCreatorDto(ProjectDto projectDto) {
        return ProjectCreatorDto.builder()
                                .pj_sel_name(projectDto.getPj_sel_name())
                                .pj_sel_short_intro(projectDto.getPj_sel_short_intro())
                                .pj_prof_image_url(projectDto.getPj_prof_image_url())
                                .build();
    }


    public void updateBasicInfo(ProjectInfoUpdateRequest request) {
        this.ctg = request.getCtg();
        this.sub_ctg = request.getSub_ctg();
        this.pj_long_title = request.getPj_long_title();
        this.pj_short_title = request.getPj_short_title();
        this.pj_tag = request.getPj_tag();
    }

    public void updateStory(StoryForm storyForm) {
        this.pj_intro = storyForm.getPj_intro();
        this.pj_budget = storyForm.getPj_budget();
        this.pj_sched = storyForm.getPj_sched();
        this.pj_sel_intro = storyForm.getPj_sel_intro();
        this.pj_gift_intro = storyForm.getPj_gift_intro();
    }

    public void updateFunding(FundingForm fundingForm) {
        this.fund_goal_money = fundingForm.getFund_goal_money();
        this.fund_str_dtm = fundingForm.getFund_str_dtm();
        this.fund_end_dtm = fundingForm.getFund_end_dtm();
        this.pj_pay_due_dtm = fundingForm.getPj_pay_due_dtm();
        this.fund_calc_due_dtm = fundingForm.getFund_calc_due_dtm();
    }

//    public void updateGiftShipDueDate(GiftForm giftForm){
//        this.gift_ship_due_date = giftForm.getGift_ship_due_date();
//    }

    public static FundingForm toFundingForm(ProjectDto project) {
        FundingForm fundingForm = FundingForm.builder()
                                             .fund_goal_money(project.getFund_goal_money())
                                             .fund_str_dtm(project.getFund_str_dtm())
                                             .fund_end_dtm(project.getFund_end_dtm())
                                             .pj_pay_due_dtm(project.getPj_pay_due_dtm())
                                             .fund_calc_due_dtm(project.getFund_calc_due_dtm())
                                             .build();

        return fundingForm.calcFundPeriod()
                          .calcFundStrTime()
                          .dtmToString();
    }

    public static StoryForm toStoryForm(ProjectDto project) {
        StoryForm storyForm = StoryForm.builder()
                                       .pj_id(project.getPj_id())
                                       .pj_intro(project.getPj_intro())
                                       .pj_budget(project.getPj_budget())
                                       .pj_sched(project.getPj_sched())
                                       .pj_sel_intro(project.getPj_sel_intro())
                                       .pj_gift_intro(project.getPj_gift_intro())
                                       .build();

        storyForm.isEmpty();
        return storyForm;
    }

    public static ProjectBasicInfo toBasicInfo(ProjectDto project) {
        String pjTags = project.getPj_tag();
        List<String> tagList = new ArrayList<>();

        if (pjTags != null) {
            StringTokenizer tokenizer = new StringTokenizer(pjTags, ",");
            while (tokenizer.hasMoreTokens()) {
                tagList.add(tokenizer.nextToken());
            }
        }
        return ProjectBasicInfo.builder()
                               .pj_id(project.getPj_id())
                               .sel_name(project.getPj_sel_name())
                               .ctg(project.getCtg())
                               .sub_ctg(project.getSub_ctg())
                               .pj_short_title(project.getPj_short_title())
                               .pj_long_title(project.getPj_long_title())
                               .tags(tagList)
                               .build();
    }

    public static ProjectTemplate toProjectTemplate(ProjectDto projectDto) {
//        펀딩 달성률을 여기서 계산해서 넣어주자.
        BigInteger goal_money = projectDto.getFund_goal_money();
        BigInteger curr_money = projectDto.getCurr_fund_money();
        BigInteger fund_percent;
        try {
            fund_percent = curr_money.multiply(BigInteger.valueOf(100L))
                                     .divide(goal_money);
        } catch (NullPointerException e) {
            fund_percent = BigInteger.valueOf(0L);
        }


        return ProjectTemplate.builder()
                              .pj_id(projectDto.getPj_id())
                              .thumbnail_img_url(projectDto.getPj_thumbnail_url())
                              .category(projectDto.getCtg())
                              .sel_name(projectDto.getPj_sel_name())
                              .long_title(projectDto.getPj_long_title())
                              .funding_percentage(String.valueOf(fund_percent))
                              .build();
    }

    public void updateCreatorInfo(ProjectCreatorUpdateRequest request) {
        this.pj_sel_name = request.getPj_sel_name();
//        this.pj_prof_image_url =
    }

    public void updateProfileImage(String tagSrcUrl) {
        this.pj_prof_image_url = tagSrcUrl;
    }

    public void updateThumbnailImage(String tagSrcUrl) {
        this.pj_thumbnail_url = tagSrcUrl;
    }
}

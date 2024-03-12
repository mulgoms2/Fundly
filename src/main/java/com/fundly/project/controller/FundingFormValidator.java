package com.fundly.project.controller;

import com.fundly.project.exception.FundingValidationException;
import com.fundly.project.service.HolidayAPIService;
import com.fundly.project.service.HolidayAPIServiceImpl;
import com.persistence.dto.HolidayDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigInteger;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class FundingFormValidator implements Validator {
    HolidayAPIService holidayAPIService;

    @Autowired
    FundingFormValidator(HolidayAPIService holidayAPIService){
        this.holidayAPIService = holidayAPIService;
    } //Validator에서 Service를 호출하는게 맞는걸까...
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(FundingForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FundingForm fundingForm = (FundingForm) target;
        BigInteger fund_goal_money = fundingForm.getFund_goal_money();
        LocalDateTime fund_str_dtm = fundingForm.getFund_str_dtm();
        LocalDateTime fund_end_dtm = fundingForm.getFund_end_dtm();
        LocalDateTime pj_pay_due_dtm = fundingForm.getPj_pay_due_dtm();
        LocalDateTime fund_calc_due_dtm = fundingForm.getFund_calc_due_dtm();

        //validation이 아닌 binding에러에 대한 message도 따로 정리하기(typeMismatch)

        //1. 펀딩 목표 금액 검사
        //fund_goal_money 유효성 검사 : (범위 500000 ~ 9999999999), NotNull조건 체크
        final String MIN = "500000";
        final String MAX = "9999999999";
        if(fund_goal_money.compareTo(new BigInteger(MIN))<0
            || fund_goal_money.compareTo(new BigInteger(MAX)) > 0) {

            errors.rejectValue("fund_goal_money", "invalidRange.fund_goal_money", new String[]{MIN, MAX}, null);
        }


        //LocalDateTime에 대해서도 typeMismatch 정리하기

        //2. 펀딩 기간(시작일, 마감일, 기간) 검사
        //fund_str_dtm 유효성 검사 : (프로젝트 심시기간을 고려해서) 오늘로부터 최소 10일 이후부터 펀딩 시작일 지정 가능
        final int MIN_AFTER = 10;
        boolean isMinAfter = fund_str_dtm.minusDays(10).isAfter(LocalDateTime.now());

        if(!isMinAfter) {
            errors.rejectValue("fund_str_dtm","invalidDate.fund_str_dtm", new String[]{String.valueOf(MIN_AFTER)},null);
        }

        //fund_end_dtm : fund_str_dtm보다 미래 + MAX 60일 (펀딩기간 최대 60일)
        boolean isAfter = fund_end_dtm.isAfter(fund_str_dtm);
        final int MAX_AFTER = 60; //최대 펀딩 기간

        fundingForm.calcFundPeriod();
        int fund_period = fundingForm.getFund_period();

        if(!isAfter || !(0 <= fund_period && fund_period <= MAX_AFTER)){
            errors.rejectValue("fund_end_dtm", "invalidDate.fund_end_dtm", new String[]{"0", String.valueOf(MAX_AFTER)},null);
        }


        //3. 결제 예정일 (펀딩 종료 다음날 결제됨)
        final int PAY_DIFF_DAYS = 1;
        boolean pay_after_fund_day = pj_pay_due_dtm.minusDays(PAY_DIFF_DAYS).equals(fund_end_dtm);
        if(!pay_after_fund_day){
            errors.rejectValue("pj_pay_due_dtm","invalidDDate.pj_pay_due_dtm", new String[]{String.valueOf(PAY_DIFF_DAYS)},null);
        }

        //4. 정산 예정일(펀딩 종료 이후 7영업일째)
        int workDay = 7; //7영업일
        LocalDateTime nextDayFromPay = pj_pay_due_dtm.plusDays(1);
        List<String> holidayNameList;
        try {
            List<HolidayDto> dtolist = holidayAPIService.getHolidayList(pj_pay_due_dtm);
            holidayNameList = HolidayAPIServiceImpl.getHolidayNameList(dtolist);
        } catch (Exception e) {
            e.printStackTrace();
            FundingValidationException fe = new FundingValidationException("정산 예정일 유효성 검사 중 예외가 발생했습니다.");
            fe.initCause(e);
            throw fe;
        }

        for(int i=0; i<workDay; i++){ //주말이거나 공휴일이면 +1일 추가됨. 주말이거나 공휴일이 아닐때까지 반복
            if(isWeekendOrHoliday(nextDayFromPay,holidayNameList)){
                ++workDay;
            }
            nextDayFromPay = nextDayFromPay.plusDays(1);
        }
        //여기까지가 workDay를 구하기 위한 과정

        //전달받은 정산예정일이 결제예정일로부터 7영업일째인지
        if(!fund_calc_due_dtm.isEqual(pj_pay_due_dtm.plusDays(workDay))){
            errors.rejectValue("fund_calc_due_dtm","invalidDate.fund_calc_due_dtm",null);
        }
    }


    private static boolean isWeekendOrHoliday(LocalDateTime date, List<String> holidayNameList){
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        //주말이거나 공휴일이면 true
        return dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY)
                || holidayNameList.contains(date.toLocalDate());
    }
}

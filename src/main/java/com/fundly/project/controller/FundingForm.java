package com.fundly.project.controller;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Data
@Builder
public class FundingForm {
    BigInteger fund_goal_money;
    LocalDateTime fund_str_dtm;
    LocalDateTime fund_end_dtm;
    int fund_period;
    LocalDateTime pj_pay_due_dtm;
    LocalDateTime fund_calc_due_dtm;
    String fund_range;

    public void setFundPeriod(){
        Period period = Period.between(fund_str_dtm.toLocalDate(), fund_end_dtm.toLocalDate());
        fund_period = period.getDays();
    }
    public void setFund_range(){
        this.fund_range = this.fund_str_dtm.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                + "~" + this.fund_str_dtm.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}

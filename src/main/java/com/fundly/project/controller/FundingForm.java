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
    private BigInteger fund_goal_money;
    private LocalDateTime fund_str_dtm;
    private LocalDateTime fund_end_dtm;
    private int fund_period;
    private LocalDateTime pj_pay_due_dtm;
    private LocalDateTime fund_calc_due_dtm;
    private String fund_range;

    public void setFundPeriod(){
        Period period = Period.between(fund_str_dtm.toLocalDate(), fund_end_dtm.toLocalDate());
        fund_period = period.getDays();
    }
    public void setFund_range(){
        this.fund_range = this.fund_str_dtm.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                + "~" + this.fund_str_dtm.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}

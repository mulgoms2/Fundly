package com.fundly.project.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class FundingForm {
    private BigInteger fund_goal_money;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fund_str_dtm;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fund_end_dtm;
    private int fund_period = 0;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime pj_pay_due_dtm;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fund_calc_due_dtm;
    private String fund_range ="";

    public FundingForm calcFundPeriod(){
//        log.error("\n\n fund_str_dtm={} \n\n", fund_str_dtm);
//        log.error("\n\n fund_end_dtm={} \n\n", fund_end_dtm);
        Period period = Period.between(fund_str_dtm.toLocalDate(), fund_end_dtm.toLocalDate());
        fund_period = period.getDays();
        return this;
    }
    public FundingForm calcFundRange(){
        this.fund_range = this.fund_str_dtm.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                + "~" + this.fund_end_dtm.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return this;
    }


}

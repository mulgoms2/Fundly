package com.fundly.project.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class FundingForm {
    @NotNull
    private BigInteger fund_goal_money;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fund_str_dtm;

    private String fund_str_tm;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fund_end_dtm;

    private int fund_period = 0;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime pj_pay_due_dtm;

    private String pj_pay_dtm_string; //편의를 위해 (el에서 바로 쓰려고)

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fund_calc_due_dtm;

    private String calc_due_dtm_string=""; //편의를 위해 (el에서 바로 쓰려고)
    //private String fund_range ="";

    public FundingForm calcFundPeriod(){
        if(fund_str_dtm!=null && fund_end_dtm!=null){
            Duration duration = Duration.between(fund_str_dtm, fund_end_dtm);
            this.fund_period = (int)duration.toDays();
            log.error("\n\n duration={} \n\n", duration);
            log.error("\n\n duration.toDays()={} \n\n", duration.toDays());
        }
        return this;
    }
//    public FundingForm calcFundRange(){
//        if(fund_str_dtm!=null && fund_end_dtm!=null) {
//            this.fund_range = this.fund_str_dtm.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
//                    + "~" + this.fund_end_dtm.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        }
//        return this;
//    }

    public FundingForm calcFundStrTime(){
        if(fund_str_dtm!=null) {
            this.fund_str_tm = this.fund_str_dtm.toLocalTime().toString();
        }
        return this;
    }

    public FundingForm dtmToString(){
        if(pj_pay_due_dtm!=null && fund_calc_due_dtm!=null) {
            this.pj_pay_dtm_string = this.pj_pay_due_dtm.toLocalDate().toString();
            this.calc_due_dtm_string = this.fund_calc_due_dtm.toLocalDate().toString();
        }
        return this;
    }
}

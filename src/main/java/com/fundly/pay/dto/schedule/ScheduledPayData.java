package com.fundly.pay.dto.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class ScheduledPayData {
    private Integer total;
    private Integer previous;
    private Integer next;
    private List<ScheduledPay> list;
}
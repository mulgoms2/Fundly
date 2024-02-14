package com.fundly.pay.dto.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ScheduledPayResponseDto {
    private Integer code;
    private String message;
    private ScheduledPayData response;
}
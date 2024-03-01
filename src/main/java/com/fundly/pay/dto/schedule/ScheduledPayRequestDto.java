package com.fundly.pay.dto.schedule;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class ScheduledPayRequestDto {
    private String customer_uid; // pay_means_id
    private String schedule_status; // scheduled: 예약, executed: 예약결제완료, revoked: 예약취소
    private long from; // 최대 3개월 전 Unix time
    private long to; // 현재시간 Unix time
}
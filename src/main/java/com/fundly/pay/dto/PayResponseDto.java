package com.fundly.pay.dto;

import com.persistence.dto.PayMeansDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PayResponseDto {
    private String code; // *_SUCCESS 또는 *_ERROR
    private PayMeansDto payMeansDto;
}

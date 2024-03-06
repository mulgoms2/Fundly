package com.fundly.pay.dto;

import com.persistence.dto.PayMeansDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PayResponseDto {
    private String code; // *_SUCCESS 또는 *_ERROR
    private PayMeansDto payMeansDto;
    private List<PayMeansDto> payMeansDtoList;
    private PayPageHandler pageHandler;

    public PayResponseDto(String code, PayMeansDto payMeansDto) {
        this.code = code;
        this.payMeansDto = payMeansDto;
    }

    public PayResponseDto(String code, List<PayMeansDto> payMeansDtoList, PayPageHandler pageHandler) {
        this.code = code;
        this.payMeansDtoList = payMeansDtoList;
        this.pageHandler = pageHandler;
    }
}

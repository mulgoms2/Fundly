package com.fundly.pay.service;

import com.fundly.pay.dto.billkey.BillKeyRequestDto;
import com.fundly.pay.dto.billkey.BillKeyResponseDto;
import com.fundly.pay.dto.schedule.ScheduledPayRequestDto;
import com.fundly.pay.dto.schedule.ScheduledPayResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface PortOneService {
    BillKeyResponseDto removeBillKey(BillKeyRequestDto billKeyRequestDto, String authToken) throws Exception;
    ScheduledPayResponseDto getScheduledPay(ScheduledPayRequestDto scheduledPayRequestDto, String authToken) throws Exception;
    BillKeyResponseDto getBillKey(BillKeyRequestDto billKeyRequestDto, String authToken) throws Exception;
    String getToken() throws Exception;
}

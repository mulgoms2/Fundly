package com.fundly.pay.service;

import com.fundly.pay.dto.BillKeyRequestDto;
import com.fundly.pay.dto.BillKeyResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface PortOneService {
    BillKeyResponseDto getBillKey(BillKeyRequestDto billKeyRequestDto, String authToken) throws Exception;
    String getToken() throws Exception;
}

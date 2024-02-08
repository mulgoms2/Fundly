package com.fundly.pay.service;

import com.fundly.pay.dto.BillKeyRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface PortOneService {
    String getBillKey(BillKeyRequestDto billKeyRequestDto, String authToken) throws Exception;
    String getToken() throws Exception;
}

package com.fundly.pay.service;

import com.fundly.pay.dto.billkey.BillKeyRequestDto;
import com.fundly.pay.dto.billkey.BillKeyResponseDto;
import com.fundly.pay.dto.payment.PaymentRequestDto;
import com.fundly.pay.dto.payment.PaymentResponseDto;
import com.fundly.pay.dto.schedule.ScheduledPayRequestDto;
import com.fundly.pay.dto.schedule.ScheduledPayResponseDto;
import com.fundly.pay.dto.token.TokenResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PortOneService {
    // 결제수단 삭제
    BillKeyResponseDto removeBillKey(BillKeyRequestDto billKeyRequestDto, String authToken) throws Exception;
    // 예약된 결제 조회
    ScheduledPayResponseDto getScheduledPay(ScheduledPayRequestDto scheduledPayRequestDto, String authToken) throws Exception;
    // 결제수단 등록
    BillKeyResponseDto getBillKey(BillKeyRequestDto billKeyRequestDto, String authToken) throws Exception;
    // 공통: 인증토큰 발급
    TokenResponseDto getToken() throws Exception;
    // 결제 요청
    ResponseEntity<PaymentResponseDto> requestPay(PaymentRequestDto paymentRequestDto, String authToken) throws Exception;
    // 결제 취소
    ResponseEntity<PaymentResponseDto> cancelPay(PaymentRequestDto paymentRequestDto, String authToken) throws Exception;
}

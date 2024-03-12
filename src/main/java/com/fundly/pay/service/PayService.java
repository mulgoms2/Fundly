package com.fundly.pay.service;

import com.fundly.pay.dto.payment.PaymentResponseDto;
import com.persistence.dto.PayDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service
public interface PayService {
    void createPayRecordFromOrder(); // 주문테이블에서 결제테이블로 데이터 insert
    void executePayment(); // 결제 요청 후 결제상태 update
    void retryPayment(); // 재결제 요청 후 결제상태 update
    @Transactional
    void setUpAndInsertPayRecord(PayDto payDto);
    void processPayment(PayDto payDto, String flag);
    @Transactional
    void updatePayStatus(PayDto payDto, String payStatus);
    boolean isValidPaymentAmount(BigInteger amountToBePaid, BigInteger amount);
    ResponseEntity<PaymentResponseDto> requestPayToPortOne(PayDto payDto, String flag);
    void cancelPayment(PayDto payDto, String flag);
    @Transactional
    void setUpAndInsertPayRecordForTest(PayDto payDto);
    @Transactional
    void updatePayStatusForTest(PayDto payDto, String payStatus);
}

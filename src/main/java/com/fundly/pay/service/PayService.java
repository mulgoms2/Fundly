package com.fundly.pay.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface PayService {
    @Transactional(rollbackFor = Exception.class)
    void createPayRecordFromOrder(); // 주문테이블에서 결제테이블로 데이터 insert

    @Transactional(rollbackFor = Exception.class)
    void executePayment(); // 결제 요청 후 결제상태 update

    @Transactional(rollbackFor = Exception.class)
    void retryPayment(); // 재결제 요청 후 결제상태 update
}
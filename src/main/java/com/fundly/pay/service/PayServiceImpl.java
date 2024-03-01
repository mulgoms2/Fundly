package com.fundly.pay.service;

import com.fundly.pay.dto.payment.PaymentRequestDto;
import com.fundly.pay.dto.payment.PaymentResponseDto;
import com.fundly.pay.model.PayDao;
import com.persistence.dto.PayDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private final PayDao payDao;
    private final PortOneService portOneService;

    @Autowired
    public PayServiceImpl(PayDao payDao, PortOneService portOneService) {
        this.payDao = payDao;
        this.portOneService = portOneService;
    }

    String userId = "test"; // TODO: 하드코딩, 세션에서 가져오기

    // 결제테이블 데이터 생성: 주문테이블에서 결제테이블로 데이터 insert
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPayRecordFromOrder() {
        try {
            // 주문테이블에서 주문상태 == '주문완료' 데이터를 가져온다.
            List<PayDto> payDtoList = payDao.selectCompletedOrder();

            for (PayDto payDto : payDtoList) {
                // 데이터 세팅 및 결제테이블 insert
                setUpAndInsertPayRecord(payDto);
            }
        } catch (Exception e) {
            handleException("createPayRecordFromOrder()", e);
        }
    }

    // 결제 메서드
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void executePayment() {
        try {
            // 결제대상을 조회한다.
            List<PayDto> payDtoList = payDao.selectPayTarget();
            // 결제 로직을 수행한다.
            processPayment(payDtoList, "결제");
        } catch (Exception e) {
            // 예외 처리
            handleException("executePayment()", e);
        }
    }

    // 재결제 메서드
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void retryPayment() {
        try {
            // 재결제대상을 조회한다.
            List<PayDto> payDtoList = payDao.selectPayRetryTarget();
            // 결제 로직을 수행한다.
            processPayment(payDtoList, "재결제");
        } catch (Exception e) {
            // 예외 처리
            handleException("retryPayment()", e);
        }
    }

    // 데이터 세팅 및 결제테이블 insert
    private void setUpAndInsertPayRecord(PayDto payDto) {
        try {
            // 1. 결제테이블의 PK인 payId를 세팅한다.
            payDto.setPay_id(payDao.selectPayId(userId));
            // 2. 결제테이블에 insert 한다.
            payDao.insert(payDto);
            // 3. 주문테이블의 pay_inserted_yn 컬럼의 값을 'Y'로 바꾼다.
            payDao.updatePayInsertedToY(payDto.getOrder_list_id());
        } catch (Exception e) {
            handleException("setUpAndInsertPayRecord(PayDto payDto)", e);
        }
    }

    // 결제 수행 로직
    private void processPayment(List<PayDto> payDtoList, String flag) {
        for (PayDto payDto : payDtoList) {
            try {
                // 포트원에 결제 요청을 한다.
                ResponseEntity<PaymentResponseDto> requestPayResponseDto = requestPayToPortOne(payDto);
                // 결제 성공
                if (isPaymentSucessful(requestPayResponseDto)) {
                    // 결제금액 검증: 요청한 금액과 실제 결제된 금액이 같은지 비교
                    if (isValidPaymentAmount(payDto.getOrder_pay_money(), requestPayResponseDto.getBody().getResponse().getCancel_amount())) {
                        // 결제금액 검증에 성공한 경우, 결제상태 == '결제완료'로 update
                        payDto.setPay_dtm(new Timestamp(requestPayResponseDto.getBody().getResponse().getPaid_at())); // 결제일시 setting TODO: 타입체크
                        updatePayStatus(payDto, "결제완료");
                    } else { // 결제금액 검증에 실패한 경우, 포트원에 결제 취소 요청을 한다.
                        cancelPayment(payDto, flag);
                    }
                } else { // 결제 실패
                    // 결제상태 == '(재)결제실패'로 update
                    handleFailedPayment(payDto, String.format("%실패", flag), String.format("% 실패", flag));
                }
            } catch (Exception e) {
                handleException("processPayment(List<PayDto> payDtoList, String flag)", e);
            }
        }
    }

    // 결제 취소 로직 수행
    private void cancelPayment(PayDto payDto, String flag) {
        ResponseEntity<PaymentResponseDto> cancelPayResponseDto = requestCancelPayToPortOne(payDto);
        // 결제 취소 성공
        if (isPaymentCancelSuccessful(cancelPayResponseDto)) {
            // 결제 취소가 되었으면, 결제상태 == '(재)결제취소'로 update
            handleFailedPayment(payDto, String.format("%s취소", flag), "결제금액 검증 실패: 결제금액 위변조 의심");
        } else { // 결제 취소 실패
            // TODO: "결제취소실패"는 따로 결제취소 재시도해야 한다.
            // 결제상태 == '(재)결제취소실패'로 update
            handleFailedPayment(payDto, String.format("%s취소실패", flag), String.format("% 취소 실패", flag));
        }
    }

    // 포트원 결제 요청
    private ResponseEntity<PaymentResponseDto> requestPayToPortOne(PayDto payDto) {
        // 1. 결제요청 시 보낼 객체를 생성한다.
        PaymentRequestDto paymentRequestDto = PaymentRequestDto.builder()
                .customer_uid(payDto.getPay_means_id())
                .merchant_uid(payDto.getOrder_list_id())
                .amount(payDto.getOrder_pay_money())
                .name(payDto.getOrder_list_id())
                .build();
        try {
            // 2. access token 을 발급받는다.
            String authToken = portOneService.getToken().getBody().getResponse().getAccess_token();
            // 3. 결제 요청한다.
            return portOneService.requestPay(paymentRequestDto, authToken);
        } catch (Exception e) {
            handleException("requestPayToPortOne(PayDto payDto)", e);
            return null;
        }
    }

    // 포트원 결제 취소 요청
    private ResponseEntity<PaymentResponseDto> requestCancelPayToPortOne(PayDto payDto) {
        // 1. 결제요청 시 보낼 객체를 생성한다.
        PaymentRequestDto paymentRequestDto = PaymentRequestDto.builder()
                .merchant_uid(payDto.getOrder_list_id())
                .build();
        try {
            // 2. access token 을 발급받는다.
            String authToken = portOneService.getToken().getBody().getResponse().getAccess_token();
            // 3. 결제 취소 요청한다.
            return portOneService.cancelPay(paymentRequestDto, authToken);
        } catch (Exception e) {
            handleException("requestCancelPayToPortOne(PayDto payDto)", e);
            return null;
        }
    }

    // 결제취소 여부 확인
    private boolean isPaymentCancelSuccessful(ResponseEntity<PaymentResponseDto> paymentResponseDto) {
        return paymentResponseDto != null && paymentResponseDto.getBody().getResponse().getStatus().equals("cancelled");
    }

    // 결제 여부 확인
    private boolean isPaymentSucessful(ResponseEntity<PaymentResponseDto> paymentResponseDto) {
        return paymentResponseDto != null && paymentResponseDto.getBody().getResponse().getStatus().equals("paid");
    }

    // (재)결제(취소) 실패 처리
    private void handleFailedPayment(PayDto payDto, String payStatus, String errorMessage) {
        // 결제상태 update
        updatePayStatus(payDto, payStatus);
        throw new RuntimeException(errorMessage);
    }

    // 결제상태 update
    private void updatePayStatus(PayDto payDto, String payStatus) {
        payDto.setPay_status(payStatus);
        try {
            payDao.updatePayStatus(payDto);
        } catch (Exception e) {
            handleException("updatePayStatus(PayDto payDto, String payStatus)", e);
        }
    }

    // 예외 처리
    private void handleException(String methodName, Exception e) {
        log.error("{} : {}\n {}\n", methodName, e.getMessage(), e.getStackTrace());
        throw new RuntimeException(e);
    }

    // 결제금액 검증 메서드 : 결제되어야 하는 금액과 실제 결제된 금액이 일치하는지 검증
    private boolean isValidPaymentAmount(BigInteger amountToBePaid, BigInteger amount) {
        return amount.equals(amountToBePaid);
    }
}

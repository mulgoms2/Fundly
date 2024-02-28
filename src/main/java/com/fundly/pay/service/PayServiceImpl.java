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

    @Override
    @Transactional(rollbackFor = Exception.class)
    // 주문테이블에서 결제테이블로 데이터 insert
    public void createPayRecordFromOrder() {
        try {
            // 1. 주문테이블에서 주문상태 == '주문완료' 데이터를 가져온다.
            List<PayDto> payDto = payDao.selectCompletedOrder();

            for (PayDto dto : payDto) {
                // 2. 결제테이블의 PK인 payId를 세팅한다.
                dto.builder().pay_id(payDao.selectPayId(userId)).build();

                // 3. 결제테이블에 insert 한다.
                payDao.insert(dto);

                // 4. 주문테이블의 pay_inserted_yn 컬럼의 값을 'Y'로 바꾼다.
                payDao.updatePayInsertedToY(dto.getOrder_list_id());
            }
        } catch (Exception e) {
            log.error("createPayRecordFromOrder() : {}\n {}\n", e.getMessage(), e.getStackTrace());
            throw new RuntimeException(e);
        }
    }


    // TODO: executePayment(), retryPayment() 중복로직 리팩토링 필요
    @Override
    @Transactional(rollbackFor = Exception.class)
    // 결제 메서드
    public void executePayment() {
        try {
            // 결제대상을 조회한다.
            List<PayDto> payDto = payDao.selectPayTarget();

            for (PayDto dto : payDto) {
                // 포트원에 결제 요청을 한다.
                ResponseEntity<PaymentResponseDto> requestPayResponseDto = requestPayToPortOne(dto);

                // 결제되지 않은 경우
                if (!requestPayResponseDto.getBody().getResponse().getStatus().equals("paid")) {
                    // 결제상태 == '결제실패'로 update
                    dto.setPay_status("결제실패");
                    payDao.updatePayStatusToFailed(dto);

                    throw new RuntimeException("결제 실패");
                } else { // 결제된 경우
                    // 결제금액 검증: 요청한 금액과 실제 결제된 금액이 같은지 비교
                    // 결제금액 검증에 실패한 경우
                    if (!validatePayAmount(dto.getOrder_pay_money(), requestPayResponseDto.getBody().getResponse().getAmount())) {
                        // 포트원에 결제 취소 요청을 한다.
                        ResponseEntity<PaymentResponseDto> cancelPayResponseDto = requestCancelPayToPortOne(dto);
                        if (!cancelPayResponseDto.getBody().getResponse().getStatus().equals("cancelled")) { // 결제 취소 실패
                            // TODO: "결제실패"가 아닌 "결제취소실패"로 바꿔야할 것 같다. "결제취소실패"는 따로 결제취소 재시도해야 한다.
                            // 결제상태 == '결제실패'로 update
                            dto.setPay_status("결제취소실패");
                            payDao.updatePayStatusToFailed(dto);

                            throw new RuntimeException("결제 취소 실패");
                        }
                        // 결제상태 == '결제실패'로 update
                        dto.setPay_status("결제실패");
                        payDao.updatePayStatusToFailed(dto);

                        throw new RuntimeException("결제금액 검증 실패: 결제금액 위변조 의심");
                    }
                    // 결제금액 검증에 성공한 경우 (status == "paid" && 결제금액 검증 성공)
                    // 결제상태 == '결제완료'로 update
                    payDao.updatePayStatusToCompleted(dto);
                }
            }
        } catch (Exception e) {
            log.error("executePayment() : {}\n {}\n", e.getMessage(), e.getStackTrace());

            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    // 재결제 메서드
    public void retryPayment() {
        try {
            // 재결제대상을 조회한다.
            List<PayDto> payDto = payDao.selectPayRetryTarget();

            for (PayDto dto : payDto) {
                // 포트원에 결제 요청을 한다.
                ResponseEntity<PaymentResponseDto> requestPayResponseDto = requestPayToPortOne(dto);

                // 결제되지 않은 경우
                if (!requestPayResponseDto.getBody().getResponse().getStatus().equals("paid")) {
                    // 결제상태 == '재결제실패'로 update
                    dto.setPay_status("재결제실패");
                    payDao.updatePayStatusToFailed(dto);

                    throw new RuntimeException("재결제 실패");
                } else { // 결제된 경우
                    // 결제금액 검증: 요청한 금액과 실제 결제된 금액이 같은지 비교
                    // 결제금액 검증에 실패한 경우
                    if (!validatePayAmount(dto.getOrder_pay_money(), requestPayResponseDto.getBody().getResponse().getAmount())) {
                        // 포트원에 결제 취소 요청을 한다.
                        ResponseEntity<PaymentResponseDto> cancelPayResponseDto = requestCancelPayToPortOne(dto);
                        if (!cancelPayResponseDto.getBody().getResponse().getStatus().equals("cancelled")) { // 결제 취소 실패
                            // TODO: "결제실패"가 아닌 "결제취소실패"로 바꿔야할 것 같다. "결제취소실패"는 따로 결제취소 재시도해야 한다.
                            // 결제상태 == '결제실패'로 update
                            dto.setPay_status("재결제취소실패");
                            payDao.updatePayStatusToFailed(dto);

                            throw new RuntimeException("재결제 취소 실패");
                        }
                        // 결제상태 == '결제실패'로 update
                        dto.setPay_status("재결제실패");
                        payDao.updatePayStatusToFailed(dto);

                        throw new RuntimeException("결제금액 검증 실패: 결제금액 위변조 의심");
                    }
                    // 결제금액 검증에 성공한 경우 (status == "paid" && 결제금액 검증 성공)
                    // 결제상태 == '결제완료'로 update
                    payDao.updatePayStatusToCompleted(dto);
                }
            }
        } catch (Exception e) {
            log.error("retryPayment() : {}\n {}\n", e.getMessage(), e.getStackTrace());

            throw new RuntimeException(e);
        }
    }

    // 결제 취소 메서드
    private ResponseEntity<PaymentResponseDto> requestCancelPayToPortOne(PayDto payDto) {
        // 1. 결제요청 시 보낼 객체를 생성한다.
        PaymentRequestDto paymentRequestDto = PaymentRequestDto.builder()
                .merchant_uid(payDto.getOrder_list_id())
                .build();

        //Todo: try~catch할지 throws Exception할지 고민
        try {
            // 2. access token 을 발급받는다.
            String authToken = portOneService.getToken().getBody().getResponse().getAccess_token();

            // 3. 결제 취소 요청한다.
            return portOneService.cancelPay(paymentRequestDto, authToken);
        } catch (Exception e) {
            log.error("ResponseEntity<PaymentResponseDto> requestCancelPayToPortOne(PayDto payDto) : {}\n {}\n", e.getMessage(), e.getStackTrace());
            throw new RuntimeException(e);
        }

    }

    // 결제 요청 메서드
    private ResponseEntity<PaymentResponseDto> requestPayToPortOne(PayDto payDto) {
        // 1. 결제요청 시 보낼 객체를 생성한다.
        PaymentRequestDto paymentRequestDto = PaymentRequestDto.builder()
                .customer_uid(payDto.getPay_means_id())
                .merchant_uid(payDto.getOrder_list_id())
                .amount(payDto.getOrder_pay_money())
                .name(payDto.getOrder_list_id())
                .build();

        //Todo: try~catch할지 throws Exception할지 고민
        try {
            // 2. access token 을 발급받는다.
            String authToken = portOneService.getToken().getBody().getResponse().getAccess_token();

            // 3. 결제 요청한다.
            return portOneService.requestPay(paymentRequestDto, authToken);
        } catch (Exception e) {
            log.error("ResponseEntity<PaymentResponseDto> requestPayToPortOne(PayDto payDto() : {}\n {}\n", e.getMessage(), e.getStackTrace());
            throw new RuntimeException(e);
        }
    }

    // 결제금액 검증 메서드
    private boolean validatePayAmount(BigInteger reqPayAmount, BigInteger resPayAmount) {
        return reqPayAmount.equals(resPayAmount);
    }
}

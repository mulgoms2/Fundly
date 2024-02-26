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
    public void createPayRecordFromOrder() throws Exception {
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
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    // 결제 메서드
    public void executePayment() throws Exception {
        // 결제대상을 조회한다.
        List<PayDto> payDto = payDao.selectPayTarget();

        for (PayDto dto : payDto) {
            try {
                // 포트원에 결제 요청을 한다.
                ResponseEntity<PaymentResponseDto> requestPayResponseDto = requestPayToPortOne(dto);
                log.info("[결제요청] getStatusCode() : " + requestPayResponseDto.getStatusCode());
                if (requestPayResponseDto.getBody().getCode() != 0) {
                    throw new RuntimeException("결제 요청 오류");
                } else {
                    // 결제되지 않은 경우
                    if (!requestPayResponseDto.getBody().getResponse().getStatus().equals("paid")) {
                        throw new RuntimeException("결제 실패");
                    } else { // 결제된 경우
                        // 결제금액 검증: 요청한 금액과 실제 결제된 금액이 같은지 비교
                        // 결제금액 검증에 실패한 경우
                        if (!validatePayAmount(dto.getOrder_pay_money(), requestPayResponseDto.getBody().getResponse().getAmount())) {
                            // 포트원에 결제 취소 요청을 한다.
                            ResponseEntity<PaymentResponseDto> cancelPayResponseDto = requestCancelPayToPortOne(dto);
//                            if (requestPayResponseDto.getBody().getCode() != 0) {
//                                throw new RuntimeException("결제 요청 오류");
//                            }
                            throw new RuntimeException("결제금액 위변조 의심");
                        } else { // 결제금액 검증에 성공한 경우
                            // 결제상태 == '결제완료'로 update
                            payDao.updatePayStatusToCompleted(dto);
                        }
                    }
                }
            } catch (Exception e) {
                // 결제상태 == '결제실패'로 update
                dto.setPay_status("결제실패");
                payDao.updatePayStatusToFailed(dto);
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    // 재결제 메서드
    public void retryPayment() throws Exception {
        // 1. 재결제대상을 조회한다.
        List<PayDto> payDto = payDao.selectPayRetryTarget();

        for (PayDto dto : payDto) {
            // 2. 포트원 결제 요청을 한다.
            ResponseEntity<PaymentResponseDto> paymentResponseDto = requestPayToPortOne(dto);

            log.info("[재결제요청] getStatusCode() : " + paymentResponseDto.getStatusCode());
            if (paymentResponseDto.getBody().getCode() == 0 && paymentResponseDto.getBody().getResponse().getStatus().equals("paid")) {
                // 4. 결제금액 검증: 요청한 금액과 실제 결제된 금액이 같은지 비교
                if (validatePayAmount(dto.getOrder_pay_money(), paymentResponseDto.getBody().getResponse().getAmount())) {
                    // 결제금액 검증에 성공하면, 결제상태 == '결제완료'로 update
                    payDao.updatePayStatusToCompleted(dto);
                } else {
                    // TODO: 결제금액 검증에 실패한 경우, 결제 취소

                }
            } else { // 결제가 수행되지 않은 경우
                // 결제 실패 시 결제상태 == '재결제실패'로 update
                dto.setPay_status("재결제실패");
                payDao.updatePayStatusToFailed(dto);
            }
        }
    }

    // 결제 취소 메서드
    private ResponseEntity<PaymentResponseDto> requestCancelPayToPortOne(PayDto payDto) throws Exception {
        // 1. access token 을 발급받는다.
        String authToken = portOneService.getToken().getResponse().getAccess_token();

        // 2. 결제요청 시 보낼 객체를 생성한다.
        PaymentRequestDto paymentRequestDto = PaymentRequestDto.builder()
                .merchant_uid(payDto.getOrder_list_id())
                .build();

        // 3. 결제 취소 요청한다.
        return portOneService.cancelPay(paymentRequestDto, authToken);
//        if (paymentResponseDto.getBody().getCode() != 0) { // 포트원 요청 실패
//            throw new Exception("포트원 결제 취소 요청 실패"); // TODO: 결제취소요청 오류나면 특별한 처리가 필요하다. 결제취소 오류건들만 모아서 재시도해야 한다.
//        } else { // 포트원 요청 성공
//            if (!paymentResponseDto.getBody().getResponse().getStatus().equals("cancelled")) { // 결제 취소 실패
//                throw new RuntimeException("결제 취소 실패");
//            } else { // 결제 취소 성공
//
//            }
//        }
    }

    // 결제 요청 메서드
    private ResponseEntity<PaymentResponseDto> requestPayToPortOne(PayDto payDto) throws Exception {
        // 1. access token 을 발급받는다.
        String authToken = portOneService.getToken().getResponse().getAccess_token();

        // 2. 결제요청 시 보낼 객체를 생성한다.
        PaymentRequestDto paymentRequestDto = PaymentRequestDto.builder()
                .customer_uid(payDto.getPay_means_id())
                .merchant_uid(payDto.getOrder_list_id())
                .amount(payDto.getOrder_pay_money())
                .name(payDto.getOrder_list_id())
                .build();

        // 3. 결제 요청한다.
        return portOneService.requestPay(paymentRequestDto, authToken);
    }

    // 결제금액 검증 메서드
    private boolean validatePayAmount(BigInteger reqPayAmount, BigInteger resPayAmount) {
        return reqPayAmount.equals(resPayAmount);
    }
}

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
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static com.fundly.pay.util.ExceptionHandlerUtil.handleException;

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

    // 결제테이블 데이터 생성: 주문테이블에서 결제테이블로 데이터 insert
    @Override
    public void createPayRecordFromOrder() {
        try {
            // 주문테이블에서 주문상태 == '주문완료' 데이터를 가져온다.
            List<PayDto> payDtoList = payDao.selectCompletedOrder();

            for (PayDto payDto : payDtoList) {
                try {
                    // 결제테이블 PK setting
                    payDto.setPay_id(payDao.selectPayId(payDto.getUser_id()));
                    // 결제테이블 insert
                    setUpAndInsertPayRecord(payDto);
                }
                catch (Exception e) {
                    // 에러가 발생해도 다음 데이터에 대한 처리가 수행되도록 로그만 출력
                    log.error("{} : {}\n {}\n", "setUpAndInsertPayRecord(payDto)", e.getMessage(), e.getStackTrace());
                }
            }
        } catch (Exception e) {
            handleException("createPayRecordFromOrder()", e);
        }
    }

    // 결제 메서드
    // 특정 결제(processPayment)에서 실패가 발생했다고 해도, 이전에 수행완료된 결제 로직이 롤백될 필요가 없기 때문에 트랜잭션 필요 X
    @Override
    public void executePayment() {
        try {
            // 결제대상을 조회한다. (결제상태 == '미결제')
            List<PayDto> payDtoList = payDao.selectPayTarget();
            // 결제 로직을 수행한다.
            for (PayDto payDto : payDtoList) {
                try {
                    processPayment(payDto, "결제");
                } catch (Exception e) {
                    // 에러가 발생해도 다음 데이터에 대한 처리가 수행되도록 로그만 출력
                    log.error("{} : {}\n {}\n", "processPayment(payDto, '결제')", e.getMessage(), e.getStackTrace());
                }
            }
        } catch (Exception e) {
            handleException("executePayment()", e);
        }
    }

    // 재결제 메서드
    // 특정 결제(processPayment)에서 실패가 발생했다고 해도, 이전에 수행완료된 결제 로직이 롤백될 필요가 없기 때문에 트랜잭션 필요 X
    @Override
    public void retryPayment() {
        try {
            // 재결제대상을 조회한다.
            List<PayDto> payDtoList = payDao.selectPayRetryTarget();
            // 결제 로직을 수행한다.
            for (PayDto payDto : payDtoList) {
                try {
                    processPayment(payDto, "재결제");
                } catch (Exception e) {
                    // 에러가 발생해도 다음 데이터에 대한 처리가 수행되도록 로그만 출력
                    log.error("{} : {}\n {}\n", "processPayment(payDto, '재결제')", e.getMessage(), e.getStackTrace());
                }
            }
        } catch (Exception e) {
            handleException("retryPayment()", e);
        }
    }

    // 결제테이블 insert
    @Override
    @Transactional // insert()와 updatePayInsertedToY()가 한 번에 수행되어야 하므로 트랜잭션 필요
    public void setUpAndInsertPayRecord(PayDto payDto) {
        try {
            // 1. 결제테이블에 insert 한다.
            payDao.insert(payDto);
            // 2. 주문테이블의 pay_inserted_yn 컬럼의 값을 'Y'로 바꾼다.
            payDao.updatePayInsertedToY(payDto.getOrder_list_id());
        } catch (Exception e) {
            handleException("setUpAndInsertPayRecord(PayDto payDto)", e);
        }
    }

    // 결제 로직
    @Override
    public void processPayment(PayDto payDto, String flag) {
        try {
            // 1. 포트원에 결제 요청을 한다.
            ResponseEntity<PaymentResponseDto> requestPayResponseDto = requestPayToPortOne(payDto, flag);

            // 2. 결제금액 검증: 요청한 금액과 실제 결제된 금액이 같은지 비교
            // 결제금액 검증 성공 시, 결제상태 == '결제완료'로 update
            if (isValidPaymentAmount(payDto.getPay_money(), requestPayResponseDto.getBody().getResponse().getAmount())) {
                Instant instant = Instant.ofEpochSecond(requestPayResponseDto.getBody().getResponse().getPaid_at()); // Unix Epoch Time을 Instant 객체로 변환
                LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("UTC")); // Instant를 LocalDateTime으로 변환
                // 1) 결제일시 setting
                payDto.setPay_dtm(localDateTime);
                // 2) 결제상태 == '결제완료'로 update
                try {
                    updatePayStatus(payDto, "결제완료");
                } catch (Exception e) {
                    // 결제완료 상태 update 실패 시, 결제 취소 요청
                    cancelPayment(payDto, flag);
                }
            } else { // 결제금액 검증 실패 시, 결제 취소 요청
                cancelPayment(payDto, flag);
            }
        } catch (Exception e) {
            handleException("processPayment(PayDto payDto, String flag)", e);
        }
    }

    // 결제취소 로직
    @Override
    public void cancelPayment(PayDto payDto, String flag) {
        try {
            // 결제취소 요청
            requestCancelPayToPortOne(payDto, flag);
            // 결제취소 성공 시, 결제상태 == '(재)결제실패'로 update
            handleFailedPayment(payDto, String.format("%s실패", flag));
        } catch (Exception e) {
            handleException("cancelPayment(PayDto payDto, String flag)", e);
        }
    }

    // 포트원 결제 요청
    @Override
    public ResponseEntity<PaymentResponseDto> requestPayToPortOne(PayDto payDto, String flag) {
        // 1. 결제요청 시 보낼 객체를 생성한다.
        PaymentRequestDto paymentRequestDto = PaymentRequestDto.builder()
                .customer_uid(payDto.getPay_means_id())
                .merchant_uid(payDto.getOrder_list_id())
                .amount(payDto.getPay_money())
                .name(payDto.getOrder_list_id())
                .build();
        try {
            // 2. access token 을 발급받는다.
            String authToken = portOneService.getToken().getBody().getResponse().getAccess_token();
            // 3. 결제 요청한다.
            return portOneService.requestPay(paymentRequestDto, authToken);
        } catch (Exception e) { // 에러 발생 시, 결제상태 == '(재)결제실패'로 update
            handleFailedPayment(payDto, String.format("%s실패", flag));
            return null;
        }
    }

    // 포트원 결제취소 요청
    private void requestCancelPayToPortOne(PayDto payDto, String flag) {
        // 1. 결제요청 시 보낼 객체를 생성한다.
        PaymentRequestDto paymentRequestDto = PaymentRequestDto.builder()
                .merchant_uid(payDto.getOrder_list_id())
                .build();
        try {
            // 2. access token 을 발급받는다.
            String authToken = portOneService.getToken().getBody().getResponse().getAccess_token();
            // 3. 결제 취소 요청한다.
            portOneService.cancelPay(paymentRequestDto, authToken);
        } catch (Exception e) { // 에러 발생 시, 결제상태 == '(재)결제취소실패'로 update
            handleFailedPayment(payDto, String.format("%s취소실패", flag));
        }
    }

    // (재)결제(취소) 실패 처리
    private void handleFailedPayment(PayDto payDto, String payStatus) {
        try {
            updatePayStatus(payDto, payStatus); // 결제상태 update
        } catch (Exception e) {}

        String errorMessage = String.format("%s - 결제ID: %s, 주문ID: %s, 결제수단: %s, 결제금액: %s",
                payStatus, payDto.getPay_id(), payDto.getOrder_list_id(), payDto.getPay_means_id(), payDto.getPay_money());

        throw new RuntimeException(errorMessage);
    }

    // 결제상태 update
    @Override
    @Transactional // updatePayStatus()와 insertPayHistory()가 한 번에 수행되어야 하므로 트랜잭션 필요
    public void updatePayStatus(PayDto payDto, String payStatus) {
        payDto.setPay_status(payStatus);
        try {
            payDao.updatePayStatus(payDto); // 결제상태 update
            payDto.setPay_status_hist_id(payDao.selectPayStatusHistId(payDto.getUser_id())); // 결제상태이력 PK
            payDto.setDba_mod_dtm(payDao.selectPayModDtm(payDto.getPay_id())); // 결제상태 udpate 시각 조회
            payDao.insertPayHistory(payDto); // 결제상태이력 테이블에 insert
        } catch (Exception e) {
            // 앞에 결제로직이 수행되었는데 결제상태만 update되지 않은 경우는 어떻게 해야 하나? 일단 로그를 남기자.
            handleException("updatePayStatus(PayDto payDto, String payStatus)", e);
        }
    }

    // 결제금액 검증 메서드 : 결제되어야 하는 금액과 실제 결제된 금액이 일치하는지 검증
    public boolean isValidPaymentAmount(BigInteger amountToBePaid, BigInteger amount) {
        return amount.equals(amountToBePaid);
    }

    // ---------------------------- Transaction Test Methods >.< ----------------------------
    // 결제테이블 insert
    @Transactional // insert()와 updatePayInsertedToY()가 한 번에 수행되어야 하므로 트랜잭션 필요
    public void setUpAndInsertPayRecordForTest(PayDto payDto) {
        try {
            // 1. 결제테이블에 insert 한다.
            payDao.insert(payDto);
            // 2. 주문테이블의 pay_inserted_yn 컬럼의 값을 'Y'로 바꾼다.
            payDao.updatePayInsertedToY(payDto.getOrder_list_id());

            throw new RuntimeException("this is intentional error!"); // 트랜잭션 테스트를 위해 의도적인 에러 발생
        } catch (Exception e) {
            handleException("setUpAndInsertPayRecord(PayDto payDto)", e);
        }
    }

    // 결제상태 update
    @Transactional // updatePayStatus()와 insertPayHistory()가 한 번에 수행되어야 하므로 트랜잭션 필요
    public void updatePayStatusForTest(PayDto payDto, String payStatus) {
        payDto.setPay_status(payStatus);
        try {
            payDao.updatePayStatus(payDto); // 결제상태 update
            payDto.setDba_mod_dtm(payDao.selectPayModDtm(payDto.getPay_id())); // 결제상태 udpate 시각 조회
            payDao.insertPayHistory(payDto); // 결제상태이력 테이블에 insert

            throw new RuntimeException("this is intentional error!"); // 트랜잭션 테스트를 위해 의도적인 에러 발생
        } catch (Exception e) {
            // 앞에 결제로직이 수행되었는데 결제상태만 update되지 않은 경우는 어떻게 해야 하나? 일단 로그를 남기자.
            handleException("updatePayStatus(PayDto payDto, String payStatus)", e);
        }
    }
}

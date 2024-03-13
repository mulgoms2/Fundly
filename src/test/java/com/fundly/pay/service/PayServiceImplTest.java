package com.fundly.pay.service;

import com.fundly.pay.dto.payment.PaymentResponseDto;
import com.fundly.pay.model.PayDao;
import com.persistence.dto.PayDto;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static com.fundly.pay.util.ExceptionHandlerUtil.handleException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
class PayServiceImplTest {

    @Autowired
    PayService payService;
    @Autowired
    PayDao payDao;
    @Autowired
    PortOneService portOneService;

    String userId = "test";
    String payMeansId = "PAY-MEANS_am0229@naver.com_1"; // TODO: 결제수단 등록 선행
    BigInteger payMoney = BigInteger.valueOf(100L);
    LocalDateTime payDueDtm = LocalDateTime.now(); // 결제예정일
    LocalDateTime payDdlnDtm = LocalDateTime.now()
            .plusDays(14); // 결제마감일
    String payId;
    PayDto payDto;
//    String adrId = "ADR_test_1";
//    String pjId = "PJ_test_1";

    @BeforeEach
    @SneakyThrows
    @DisplayName("테스트 전 셋업")
    public void beforeTest() {
        // 데이터 Reset
        payDao.deleteAllOrder();
        payDao.deleteAllPay();
        payDao.deleteAllPayStatusHist();

        payId = payDao.selectPayId(userId);
        payDto = PayDto.builder()
                .user_id(userId)
                .pay_id(payId)
                .pay_means_id(payMeansId)
                .pay_money(payMoney)
                .pay_due_dtm(payDueDtm)
                .pay_ddln_dtm(payDdlnDtm)
                .build();

        // given
        insertOrderData(); // 주문데이터 생성
    }

    @Test
    @SneakyThrows
    public void insertOrderData() {
        // 주문테이블 insert
        String orderListId = payDao.selectOrderListId(userId);
        payDto.setOrder_list_id(orderListId);
        assertEquals(payDao.insertIntoOrder(payDto), 1);

        // insert한 데이터 확인
        assertTrue(payDao.selectOrderData(orderListId) != null);
        assertEquals(payDao.selectOrderData(orderListId)
                .getOrder_list_id(), orderListId);
        assertEquals(payDao.selectOrderData(orderListId)
                .getOrder_status(), "010002"); // default: '주문완료'
        assertEquals(payDao.selectOrderData(orderListId)
                .getPay_means_id(), payMeansId);
        assertEquals(payDao.selectOrderData(orderListId)
                .getPay_inserted_yn(), 'N'); // default: N
    }

    @DisplayName("테스트 후 데이터 리셋")
    @SneakyThrows
    @AfterEach
    public void afterTest() {
        // 데이터 Reset
        payDao.deleteAllOrder();
        payDao.deleteAllPay();
        payDao.deleteAllPayStatusHist();
    }

    @Test
    @SneakyThrows
    @DisplayName("결제상태 update")
    public void updatePayStatusTest() {
        // given
        initDataForUpdateStatusTest();

        // when
        String methodName = "updatePayStatusTest";
        verifyUpdatePayStatus("결제실패", methodName);
        verifyUpdatePayStatus("재결제실패", methodName);
        verifyUpdatePayStatus("결제완료", methodName);
        verifyUpdatePayStatus("결제취소실패", methodName);
        verifyUpdatePayStatus("재결제취소실패", methodName);
        verifyUpdatePayStatus("미결제", methodName);
    }

    @Test
    @SneakyThrows
    @DisplayName("[TransactionTest] 결제상태 update")
    public void updatePayStatusTransactionTest() {
        // given
        initDataForUpdateStatusTest();

        // when
        String methodName = "updatePayStatusTransactionTest";
        verifyUpdatePayStatus("결제실패", methodName);
        verifyUpdatePayStatus("재결제실패", methodName);
        verifyUpdatePayStatus("결제완료", methodName);
        verifyUpdatePayStatus("결제취소실패", methodName);
        verifyUpdatePayStatus("재결제취소실패", methodName);
        verifyUpdatePayStatus("미결제", methodName);
    }

    @SneakyThrows
    private void initDataForUpdateStatusTest() {
        payService.setUpAndInsertPayRecord(payDto); // 결제데이터 생성
        payDto.setPay_status_hist_id(payDao.selectPayStatusHistId(userId)); // pay_status_hist_id 초기화
        assertEquals(payDao.selectPayData(payId)
                .getPay_status(), "001001"); // before: 미결제
        assertTrue(payDao.selectPayStatusHistData(payDto.getPay_status_hist_id()) == null); // before
    }

    @SneakyThrows
    private void verifyUpdatePayStatus(String payStatus, String methodName) {
        String currStatusCode = payDao.selectPayData(payId)
                .getPay_status() == null ? "001001" : payDao.selectPayData(payId)
                .getPay_status(); // 기존 결제상태코드
        String targetStatusCode = getPayStatusCode(payStatus); // 변경할 결제상태코드

        payDto.setPay_status_hist_id(payDao.selectPayStatusHistId(userId)); // 결제상태이력테이블 PK 생성
        if (methodName == "updatePayStatusTest") {
            // when
            payService.updatePayStatus(payDto, payStatus);

            // then
            assertEquals(payDao.selectPayData(payId)
                    .getPay_status(), targetStatusCode); // 새로운 결제상태로 update
            assertEquals(payDao.selectPayStatusHistData(payDto.getPay_status_hist_id())
                    .getPay_status(), targetStatusCode);
            assertEquals(payDao.selectPayStatusHistData(payDto.getPay_status_hist_id())
                    .getMod_dtm(), payDao.selectPayData(payId)
                    .getDba_mod_dtm());
        } else { // 트랜잭션 테스트인 경우
            try {
                // when - 의도적으로 에러를 발생시키는 테스트 메서드 실행
                payService.updatePayStatusForTest(payDto, payStatus);
            } catch (Exception e) {
                log.error("{} : {}\n {}\n", "verifyUpdatePayStatus()", e.getMessage(), e.getStackTrace());
            }
            // then
            assertEquals(payDao.selectPayData(payId)
                    .getPay_status(), currStatusCode); // 기존 결제상태로 rollback
            assertTrue(payDao.selectPayStatusHistData(payDto.getPay_status_hist_id()) == null); // 결제상태이력 insert rollback
        }
    }

    private String getPayStatusCode(String payStatus) {
        switch (payStatus) {
            case "미결제":
                return "001001";
            case "결제실패":
                return "001002";
            case "재결제실패":
                return "001003";
            case "결제완료":
                return "001004";
            case "결제취소실패":
                return "001005";
            case "재결제취소실패":
                return "001006";
            default:
                throw new IllegalArgumentException("Invalid payStatus: " + payStatus);
        }
    }

    @Test
    @SneakyThrows
    @DisplayName("결제데이터 생성 및 주문데이터 변경")
    public void setUpAndInsertPayRecordTest() {
        // when
        payService.setUpAndInsertPayRecord(payDto);

        // then
        // insert 검증
        PayDto insertedPayData = payDao.selectPayData(payDto.getPay_id());
        assertTrue(insertedPayData != null);
        assertEquals(insertedPayData.getPay_id(), payId);
        assertEquals(insertedPayData.getOrder_list_id(), payDto.getOrder_list_id());
        assertEquals(insertedPayData.getPay_status(), "001001"); // default: 미결제

        // after: 주문테이블 pay_inserted_yn == 'Y'
        assertEquals(payDao.selectOrderData(payDto.getOrder_list_id())
                .getPay_inserted_yn(), 'Y');
    }

    @Test
    @SneakyThrows
    @DisplayName("[TransactionTest] 결제데이터 생성 및 주문데이터 변경")
    public void setUpAndInsertPayRecordTransactionTest() {
        try {
            // when - 의도적으로 에러를 발생시키는 테스트 메서드 실행
            payService.setUpAndInsertPayRecordForTest(payDto);
//            setUpAndInsertPayRecord(payDto);
        } catch (Exception e) {
            log.error("{} : {}\n {}\n", "setUpAndInsertPayRecordTransactionTest()", e.getMessage(), e.getStackTrace());
        }
        // then
        assertTrue(payDao.selectPayData(payDto.getPay_id()) == null); // insert 롤백되어 null
        assertEquals(payDao.selectOrderData(payDto.getOrder_list_id())
                .getPay_inserted_yn(), 'N'); // update 롤백되어 'N'
    }

    @Test
    @SneakyThrows
    @DisplayName("결제데이터 생성 테스트")
    void createPayRecordFromOrderTest() {
        // given
        for (int i = 0; i < 3; i++) {
            insertOrderData();
        } // 주문데이터 생성

        // when
        payService.createPayRecordFromOrder(); // 테스트 메서드 실행

        // then
        // 결제테이블에 insert된 주문데이터의 pay_inserterd_yn이 'Y'로 업데이트되었는지 확인
        for (PayDto pay : payDao.selectAll()) {
            assertEquals(payDao.selectOrderData(pay.getOrder_list_id())
                    .getPay_inserted_yn(), 'Y');
            assertEquals(pay.getPay_status(), "001001");
        }
    }

    @Test
    @SneakyThrows
    @DisplayName("결제 성공 테스트")
    void processPaymentSuccessTest() {
        // given
        insertOrderData(); // 주문데이터 세팅
        initDataForUpdateStatusTest(); // 결제데이터 세팅
        String flag = "결제";

        // when
        payService.processPayment(payDto, flag); // 결제 로직 실행

        // then - 결제 성공 시
        assertEquals(payDao.selectPayData(payId)
                .getPay_status(), getPayStatusCode("결제완료")); // '결제완료'로 update
        assertEquals(payDao.selectPayStatusHistData(payDto.getPay_status_hist_id())
                .getPay_status(), getPayStatusCode("결제완료"));
        assertEquals(payDao.selectPayStatusHistData(payDto.getPay_status_hist_id())
                .getMod_dtm(), payDao.selectPayData(payId)
                .getDba_mod_dtm());
    }

    @Test
    @SneakyThrows
    @DisplayName("결제취소 성공 테스트")
    void cancelPaymentSuccessTest() {
        // given
        insertOrderData(); // 주문데이터 세팅
        initDataForUpdateStatusTest(); // 결제데이터 세팅
        String flag = "결제"; // "결제" 또는 "재결제"
        payService.requestPayToPortOne(payDto, flag); // 결제

        // when
        try {
            payService.cancelPayment(payDto, flag); // 결제취소 로직 실행
        } catch (Exception e) {
            // 결제취소 성공 시 예외를 던지기 때문에 처리 필요
            log.error("{} : {}\n {}\n", "cancelPaymentTest()", e.getMessage(), e.getStackTrace());
        }
        // then - 결제취소 성공 결과
        assertEquals(payDao.selectPayData(payId)
                .getPay_status(), getPayStatusCode(flag + "실패")); // flag+"실패'로 update
        assertEquals(payDao.selectPayStatusHistData(payDto.getPay_status_hist_id())
                .getPay_status(), getPayStatusCode(flag + "실패"));
        assertEquals(payDao.selectPayStatusHistData(payDto.getPay_status_hist_id())
                .getMod_dtm(), payDao.selectPayData(payId)
                .getDba_mod_dtm());
    }

    @Test
    @SneakyThrows
    @DisplayName("결제취소 실패 테스트")
    void cancelPaymentFailTest() {
        // given
        // + 결제를 수행하지 않았기 때문에 필연적으로 에러가 발생할 수 밖에 없는 상황. [취소할 결제건이 존재하지 않음]
        insertOrderData(); // 주문데이터 세팅
        initDataForUpdateStatusTest(); // 결제데이터 세팅
        String flag = "재결제"; // "결제" 또는 "재결제"

        // when
        try {
            payService.cancelPayment(payDto, flag); // 결제취소 로직 실행
        } catch (Exception e) {
            // 결제취소 실패 시 예외를 던지기 때문에 처리 필요
            log.error("{} : {}\n {}\n", "cancelPaymentFailTest()", e.getMessage(), e.getStackTrace());
        }
        // then - 결제취소 실패 결과
        assertEquals(payDao.selectPayData(payId)
                .getPay_status(), getPayStatusCode(flag + "취소실패")); // flag+"실패'로 update
        assertEquals(payDao.selectPayStatusHistData(payDto.getPay_status_hist_id())
                .getPay_status(), getPayStatusCode(flag + "취소실패"));
        assertEquals(payDao.selectPayStatusHistData(payDto.getPay_status_hist_id())
                .getMod_dtm(), payDao.selectPayData(payId)
                .getDba_mod_dtm());
    }

    @Test
    @SneakyThrows
    @DisplayName("결제 실패 테스트 [사유: 결제금액 검증실패]")
    void processPaymentValidFailTest() {
        // given
        insertOrderData(); // 주문데이터 세팅
        initDataForUpdateStatusTest(); // 결제데이터 세팅
        String flag = "재결제";

        // when
        try {
            processPaymentForValidFailTest(payDto, flag); // 결제 로직 실행
        } catch (Exception e) {
            // cancelPayment() 결제취소 성공 이후 예외를 던지기 때문에 처리 필요
            log.error("{} : {}\n {}\n", "processPaymentFailTest()", e.getMessage(), e.getStackTrace());
        }

        // then - 결제금액 검증실패 -> (재)결제취소 성공 결과 = (재)결제실패
        assertEquals(payDao.selectPayData(payId)
                .getPay_status(), getPayStatusCode(flag + "실패")); // flag+"실패'로 update
        assertEquals(payDao.selectPayStatusHistData(payDto.getPay_status_hist_id())
                .getPay_status(), getPayStatusCode(flag + "실패"));
        assertEquals(payDao.selectPayStatusHistData(payDto.getPay_status_hist_id())
                .getMod_dtm(), payDao.selectPayData(payId)
                .getDba_mod_dtm());
    }

    @Test
    @SneakyThrows
    @DisplayName("결제 실패 테스트 [사유: 결제상태 업데이트 실패]")
    void processPaymentUpdateFailTest() {
        // given
        insertOrderData(); // 주문데이터 세팅
        initDataForUpdateStatusTest(); // 결제데이터 세팅
        String flag = "재결제";

        // when
        try {
            processPaymentForUpdateFailTest(payDto, flag); // 결제 로직 실행
        } catch (Exception e) {
            // cancelPayment() 결제취소 성공 이후 예외를 던지기 때문에 처리 필요
            log.error("{} : {}\n {}\n", "processPaymentUpdateFailTest()", e.getMessage(), e.getStackTrace());
        }

        // then - 결제상태 업데이트 실패 -> (재)결제취소 성공 결과 = (재)결제실패
        assertEquals(payDao.selectPayData(payId)
                .getPay_status(), getPayStatusCode(flag + "실패")); // flag+"실패'로 update
        assertEquals(payDao.selectPayStatusHistData(payDto.getPay_status_hist_id())
                .getPay_status(), getPayStatusCode(flag + "실패"));
        assertEquals(payDao.selectPayStatusHistData(payDto.getPay_status_hist_id())
                .getMod_dtm(), payDao.selectPayData(payId)
                .getDba_mod_dtm());
    }

    // 결제금액 검증실패로 인한 결제실패 메서드
    private void processPaymentForValidFailTest(PayDto payDto, String flag) {
        BigInteger fakeMoney = payDto.getPay_money()
                .add(BigInteger.valueOf(1000)); // 위조 금액
        try {
            // 1. 포트원에 결제 요청을 한다.
            ResponseEntity<PaymentResponseDto> requestPayResponseDto = payService.requestPayToPortOne(payDto, flag);

            // 2. 결제금액 검증: 요청한 금액과 실제 결제된 금액이 같은지 비교
            // 결제금액 검증 성공 시, 결제상태 == '결제완료'로 update
            if (payService.isValidPaymentAmount(payDto.getPay_money(), fakeMoney)) { // false. error point
                Instant instant = Instant.ofEpochSecond(requestPayResponseDto.getBody()
                        .getResponse()
                        .getPaid_at()); // Unix Epoch Time을 Instant 객체로 변환
                LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("UTC")); // Instant를 LocalDateTime으로 변환
                // 1) 결제일시 setting
                payDto.setPay_dtm(localDateTime);
                // 2) 결제상태 == '결제완료'로 update
                try {
                    payService.updatePayStatus(payDto, "결제완료");
                } catch (Exception e) {
                    // 결제완료 상태 update 실패 시, 결제 취소 요청
                    payService.cancelPayment(payDto, flag);
                }
            } else { // 결제금액 검증 실패 시, 결제 취소 요청
                payService.cancelPayment(payDto, flag);
            }
        } catch (Exception e) {
            handleException("processPayment(PayDto payDto, String flag)", e);
        }
    }

    // 결제상태 업데이트 실패로 인한 결제실패 메서드
    private void processPaymentForUpdateFailTest(PayDto payDto, String flag) {
        try {
            // 1. 포트원에 결제 요청을 한다.
            ResponseEntity<PaymentResponseDto> requestPayResponseDto = payService.requestPayToPortOne(payDto, flag);

            // 2. 결제금액 검증: 요청한 금액과 실제 결제된 금액이 같은지 비교
            // 결제금액 검증 성공 시, 결제상태 == '결제완료'로 update
            if (payService.isValidPaymentAmount(payDto.getPay_money(), requestPayResponseDto.getBody()
                    .getResponse()
                    .getAmount())) {
                Instant instant = Instant.ofEpochSecond(requestPayResponseDto.getBody()
                        .getResponse()
                        .getPaid_at()); // Unix Epoch Time을 Instant 객체로 변환
                LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("UTC")); // Instant를 LocalDateTime으로 변환
                // 1) 결제일시 setting
                payDto.setPay_dtm(localDateTime);
                // 2) 결제상태 == '결제완료'로 update
                try {
                    payService.updatePayStatusForTest(payDto, "결제완료"); // error point
                } catch (Exception e) {
                    // 결제완료 상태 update 실패 시, 결제 취소 요청
                    payService.cancelPayment(payDto, flag);
                }
            } else { // 결제금액 검증 실패 시, 결제 취소 요청
                payService.cancelPayment(payDto, flag);
            }
        } catch (Exception e) {
            handleException("processPayment(PayDto payDto, String flag)", e);
        }
    }

    @Test
    @SneakyThrows
    @DisplayName("복수데이터 결제 테스트")
    void executePaymentTest() {
        // given
        for (int i = 0; i < 3; i++) {
            insertOrderData();
        } // 주문데이터 생성
        payService.createPayRecordFromOrder(); // 결제데이터 생성

        // when
        payService.executePayment();

        // then
        List<PayDto> payDtoList = payDao.selectAll();
        for (int i = 0; i < payDtoList.size(); i++) {
            if (i == 0) { // 이미 주문이 이루어진 id -> 실패
                assertEquals(payDtoList.get(i)
                        .getPay_status(), getPayStatusCode("결제실패"));
            } else {
                assertEquals(payDtoList.get(i)
                        .getPay_status(), getPayStatusCode("결제완료"));
            }
        }
    }

    @Test
    @SneakyThrows
    @DisplayName("복수데이터 재결제 테스트")
    void retryPaymentTest() {
        // given
        for (int i = 0; i < 3; i++) {
            insertOrderData();
        } // 주문데이터 생성
        payService.createPayRecordFromOrder(); // 결제데이터 생성

        // 재결제대상 데이터로 가공
        for (PayDto pay : payDao.selectAll()) {
            pay.setPay_due_dtm(pay.getPay_due_dtm()
                    .minusDays(1)); // 결제예정일을 어제로 설정 (재결제예정이 오늘)
            payService.updatePayStatus(pay, "결제실패");// '결제실패'로 update
            assertEquals(payDao.selectByPayId(pay.getPay_id())
                    .getPay_status(), getPayStatusCode("결제실패"));
        }

        // when
        payService.retryPayment();

        // then
        List<PayDto> payDtoList = payDao.selectAll();
        for (int i = 0; i < payDtoList.size(); i++) {
            if (i == 0) { // 이미 주문이 이루어진 id -> 실패
                assertEquals(payDtoList.get(i)
                        .getPay_status(), getPayStatusCode("재결제실패"));
            } else {
                assertEquals(payDtoList.get(i)
                        .getPay_status(), getPayStatusCode("결제완료"));
            }
        }
    }
}
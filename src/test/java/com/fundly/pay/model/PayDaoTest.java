package com.fundly.pay.model;

import com.persistence.dto.PayDto;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
class PayDaoTest {

    @Autowired
    PayDao payDao;

    String userId = "test";
    String orderListId = "ORDER_test_9991";
    String orderListId2 = "ORDER_test_9992";

    @BeforeEach
    @SneakyThrows
    // Test 수행 전 초기화 코드
    void payTest() {
        // 결제테이블 row 전체 삭제
        payDao.deleteAll();

        // 주문테이블 pay_inserted_yn 값을 전부 'N'으로 초기화
        payDao.updatePayInsertedToN();

        // [selectPayTarget(결제대상조회), selectPayRetryTarget(재결제대상조회) 테스트를 위한 세팅]
        // -> 1. 주문테이블 PK 중복 에러 방지하기 위한 delete 수행
        payDao.deleteOrder(orderListId);
        payDao.deleteOrder(orderListId2);

        // -> 2-1. 결제예정일시가 오늘 날짜이면서 && 결제상태가 '미결제'인 PayDto 준비
        PayDto payDto = PayDto.builder()
                .order_list_id(orderListId)
                .pay_means_id("PAY_test_119")
                .pj_id("PJ_test_1")
                .user_id(userId)
                .order_pay_money(new BigInteger("1"))
                .pay_due_dtm(new Timestamp(System.currentTimeMillis())) // 현재 날짜
                .gift_ship_due_date(new Timestamp(System.currentTimeMillis()))
                .dba_reg_id(userId)
                .pay_inserted_yn('N')
                .build();
        // -> 2-2. 결제예정일시가 어제 날짜이면서 && 결제상태가 '결제실패'인 PayDto 준비
        // 어제 날짜 계산
        LocalDate localDate = LocalDate.now().minusDays(1);
        // LocalDate를 LocalDateTime으로 변환 (시간 부분은 00:00:00으로 설정)
        LocalDateTime localDateTime = localDate.atStartOfDay();
        // LocalDateTime을 Timestamp로 변환
        Timestamp yesterday = Timestamp.valueOf(localDateTime);

        PayDto payDto2 = PayDto.builder()
                .order_list_id(orderListId2)
                .pay_means_id("PAY_test_119")
                .pj_id("PJ_test_1")
                .user_id(userId)
                .order_pay_money(new BigInteger("1"))
                .pay_due_dtm(yesterday) // 어제 날짜
                .gift_ship_due_date(new Timestamp(System.currentTimeMillis()))
                .dba_reg_id(userId)
                .pay_status("결제실패")
                .build();

        // -> 3.주문테이블에 insert
        assertEquals(payDao.insertIntoOrder(payDto), 1, "[payTest] insertIntoOrder Error");
        assertEquals(payDao.insertIntoOrder(payDto2), 1, "[payTest] insertIntoOrder Error");

        // 주문테이블에서 '주문완료' row 가져오기
        List<PayDto> payDtoList = payDao.selectCompletedOrder();

        for (PayDto dto : payDtoList) {
            log.info("[payTest] dto.getOrder_list_id() = " + dto.getOrder_list_id());
            assertEquals(dto.getOrder_status(), "주문완료");
            assertEquals(dto.getPay_inserted_yn(), 'N');

            dto.setPay_id(payDao.selectPayId(dto.getUser_id()));
            dto.setDba_reg_id(userId);

            // 재결제대상 데이터인 경우, 결제상태를 '결제실패'로 생성
            if (dto.getOrder_list_id().equals(orderListId2)) {
                dto.setPay_status("결제실패");
            } else {
                dto.setPay_status("미결제");
            }

            // 결제테이블에 insert
            assertEquals(payDao.insert(dto), 1, "[payTest] insert Error");

            // insert 성공하면 주문테이블의 pay_inserted_yn을 'Y'로 업데이트
            payDao.updatePayInsertedToY(dto.getOrder_list_id());
        }

    }

//    @Test
//    @SneakyThrows
//    @DisplayName("주문테이블에서 '주문완료' 싱태인 행 조회")
//    void selectCompletedOrder() {
//        List<PayDto> payDto = payDao.selectCompletedOrder();
//
//        for (PayDto dto : payDto) {
//            log.info("selectCompletedOrder - getOrder_list_id : " + dto.getOrder_list_id());
//            assertEquals(dto.getOrder_status(), "주문완료");
//            assertEquals(dto.getPay_inserted_yn(), 'N');
//        }
//    }

//    @Test
//    @SneakyThrows
//    @DisplayName("결제테이블 PK 생성")
//    void selectPayId() {
//
//    }

    @Test
    @SneakyThrows
    @DisplayName("결제테이블 insert")
    void insert() {
        payDao.deleteAll();

        // selectCompletedOrder
        List<PayDto> payDto = payDao.selectCompletedOrder();

        // insert
        for (PayDto dto : payDto) {
            assertEquals(dto.getOrder_status(), "주문완료");
            assertEquals(dto.getPay_inserted_yn(), 'N');

            dto.setPay_id(payDao.selectPayId(dto.getUser_id()));
            dto.setDba_reg_id(userId);

            assertEquals(payDao.insert(dto), 1, "[insert] insert Error");
        }
    }

//    @Test
//    @SneakyThrows
//    @DisplayName("결제테이블에 insert되면 주문테이블 pay_inserted_yn 업데이트")
//    void updatePayInsertedToY() {
//        for (PayDto dto : payDao.selectAll()) {
//            assertEquals(payDao.updatePayInsertedToY(dto.getOrder_list_id()), 1, "[updatePayInsertedToY] updatePayInsertedToY Error");
//        }
//
//    }

    @Test
    @SneakyThrows
    @DisplayName("결제 대상 조회")
    void selectPayTarget() {
        // 결제예정일시가 오늘 날짜이면서 && 결제상태가 '미결제'인 row 조회
        for (PayDto dto : payDao.selectPayTarget()) {
            log.info("[결제대상] dto.getPay_id() = " + dto.getPay_id());
            assertEquals(dto.getPay_status(), "미결제", "[selectPayTarget] pay_status 검증 Error");
//            log.info("dto.getPay_due_dtm().toLocalDateTime().toLocalDate() = " + dto.getPay_due_dtm().toLocalDateTime().toLocalDate());
//            log.info("LocalDate.now() = " + LocalDate.now());
            assertEquals(dto.getPay_due_dtm().toLocalDateTime().toLocalDate(), LocalDate.now(), "[selectPayTarget] pay_due_dtm 검증 Error");
        }
    }

    @Test
    @SneakyThrows
    @DisplayName("재결제 대상 조회")
    void selectPayRetryTarget() {
        // 결제예정일시가 어제 날짜이면서 && 결제상태가 '결제실패'인 row 조회
        for (PayDto dto : payDao.selectPayRetryTarget()) {
            log.info("[재결제대상] dto.getPay_id() = " + dto.getPay_id());
            assertEquals(dto.getPay_status(), "결제실패", "[selectPayTarget] pay_status 검증 Error");
            log.info("dto.getPay_due_dtm().toLocalDateTime().toLocalDate() = " + dto.getPay_due_dtm().toLocalDateTime().toLocalDate());
            log.info("LocalDate.now().minusDays(1) = " + LocalDate.now().minusDays(1));
            assertEquals(dto.getPay_due_dtm().toLocalDateTime().toLocalDate(), LocalDate.now().minusDays(1), "[selectPayTarget] pay_due_dtm 검증 Error");
        }

    }

    @Test
    @SneakyThrows
    @DisplayName("결제상태 update ('미결제'||'결제실패' -> '결제완료') 및 결제일시 update")
    void updatePayStatusToCompleted() {
        String payId = "";

        // '미결제' + '결제실패' 상태인 객체 리스트 구하기 (결제대상조회 + 재결제대상조회)
        List<PayDto> targetList = Stream.concat(payDao.selectPayTarget().stream(), payDao.selectPayRetryTarget().stream())
                .collect(Collectors.toList());

        for (PayDto dto : targetList) {
            payId = dto.getPay_id();
            log.info("[updatePayStatusToCompleted] dto.getPay_id() = " + dto.getPay_id());

            dto.setPay_dtm(new Timestamp(System.currentTimeMillis())); // 결제일시를 현재 시간으로 update (test)
            assertEquals(payDao.updatePayStatusToCompleted(dto), 1, "[updatePayStatusToCompleted] updatePayStatusToCompleted Error");

            assertTrue(dto.getPay_status().equals("미결제") || dto.getPay_status().equals("결제실패")); // before
            assertEquals(payDao.selectByPayId(payId).getPay_status(), "결제완료"); // after
        }
    }

    @Test
    @SneakyThrows
    @DisplayName("결제상태 update ('미결제' -> '결제실패')")
    void updatePayStatusToFailed() {
        String payId = "";

        // '미결제' 데이터 조회 (결제대상)
        for (PayDto dto : payDao.selectPayTarget()) {
            payId = dto.getPay_id();
            assertEquals(dto.getPay_status(), "미결제"); // before
            assertEquals(payDao.updatePayStatusToFailed(dto), 1, "[updatePayStatusToFailed] updatePayStatusToFailed Error");
            assertEquals(payDao.selectByPayId(payId).getPay_status(), "결제실패"); // after
        }
    }

    @Test
    @SneakyThrows
    @DisplayName("결제상태 update ('결제실패' -> '재결제실패')")
    void updatePayStatusToRetryFailed() {
        String payId = "";

        // '결제실패' 데이터 조회 (재결제대상)
        for (PayDto dto : payDao.selectPayRetryTarget()) {
            payId = dto.getPay_id();
            assertEquals(dto.getPay_status(), "결제실패"); // before
            assertEquals(payDao.updatePayStatusToRetryFailed(dto), 1, "[updatePayStatusToRetryFailed] updatePayStatusToRetryFailed Error");
            assertEquals(payDao.selectByPayId(payId).getPay_status(), "재결제실패"); // after
        }
    }

    @Test
    @SneakyThrows
    @DisplayName("회원별 결제 내역 조회")
    void selectPayForUser() {
        for (PayDto dto : payDao.selectPayForUser(userId)) {
            assertEquals(dto.getUser_id(), userId, "[selectPayForUser] selectPayForUser Error");
        }
    }

    @Test
    @SneakyThrows
    @DisplayName("프로젝트별 결제 내역 조회")
    void selectPayForProject() {
        String pjId = "PJ_test_1";
        for (PayDto dto : payDao.selectPayForProject(pjId)) {
            assertEquals(dto.getPj_id(), pjId, "[selectPayForProject] selectPayForProject Error");
        }

    }


    @Test
    @SneakyThrows
    @DisplayName("전체 행 조회")
    void selectAll() {
        for (PayDto dto : payDao.selectAll()) {
            System.out.println("payDto selectAll(): " + dto);
        }
    }

    @Test
    @SneakyThrows
    @DisplayName("전체 행 삭제")
    void deleteAll() {
        payDao.deleteAll();
        assertEquals(payDao.countAll(), 0, "[deleteAll] deleteAll Error");
    }

    @Test
    @SneakyThrows
    @DisplayName("전체 행 개수 조회")
    void countAll() {
        assertEquals(payDao.countAll(), payDao.selectAll().size(), "[countAll] countAll Error");
    }

//    @Test
//    @SneakyThrows
//    @DisplayName("결제테이블 inserted 여부 'N'으로 변경")
//    void updatePayInsertedToN() {
//        payDao.updatePayInsertedToN();
//    }

    @Test
    @SneakyThrows
    @DisplayName("주문테이블에 insert")
    void insertIntoOrder() {
        // 초기화
        payDao.deleteAll();
        payDao.deleteOrder(orderListId);

        // insertIntoOrder
        PayDto payDto = PayDto.builder()
                .order_list_id(orderListId)
                .pay_means_id("PAY_test_119")
                .pj_id("PJ_test_1")
                .user_id(userId)
                .order_pay_money(new BigInteger("1"))
                .pay_due_dtm(new Timestamp(System.currentTimeMillis())) // 현재 날짜
                .gift_ship_due_date(new Timestamp(System.currentTimeMillis()))
                .dba_reg_id(userId)
                .build();

        assertEquals(payDao.insertIntoOrder(payDto), 1, "[insertIntoOrder] insertIntoOrder Error");
    }

    @Test
    @SneakyThrows
    @DisplayName("주문테이블 특정 행 delete")
    void deleteOrder() {
        payDao.deleteAll();

        assertEquals(payDao.deleteOrder(orderListId), 1, "[deleteOrder] deleteOrder Error");
    }
}
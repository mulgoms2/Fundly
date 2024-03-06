package com.fundly.pay.model;

import com.persistence.dto.PayMeansDto;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@Slf4j
@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
class PayMeansDaoTest {

    @Autowired
    PayMeansDao payMeansDao;
    String userId = "test";

    @Test
    @SneakyThrows
    void count() {
        // deleteAll
        payMeansDao.deleteAll();
        assertEquals(payMeansDao.count(), 0, "[count] count#1 실패");

        // insert 1번
        PayMeansDto payMeansDto = new PayMeansDto(userId, "personal", "test_bill_key", "N", "1234", userId);
        String payMeansId = payMeansDao.selectPayMeansId(userId);
        payMeansDto.setPay_means_id(payMeansId);
        assertEquals(payMeansDao.insert(payMeansDto), 1, "[count] insert#1 실패");
        assertEquals(payMeansDao.count(), 1, "[count] count#2 실패");

        // insert 2번
        payMeansDto = new PayMeansDto(userId, "personal", "test_bill_key2", "N", "5678", userId);
        payMeansId = payMeansDao.selectPayMeansId(userId);
        payMeansDto.setPay_means_id(payMeansId);
        assertEquals(payMeansDao.insert(payMeansDto), 1, "[count] insert#2 실패");
        assertEquals(payMeansDao.count(), 2, "[count] count#3 실패");
    }

    @Test
    void selectPayMeansId() {
    }

    @Test
    @SneakyThrows
    void insert() {
        // deleteAll
        payMeansDao.deleteAll();
        assertEquals(payMeansDao.count(), 0, "[insert] 초기화 실패");

        // insert 1번
        PayMeansDto payMeansDto = new PayMeansDto(userId, "personal", "test_bill_key", "N", "1234", userId);
        String payMeansId = payMeansDao.selectPayMeansId(userId);
        payMeansDto.setPay_means_id(payMeansId);
        assertEquals(payMeansDao.insert(payMeansDto), 1, "[insert] insert#1 실패");
        assertEquals(payMeansDao.count(), 1, "[insert] count#1 실패");
        assertEquals(payMeansDao.select(payMeansId).getPay_means_id(), payMeansDto.getPay_means_id(), "[insert] select#1 실패");

        // insert 2번
        payMeansDto = new PayMeansDto(userId, "personal", "test_bill_key2", "N", "5678", userId);
        payMeansId = payMeansDao.selectPayMeansId(userId);
        payMeansDto.setPay_means_id(payMeansId);
        assertEquals(payMeansDao.insert(payMeansDto), 1, "[insert] insert#2 실패");
        assertEquals(payMeansDao.count(), 2, "[insert] count#2 실패");
        assertEquals(payMeansDao.select(payMeansId).getPay_means_id(), payMeansDto.getPay_means_id(), "[insert] select#2 실패");
    }

    @Test
    @SneakyThrows
    void updateDefaultMeansToNo() {
        // deleteAll
        payMeansDao.deleteAll();
        assertEquals(payMeansDao.count(), 0, "[updateDefaultMeansToNo] 초기화 실패");

        // insert 1번 (default_pay_means_yn == 'N')
        PayMeansDto payMeansDto1 = new PayMeansDto(userId, "personal", "test_bill_key", "N", "1234", userId);
        String payMeansId = payMeansDao.selectPayMeansId(userId);
        payMeansDto1.setPay_means_id(payMeansId);
        assertEquals(payMeansDao.insert(payMeansDto1), 1, "[updateDefaultMeansToNo] insert#1 실패");
        assertEquals(payMeansDao.count(), 1, "[updateDefaultMeansToNo] count#1 실패");
        assertEquals(payMeansDao.select(payMeansId).getPay_means_id(), payMeansDto1.getPay_means_id(), "[updateDefaultMeansToNo] select#1 실패");

        // countDefaultMeans 1번
        assertEquals(payMeansDao.countDefaultMeans(userId), 0, "[updateDefaultMeansToNo] countDefaultMeans#1 실패");

        // updateDefaultMeansToNo 1번
        Map map = new HashMap();
        map.put("user_id", userId);
        map.put("dba_mod_id", userId);
        assertEquals(payMeansDao.updateDefaultMeansToNo(map), 0, "[updateDefaultMeansToNo] updateDefaultMeansToNo#1 실패");

        // insert 2번 (default_pay_means_yn == 'Y')
        PayMeansDto payMeansDto2 = new PayMeansDto(userId, "personal", "test_bill_key2", "Y", "5678", userId);
        payMeansId = payMeansDao.selectPayMeansId(userId);
        payMeansDto2.setPay_means_id(payMeansId);
        assertEquals(payMeansDao.insert(payMeansDto2), 1, "[updateDefaultMeansToNo] insert#2 실패");
        assertEquals(payMeansDao.count(), 2, "[updateDefaultMeansToNo] count#2 실패");
        assertEquals(payMeansDao.select(payMeansId).getPay_means_id(), payMeansDto2.getPay_means_id(), "[updateDefaultMeansToNo] select#2 실패");

        // countDefaultMeans 2번
        assertEquals(payMeansDao.countDefaultMeans(userId), 1, "[updateDefaultMeansToNo] countDefaultMeans#2 실패");

        // selectDefaultMeans 1번
        assertEquals(payMeansDao.selectDefaultMeans(userId).getDefault_pay_means_yn(), "Y", "[updateDefaultMeansToNo] selectDefaultMeans#1 실패");

        // updateDefaultMeansToNo 2번
        assertEquals(payMeansDao.updateDefaultMeansToNo(map), 1, "[updateDefaultMeansToNo] updateDefaultMeansToNo#2 실패");

        // countDefaultMeans 3번
        assertEquals(payMeansDao.countDefaultMeans(userId), 0, "[updateDefaultMeansToNo] countDefaultMeans#3 실패");

        // selectDefaultMeans 2번
        log.info("[updateDefaultMeansToNo] selectDefaultMeans#2 실패");
        assertNull(payMeansDao.selectDefaultMeans(userId));
    }

    @Test
    @SneakyThrows
    void updateDefaultMeansToYes() {
        // deleteAll
        payMeansDao.deleteAll();
        assertEquals(payMeansDao.count(), 0, "[updateDefaultMeansToYes] 초기화 실패");

        // insert 1번 (default_pay_means_yn == 'N')
        PayMeansDto payMeansDto = new PayMeansDto(userId, "personal", "test_bill_key", "N", "1234", userId);
        String payMeansId = payMeansDao.selectPayMeansId(userId);
        payMeansDto.setPay_means_id(payMeansId);
        assertEquals(payMeansDao.insert(payMeansDto), 1, "[updateDefaultMeansToYes] insert#1 실패");
        assertEquals(payMeansDao.count(), 1, "[updateDefaultMeansToYes] count#1 실패");

        // countDefaultMeans 1번
        assertEquals(payMeansDao.countDefaultMeans(userId), 0, "[updateDefaultMeansToYes] countDefaultMeans#1 실패");

        // updateDefaultMeansToYes 1번
        assertEquals(payMeansDao.updateDefaultMeansToYes(payMeansDto), 1, "[updateDefaultMeansToYes] updateDefaultMeansToYes#1 실패");

        // countDefaultMeans 2번
        assertEquals(payMeansDao.countDefaultMeans(userId), 1, "[updateDefaultMeansToYes] countDefaultMeans#2 실패");

        // select 1번
        assertEquals(payMeansDao.select(payMeansId).getDefault_pay_means_yn(), "Y", "[updateDefaultMeansToYes] select#1 실패");

        // selectDefaultMeans 1번
        assertEquals(payMeansDao.selectDefaultMeans(userId).getDefault_pay_means_yn(), "Y", "[updateDefaultMeansToYes] selectDefaultMeans#1 실패");

        // updateDefaultMeansToNo
        Map map = new HashMap();
        map.put("user_id", userId);
        map.put("dba_mod_id", userId);
        assertEquals(payMeansDao.updateDefaultMeansToNo(map), 1, "[updateDefaultMeansToYes] updateDefaultMeansToNo 실패");

        // countDefaultMeans 2번
        assertEquals(payMeansDao.countDefaultMeans(userId), 0, "[updateDefaultMeansToYes] countDefaultMeans#2 실패");

        // selectDefaultMeans 2번
        log.info("[updateDefaultMeansToYes] selectDefaultMeans#2 시도");
        assertNull(payMeansDao.selectDefaultMeans(userId));

        // select 2번
        assertEquals(payMeansDao.select(payMeansId).getDefault_pay_means_yn(), "N", "[updateDefaultMeansToYes] select#2 실패");
    }

    @Test
    @SneakyThrows
    void delete() {
        // deleteAll
        payMeansDao.deleteAll();
        assertEquals(payMeansDao.count(), 0, "[delete] 초기화 실패");

        // insert 1번
        PayMeansDto payMeansDto = new PayMeansDto(userId, "personal", "test_bill_key", "N", "1234", userId);
        String payMeansId = payMeansDao.selectPayMeansId(userId);
        payMeansDto.setPay_means_id(payMeansId);
        assertEquals(payMeansDao.insert(payMeansDto), 1, "[delete] insert#1 실패");

        // delete 1번
        assertEquals(payMeansDao.delete(payMeansId), 1, "[delete] delete#1 실패");
        log.info("[delete] select#1 시도");
        assertNull(payMeansDao.select(payMeansId));

        // insert 2번
        payMeansDto = new PayMeansDto(userId, "personal", "test_bill_key2", "N", "5678", userId);
        payMeansId = payMeansDao.selectPayMeansId(userId);
        payMeansDto.setPay_means_id(payMeansId);
        assertEquals(payMeansDao.insert(payMeansDto), 1, "[delete] insert#2 실패");

        // delete 2번
        assertEquals(payMeansDao.delete(payMeansId), 1, "[delete] delete#2 실패");
        log.info("[delete] select#2 시도");
        assertNull(payMeansDao.select(payMeansId));
    }

    @Test
    @SneakyThrows
    void deleteAll() {
        // deleteAll
        payMeansDao.deleteAll();
        assertEquals(payMeansDao.count(), 0, "[deleteAll] count#1 실패");

        // insert 1번
        PayMeansDto payMeansDto = new PayMeansDto(userId, "personal", "test_bill_key", "N", "1234", userId);
        String payMeansId = payMeansDao.selectPayMeansId(userId);
        payMeansDto.setPay_means_id(payMeansId);
        assertEquals(payMeansDao.insert(payMeansDto), 1, "[deleteAll] insert#1 실패");
        assertEquals(payMeansDao.count(), 1, "[deleteAll] count#2 실패");

        // insert 2번
        payMeansDto = new PayMeansDto(userId, "personal", "test_bill_key2", "N", "5678", userId);
        payMeansId = payMeansDao.selectPayMeansId(userId);
        payMeansDto.setPay_means_id(payMeansId);
        assertEquals(payMeansDao.insert(payMeansDto), 1, "[deleteAll] insert#2 실패");
        assertEquals(payMeansDao.count(), 2, "[deleteAll] count#3 실패");

        // deleteAll
        payMeansDao.deleteAll();
        assertEquals(payMeansDao.count(), 0, "[deleteAll] count#4 실패");
    }

    @Test
    @SneakyThrows
    void select() {
        // deleteAll
        payMeansDao.deleteAll();
        assertEquals(payMeansDao.count(), 0, "[select] 초기화 실패");

        // insert
        PayMeansDto payMeansDto = new PayMeansDto(userId, "personal", "test_bill_key", "N", "1234", userId);
        String payMeansId = payMeansDao.selectPayMeansId(userId);
        payMeansDto.setPay_means_id(payMeansId);
        assertEquals(payMeansDao.insert(payMeansDto), 1, "[select] insert 실패");
        assertEquals(payMeansDao.count(), 1, "[select] count 실패");

        // select
        assertEquals(payMeansDao.select(payMeansId).getPay_means_id(), payMeansDto.getPay_means_id(), "[select] select#1 실패");
        assertEquals(payMeansDao.select(payMeansId).getUser_id(), payMeansDto.getUser_id(), "[select] select#2 실패");
        assertEquals(payMeansDao.select(payMeansId).getCard_no(), payMeansDto.getCard_no(), "[select] select#3 실패");
        assertEquals(payMeansDao.select(payMeansId).getBill_key(), payMeansDto.getBill_key(), "[select] select#4 실패");

        // delete
        assertEquals(payMeansDao.delete(payMeansId), 1, "[select] delete 실패");

        // select
        log.info("[select] select#5 시도");
        assertNull(payMeansDao.select(payMeansId));
    }

//    @Test
//    @SneakyThrows
//    void selectAllForUser() {
//        // deleteAll
//        payMeansDao.deleteAll();
//        assertEquals(payMeansDao.count(), 0, "[selectAllForUser] 초기화 실패");
//
//        // insert 1번
//        PayMeansDto payMeansDto1 = new PayMeansDto(userId, "personal", "test_bill_key", "N", "1234", userId);
//        String payMeansId = payMeansDao.selectPayMeansId(userId);
//        payMeansDto1.setPay_means_id(payMeansId);
//        assertEquals(payMeansDao.insert(payMeansDto1), 1, "[selectAllForUser] insert#1 실패");
//        assertEquals(payMeansDao.count(), 1, "[selectAllForUser] count#1 실패");
//        assertEquals(payMeansDao.select(payMeansId).getPay_means_id(), payMeansDto1.getPay_means_id(), "[selectAllForUser] select#1 실패");
//
//        // insert 2번
//        PayMeansDto payMeansDto2 = new PayMeansDto(userId, "personal", "test_bill_key2", "N", "5678", userId);
//        payMeansId = payMeansDao.selectPayMeansId(userId);
//        payMeansDto2.setPay_means_id(payMeansId);
//        assertEquals(payMeansDao.insert(payMeansDto2), 1, "[selectAllForUser] insert#2 실패");
//        assertEquals(payMeansDao.count(), 2, "[selectAllForUser] count#2 실패");
//        assertEquals(payMeansDao.select(payMeansId).getPay_means_id(), payMeansDto2.getPay_means_id(), "[selectAllForUser] select#2 실패");
//
//        // selectAllForUser
//        List<PayMeansDto> list = payMeansDao.selectAllForUser(userId);
//        assertEquals(list.size(), payMeansDao.count(), "[selectAllForUser] selectAllForUser#1 실패");
//        assertEquals(list.get(0).getUser_id(), userId, "[selectAllForUser] selectAllForUser#2 실패");
//        assertEquals(list.get(1).getUser_id(), userId, "[selectAllForUser] selectAllForUser#3 실패");
//    }

    @Test
    @SneakyThrows
    void selectAll() {
        String userId1 = "test1";
        String userId2 = "test2";

        // deleteAll
        payMeansDao.deleteAll();
        assertEquals(payMeansDao.count(), 0, "[selectAll] 초기화 실패");

        // insert 1번
        PayMeansDto payMeansDto1 = new PayMeansDto(userId1, "personal", "test_bill_key", "N", "1234", userId1);
        String payMeansId = payMeansDao.selectPayMeansId(userId1);
        payMeansDto1.setPay_means_id(payMeansId);
        assertEquals(payMeansDao.insert(payMeansDto1), 1, "[selectAll] insert#1 실패");
        assertEquals(payMeansDao.count(), 1, "[selectAll] count#1 실패");
        assertEquals(payMeansDao.select(payMeansId).getPay_means_id(), payMeansDto1.getPay_means_id(), "[selectAll] select#1 실패");

        // insert 2번
        PayMeansDto payMeansDto2 = new PayMeansDto(userId2, "personal", "test_bill_key2", "N", "5678", userId2);
        payMeansId = payMeansDao.selectPayMeansId(userId2);
        payMeansDto2.setPay_means_id(payMeansId);
        assertEquals(payMeansDao.insert(payMeansDto2), 1, "[selectAll] insert#2 실패");
        assertEquals(payMeansDao.count(), 2, "[selectAll] count#2 실패");
        assertEquals(payMeansDao.select(payMeansId).getPay_means_id(), payMeansDto2.getPay_means_id(), "[selectAll] select#2 실패");

        // selectAll
        List<PayMeansDto> list = payMeansDao.selectAll();
        assertEquals(list.get(0).getUser_id(), payMeansDto1.getUser_id(), "[selectAll] selectAll#1 실패");
        assertEquals(list.get(1).getUser_id(), payMeansDto2.getUser_id(), "[selectAll] selectAll#1 실패");
    }

    @Test
    @SneakyThrows
    void selectDefaultMeans() {
        // deleteAll
        payMeansDao.deleteAll();
        assertEquals(payMeansDao.count(), 0, "[selectDefaultMeans] 초기화 실패");

        // insert 1번 (default_pay_means_yn == 'N')
        PayMeansDto payMeansDto1 = new PayMeansDto(userId, "personal", "test_bill_key", "N", "1234", userId);
        String payMeansId = payMeansDao.selectPayMeansId(userId);
        payMeansDto1.setPay_means_id(payMeansId);
        assertEquals(payMeansDao.insert(payMeansDto1), 1, "[selectDefaultMeans] insert#1 실패");
        assertEquals(payMeansDao.count(), 1, "[selectDefaultMeans] count#1 실패");
        assertEquals(payMeansDao.select(payMeansId).getPay_means_id(), payMeansDto1.getPay_means_id(), "[selectDefaultMeans] select#1 실패");

        // selectDefaultMeans 1번
        log.info("[selectDefaultMeans] selectDefaultMeans#1 시도");
        assertNull(payMeansDao.selectDefaultMeans(userId));

        // insert 2번 (default_pay_means_yn == 'Y')
        PayMeansDto payMeansDto2 = new PayMeansDto(userId, "personal", "test_bill_key2", "Y", "5678", userId);
        payMeansId = payMeansDao.selectPayMeansId(userId);
        payMeansDto2.setPay_means_id(payMeansId);
        assertEquals(payMeansDao.insert(payMeansDto2), 1, "[selectDefaultMeans] insert#2 실패");
        assertEquals(payMeansDao.count(), 2, "[selectDefaultMeans] count#2 실패");
        assertEquals(payMeansDao.select(payMeansId).getPay_means_id(), payMeansDto2.getPay_means_id(), "[selectDefaultMeans] select#2 실패");

        // selectDefaultMeans 2번
        assertEquals(payMeansDao.selectDefaultMeans(userId).getDefault_pay_means_yn(), "Y", "[selectDefaultMeans] selectDefaultMeans#2 실패");

        // delete
        assertEquals(payMeansDao.delete(payMeansDto2.getPay_means_id()), 1, "[selectDefaultMeans] delete 실패");

        // selectDefaultMeans 3번
        log.info("[selectDefaultMeans] selectDefaultMeans#3 시도");
        assertNull(payMeansDao.selectDefaultMeans(userId));
    }

    @Test
    @SneakyThrows
    void countDefaultMeans() {
        // deleteAll
        payMeansDao.deleteAll();
        assertEquals(payMeansDao.count(), 0, "[countDefaultMeans] 초기화 실패");

        // insert 1번 (default_pay_means_yn == 'N')
        PayMeansDto payMeansDto1 = new PayMeansDto(userId, "personal", "test_bill_key", "N", "1234", userId);
        String payMeansId = payMeansDao.selectPayMeansId(userId);
        payMeansDto1.setPay_means_id(payMeansId);
        assertEquals(payMeansDao.insert(payMeansDto1), 1, "[countDefaultMeans] insert#1 실패");
        assertEquals(payMeansDao.count(), 1, "[countDefaultMeans] count#1 실패");
        assertEquals(payMeansDao.select(payMeansId).getPay_means_id(), payMeansDto1.getPay_means_id(), "[countDefaultMeans] select#1 실패");

        // countDefaultMeans 1번
        assertEquals(payMeansDao.countDefaultMeans(userId), 0, "[countDefaultMeans] countDefaultMeans#1 실패");

        // insert 2번 (default_pay_means_yn == 'Y')
        PayMeansDto payMeansDto2 = new PayMeansDto(userId, "personal", "test_bill_key2", "Y", "5678", userId);
        payMeansId = payMeansDao.selectPayMeansId(userId);
        payMeansDto2.setPay_means_id(payMeansId);
        assertEquals(payMeansDao.insert(payMeansDto2), 1, "[countDefaultMeans] insert#2 실패");
        assertEquals(payMeansDao.count(), 2, "[countDefaultMeans] count#2 실패");
        assertEquals(payMeansDao.select(payMeansId).getPay_means_id(), payMeansDto2.getPay_means_id(), "[countDefaultMeans] select#2 실패");

        // countDefaultMeans 2번
        assertEquals(payMeansDao.countDefaultMeans(userId), 1, "[countDefaultMeans] countDefaultMeans#2 실패");
    }
}
package com.fundly.pay.model;

import com.persistence.dto.PayDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Repository
public interface PayDao {
    List<PayDto> selectCompletedOrder() throws Exception;
    String selectPayId(String userId) throws Exception;
    int insert(PayDto payDto) throws Exception;
    PayDto selectPayData(String payId) throws Exception;
    int updatePayInsertedToY(String orderListId) throws Exception;
    List<PayDto> selectPayTarget() throws Exception;
    List<PayDto> selectPayRetryTarget() throws Exception;
    int updatePayStatus(PayDto payDto) throws Exception;
    String selectPayStatusHistId(String userId) throws Exception;
    int insertPayHistory(PayDto payDto) throws Exception;
    LocalDateTime selectPayModDtm(String payId) throws Exception;
    PayDto selectPayStatusHistData(String payStatusHistId) throws Exception;
    int deleteAllPayStatusHist() throws Exception;
    List<PayDto> selectPayForUser(String userId) throws Exception;
    List<PayDto> selectPayForProject(String pjId) throws Exception;
    List<PayDto> selectAll() throws Exception;
    PayDto selectByPayId(String payId) throws Exception;
    int deleteAllPay() throws Exception;
    int countAll() throws Exception;
    int updatePayInsertedToN() throws Exception;
    String selectOrderListId(String userId) throws Exception;
    int insertIntoOrder(PayDto payDto) throws Exception;
    PayDto selectOrderData(String orderListId) throws Exception;
    List<PayDto> selectAllOrder() throws Exception;
    int deleteOrder(String orderListId) throws Exception;
    int deleteAllOrder() throws Exception;
}

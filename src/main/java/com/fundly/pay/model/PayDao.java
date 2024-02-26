package com.fundly.pay.model;

import com.persistence.dto.PayDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PayDao {
    List<PayDto> selectCompletedOrder() throws Exception;
    String selectPayId(String userId) throws Exception;
    int insert(PayDto payDto) throws Exception;
    int updatePayInsertedToY(String orderListid) throws Exception;
    List<PayDto> selectPayTarget() throws Exception;
    List<PayDto> selectPayRetryTarget() throws Exception;
    int updatePayStatusToCompleted(PayDto payDto) throws Exception;
    int updatePayStatusToFailed(PayDto payDto) throws Exception;
//    int updatePayStatusToRetryFailed(PayDto payDto) throws Exception;
    List<PayDto> selectPayForUser(String userId) throws Exception;
    List<PayDto> selectPayForProject(String pjId) throws Exception;
    List<PayDto> selectAll() throws Exception;
    PayDto selectByPayId(String payId) throws Exception;
    int deleteAll() throws Exception;
    int countAll() throws Exception;
    int updatePayInsertedToN() throws Exception;
    int insertIntoOrder(PayDto payDto) throws Exception;
    int deleteOrder(String orderListId) throws Exception;
}

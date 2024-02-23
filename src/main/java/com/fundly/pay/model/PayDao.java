package com.fundly.pay.model;

import com.persistence.dto.PayDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PayDao {
    PayDto selectCompletedOrder() throws Exception;
    String selectPayId(String userId) throws Exception;
    int insert(PayDto payDto) throws Exception;
    PayDto selectPayTarget() throws Exception;
    PayDto selectPayRetryTarget() throws Exception;
    int updatePayStatusToCompleted(PayDto payDto) throws Exception;
    int updatePayStatusToFailed(PayDto payDto) throws Exception;
    int updatePayStatusToRetryFailed(PayDto payDto) throws Exception;
    PayDto selectPayForUser(String userId) throws Exception;
    PayDto selectPayForProject(String pjId) throws Exception;
    int deleteAll() throws Exception;
}

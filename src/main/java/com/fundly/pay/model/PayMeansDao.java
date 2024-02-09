package com.fundly.pay.model;

import com.persistence.dto.PayMeansDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PayMeansDao {
    int count() throws Exception;
    String selectPayMeansId(String UserId) throws Exception;
    int insert(PayMeansDto payMeansDto) throws Exception;
    int updateDefaultMeansToNo(PayMeansDto payMeansDto) throws Exception;
    int updateDefaultMeansToYes(PayMeansDto payMeansDto) throws Exception;
    int delete(String pay_means_id) throws Exception;
    int deleteAll() throws Exception;
    PayMeansDto select(String pay_means_id) throws Exception;
    List<PayMeansDto> selectAllForUser(String user_id) throws Exception;
    List<PayMeansDto> selectAll() throws Exception;
    PayMeansDto selectDefaultMeans() throws Exception;

}

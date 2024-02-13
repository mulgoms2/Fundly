package com.fundly.pay.model;

import com.persistence.dto.PayMeansDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface PayMeansDao {
    int count() throws Exception;
    String selectPayMeansId(String user_id) throws Exception;
    int insert(PayMeansDto payMeansDto) throws Exception;
    int updateDefaultMeansToNo(Map map) throws Exception;
    int updateDefaultMeansToYes(PayMeansDto payMeansDto) throws Exception;
    int delete(String pay_means_id) throws Exception;
    int deleteAll() throws Exception;
    PayMeansDto select(String pay_means_id) throws Exception;
    List<PayMeansDto> selectAllForUser(String user_id) throws Exception;
    List<PayMeansDto> selectAll() throws Exception;
    PayMeansDto selectDefaultMeans(String user_id) throws Exception;
    int countDefaultMeans(String user_id) throws Exception;

}

package com.fundly.pay.service;

import com.persistence.dto.PayMeansDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PayMeansService {
    int getPayMeansCount() throws Exception;
    String getPayMeansId(String user_id) throws Exception;
    int registerPayMeans(PayMeansDto payMeansDto) throws Exception;
    int unsetDefaultPayMeans(Map map) throws Exception;
    int setDefaultPayMeans(PayMeansDto payMeansDto) throws Exception;
    int removePayMeans(String pay_means_id) throws Exception;
    int removeAllPayMeansForAdmin() throws Exception;
    PayMeansDto getPayMeans(String pay_means_id) throws Exception;
    List<PayMeansDto> getAllPayMeans(String user_id) throws Exception;
    List<PayMeansDto> getAllPayMeansForAdmin() throws Exception;
    PayMeansDto getDefaultPayMeans(String user_id) throws Exception;
    int getDefaultPayMeansCount(String user_id) throws Exception;
}

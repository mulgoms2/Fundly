package com.fundly.pay.service;

import com.fundly.pay.model.PayMeansDao;
import com.persistence.dto.PayMeansDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PayMeansServiceImpl implements PayMeansService {

    @Autowired
    PayMeansDao payMeansDao;

    @Override
    public int getPayMeansCount() throws Exception {
        return payMeansDao.count();
    }

    @Override
    public String getPayMeansId(String userId) throws Exception {
        return payMeansDao.selectPayMeansId(userId);
    }

    @Override
    public int registerPayMeans(PayMeansDto payMeansDto) throws Exception {
        return payMeansDao.insert(payMeansDto);
    }

    @Override
    public int unsetDefaultPayMeans(Map map) throws Exception {
        return payMeansDao.updateDefaultMeansToNo(map);
    }

    @Override
    public int setDefaultPayMeans(PayMeansDto payMeansDto) throws Exception {
        return payMeansDao.updateDefaultMeansToYes(payMeansDto);
    }

    @Override
    public int removePayMeans(String pay_means_id) throws Exception {
        return payMeansDao.delete(pay_means_id);
    }

    @Override
    public int removeAllPayMeansForAdmin() throws Exception {
        return payMeansDao.deleteAll();
    }

    @Override
    public PayMeansDto getPayMeans(String pay_means_id) throws Exception {
        return payMeansDao.select(pay_means_id);
    }

    @Override
    public List<PayMeansDto> getAllPayMeans(Map map) throws Exception {
        return payMeansDao.selectAllForUser(map);
    }

    @Override
    public int getPayMeansCountForUser(String user_id) throws Exception {
        return payMeansDao.countForUser(user_id);
    }

    @Override
    public List<PayMeansDto> getAllPayMeansForAdmin() throws Exception {
        return payMeansDao.selectAll();
    }

    @Override
    public PayMeansDto getDefaultPayMeans(String user_id) throws Exception {
        return payMeansDao.selectDefaultMeans(user_id);
    }

    @Override
    public int getDefaultPayMeansCount(String user_id) throws Exception {
        return payMeansDao.countDefaultMeans(user_id);
    }
}

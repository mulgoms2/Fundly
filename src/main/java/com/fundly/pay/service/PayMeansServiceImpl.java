package com.fundly.pay.service;

import com.fundly.pay.model.PayMeansDao;
import com.persistence.dto.PayMeansDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayMeansServiceImpl implements PayMeansService {

    @Autowired
    PayMeansDao payMeansDao;

    @Override
    public int getPayMeansCount() throws Exception {
        return payMeansDao.count();
    }

    @Override
    public int registerPayMeans(PayMeansDto payMeansDto) throws Exception {
        return payMeansDao.insert(payMeansDto);
    }

    @Override
    public int unsetDefaultPayMeans(PayMeansDto payMeansDto) throws Exception {
        return payMeansDao.updateDefaultMeansToNo(payMeansDto);
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
    public List<PayMeansDto> getAllPayMeans(String user_id) throws Exception {
        return payMeansDao.selectAllForUser(user_id);
    }

    @Override
    public List<PayMeansDto> getAllPayMeansForAdmin() throws Exception {
        return payMeansDao.selectAll();
    }

    @Override
    public PayMeansDto getDefaultPayMeans() throws Exception {
        return payMeansDao.selectDefaultMeans();
    }
}

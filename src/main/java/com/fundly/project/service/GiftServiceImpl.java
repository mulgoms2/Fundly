package com.fundly.project.service;

import com.fundly.project.model.GiftMapper;
import com.persistence.dto.GiftDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftServiceImpl implements GiftService {
    @Autowired
    GiftMapper giftMapper;

    @Override
    public int registerGift(GiftDto giftDto) throws Exception{
        return giftMapper.insert(giftDto);
    }

    @Override
    public GiftDto getGift(String pj_id, String gift_name) throws Exception {
        return giftMapper.select(pj_id, gift_name);
    }


}

package com.fundly.project.service;

import com.persistence.dto.GiftDto;

import java.util.List;

public interface GiftService {
    int registerGift(GiftDto giftDto) throws Exception;
    List<GiftDto> getGift(String pj_id, String gift_name) throws Exception;
}

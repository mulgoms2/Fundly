package com.fundly.project.service;

import com.persistence.dto.GiftDto;

public interface GiftService {
    int registerGift(GiftDto giftDto) throws Exception;
    GiftDto getGift(String pj_id, String gift_name) throws Exception;
}

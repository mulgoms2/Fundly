package com.fundly.project.service;

import com.fundly.project.controller.GiftForm;
import com.persistence.dto.GiftDto;
import com.persistence.dto.GiftItemDetailDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GiftService {
    int getCount(String pj_id) throws Exception// 특정 프로젝트의 모든 선물의 갯수 구하기
    ;

    int registerGift(GiftForm giftForm) throws Exception;// 선물 등록하기 (선물 테이블에 insert + 선물 아이템 상세 테이블에도 insert)

    @Transactional(rollbackFor = Exception.class)
    int registerGift(GiftDto giftDto, List<GiftItemDetailDto> itemList) throws Exception// 선물 등록하기 (선물 테이블에 insert + 선물 아이템 상세 테이블에도 insert)
    ;

    GiftDto toGiftDto(GiftForm giftForm);
    List<GiftItemDetailDto> toGiftItemDetailDto(GiftForm giftForm);

    GiftDto getGift(Integer gift_id) throws Exception// 특정 선물 하나 가져오기
    ;

    List<GiftDto> getAllGiftList(String pj_id) throws Exception// 특정 프로젝트의 모든 선물 리스트 가져오기
    ;

    List<GiftDto> getGiftByStatus(GiftDto giftDto) throws Exception //특정 프로젝트의, 판매 상태에 따른 선물 리스트 가져오기
    ;

    @Transactional(rollbackFor = Exception.class)
    int modifyGiftContent(GiftDto giftDto, List<GiftItemDetailDto> afterList) throws Exception;

    int modifyGiftStatus(GiftDto giftDto) throws Exception;

    int modifyGiftQty(GiftDto giftDto) throws Exception;

    int removeGift(Integer gift_id) throws Exception;



}

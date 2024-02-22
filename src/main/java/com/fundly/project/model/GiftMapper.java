package com.fundly.project.model;

import com.persistence.dto.GiftDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GiftMapper {

    int count(@Param("pj_id") String pj_id) throws Exception;
    // 특정 프로젝트의 모든 선물의 갯수 구하기
    int insert(GiftDto giftDto) throws Exception;
    // 선물 하나 등록하기
    GiftDto select(@Param("gift_id") String gift_id) throws Exception;
    // 선물 하나 가져오기(읽기)
    List<GiftDto> selectAllByPj(@Param("pj_id") String pj_id) throws Exception;
    // 특정 프로젝트의 모든 선물 가져오기(읽기)
    List<GiftDto> selectByStatus(GiftDto giftDto) throws Exception;
    // 특정 프로젝트의 판매상태에 따른 선물 목록 가져오기(ex. 프로젝트1의 판매중 선물 가져오기)
    int updateContentBefore(GiftDto giftDto) throws Exception;
    // 해당 선물의 상세 내역 변경(프로젝트 등록중)
    int updateStatus(GiftDto giftDto) throws Exception;
    // 해당 선물의 판매 상태 변경(판매중, 일시품절)
    int updateQty(GiftDto giftDto) throws Exception;
    // 해당 선물의 판매 수량 수정
    int delete(@Param("gift_id") String gift_id) throws Exception;

    int deleteAll();
    // 해당 선물 삭제





}

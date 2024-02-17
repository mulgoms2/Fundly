package com.fundly.project.model;

import com.persistence.dto.GiftItemDetailDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GiftItemDetailMapper {
    int countGiftByItem(@Param("item_id") Integer item_id) throws Exception;
    int countItemByGift(@Param("gift_id") Integer gift_id) throws Exception;
    List<GiftItemDetailDto> selectItemDetail(@Param("gift_id") Integer gift_id) throws Exception;
    List<Integer> selectGift(@Param("item_id") Integer item_id) throws Exception;
    int insert(@Param("GiftItemDetailDto") GiftItemDetailDto giftItemDetailDto) throws Exception;
    int update(@Param("GiftItemDetailDto") GiftItemDetailDto giftItemDetailDto) throws Exception;

    int delete(@Param("gift_item_id") Integer gift_item_id) throws Exception;
    int deleteAllByGift(@Param("gift_id") Integer gift_id) throws Exception;
}

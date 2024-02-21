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
    int countItemByGift(@Param("gift_id") String gift_id) throws Exception;
    List<GiftItemDetailDto> selectItemDetail(@Param("gift_id") String gift_id) throws Exception;
    List<String> selectGift(@Param("item_id") Integer item_id) throws Exception;
    int insert(GiftItemDetailDto giftItemDetailDto) throws Exception;
    //현재 쿼리상으로는 특정 프로젝트의 한 선물에 대하여 같은 이름의 아이템이 여러번 insert될수가 있음(논리적 오류)
    //실제 뷰에서는 그렇게 입력하지 못하지만, 그래도 쿼리 수정이 필요함.
    // -> gift_id와 item_id를 복합 유니크키 제약 조건 줌.
    int update(GiftItemDetailDto giftItemDetailDto) throws Exception;

    int delete(@Param("gift_item_id") Integer gift_item_id) throws Exception;
    int deleteAllByGift(@Param("gift_id") String gift_id) throws Exception;

    int deleteAll();
}

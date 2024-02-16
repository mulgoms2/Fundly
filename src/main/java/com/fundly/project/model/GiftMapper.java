package com.fundly.project.model;

import com.persistence.dto.GiftDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GiftMapper {
    int insert(GiftDto giftDto) throws Exception;
    GiftDto select(@Param("pj_id") String pj_id, @Param("gift_name") String gift_name) throws Exception;
}

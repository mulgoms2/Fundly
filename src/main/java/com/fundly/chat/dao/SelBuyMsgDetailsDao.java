package com.fundly.chat.dao;

import com.persistence.dto.SelBuyMsgDetailsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SelBuyMsgDetailsDao {
    int insertMsg(SelBuyMsgDetailsDto selBuyMsgDetailsDto) throws Exception;
    List<SelBuyMsgDetailsDto> loadAllMessages(Long roomNum) throws Exception;
    int deleteAll();
    int count();
}

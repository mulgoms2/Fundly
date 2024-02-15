package com.fundly.chat.model;

import com.persistence.dto.ChatRoomDto;
import com.persistence.dto.SelBuyMsgDetailsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface SelBuyMsgDetailsDao {
    int insertMsg(SelBuyMsgDetailsDto selBuyMsgDetailsDto) throws Exception;

    ArrayList<SelBuyMsgDetailsDto> loadAllMessages(ChatRoomDto chatRoomDto) throws Exception;
}

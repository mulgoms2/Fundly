package com.fundly.chat.model;

import com.persistence.dto.ChatRoomDto;
import com.persistence.dto.SelBuyMsgDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ChatRoomDao {
    ChatRoomDto selectChatRoom(@Param("user_id") String user_id, @Param("pj_id") String pj_id) throws Exception;

    int makeChatRoom(@Param("user_id") String user_id, @Param("pj_id") String pj_id) throws Exception;

    int insertMsg(SelBuyMsgDetails selBuyMsgDetails) throws Exception;

    SelBuyMsgDetails[] loadAllMessages(@Param("buy_id") String buy_id,@Param("pj_id") String pj_id) throws Exception;

}

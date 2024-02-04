package com.fundly.chat.model;

import com.persistence.dto.ChatRoomDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ChatRoomDao {
    ChatRoomDto selectChatRoom(@Param("user_id") String user_id, @Param("pj_id") String pj_id) throws Exception;

    Integer makeChatRoom(@Param("user_id") String user_id, @Param("pj_id") String pj_id);
}

package com.fundly.chat.dao;

import com.fundly.chat.model.ChatRoom;

public interface ChatRoomDao {
    Long findRoomNum(String from_user_id, String to_user_id);

    ChatRoom getRoom(Long roomNum);

    Long maxRoomNum();

    ChatRoom save(ChatRoom chatRoom);
}

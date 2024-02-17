package com.fundly.chat.repository;

import com.fundly.chat.model.SelBuyMsgDao;
import com.fundly.chat.model.SelBuyMsgDetailsDao;
import com.persistence.dto.ChatRequest;
import com.persistence.dto.SelBuyMsgDetailsDto;
import com.persistence.dto.SelBuyMsgDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class ChatRepositoryImpl implements ChatRepository {
    SelBuyMsgDao selBuyMsgDao;
    SelBuyMsgDetailsDao selBuyMsgDetailsDao;

    @Autowired
    public ChatRepositoryImpl(SelBuyMsgDao selBuyMsgDao, SelBuyMsgDetailsDao selBuyMsgDetailsDao) {
        this.selBuyMsgDao = selBuyMsgDao;
        this.selBuyMsgDetailsDao = selBuyMsgDetailsDao;
    }

    @Override
    public SelBuyMsgDto getChatRoom(ChatRequest chatRequest) throws Exception {
            SelBuyMsgDto selBuyMsgDto = selBuyMsgDao.selectChatRoom(chatRequest);
//            채팅방이 존재하면 메시지를 채워서 반환한다.
            if (selBuyMsgDto != null) {
                selBuyMsgDto.setMessage_list(loadAllMessages(selBuyMsgDto));
                return selBuyMsgDto;
            }
            return selBuyMsgDto;
    }

    //    채팅방 만들기
    @Override
    @Transactional
    public SelBuyMsgDto makeChatRoom(ChatRequest request) throws Exception {
        selBuyMsgDao.makeChatRoom(request);
        return selBuyMsgDao.selectChatRoom(request);
    }

    @Override
    public List<SelBuyMsgDetailsDto> loadAllMessages(SelBuyMsgDto selBuyMsgDto) {
        int roomNum = selBuyMsgDto.getRoom_num();
        try {
            return selBuyMsgDetailsDao.loadAllMessages(roomNum);
        } catch (Exception e) {
            log.error("ChatRepositoryImpl.loadAllMessages");
            throw new RuntimeException(e);
        }
    }

    @Override
    public int saveMessage(SelBuyMsgDetailsDto selBuyMsgDetailsDto) {
        try {
            return selBuyMsgDetailsDao.insertMsg(selBuyMsgDetailsDto);
        } catch (Exception e) {
            log.error("ChatRepositoryImpl.insertMsg() 호출중 에러");
            throw new RuntimeException(e);
        }
    }
}

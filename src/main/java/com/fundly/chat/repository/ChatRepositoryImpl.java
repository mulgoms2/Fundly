package com.fundly.chat.repository;

import com.fundly.chat.entity.SelbuyMsgEntity;
import com.fundly.chat.model.SelBuyMsgDao;
import com.fundly.chat.model.SelBuyMsgDetailsDao;
import com.persistence.dto.ChatRequest;
import com.persistence.dto.SelBuyMsgDetailsDto;
import com.persistence.dto.SelBuyMsgDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

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
    public SelBuyMsgDto findRoom(ChatRequest chatRequest) {
        try {
            SelbuyMsgEntity selbuyMsgEntity = selBuyMsgDao.selectChatRoom(chatRequest);
            if (selbuyMsgEntity == null) {
                return null;
            }
            return selbuyMsgEntity.toSelBuyMsgDto();
        } catch (Exception e) {
            log.error("error ChatRepositoryImpl.findRoom()");
            throw new RuntimeException(e);
        }
    }

    //    채팅방 만들기
    @Override
    public int makeChatRoom(ChatRequest request) {
        try {
            return selBuyMsgDao.makeChatRoom(request);
        } catch (Exception e) {
            log.error("error ChatRepositoryImpl.makeChatRoom()");
            throw new RuntimeException(e);
        }
    }
    @Override
    public ArrayList<SelBuyMsgDetailsDto> loadAllMessages(SelBuyMsgDto selBuyMsgDto) {
        int roomNum = selBuyMsgDto.getRoom_num();
        try {
            return selBuyMsgDetailsDao.loadAllMessages(roomNum);
        } catch (Exception e) {
            log.error("ChatRepositoryImpl.loadAllMessages");
            throw new RuntimeException(e);
        }
    }
    @Override
    public int insertMsg(SelBuyMsgDetailsDto selBuyMsgDetailsDto) {
        try {
            return selBuyMsgDetailsDao.insertMsg(selBuyMsgDetailsDto);
        } catch (Exception e) {
            log.error("ChatRepositoryImpl.insertMsg() 호출중 에러");
            throw new RuntimeException(e);
        }
    }
}

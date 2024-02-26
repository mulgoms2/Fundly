package com.fundly.chat.repository;

import com.fundly.chat.dao.SelBuyMsgDao;
import com.fundly.chat.dao.SelBuyMsgDetailsDao;
import com.persistence.dto.ChatRequest;
import com.persistence.dto.FileDto;
import com.persistence.dto.SelBuyMsgDetailsDto;
import com.persistence.dto.SelBuyMsgDto;
import com.persistence.dao.FileDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.fundly.chat.service.ChatService.SEL_BUY_MSG_DETAILS;
import static java.util.stream.Collectors.toCollection;

@Slf4j
@Repository
@Transactional
public class ChatRepositoryImpl implements ChatRepository {
    SelBuyMsgDao selBuyMsgDao;
    SelBuyMsgDetailsDao selBuyMsgDetailsDao;
    FileDao fileDao;

    @Autowired
    public ChatRepositoryImpl(SelBuyMsgDao selBuyMsgDao, SelBuyMsgDetailsDao selBuyMsgDetailsDao, FileDao fileDao) {
        this.selBuyMsgDao = selBuyMsgDao;
        this.selBuyMsgDetailsDao = selBuyMsgDetailsDao;
        this.fileDao = fileDao;
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
    public List<SelBuyMsgDetailsDto> loadAllMessages(SelBuyMsgDto selBuyMsgDto) throws Exception {
        Long roomNum = selBuyMsgDto.getRoom_num();
        // 메시지를 로딩할때 첨부파일 및 시간을 작성해서 넘겨준다.
        return selBuyMsgDetailsDao.loadAllMessages(roomNum)
                .stream()
                .map(this::timeFormatting)
                .map(this::mappingImgUrl)
                .collect(toCollection(ArrayList<SelBuyMsgDetailsDto>::new));
    }

    @Override
    public int saveMessage(SelBuyMsgDetailsDto selBuyMsgDetailsDto) throws Exception {
        return selBuyMsgDetailsDao.insertMsg(selBuyMsgDetailsDto);
    }

    @Override
    public void saveImageFile(FileDto fileDto) throws Exception {
        fileDao.saveFile(fileDto);
    }

    private SelBuyMsgDetailsDto timeFormatting(SelBuyMsgDetailsDto selBuyMsgDetailsDto) {
        LocalDateTime date = selBuyMsgDetailsDto.getSvr_intime();

        String hourAndMinute = date.format(DateTimeFormatter.ofPattern("HH:mm"));

        selBuyMsgDetailsDto.setSvr_intime_string(hourAndMinute);

        return selBuyMsgDetailsDto;
    }


    private SelBuyMsgDetailsDto mappingImgUrl(SelBuyMsgDetailsDto selBuyMsgDetailsDto) {
//        파일이 첨부되어있는 메시지에 첨부파일 url 을 매핑
        if (!isFileAttached(selBuyMsgDetailsDto)) {
            return selBuyMsgDetailsDto;
        }
//            파일 테이블에서 첨부파일 url을 가져와 dto에 세팅한다.
        String msgKey = selBuyMsgDetailsDto.getMsg_id();
        String savedFileUri = null;
        try {
            savedFileUri = fileDao.getSavedFileUri(SEL_BUY_MSG_DETAILS, msgKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        selBuyMsgDetailsDto.setFile_url(savedFileUri);

        return selBuyMsgDetailsDto;
    }

    public boolean isFileAttached(SelBuyMsgDetailsDto selBuyMsgDetailsDto) {
        return selBuyMsgDetailsDto.getFile_cnt() != 0;
    }


}

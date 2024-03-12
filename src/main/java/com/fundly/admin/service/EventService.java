package com.fundly.admin.service;

import com.persistence.dto.CtgGuideDto;
import com.persistence.dto.EventDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {
    public int insertEvent(EventDto dto) throws Exception;
    public EventDto selectEvent(Integer event_seq) throws Exception;
    public List<EventDto> selectAllEvent() throws Exception;
    public int deleteEvent(Integer event_seq,String reg_id) throws Exception;

    public int deleteAll();

    public int count() throws Exception;
    public int updateEvent(EventDto dto) throws Exception;

    public int increaseView(Integer event_seq) throws Exception;

    List<EventDto> selectPage(Integer page, Integer pageSize) throws Exception;
}

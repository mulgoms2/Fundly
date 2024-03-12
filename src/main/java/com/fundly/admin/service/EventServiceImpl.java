package com.fundly.admin.service;

import com.fundly.admin.model.EventDao;
import com.persistence.dto.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EventServiceImpl implements EventService{
    @Autowired
    EventDao eventDao;
    @Override
    public int insertEvent(EventDto dto) throws Exception {

        return eventDao.insert(dto);
    }

    @Override
    public EventDto selectEvent(Integer event_seq) throws Exception {
        return eventDao.select(event_seq);
    }

    @Override
    public List<EventDto> selectAllEvent() throws Exception {
        return eventDao.selectAll();
    }

    @Override
    public int deleteEvent(Integer event_seq, String reg_id) throws Exception {
        Map map = new HashMap();
        map.put("event_seq",event_seq);
        map.put("reg_id",reg_id);
        return eventDao.delete(map);
    }

    @Override
    public int deleteAll() {
        return eventDao.deleteAll();
    }

    @Override
    public int count() throws Exception {
        return eventDao.count();
    }

    @Override
    public int updateEvent(EventDto dto) throws Exception {
        return eventDao.update(dto);
    }

    @Override
    public int increaseView(Integer event_seq) throws Exception {
        return eventDao.increaseView(event_seq);
    }

    @Override
    public List<EventDto> selectPage(Integer page, Integer pageSize) throws Exception {
        Map map = new HashMap();
        map.put("offset",(page-1)*10);
        map.put("pageSize",pageSize);
        return eventDao.selectPage(map);
    }
}

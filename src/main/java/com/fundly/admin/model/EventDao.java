package com.fundly.admin.model;

import com.persistence.dto.CtgGuideDto;
import com.persistence.dto.EventDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EventDao {
    int insert(EventDto dto) throws Exception;

    EventDto select(Integer event_seq) throws Exception;

    List<EventDto> selectAll() throws Exception;

    int delete(Map map) throws Exception;

    int deleteAll(); //

    int count() throws Exception;

    int update(EventDto dto) throws Exception;

    int increaseView(Integer event_seq) throws Exception;

    List<EventDto> selectPage(Map map) throws Exception;
}

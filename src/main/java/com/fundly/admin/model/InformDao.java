package com.fundly.admin.model;

import com.persistence.dto.InformDto;
import com.persistence.dto.NewsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface InformDao {
    int insert(InformDto dto) throws Exception;


    InformDto select(Integer news_seq) throws Exception;

    List<InformDto> selectAll() throws Exception;

    int delete(Map map) throws Exception;

    int deleteAll(); //

    int count() throws Exception;

    int update(InformDto dto) throws Exception;

    int increaseView(Integer inform_seq) throws Exception;

}

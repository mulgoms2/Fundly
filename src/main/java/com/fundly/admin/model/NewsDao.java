package com.fundly.admin.model;


import com.persistence.dto.NewsDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
public interface NewsDao {
    int insert(NewsDto dto) throws Exception;


    NewsDto select(Integer news_seq) throws Exception;

    List<NewsDto> selectAll() throws Exception;

    int delete(Map map) throws Exception;

    int deleteAll(); //

    int count() throws Exception;

    int update(NewsDto dto) throws Exception;

    int increaseView(Integer news_seq) throws Exception;
}

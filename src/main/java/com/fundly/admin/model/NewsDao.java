package com.fundly.admin.model;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NewsDao {
    int insert(NewsDto dto) throws Exception;


    NewsDto select(Integer news_seq) throws Exception; //파라미터타입 int resultType NewsDto

    List<NewsDto> selectAll() throws Exception;//reusltType NewsDto 여러개의 NewsDto객체를 받을거기때문애 list 구조로

    int delete(Integer news_seq, String reg_id) throws Exception;

    int deleteAll(); //

    int count() throws Exception;

    int update(NewsDto dto) throws Exception;

    int increaseView(Integer news_seq) throws Exception;
}

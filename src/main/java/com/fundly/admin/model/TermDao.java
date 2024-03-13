package com.fundly.admin.model;


import com.persistence.dto.TermDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TermDao {
    int insert(TermDto dto) throws Exception;

    TermDto select(Integer term_seq) throws Exception;

    List<TermDto> selectAll() throws Exception;

    int delete(Map map) throws Exception;

    int deleteAll(); //

    int count() throws Exception;

    int update(TermDto dto) throws Exception;

    int increaseView(Integer term_seq) throws Exception;

    List<TermDto> selectAllTitle() throws Exception;

    TermDto selectTitle(String term_title) throws Exception;
    TermDto termPrevNext(Map map) throws Exception;

    int prevUpdate(Integer term_seq) throws Exception;
    List<TermDto> selectPage(Map map) throws Exception;
}

package com.fundly.admin.model;

import com.persistence.dto.CtgGuideDto;
import com.persistence.dto.NewsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CtgGuideDao {
    int insert(CtgGuideDto dto) throws Exception;


    CtgGuideDto select(Integer ctg_guide_seq) throws Exception;

    List<CtgGuideDto> selectAll() throws Exception;

    int delete(Map map) throws Exception;

    int deleteAll(); //

    int count() throws Exception;

    int update(CtgGuideDto dto) throws Exception;

    int increaseView(Integer ctg_guide_seq) throws Exception;
}

package com.fundly.project.model;

import com.persistence.dto.ItemDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface ItemMapper {
    int count(@Param("pj_id") String pj_id) throws Exception;
    List<ItemDto> selectAllFromPj(@Param("pj_id") String pj_id) throws Exception;
    List<ItemDto> selectAll() throws Exception;
    int insert(ItemDto itemDto) throws Exception;

}

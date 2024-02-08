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
    int deleteAll() throws Exception;
    int delete(@Param("item_id") String item_id, @Param("dba_reg_id") String dba_reg_id) throws Exception;
//    int delete(Map map) throws Exception;

}

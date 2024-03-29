package com.fundly.admin.model;


import com.persistence.dto.SubHelpDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SubHelpDao {
    int insert(SubHelpDto dto) throws Exception;
    SubHelpDto select(Integer sub_help_seq) throws Exception;

    List<SubHelpDto> selectAll(Integer sub_help_sort) throws Exception;

    int delete(Map map) throws Exception;

    int deleteAll(); //

    int count(Integer sub_help_sort) throws Exception;

    int countAll() throws Exception;

    int countSub() throws Exception;

    int update(SubHelpDto dto) throws Exception;

    int increaseView(Integer sub_help_seq) throws Exception;

    List<SubHelpDto> selectAllAll() throws Exception;

    List<SubHelpDto> selectPage(Map map) throws Exception;
    List<SubHelpDto> searchHelp(String sub_help_title) throws Exception;
}

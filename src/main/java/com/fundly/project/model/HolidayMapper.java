package com.fundly.project.model;

import com.persistence.dto.HolidayDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HolidayMapper {
    int insert(HolidayDto holidayDto) throws Exception;
    List<HolidayDto> selectAll() throws Exception;
}

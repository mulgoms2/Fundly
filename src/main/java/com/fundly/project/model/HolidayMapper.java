package com.fundly.project.model;

import com.persistence.dto.HolidayDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface HolidayMapper {
    int insert(HolidayDto holidayDto) throws Exception;
    List<HolidayDto> selectAll() throws Exception;
    List<HolidayDto> selectHolidays(@Param("pj_pay_due_dtm") LocalDateTime pj_pay_due_dtm);
    int truncate() throws Exception;


}

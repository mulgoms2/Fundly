package com.fundly.project.model;

import com.persistence.dto.HolidayDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface HolidayMapper {
    int insert(HolidayDto holidayDto) throws DataAccessException;
    List<HolidayDto> selectAll() throws DataAccessException;
    List<HolidayDto> selectHolidays(@Param("pj_pay_due_dtm") LocalDateTime pj_pay_due_dtm) throws DataAccessException;
    int truncate() throws Exception;

}

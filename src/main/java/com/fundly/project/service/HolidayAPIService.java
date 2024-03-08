package com.fundly.project.service;

import com.persistence.dto.HolidayDto;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface HolidayAPIService {
    @Transactional(rollbackFor = Exception.class)
    void saveHolidayList(List<Map<String, Object>> itemList) throws Exception;

    List<HolidayDto> getFullHolidayList() throws Exception;
    List<HolidayDto> getHolidayList(LocalDateTime pj_pay_due_dtm) throws Exception;
}

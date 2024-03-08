package com.fundly.project.service;

import com.persistence.dto.HolidayDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface HolidayAPIService {
    @Transactional(rollbackFor = Exception.class)
    void saveHolidayList(List<Map<String, Object>> itemList) throws Exception;

    List<HolidayDto> getHolidayList() throws Exception;
}

package com.fundly.project.service;

import com.persistence.dto.HolidayDto;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

public interface HolidayAPIService {
    @Transactional(rollbackFor = Exception.class)
    List<HolidayDto> getHolidayList() throws IOException;
}

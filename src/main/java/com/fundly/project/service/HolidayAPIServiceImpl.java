package com.fundly.project.service;


import com.fundly.project.model.HolidayMapper;
import com.persistence.dto.HolidayDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class HolidayAPIServiceImpl implements HolidayAPIService {
    HolidayMapper holidayMapper;

    @Autowired
    HolidayAPIServiceImpl (HolidayMapper holidayMapper){
        this.holidayMapper = holidayMapper;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveHolidayList(List<Map<String, Object>> itemList) throws Exception {
        for(Map<String, Object> item : itemList){
            //item정보로 holidayDto 만들기
            if(item.get("isHoliday").equals("Y")){
                HolidayDto holidayDto = HolidayDto.toHolidayDto(item);
                int rowCnt = holidayMapper.insert(holidayDto);
                if(rowCnt!=1) throw new Exception();
            }
        }
    }

    @Override
    public List<HolidayDto> getFullHolidayList() throws Exception{
        List<HolidayDto> list = holidayMapper.selectAll();
        return list;
    }

    public List<HolidayDto> getHolidayList(LocalDateTime pj_pay_due_dtm){
        List<HolidayDto> list = holidayMapper.selectHolidays(pj_pay_due_dtm);
        return list;
    }
}
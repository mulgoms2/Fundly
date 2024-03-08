package com.fundly.project.service;


import com.fundly.project.model.HolidayMapper;
import com.persistence.dto.HolidayDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        //메서드가 다시 호출되었을 때 데이터가 중복되지 않게
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
    public List<HolidayDto> getHolidayList() throws Exception{
        List<HolidayDto> list = holidayMapper.selectAll();
        return list;
    }
}
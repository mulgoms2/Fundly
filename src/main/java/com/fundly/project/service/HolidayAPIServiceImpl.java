package com.fundly.project.service;


import com.fundly.project.controller.HolidayAPIExplorer;
import com.fundly.project.model.HolidayMapper;
import com.persistence.dto.HolidayDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public List<HolidayDto> getHolidayList() throws IOException {
        List<HolidayDto> holidayList = new ArrayList<>();
        int year = LocalDateTime.now().getYear();
        for (int i = 0; i < 2; i++) { //올해와 내년말까지의 공휴일 데이터 가져오기
            String solYear = String.valueOf(year + i);
            Map<String, Object> holidayMap = HolidayAPIExplorer.getHolidays(solYear);
            //HolidayExplorer를 호출해서 data를 받아온다.
            //중첩된 Map형태라서 하나씩 꺼내야함.
            Map<String, Object> response = (Map<String, Object>)holidayMap.get("response");
            Map<String, Object> body = (Map<String, Object>)response.get("body");
            Map<String, Object> items = (Map<String, Object>)body.get("items");
            ArrayList<Map<String, Object>> itemList = (ArrayList<Map<String, Object>>)items.get("item");

            for(Map<String, Object> item : itemList){
                //item정보로 holidayDto 만들기
                if(item.get("isHoliday").equals("Y")){
                    HolidayDto holidayDto = HolidayDto.toHolidayDto(item);
                    try {
                        int rowCnt = holidayMapper.insert(holidayDto);
                        if(rowCnt!=1) throw new Exception();

                        List<HolidayDto> list = holidayMapper.selectAll();
                        for(HolidayDto dto : list){
                            holidayList.add(dto); //리턴값 채우기
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }



            }
        }

        return holidayList;
    }
}
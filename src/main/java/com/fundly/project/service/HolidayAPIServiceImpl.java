package com.fundly.project.service;


import com.fundly.project.model.HolidayMapper;
import com.persistence.dto.HolidayDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public List<HolidayDto> getFullHolidayList() {
        List<HolidayDto> list = holidayMapper.selectAll();
        //Mapper에서 DataAccessException 발생 가능(runtimeException)
        //이 에러에 대한 정보를 가공해서 알려주어야 하나? 아니면
        return list;
    }

    public List<HolidayDto> getHolidayList(LocalDateTime pj_pay_due_dtm){
        List<HolidayDto> list = holidayMapper.selectHolidays(pj_pay_due_dtm);
        return list;
    }

    public static List<String> getHolidayNameList(List<HolidayDto> dtolist){
        //내부에서 service인스턴스를 쓰지 않아서 static 메서드로 선언함

        //매개변수 유효성 체크
        dtolist = Objects.requireNonNull(dtolist, "List<HolidayDto>타입의 매개변수가 null입니다.");

        return dtolist.stream().map(HolidayDto::getHolidayDate).collect(Collectors.toList());
    }
}
package com.persistence.dto;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class HolidayDto {
    private int id;
    private String holidayDate;
    private String holidayName;

    public static HolidayDto toHolidayDto(Map<String, Object> map){
        Map<String, String> dateFormat = HolidayDto.dateFormat(String.valueOf(map.get("locdate")));
        return HolidayDto.builder()
                .holidayDate(dateFormat.get("year")+"-"+dateFormat.get("month")+"-"+dateFormat.get("day"))
                .holidayName((String)map.get("dateName"))
                .build();
    }

    private static Map<String,String> dateFormat(String yyyymmdd){
        Map<String, String> map = new HashMap<>();
        map.put("year",yyyymmdd.substring(0,4));
        map.put("month",yyyymmdd.substring(4,6));
        map.put("day",yyyymmdd.substring(6));
        return map;
    }
}

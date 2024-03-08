package com.fundly.project.controller;

import com.fundly.project.service.HolidayAPIService;
import com.persistence.dto.HolidayDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
public class HolidayAPIController {
    private HolidayAPIService holidayAPIService;

    @Autowired
    HolidayAPIController(HolidayAPIService holidayAPIService) {
        this.holidayAPIService = holidayAPIService;
    }

    @GetMapping("/holiday")
    public ResponseEntity<?> getHolidays() throws Exception {
        int year = LocalDateTime.now().getYear();

        for (int i = 0; i < 2; i++) { //올해와 내년말까지의 공휴일 데이터 가져오기
            String solYear = String.valueOf(year + i);
            Map<String, Object> holidayMap = HolidayAPIExplorer.getHolidays(solYear);
            //HolidayExplorer를 호출해서 data를 받아온다.

            List<Map<String, Object>> itemList = getItemList(holidayMap);
            holidayAPIService.saveHolidayList(itemList);
        }
        List<HolidayDto> holidayList = holidayAPIService.getHolidayList();
        return ResponseEntity.ok().body(holidayList);
    }

    public static List<Map<String, Object>> getItemList(Map<String, Object> holidayMap){
        //중첩된 Map형태라서 하나씩 꺼내야함.
        Map<String, Object> response = (Map<String, Object>) holidayMap.get("response");
        Map<String, Object> body = (Map<String, Object>) response.get("body");
        Map<String, Object> items = (Map<String, Object>) body.get("items");
        List<Map<String, Object>> itemList = (List<Map<String, Object>>) items.get("item");
        return itemList;
    }
}

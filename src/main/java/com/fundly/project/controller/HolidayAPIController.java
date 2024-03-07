package com.fundly.project.controller;

import com.fundly.project.service.HolidayAPIService;
import com.persistence.dto.HolidayDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HolidayAPIController {
    private HolidayAPIService holidayAPIService;

    @Autowired
    HolidayAPIController(HolidayAPIService holidayAPIService){
        this.holidayAPIService = holidayAPIService;
    }

    @GetMapping("/holiday")
    public ResponseEntity<?> getHolidays() throws Exception{
        List<HolidayDto> list = holidayAPIService.getHolidayList();
        return ResponseEntity.ok().body(list);
    }

}

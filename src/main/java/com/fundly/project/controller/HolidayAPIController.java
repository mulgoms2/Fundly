package com.fundly.project.controller;

import com.fundly.project.service.HolidayAPIService;
import com.persistence.dto.HolidayDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class HolidayAPIController {
    private HolidayAPIService holidayAPIService;

    @Autowired
    HolidayAPIController(HolidayAPIService holidayAPIService) {
        this.holidayAPIService = holidayAPIService;
    }

    @GetMapping("/holidayAPI") // 올해와 내년에 해당하는 공휴일을 요청해서 DB에 저장
    public ResponseEntity<List<HolidayDto>> getHolidaysFromAPI() throws Exception {
        int year = LocalDateTime.now().getYear();

        for (int i = 0; i < 2; i++) { //올해와 내년말까지의 공휴일 데이터 가져오기
            String solYear = String.valueOf(year + i);
            Map<String, Object> holidayMap = HolidayAPIExplorer.getHolidays(solYear);
            //HolidayExplorer를 호출해서 data를 받아온다.

            List<Map<String, Object>> itemList = getItemList(holidayMap);
            holidayAPIService.saveHolidayList(itemList);
        }
        List<HolidayDto> holidayList = holidayAPIService.getFullHolidayList();
        return ResponseEntity.ok().body(holidayList);
    }

    @GetMapping("/project/holiday") //(프로젝트) 결제완료일로부터 30일에 해당하는 공휴일 리스트를 가져오기
    public ResponseEntity<List<String>> getHoliday(String finalPayDay) throws Exception {
        log.error("\n\n finalPayDay={} \n\n", finalPayDay);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime pj_pay_due_dtm = LocalDateTime.parse(finalPayDay, formatter);
        //parse() -> RuntimeException타입의 parseException 던진다. (ExceptionHandler로 처리)

        log.error("\n\n pj_pay_due_dtm={} \n\n", pj_pay_due_dtm);
        List<HolidayDto> dtolist = holidayAPIService.getHolidayList(pj_pay_due_dtm);

        List<String> list = dtolist.stream().map(HolidayDto::getHolidayDate).collect(Collectors.toList());

        log.error("\n\n list={} \n\n", list);
        return ResponseEntity.ok().body(list);
    }


    //holidayMap에서 item List를 얻기
    private static List<Map<String, Object>> getItemList(Map<String, Object> holidayMap){
        //중첩된 Map형태라서 하나씩 꺼내야함.
        Map<String, Object> response = (Map<String, Object>) holidayMap.get("response");
        Map<String, Object> body = (Map<String, Object>) response.get("body");
        Map<String, Object> items = (Map<String, Object>) body.get("items");
        List<Map<String, Object>> itemList = (List<Map<String, Object>>) items.get("item");
        return itemList;
    }

    @ExceptionHandler(Exception.class) //이 컨트롤러에서 발생할 수 있는 에러를 모두 처리
    public ResponseEntity<String> handleGeneralHolidayException(Exception e, Errors errors){
        e.printStackTrace();
        if(e instanceof DateTimeParseException){ //getHoliday의 경우, LocalDateTime.parse()에서 parseException발생 가능
            log.error("\n\n dateTimeParseException error={} \n\n", e.getMessage(),e.getCause());
            return ResponseEntity.badRequest().body("invalid date format");
            //todo 뷰에서 alert()로 알맞은 알림메시지를 띄우기 (ex. 펀딩기간을 알맞게 설정해주세요)
            // 결제예정일은 펀딩종료일이 입력되면 자동적으로 계산되는 value이지만
            // client쪽에서 조작 등으로 접근할 수도 있으니(?)

        }
        return ResponseEntity.internalServerError().body("에러가 발생했습니다.");
        //API로부터 정보를 가져오지 못한 경우
    }

}

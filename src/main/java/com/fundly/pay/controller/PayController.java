package com.fundly.pay.controller;

import com.fundly.pay.dto.PayPageHandler;
import com.fundly.pay.dto.PayResponseDto;
import com.fundly.pay.dto.billkey.BillKeyRequestDto;
import com.fundly.pay.dto.billkey.BillKeyResponseDto;
import com.fundly.pay.dto.schedule.ScheduledPayRequestDto;
import com.fundly.pay.dto.schedule.ScheduledPayResponseDto;
import com.fundly.pay.service.PayMeansService;
import com.fundly.pay.service.PortOneService;
import com.persistence.dto.PayMeansDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/pay")
@Slf4j
public class PayController {
    // 1. 포트원 API 호출 전 모든 함수 공통 호출: getToken()
    // 2. 결제사에 카드 등록: getBillKey() -> registerPayMeans()
    // 3. 결제 예약: schedulePay() - 결제예정일에 스케줄되도록.. -> 결제예약 api 말고, 한날한시에 일괄적으로 결제 요청을 보내는게 나을 수도?
    // 4. (주문 취소) -> 결제 취소: cancelPay()
    // 5. 결제 조회 (내역, 상태):  getPayList() -> DB 결제 상태 업데이트
    // 6. 결제 재시도: retryFailedPay() -> DB 결제 상태 업데이트

    private final PayMeansService payMeansService;
    private final PortOneService portOneService;

    @Autowired
    public PayController(PayMeansService payMeansService, PortOneService portOneService) {
        this.payMeansService = payMeansService;
        this.portOneService = portOneService;
    }

    @ResponseBody
    @PostMapping("/update")
    public ResponseEntity<PayResponseDto> updateDefaultMeans(PayMeansDto payMeansDto) {
        // 1. 기본결제수단지정 버튼을 클릭한다.
        // 2. Y인 row가 있으면 N으로 바꾼다.
        // 3. 해당 결제수단을 Y로 바꾼다.
        // 4. Y인 row가 1개인지 검증한다.
        // 4. Y인 것은 태그를 붙이고 첫번째로 출력한다.
        String userId = "test"; // TODO: 세션에서 유저아이디 가져오기 (String) session.getAttribute("id")
        try {
            // 1. session의 user Id와 payMeansDto user Id가 같은지 확인한다.
            if (!userCheck(payMeansDto.getUser_id())) {
                throw new Exception("Update Failed. - userCheck Error");
            }
            log.info("userCheck 성공");

            // 2. Y인 row가 있으면 N으로 바꾼다.
            Map map = new HashMap();
            map.put("user_id", userId);
            map.put("dba_mod_id", userId);

            // 기존 기뵨결제수단이 있는지 확인 (있다면 값은 1)
            int defaultPayMeansCnt = payMeansService.getDefaultPayMeansCount(payMeansDto.getUser_id());
            // 기존의 기본결제수단이 있다면 해제
            int rowCnt;
            if (defaultPayMeansCnt != 0) {
                rowCnt = payMeansService.unsetDefaultPayMeans(map);
                if (rowCnt != 1) {
                    throw new Exception("Update Failed. - unsetDefaultPayMeans Error");
                }
                log.info("unsetDefaultPayMeans 성공");
            }

            // 3. 해당 결제수단을 Y로 바꾼다.
            payMeansDto.setDba_mod_id(userId);
            rowCnt = payMeansService.setDefaultPayMeans(payMeansDto);
            if (rowCnt != 1) {
                throw new Exception("Update Failed. - setDefaultPayMeans Error");
            }
            log.info("setDefaultPayMeans 성공");

            // 4. Y인 row가 1개인지 검증한다.
            if (defaultPayMeansCnt != 1) {
                throw new Exception("Update Failed. - getDefaultPayMeansCount Error");
            }
            log.info("getDefaultPayMeansCount 성공");

            // TODO: 상태코드별 에러 처리 세분화 필요
            PayResponseDto payResponseDto = new PayResponseDto("UPDATE_SUCCESS", payMeansDto);
            return ResponseEntity.ok().body(payResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: 상태코드별 에러 처리 세분화 필요
            PayResponseDto payResponseDto = new PayResponseDto("UPDATE_ERROR", payMeansDto);
            return ResponseEntity.ok().body(payResponseDto);
        }
    }

    @ResponseBody
    @PostMapping("/remove")
    public ResponseEntity<PayResponseDto> remove(PayMeansDto payMeansDto, long from, long to) {
        String payMeansId = payMeansDto.getPay_means_id();
        try {
            if (!userCheck(payMeansDto.getUser_id())) {
                throw new Exception("Remove Failed. - userCheck Error");
            }
            String authToken = portOneService.getToken().getBody().getResponse().getAccess_token();

            // 1. 예약된 결제 내역이 있는지 확인한다.
            ScheduledPayRequestDto scheduledPayRequestDto = new ScheduledPayRequestDto(payMeansId, "scheduled", from, to);
            ResponseEntity<ScheduledPayResponseDto> scheduledPayResponseDto = portOneService.getScheduledPay(scheduledPayRequestDto, authToken);
            if (scheduledPayResponseDto.getBody().getCode() != 0) throw new Exception("Remove Failed. - getScheduledPay Error");
            if (scheduledPayResponseDto.getBody().getResponse().getTotal() != 0) throw new Exception("Remove Failed. - 예약된 결제가 있습니다.");
            log.info("getScheduledPay 성공");

            // 2. 예약된 결제 내역이 없으면 PortOne에서 삭제한다.
            BillKeyRequestDto billKeyRequestDto = new BillKeyRequestDto();
            billKeyRequestDto.setCustomer_uid(payMeansId);
            ResponseEntity<BillKeyResponseDto> billKeyResponseDto = portOneService.removeBillKey(billKeyRequestDto, authToken);
            if (billKeyResponseDto.getBody().getCode() != 0) throw new Exception("Remove Failed. - removeBillKey Error");
            log.info("removeBillKey 성공");

            // 3. PortOne에서 삭제되면 DB에서 삭제한다.
            int rowCnt = payMeansService.removePayMeans(payMeansId);
            if (rowCnt != 1) throw new Exception("Remove Failed. - DB Delete Error");
            log.info("removePayMeans 성공");

            PayResponseDto payResponseDto = new PayResponseDto("DEL_SUCCESS", payMeansDto);
            return ResponseEntity.ok().body(payResponseDto);

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: 상태코드별 에러 처리 세분화 필요
            PayResponseDto payResponseDto = new PayResponseDto("DEL_ERROR", payMeansDto);
            return ResponseEntity.ok().body(payResponseDto);

        }
    }

    @ResponseBody
    @GetMapping("/list")
    public ResponseEntity<PayResponseDto> list(@RequestParam(defaultValue = "1") Integer page, RedirectAttributes rattr) {
        String userId = "test"; // TODO: 세션에서 유저아이디 가져오기 (String) session.getAttribute("id")

        try {
            int totalCnt = payMeansService.getPayMeansCountForUser(userId);
            PayPageHandler pageHandler = new PayPageHandler(totalCnt, page);

            Map map = new HashMap();
            map.put("pay_means_id", userId);
            map.put("offset", pageHandler.getOffset());
            map.put("dummySize", pageHandler.getDummySize());

            List<PayMeansDto> list = payMeansService.getAllPayMeans(map);

            PayResponseDto payResponseDto = new PayResponseDto("LIST_SUCCESS", list, pageHandler);
            return ResponseEntity.ok().body(payResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "LIST_ERROR");
            return ResponseEntity.badRequest().build();
        }
    }

    @ResponseBody
    @PostMapping("/register")
    public ResponseEntity<PayResponseDto> register(PayMeansDto payMeansDto) {
        String userId = "test"; // TODO: 세션에서 유저아이디 가져오기 (String) session.getAttribute("id")
        payMeansDto.setUser_id(userId);
        payMeansDto.setDba_reg_id(userId);
        if (payMeansDto.getDefault_pay_means_yn() == null) {
            payMeansDto.setDefault_pay_means_yn("N");
        }
        try {
            // userID 이용하여 결제수단테이블 id(pay_means_id) 생성
            String payMeansId = payMeansService.getPayMeansId(userId);
            payMeansDto.setPay_means_id(payMeansId);

            BillKeyRequestDto billKeyRequestDto = new BillKeyRequestDto(
                    payMeansDto.getCard_no(),payMeansDto.getCard_valid_date(), payMeansDto.getOwn_birth(),
                    payMeansDto.getCard_pwd(), payMeansDto.getPay_means_id());

            String authToken = portOneService.getToken().getBody().getResponse().getAccess_token();

            ResponseEntity<BillKeyResponseDto> billKeyResponseDto = portOneService.getBillKey(billKeyRequestDto, authToken);
            if (billKeyResponseDto.getBody().getCode() != 0) {
                throw new Exception("Register Failed. - getBillKey Error");
            }

            payMeansDto.setBill_key(billKeyResponseDto.getBody().getResponse().getCustomer_id());
            payMeansDto.setCard_co_type(billKeyResponseDto.getBody().getResponse().getCard_publisher_name());

            // 카드번호 뒤 4자리
            String cardNumber =billKeyResponseDto.getBody().getResponse().getCard_number();
            cardNumber = cardNumber.substring(cardNumber.length() - 4, cardNumber.length());
            payMeansDto.setCard_no(cardNumber);

            int rowCnt;
            int defaultPayMeansCnt = payMeansService.getDefaultPayMeansCount(payMeansDto.getUser_id());
            // 기존 기본결제수단이 있는 경우, 기본결제수단지정 체크한 상태면 기존 것은 unset
            if (defaultPayMeansCnt != 0) {
                if (payMeansDto.getDefault_pay_means_yn().equals("Y")) {
                    Map map = new HashMap();
                    map.put("user_id", userId);
                    map.put("dba_mod_id", userId);
                    rowCnt = payMeansService.unsetDefaultPayMeans(map);
                    if (rowCnt != 1) throw new Exception("Register Failed. - unsetDefaultPayMeans Error");
                    log.info("unsetDefaultPayMeans 성공");
                }
            }
            rowCnt = payMeansService.registerPayMeans(payMeansDto); // 결제수단 테이블에 insert
            if (rowCnt != 1) throw new Exception("Register Failed. - registerPayMeans Error");

            PayResponseDto payResponseDto = new PayResponseDto("REG_SUCCESS", payMeansDto);
            return ResponseEntity.ok().body(payResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: 상태코드별 에러 처리 세분화 필요
            PayResponseDto payResponseDto = new PayResponseDto("REG_ERROR", payMeansDto);
            return ResponseEntity.badRequest().body(payResponseDto);
        }
    }

    private boolean userCheck(String payMeansDtoUserId) {
//        String sessionId = (String) session.getAttribute("id");
        String sessionId = "test"; // TODO: 세션에서 유저아이디 가져오기 (String) session.getAttribute("id")
        return sessionId.equals(payMeansDtoUserId);
    }

}

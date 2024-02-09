package com.fundly.pay.controller;

import com.fundly.pay.dto.BillKeyRequestDto;
import com.fundly.pay.service.PayMeansService;
import com.fundly.pay.service.PortOneService;
import com.persistence.dto.PayMeansDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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

    private PayMeansService payMeansService;
    private PortOneService portOneService;

    @Autowired
    public PayController(PayMeansService payMeansService, PortOneService portOneService) {
        this.payMeansService = payMeansService;
        this.portOneService = portOneService;
    }

    @GetMapping("")
    public String list() {
        return "pay/userPayMeansPage";
    }

    @GetMapping("/register")
    public String register() {
        return "pay/registerCardForm";
    }

    @PostMapping("/register")
    public String register(PayMeansDto payMeansDto, Model m, RedirectAttributes rattr) {
        String userId = "test"; // TODO: 유저아이디 가져오기 (String) session.getAttribute("id")
        payMeansDto.setUser_id(userId);
        payMeansDto.setDba_reg_id(userId);
        try {
            String payMeansId = payMeansService.getPayMeansId(userId);
            payMeansDto.setPay_means_id(payMeansId);
            log.info("payMeansId = " + payMeansId);

            BillKeyRequestDto billKeyRequestDto = new BillKeyRequestDto(
                    payMeansDto.getCard_no(),payMeansDto.getCard_valid_date(), payMeansDto.getOwn_birth(),
                    payMeansDto.getCard_pwd(), payMeansDto.getPay_means_id());

            String authToken = portOneService.getToken();
//            log.info("authToken = " + authToken);
            String billKey = portOneService.getBillKey(billKeyRequestDto, authToken);
//            log.info("billKey = " + billKey);
            payMeansDto.setBill_key(billKey);
//            System.out.println("payMeansDto = " + payMeansDto);

//            payMeansService.registerPayMeans(payMeansDto); // 결제수단 테이블에 insert

            rattr.addFlashAttribute(payMeansDto);
            rattr.addFlashAttribute("msg", "REG_SUCCESS");

        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "REG_ERROR");
        }
        return "redirect:/pay";
    }

}

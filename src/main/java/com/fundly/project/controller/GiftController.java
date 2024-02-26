package com.fundly.project.controller;

import com.fundly.project.service.GiftService;
import com.fundly.project.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/project")
//@InitBinder메서드가 있어도 그냥 클래스에 @RestController붙여도 되나?
public class GiftController {
    ItemService itemService;
    GiftService giftService;
    MessageSource messageSource;
//    ObjectMapper objectMapper;

    @Autowired
    GiftController(ItemService itemService, GiftService giftService, MessageSource messageSource) {
        this.itemService = itemService;
        this.giftService = giftService;
        this.messageSource = messageSource;
//        this.objectMapper = new ObjectMapper();
//        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @GetMapping("/gift")
    @ResponseBody
    public ResponseEntity<?> getGiftList(String pj_id) {
        log.error("\n\n pj_id={} \n\n",pj_id);
        try{
            List<GiftForm> list = giftService.getAllGiftList(pj_id);
            log.error("\n\n list={} \n\n",list);
            return new ResponseEntity<List<GiftForm>>(list, HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/gift/select/{gift_id}")
    @ResponseBody
    public ResponseEntity<?> getSelectedGift(@PathVariable String gift_id){
        log.error("\n\n gift_id={} \n\n",gift_id);
        try{
            GiftForm giftForm = giftService.getGift(gift_id);
            log.error("\n\n giftForm={}\n\n",giftForm);
            return new ResponseEntity<>(giftForm, HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //선물 등록하기
    @PostMapping("/gift")
    @ResponseBody
    public ResponseEntity<?> registerGift(@RequestBody @Valid GiftForm giftForm, BindingResult result, HttpSession session){
        log.error("\n\n giftForm={}\n\n",giftForm);
        String pj_id = "pj1"; //하드코딩. 나중에 바꿔주기
        //String id = (String)session.getAttribute("id");
        String id = "asdf"; //하드코딩. 세션으로부터 id 얻어오는 것으로 바꾸기.
        String gift_id = UUID.randomUUID().toString();
        //GiftRegister시에 Tx로 GiftItemDetailDto에 insert가 함께 발생, 즉 gift_id가 미리 존재해야한다. (ai라면 다시 꺼내기 애매함)

        giftForm.setPj_id(pj_id);
        giftForm.setDba_reg_id(id);
        giftForm.setGift_id(gift_id);
        log.error("\n\n giftForm={}\n\n",giftForm);
        log.error("\n\n bindingResult={} \n\n", result);

        log.error("binding result={}",result);
        log.error("**** error codes ****");
        result.getAllErrors().stream().forEach(
                error -> Arrays.stream(error.getCodes()).forEach(System.out::println)
        ); //어떤 에러코드가 출력되는지

        if(result.hasErrors()){
            //유효성 검사에 실패하면
            ErrorResult errorResult = new ErrorResult(result, messageSource);
            //에러메시지와 함께 400번 에러를 전달
            return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
        }

        //toDo
        // 같은 프로젝트에 이미 같은 이름의 선물이 있는지도.. 검증해야함(사실 아이템도 마찬가지)
        // -> 복합unique키로 테이블 수준에서 제한함: alter table pj_gift_tmp2 add unique (pj_id, gift_name);

        //Validator에 의한 유효성 검사에 성공한 후의 코드 진행
        try {
            //DB에 gift 등록(insert)
            int rowCnt = giftService.registerGift(giftForm);
            if (rowCnt != 1) { // insert 실패시
                throw new Exception("gift register failed");
            }
            //insert 성공하면
//            List<GiftDto> list = giftService.getAllGiftList(pj_id);
            List<GiftForm> list = giftService.getAllGiftList(pj_id);
            log.error("\n\n list={}\n\n", list);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch(DuplicateKeyException e){
            String msg = "한 프로젝트 내에서 중복된 선물 이름을 지정할 수 없습니다.";
            System.out.println("Duplicate key");
            System.out.println("e = " + e);
            return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
        } catch(Exception e){
            log.error("\n\n e={} \n\n",e);
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/gift")
    @ResponseBody
    public ResponseEntity<?> modifyGift(@RequestBody @Valid GiftForm giftForm, BindingResult result){
        giftForm.setDba_mod_id("asdf"); //원래는 세션에서 얻어서 넣어준다.
        log.error("\n\n giftForm={}\n\n",giftForm);


        log.error("binding result={}",result);
        log.error("**** error codes ****");
        result.getAllErrors().stream().forEach(
                error -> Arrays.stream(error.getCodes()).forEach(System.out::println)
        ); //어떤 에러코드가 출력되는지

        if(result.hasErrors()){
            //유효성 검사에 실패하면
            ErrorResult errorResult = new ErrorResult(result, messageSource);
            //에러메시지와 함께 400번 에러를 전달
            return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
        }

        try{
            int rowCnt = giftService.modifyGiftContent(giftForm);
            if(rowCnt!=1){
                throw new Exception("modify gift failed");
            }
            List<GiftForm> list = giftService.getAllGiftList(giftForm.getPj_id());
            log.error("\n\n patchMapping - list = {}\n\n", list);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/gift")
    @ResponseBody
    public ResponseEntity<?> deleteGift(String gift_id, String pj_id) {
        log.error("\n\n gift_id={}\n\n",gift_id);
        log.error("\n\n pj_id={}\n\n",pj_id);

        try{
            int rowCnt = giftService.removeGift(gift_id); //해당 선물을 삭제한다(선물에 포함된 아이템 상세도 삭제 Tx)
            if(rowCnt!=1) {
                throw new Exception("remove gift failed");
            }
            List<GiftForm> list = giftService.getAllGiftList(pj_id);
            log.error("\n\n deleteMapping - list = {}\n\n", list);
            return new ResponseEntity<List<GiftForm>>(list, HttpStatus.OK);

        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @InitBinder
    public void dataBind(WebDataBinder binder){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        binder.registerCustomEditor(LocalDateTime.class, new CustomDateEditor(df,false));
        //이건 item이 아니라 gift쪽에서 날짜 입력받을 때 쓰기.

        binder.setValidator(new GiftValidator());
        List<Validator> validatorList = binder.getValidators();
        log.error("validatorList = {}",validatorList);
    }

}
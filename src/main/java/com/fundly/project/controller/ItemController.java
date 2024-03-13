package com.fundly.project.controller;

import com.fundly.project.exception.ProjectNotFoundException;
import com.fundly.project.service.GiftService;
import com.fundly.project.service.ItemService;
import com.fundly.project.service.ProjectService;
import com.persistence.dto.ItemDto;
import com.persistence.dto.ProjectDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Slf4j
@Controller
@RequestMapping("/project")
@SessionAttributes("projectDto")
public class ItemController {
    ItemService itemService;
    GiftService giftService;
    ProjectService projectService;
    MessageSource messageSource;
    @Autowired
    ItemController(ItemService itemService, GiftService giftService, ProjectService projectService, MessageSource messageSource){
        this.itemService = itemService;
        this.giftService = giftService;
        this.projectService = projectService;
        this.messageSource = messageSource;
    }



//    @GetMapping("/reward")
//    public String makeGift(Model m){
//        //itemService로부터 itemDtoList를 꺼내와서 뷰에 전달함
//        //뷰단에서는 itemDtoList가 empty면 보여줄 화면과 empty가 아니면 보여줄 화면이 나뉨.
//        try {
//            List<ItemDto> itemList = itemService.getItemList("pj1");
//            System.out.println("itemList = " + itemList);
//            m.addAttribute("itemList",itemList);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "project.reward";
//    }  //try-catch

    @ModelAttribute("projectDto")
    ProjectDto initProjectEditor(@SessionAttribute String user_email) {
        try {
            return projectService.getEditingProject(user_email);
        } catch (ProjectNotFoundException e) {
            return null;
        }
    }




    // 아이템+선물 페이지
    @GetMapping("/editor/reward")
    public String makeGift(Model m, ProjectDto projectDto) throws Exception {
        log.error("\n\n(itemController) projectDto={}\n\n", projectDto);
        if(Objects.nonNull(projectDto.getPj_pay_due_dtm())){
            m.addAttribute("pj_pay_due_dtm",projectDto.getPj_pay_due_dtm().toLocalDate());
        }
        m.addAttribute("pj_id", projectDto.getPj_id());

        return "project.reward";
    }


    //아이템을 등록하기
    @PostMapping("/item")
    @ResponseBody
    public ResponseEntity<?> makeItem(@Valid @RequestBody ItemDto itemDto, BindingResult result, ProjectDto projectDto, @SessionAttribute String user_email){ //아이템 등록
        String pj_id = projectDto.getPj_id();

        log.error("binding result={}",result);
        log.error("**** error codes ****");
        result.getAllErrors().stream().forEach(
                error -> Arrays.stream(error.getCodes()).forEach(System.out::println)
        ); //어떤 에러코드가 출력되는지

        if(result.hasErrors()){ //유효성 검사에 실패할 경우
            log.error("result={}",result);
            log.error("**** result.getFieldError()={} ",result.getFieldError());

            log.error("***************** messageSource={}",messageSource);
            ErrorResult errorResult = new ErrorResult(result, messageSource);
            return ResponseEntity.badRequest().body(errorResult);
            // 에러 메시지를 담고 있는 객체를 보내 400번 에러로 응답한다.
        }

        try { // 유효성 검사에 통과한 경우
            itemDto.setPj_id(pj_id); //지금은 하드코딩이지만 나중에 현 프로젝트 아이디를 넣어줘야함.
            itemDto.setDba_reg_id(user_email); //원래는 세션에서 얻어온 아이디를 넣어줘야한다.
            if(itemService.registerItem(itemDto)==1){
                List<ItemDto> list = itemService.getItemList(pj_id);
                System.out.println("list = " + list);
                return ResponseEntity.ok().body(list);
            } else {
                throw new Exception("item register FAIL");
            }

        } catch(DuplicateKeyException e) {
            String msg = "한 프로젝트 내에서 중복된 아이템 이름을 지정할 수 없습니다.";
            System.out.println("Duplicate key");
            System.out.println("e = " + e);
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PatchMapping("/item")
    @ResponseBody
    public ResponseEntity<?> modifyItem(@RequestBody @Valid ItemDto itemDto, BindingResult result){
        log.error("\n\n itemDto={} \n\n",itemDto);
        log.error("binding result={}",result);
        log.error("**** error codes ****");
        result.getAllErrors().stream().forEach(
                error -> Arrays.stream(error.getCodes()).forEach(System.out::println)
        ); //어떤 에러코드가 출력되는지

        if(result.hasErrors()){ //유효성 검사에 실패할 경우
            log.error("result={}",result);
            log.error("**** result.getFieldError()={} ",result.getFieldError());

            log.error("***************** messageSource={}",messageSource);
            ErrorResult errorResult = new ErrorResult(result, messageSource);
            return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
            // 에러 메시지를 담고 있는 객체를 보내 400번 에러로 응답한다.
        }
        String id = "asdf"; //원래는 세션에서 얻어와야 하는 값.
        itemDto.setDba_mod_id(id); //수정자 정보를 업데이트

        try{
            int rowCnt = itemService.modifyItem(itemDto);
            if(rowCnt!=1){
                throw new Exception("modify item failed");
            }
            List<ItemDto> itemList = itemService.getItemList(itemDto.getPj_id());
            log.error("\n\n itemList={}\n\n", itemList);
            return new ResponseEntity<List<ItemDto>>(itemList, HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/item")
    @ResponseBody
    public ResponseEntity<List<ItemDto>> removeItem(Integer item_id, @SessionAttribute String user_email, ProjectDto projectDto){
//        System.out.println("itemDto = " + itemDto);
//        System.out.println("itemDto.getItem_id() = " + itemDto.getItem_id());

        //아이디가 일치해야만 아이템 삭제가 가능하도록
        //String id = (String)session.getAttribute("id"); 원래는 session으로부터 로그인 아이디를 얻어와야함
        String id = user_email;
        String pj_id = projectDto.getPj_id();
        try {
            int rowCnt = itemService.remove(item_id);
            System.out.println("rowCnt = " + rowCnt);
            if(rowCnt!=1) throw new Exception("item delete ERR");
            List<ItemDto> list = itemService.getItemList(pj_id);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/item/{pj_id}")
    @ResponseBody
    public ResponseEntity<List<ItemDto>> getItemList(@PathVariable String pj_id){
        System.out.println("pj_id = " + pj_id);
        try {
            List<ItemDto> list = itemService.getItemList(pj_id);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/item/select/{item_id}")
    @ResponseBody
    public ResponseEntity<ItemDto> getSelectedItem(@PathVariable Integer item_id){
        log.error("\n\n item_id={}\n\n",item_id);
        try{
            ItemDto itemDto = itemService.getItem(item_id);
            log.error("\n\n itemDto={}\n\n",itemDto);
            return new ResponseEntity<>(itemDto, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/items") //선물페이지에서 드롭다운 체크박스로 선택한 아이템을 가져오는 메서드
    @ResponseBody
    public ResponseEntity<List<ItemDto>> getItemSelected(String item_id){ //Dto로 넘겨주도록 수정
        System.out.println("item_id = " + item_id);
        List<ItemDto> list = new ArrayList<>();
        try {
            if(item_id!=null) {
                String[] itemIdArr = item_id.split(",");
                System.out.println("itemIdArr = " + Arrays.toString(itemIdArr));
                for (int i = 0; i < itemIdArr.length; i++) {
                    list.add(itemService.getItem(Integer.valueOf(itemIdArr[i])));
                }
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(list,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/itemCnt/{pj_id}")
    @ResponseBody
    public ResponseEntity<?> getItemCnt(@PathVariable String pj_id){
        log.error("\n\n ***** itemCnt pj_id={}\n\n",pj_id);
        try {
            int itemCnt = itemService.getItemCount(pj_id);
            log.error("\n\n ***itemCnt={}\n\n",itemCnt);
            return new ResponseEntity<>(itemCnt, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @InitBinder
//    public void dataBind(WebDataBinder binder){
////        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
////        binder.registerCustomEditor(Timestamp.class, new CustomDateEditor(df,false));
//        // 이건 item이 아니라 gift쪽에서 날짜 입력받을 때 쓰기.
//
//        binder.setValidator(new ItemValidator());
////        binder.addValidators(new GiftValidator()); //giftValidator
//        List<Validator> validatorList = binder.getValidators();
//        log.error("validatorList = {}",validatorList);
//    }

}

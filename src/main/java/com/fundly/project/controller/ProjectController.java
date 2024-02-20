package com.fundly.project.controller;

import com.fundly.project.service.GiftService;
import com.fundly.project.service.ItemService;
import com.persistence.dto.ItemDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/project")
public class ProjectController {
    ItemService itemService;
    GiftService giftService;

    @Autowired
    ProjectController(ItemService itemService, GiftService giftService){
        this.itemService = itemService;
        this.giftService = giftService;
    }

    //기본정보 작성 페이지
    @GetMapping("/default")
    public String defaultPage(){
        return "project.basicInfo";
    }

    //펀딩 계획
    @GetMapping("/funding")
    public String fundingPlan(){
        return "project.funding";
    }

    //프로젝트 계획
    @GetMapping("/story")
    public String makeStory(){
        return "project.story";
    }

    //창작자 정보 입력
    @GetMapping("/creator")
    public String creatorInfo(){
        return "project.creator";
    }


    // 아이템+선물 페이지
    @GetMapping("/reward")
    public String makeGift(Model m){
        //itemService로부터 itemDtoList를 꺼내와서 뷰에 전달함
        //뷰단에서는 itemDtoList가 empty면 보여줄 화면과 empty가 아니면 보여줄 화면이 나뉨.
        try {
            List<ItemDto> itemList = itemService.getItemList("pj1");
            System.out.println("itemList = " + itemList);
            m.addAttribute("itemList",itemList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "project.reward";
    }
    @PostMapping("/item")
    @ResponseBody
    public ResponseEntity<List<ItemDto>> makeItem(@RequestBody ItemDto itemDto){ //아이템 등록
        System.out.println("itemDto = " + itemDto);

        try {
//            itemService.removeAll(); //테스트용..
//        log.info("itemDto",itemDto);
            itemDto.setPj_id("pj1"); //지금은 하드코딩이지만 나중에 현 프로젝트 아이디를 넣어줘야함.
            itemDto.setDba_reg_id("asdf"); //원래는 세션에서 얻어온 아이디를 넣어줘야한다.
            if(itemService.registerItem(itemDto)==1){
                List<ItemDto> list = itemService.getItemList("pj1");
                System.out.println("list = " + list);
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
                throw new Exception("item register FAIL");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @DeleteMapping("/item")
    @ResponseBody
    public ResponseEntity<List<ItemDto>> removeItem(Integer item_id, HttpSession session){
//        System.out.println("itemDto = " + itemDto);
//        System.out.println("itemDto.getItem_id() = " + itemDto.getItem_id());

        //아이디가 일치해야만 아이템 삭제가 가능하도록
        //String id = (String)session.getAttribute("id"); 원래는 session으로부터 로그인 아이디를 얻어와야함
        String id = "asdf";
        String pj_id = "pj1";
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

    @GetMapping("/items")
    @ResponseBody
    public ResponseEntity<List<ItemDto>> getItemSelected(String item_id){ //Dto로 넘겨주도록 수정해야함
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


}

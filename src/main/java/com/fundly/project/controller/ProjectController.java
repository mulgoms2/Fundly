package com.fundly.project.controller;

import com.fundly.project.service.ItemServiceImpl;
import com.persistence.dto.ItemDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    ItemServiceImpl itemService;

    @GetMapping("/reward")
    public String makeGift(){
        //itemService로부터 itemDtoList를 꺼내와서 뷰에 전달함
        //뷰단에서는 itemDtoList가 empty면 보여줄 화면과 empty가 아니면 보여줄 화면이 나뉨.

        return "project/reward";
    }
    @PostMapping("/item")
    @ResponseBody
    public ResponseEntity<ItemDto> makeItem(@RequestBody ItemDto itemDto){
        System.out.println("itemDto = " + itemDto);
//        log.info("itemDto",itemDto);
        try {
            if(itemService.registerItem(itemDto)==1){
                return new ResponseEntity<>(itemDto, HttpStatus.OK);
            } else {
                throw new Exception("item register FAIL");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
//    @DeleteMapping("/item")
//    @ResponseBody
//    public ResponseEntity<ItemDto> removeItem(@RequestBody String item_name, HttpSession session){
//        System.out.println("item_name = " + item_name);
//        //아이디가 일치해야만 아이템 삭제가 가능하도록
//        //String id = (String)session.getAttribute("id"); 원래는 session으로부터 로그인 아이디를 얻어와야함
//
//
//
//    }
}

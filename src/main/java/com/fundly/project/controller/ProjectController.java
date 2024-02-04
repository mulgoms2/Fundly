package com.fundly.project.controller;

import com.fundly.project.service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    ItemServiceImpl itemService;

    @GetMapping("/reward")
    public String makeGift(){
        //giftService로부터 itemDtoList를 꺼내와서 뷰에 전달함
        //뷰단에서는 itemDtoList가 empty면 보여줄 화면과 empty가 아니면 보여줄 화면이 나뉨.

        return "project/reward";
    }
}

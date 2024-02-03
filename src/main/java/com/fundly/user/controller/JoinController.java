package com.fundly.user.controller;

import com.fundly.user.model.userDao;
import com.persistence.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/join")
public class joinController {

    @Autowired
    userDao userDao;

    @GetMapping("/add")
    public String join(){ return "user/joinForm";}

    @PostMapping("/add")
    public String joinsave(UserDto userDto) throws Exception{


        System.out.println("userDto = " + userDto);


        int rowCnt = userDao.insert(userDto);

        System.out.println("rowCnt = " + rowCnt);


        return "joinForm";
    }
}

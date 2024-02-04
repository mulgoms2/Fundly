package com.fundly.user.controller;

import com.fundly.user.model.UserJoinDao;
import com.fundly.user.service.JoinService;
import com.persistence.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/join")
public class JoinController {

    @Autowired
    UserJoinDao userJoinDao;

//    @Autowired
//    JoinService joinService;

    @GetMapping("/add")
    public String join(){ return "user/join";}

    @PostMapping("/add")
    public String joinsave(UserDto userDto) throws Exception{

        try {
            String uuid_user_id = UUID.randomUUID().toString();
            String user_status = "A"; // 활동중

            userDto.setUser_id(uuid_user_id);
            userDto.getUser_pwd();
            userDto.getUser_name();
            userDto.getUser_email();
            userDto.setUser_status(user_status);
            userDto.setDba_reg_id(uuid_user_id);

            if(userJoinDao.insert(userDto) != 1){
                throw new RuntimeException("등록 실패");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
//            throw new RuntimeException(e);
        }

        return "index";
    }
}

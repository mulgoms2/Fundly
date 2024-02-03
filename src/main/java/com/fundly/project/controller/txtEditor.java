package com.fundly.project.controller;

import com.fundly.project.model.ProjectStoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Slf4j
@Controller
@RequestMapping("/txt")
public class txtEditor {
    @GetMapping("/editor")
    public String txtEdit(){
        return "project/story2";
    }

    @PostMapping("/post")
    @ResponseBody
    public ResponseEntity<String[]> txtPost(@RequestBody String[] strArr){
//        Enumeration<String> paramNames = request.getParameterNames();
//        System.out.println("paramNames = " + paramNames);
//        while(paramNames.hasMoreElements()){
//            String paramName = paramNames.nextElement();
//            System.out.println("paramName = " + paramName);
//            String param = request.getParameter(paramName);
//            System.out.println("param = " + param);
//        }
//        System.out.println("pjTxt = " + pjTxt);
//        m.addAttribute("pjTxt",pjTxt);
//        return "project/reward2";
        System.out.println("received");

//        System.out.println("purpose = " + purpose);
        System.out.println("strArr.toString() = " + strArr.toString());
//        System.out.println("sched = " + sched);
//        System.out.println("intro = " + intro);
//        System.out.println("reward = " + reward);

//        String[] dto = new String[]{purpose, budget, sched, intro, reward};

//        ProjectStoryDto dto = new ProjectStoryDto(purpose, budget, sched, intro, reward);

        //DB에 저장하는 작업을 수행~
        //그리고 다시 뷰로 뿌려주기

        return new ResponseEntity<>(strArr, HttpStatus.OK);


    }
}

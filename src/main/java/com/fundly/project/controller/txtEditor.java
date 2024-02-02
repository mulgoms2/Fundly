package com.fundly.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Controller
@RequestMapping("/txt")
public class txtEditor {
    @GetMapping("/editor")
    public String txtEdit(){
        return "project/story";
    }

    @PostMapping("/post")
    public String txtPost(HttpServletRequest request, Model m){
        Enumeration<String> paramNames = request.getParameterNames();
        System.out.println("paramNames = " + paramNames);
        while(paramNames.hasMoreElements()){
            String paramName = paramNames.nextElement();
            System.out.println("paramName = " + paramName);
            String param = request.getParameter(paramName);
            System.out.println("param = " + param);
        }
        return "project/story";

    }
}

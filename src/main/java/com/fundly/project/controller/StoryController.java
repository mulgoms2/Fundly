package com.fundly.project.controller;

import com.fundly.project.service.ProjectService;
import com.persistence.dto.FileDto;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/project")
public class StoryController {

    @Autowired
    ProjectService projectService;

    //프로젝트 계획
    @GetMapping("/story")
    public String makeStory(){
        return "project.story";
    }


    @PostMapping("/story")
    @ResponseBody
    public ResponseEntity<?> saveStory(@RequestBody StoryForm storyForm){
        log.error("\n\n storyForm={} \n\n", storyForm);
        return ResponseEntity.ok().body(storyForm);
    }

    @PostMapping("/story/image") //TinyMCE는 하나의 이미지 당 하나의 요청을 보냄.
    @ResponseBody
    public ResponseEntity<?> saveStoryImage(FileDto uploadFile) throws ParseException {
        //업로드된 이미지를 서버에 저장하고 이미지의 원격 주소를 반환하는 메서드
        log.error("\n\n beforeImg={} \n\n", uploadFile);
        MultipartFile uploadImg = uploadFile.getFile();
        String originFileName = uploadImg.getOriginalFilename();
        String savedImgUrl = "/Users/lemon/fundly/img/" + originFileName;

        try {
            uploadImg.transferTo(new File(savedImgUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }
        uploadFile.setFile_saved_url(savedImgUrl);
        uploadFile.setFile_origin_url(originFileName);
        uploadFile.setTable_name("project");


        String location = "/project/img/"+originFileName;

        log.error("\n\n afterImg={} \n\n", uploadFile);
        String locStr = "{\"location\": \""+location+"\"}";
        return ResponseEntity.ok().headers(new HttpHeaders()).body(locStr);
    }


    @GetMapping("/test")
    public String test(){
        return "project.test";
    }

    @PostMapping("/test")
    @ResponseBody
    public ResponseEntity<?> test(@RequestBody FileDto fileDto){
        log.error("\n\n fileDto={}\n\n",fileDto);
        return ResponseEntity.ok().body(fileDto);
    }






}

package com.fundly.project.controller;

import com.fundly.project.service.ProjectService;
import com.persistence.dto.FileDto;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/project")
public class StoryController {
    String pj_id = "pj1";

    @Autowired
    ProjectService projectService;

    //프로젝트 계획
    @GetMapping("/story")//pj_id는 session에서 가져오는 것으로 수정할 예정
    public String makeStory(@RequestParam(required = false) String pj_id, Model m){
        if(pj_id!=null){
            log.error("\n\n pj_id={}\n\n",pj_id);
            StoryForm storyForm = projectService.getStoryFormByPjId(pj_id);
            log.error("\n\n storyForm={} \n\n",storyForm);
            m.addAttribute(storyForm);
        }
        return "project.story";
    }


    @PostMapping("/story")
    @ResponseBody
    public ResponseEntity<?> saveStory(@RequestBody StoryForm storyForm){
        // todo 근데 작성 중에 임시로 올린 파일인 경우 나중에 서버에서 어떻게 삭제하지?
        //  최종적으로 form을 제출할 때 포함되지 않은 이미지는 서버에서 지워야 메모리가 낭비가 안될것 같은데.
        //
        log.error("\n\n received storyForm={} \n\n", storyForm);
        try {
            storyForm = projectService.updatePjStory(storyForm);
            log.error("\n\n after storyForm={} \n\n", storyForm);
            return ResponseEntity.ok().body(storyForm);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/story/image") //TinyMCE는 하나의 이미지 당 하나의 요청을 보냄.
    @ResponseBody
    public ResponseEntity<?> saveStoryImage(FileDto uploadFile) throws ParseException {
        //업로드된 이미지를 서버에 저장하고 이미지의 원격 주소를 반환하는 메서드


        log.error("\n\n beforeImg={} \n\n", uploadFile);
        MultipartFile uploadImg = uploadFile.getFile();
        String originFileName = uploadImg.getOriginalFilename();
        String savedImgUrl = "/Users/lemon/fundly/img/" + originFileName;
        //이미지가 저장될 서버의 물리적 주소, 나중에 이미지 서버 주소로 대체

        try {
            uploadImg.transferTo(new File(savedImgUrl)); //서버에 이미지 파일 저장하기

        } catch (IOException e) {
            e.printStackTrace();
        }
        uploadFile.setFile_saved_url(savedImgUrl);
        uploadFile.setFile_origin_url(originFileName);
        uploadFile.setTable_name("project");


        String location = "/project/img/"+originFileName;
        //textEditor에 전달하는 이미지의 remote 주소
        //resource handler가 이 주소로 온 resource에 대한 요청을 서버의 물리적 주소로 해석해준다.

        log.error("\n\n afterImg={} \n\n", uploadFile);
        String locStr = "{\"location\": \""+location+"\"}";
        return ResponseEntity.ok().headers(new HttpHeaders()).body(locStr);
    }




}

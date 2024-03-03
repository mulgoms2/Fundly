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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/project")
public class StoryController {

    @Autowired
    ProjectService projectService;

    String IMG_SAVE_SERVER_LOC = "/Users/lemon/fundly/img/"; //여기둬도 되는걸까


    //프로젝트 계획
    @GetMapping("/story")//pj_id는 session에서 가져오는 것으로 수정할 예정
    public String makeStory(@SessionAttribute String pj_id, Model m, @RequestParam(required = false) boolean edit){
        //작성한 내용이 아무것도 없으면 프로젝트 에디터를 띄우고
        //작성한 항목이 하나라도 있으면, 작성한 내용을 먼저 보여주고 수정버튼을 누르면 에디터를 띄워주도록 하기
        log.error("\n\n pj_id={}\n\n",pj_id);
        log.error("\n\n edit={}\n\n",edit);
        StoryForm storyForm = projectService.getStoryFormByPjId(pj_id);
        if(edit){
            storyForm.setEdit();
        }
        log.error("\n\n storyForm={} \n\n",storyForm);
        log.error("\n\n storyForm.getIsEmpty()",storyForm.getIsEmpty()+"");
        m.addAttribute(storyForm);

        return "project.story";
    }


//    @PostMapping("/story") fetch로 온 요청에 대해서는 view를 반환하지 못하는듯.
//    public String saveStory(@RequestBody StoryForm storyForm, Model m){
//        //  최종적으로 form을 제출할 때 포함되지 않은 이미지는 서버에서 지워야 메모리가 낭비가 안될것 같은데.
//        //
//        log.error("\n\n received storyForm={} \n\n", storyForm);
//        try {
//            storyForm = projectService.updatePjStory(storyForm);
//            log.error("\n\n after storyForm={} \n\n", storyForm);
//            throw new Exception("test");
//            //m.addAttribute(storyForm);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            String msg = "FAIL";
//            m.addAttribute(msg);
//        }
//        return "project.reward";
//    }
    @PostMapping("/story")
    @ResponseBody
    public ResponseEntity<?> saveStory(@RequestBody StoryForm storyForm){
        // 수정된 내용을 받아와서
        // 그중 최종 저장하는 이미지 파일만 남기고 서버에 임시 저장된 파일은 삭제
        // 최종 저장할 이미지 경로를 File테이블에 insert
        // DB Project테이블 update
        // 뷰로 업데이트 된 데이터를 다시 전송

        String[] imgArr = storyForm.getImgArr();
        String[] delArr = mkDeleteArray(imgArr);
        log.error("\n\n imgArr={} \n\n", Arrays.toString(imgArr));
        log.error("\n\n received storyForm={} \n\n", storyForm);
        try {
            deleteTempFile(delArr); //서버에 저장된 임시 이미지 파일을 삭제한다.

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
        //DB에 저장은 아직 안함. (view에서 saveBtn을 누를때 file테이블에 저장)

        log.error("\n\n beforeImg={} \n\n", uploadFile);
        MultipartFile uploadImg = uploadFile.getFile();
        String originFileName = uploadImg.getOriginalFilename();
        String savedImgUrl = IMG_SAVE_SERVER_LOC + originFileName;
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

    public String[] mkDeleteArray(String[] imgArr){
        //imgArr는 저장이 확정된 파일명 배열
        //이미지 서버에 저장된 이미지 이름을 불러온다. tempArr
        List<String> list = new ArrayList();
        List imgList = Arrays.asList(imgArr);
        log.error("\n\n imgList={}\n\n", imgList);
        File file = new File(IMG_SAVE_SERVER_LOC);
        String[] tempArr = file.list();
        log.error("\n\n tempArr={} \n\n", Arrays.toString(tempArr));
        //js와 달리 java는 Arrays에 contains가 없네..List로 바꿔서 처리.
        for(int i=0; i<tempArr.length; i++){
            if(!imgList.contains(tempArr[i])){
                list.add(tempArr[i]);
            }
        }
        //삭제할 파일 이름 배열을 반환한다.
        log.error("\n\n delList={}\n\n",list);
        return list.toArray(String[]::new);
    }

    public void deleteTempFile(String[] delArr) throws Exception{
        //삭제할 파일목록을 주면 파일 서버에서 임시파일 삭제.
        File file;
        for(int i=0; i<delArr.length; i++){
            file = new File(IMG_SAVE_SERVER_LOC+delArr[i]);
            log.error("\n\n IMG_SAVE_SERVER_LOC+delArr[i]={} \n\n", IMG_SAVE_SERVER_LOC+delArr[i]);
            if(!file.delete()){
                throw new Exception("파일 삭제 실패");
            }
        }
    }



}

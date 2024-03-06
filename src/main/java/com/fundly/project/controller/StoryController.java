package com.fundly.project.controller;

import com.fundly.project.service.ProjectService;
import com.persistence.dto.ProjectDto;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/project")
public class StoryController {

    ProjectService projectService;
    StoryImageValidator imageValidator;
    MessageSource messageSource;
  
//    String IMG_SAVE_SERVER_LOC = "/Users/lemon/fundly/img/"; //컨트롤러에서밖에 안 쓰는데 여기 둬도 될까
    static final String IMG_SAVE_SERVER_LOC = "/Users/lemon/fundly/img/"; //컨트롤러에서밖에 안 쓰는데 여기 둬도 될까
    static final String REMOTE_URL = "/project/img/";
  
    @Autowired
    StoryController(ProjectService projectService, StoryImageValidator imageValidator, MessageSource messageSource){
        this.projectService = projectService;
        this.imageValidator = imageValidator;
        this.messageSource = messageSource;
    }

    //프로젝트 계획
    @GetMapping("/editor/story")//pj_id는 session에서 가져오는 것으로 수정할 예정
    public String makeStory(@SessionAttribute ProjectDto projectDto, Model m, @RequestParam(required = false) boolean edit){
        //작성한 내용이 아무것도 없으면 프로젝트 에디터를 띄우고
        //작성한 항목이 하나라도 있으면, 작성한 내용을 먼저 보여주고 수정버튼을 누르면 에디터를 띄워주도록 하기
        //해당 상태에 대한 정보를 storyForm이 변수로 갖고 있어서 jsp에서 그에 따라 맞는 코드를 보여준다.
        log.error("\n\n projectDto={}\n\n", projectDto);
//        log.error("\n\n edit={}\n\n",edit);
        StoryForm storyForm = projectService.getStoryFormByPjId(projectDto.getPj_id());
        if (edit) {
            storyForm.setEdit();
        }
//        log.error("\n\n storyForm={} \n\n",storyForm);
//        log.error("\n\n storyForm.getIsEmpty()={}",storyForm.getIsEmpty());
        m.addAttribute(storyForm);
        return "project.story";
    }


    @PostMapping("/story")
    @ResponseBody
//    public ResponseEntity<?> saveStory(@RequestBody StoryForm storyForm, @SessionAttribute ProjectDto projectDto){
    public ResponseEntity<Boolean> saveStory(@RequestBody StoryForm storyForm, @SessionAttribute ProjectDto projectDto) {
        // 수정된 내용을 받아와서
        // 1. 그중 최종 저장하는 이미지 파일만 남기고 서버에 임시 저장된 파일은 삭제
        // 2. 최종 저장할 이미지 경로를 File테이블에 insert
        // -> story의 경우 DB 파일테이블에 저장할 필요는 없을 것 같다.
        // 3. DB Project테이블 update
        // 뷰로 업데이트 된 데이터를 다시 전송
        String[] imgArr = storyForm.getImgArr();
        String[] delArr = mkDeleteArray(imgArr);
        //log.error("\n\n imgArr={} \n\n", Arrays.toString(imgArr));
        //log.error("\n\n received storyForm={} \n\n", storyForm);
        try {
            //1.최종 저장하는 이미지만 남기고 서버에서 임시 파일 삭제하기
            deleteTempFile(delArr); //지우기 실패하면 에러를 던지게 해둠.

            //2.최종 저장할 이미지 DB File테이블에 insert 또는 update
            // 근데 DB에서 내가 이 경로를 꺼내 쓸 일이 없는데 저장의 이유가 있을까? 좀 회의적이다.

            //3. Pj테이블 업데이트
            //storyForm = projectService.updatePjStory(storyForm); //범용update 이전 코드
            projectDto.updateStory(storyForm);
            projectDto = projectService.update(projectDto); //업데이트된 dto반환
            StoryForm ResponseStoryForm = ProjectDto.toStoryForm(projectDto); //

            log.error("\n\n after storyForm={}\n\n", storyForm);
            log.error("\n\n after projectDto={} \n\n", projectDto);
            //return ResponseEntity.ok().body(ResponseStoryForm);
            return ResponseEntity.ok().body(true);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //TinyMCE는 하나의 이미지 당 하나의 요청을 보냄.
    //이미지 자동업로드 설정이라, 텍스트 에디터에 추가되는 이미지마다 이 요청을 보내는 것
    @PostMapping("/story/image")
    @ResponseBody
    public ResponseEntity<?> saveStoryImage(StoryImage uploadFile, BindingResult result, HttpServletRequest request) throws ParseException, IOException {
        //Multipart를 file로 가진 FileDto로 받지 않고 MultipartFile로 바로 받으면 아무것도 받을 수가 없다.(null) 왜지?
        //@RequestBody를 붙여도 안되더라.
        //업로드된 이미지를 서버에 저장하고 이미지의 원격 주소를 반환하는 메서드
        //DB에 경로 저장은 아직 안함. (view에서 saveBtn을 누를때 file테이블에 저장 예정)

        this.imageValidator.validate(uploadFile, result); //이미지 파일 검증

        log.error("binding result={}",result);
        log.error("**** error codes ****");
        result.getAllErrors().stream().forEach(
                error -> Arrays.stream(error.getCodes()).forEach(System.out::println)
        ); //어떤 에러코드가 출력되는지

        if(result.hasErrors()){
            //유효성 검사에 실패하면
            ErrorResult errorResult = new ErrorResult(result, messageSource);
            //에러메시지와 함께 400번 에러를 전달
            log.error("\n\n errorResult = {} \n\n", errorResult);
            return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
            //errorResult를 js에서 출력하고 싶은데, tinyMCE의 image upload handler를 직접 구현한게 아니라 구체적으로 메세지 출력까진 힘들다.
            //image upload handler를 직접 구현할 수 있으면 제일 좋을 것 같은데..
        }

        log.error("\n\n beforeImg={} \n\n", uploadFile);
      
        MultipartFile uploadImg = uploadFile.getFile();
        uploadFile.setMetaData();
      
        log.error("\n\n afterImg={} \n\n", uploadFile);
        log.error("\n\n img size={}\n\n",uploadImg.getSize());
      
        String contentType = uploadImg.getContentType();
        log.error("\n\n img type={}\n\n", uploadImg.getContentType());
      
//        uploadFile.setDimension();
      
//        log.error("\n\n width={}\n\n", uploadFile.getWidth());
//        log.error("\n\n height={}\n\n", uploadFile.getHeight());
      
        String originFileName = uploadImg.getOriginalFilename();
      
        log.error("\n\n originFileName={}\n\n",originFileName);

        String savedImgUrl = IMG_SAVE_SERVER_LOC + originFileName;
        //이미지가 저장될 서버의 물리적 주소, 나중에 이미지 서버 주소로 대체

        try {
            uploadImg.transferTo(new File(savedImgUrl)); //서버에 이미지 파일 저장하기
        } catch (IOException e) {
            e.printStackTrace();
        }
//        uploadFile.setFile_saved_url(savedImgUrl);
//        uploadFile.setFile_origin_url(originFileName);
//        uploadFile.setTable_name("project");


        String location = request.getContextPath() + REMOTE_URL + originFileName;
        //textEditor에 전달하는 이미지의 remote 주소
        //resource handler가 이 주소로 온 resource에 대한 요청을 서버의 물리적 주소로 해석해준다.

        // log.error("\n\n afterImg={} \n\n", uploadFile);
        String locStr = "{\"location\": \"" + location + "\"}";
        return ResponseEntity.ok().headers(new HttpHeaders()).body(locStr);


    }

    public String[] mkDeleteArray(String[] imgArr) {
        //삭제할 이미지 이름을 담을 리스트
        List<String> list = new ArrayList();

        //imgArr는 저장이 확정된 파일명 배열. 편의를 위해 리스트로 바꿔줌
        List imgList = Arrays.asList(imgArr);
        log.error("\n\n imgList={}\n\n", imgList);

        //이미지 서버에 임시저장된 이미지 이름을 불러온다. tempArr
        File file = new File(IMG_SAVE_SERVER_LOC);
        String[] tempArr = file.list();
        log.error("\n\n tempArr={} \n\n", Arrays.toString(tempArr));

        //js와 달리 java는 Arrays에 contains가 없네..List로 바꿔서 처리.
        for (int i = 0; i < tempArr.length; i++) {
            if (!imgList.contains(tempArr[i])) {
                list.add(tempArr[i]); //확정된 이미지 파일 리스트에 포함되어 있지 않은 임시파일을 리스트에 담는다.
            }
        }
        //삭제할 파일 이름 배열을 반환한다. (리스트로 그냥 반환할까? 꼭 배열로 할 이유는 없긴한데)
        log.error("\n\n delList={}\n\n", list);
        return list.toArray(String[]::new);
    }

    public void deleteTempFile(String[] delArr) throws Exception {
        //삭제할 파일목록을 주면 파일 서버에서 임시파일 삭제.
        File file;
        for (int i = 0; i < delArr.length; i++) {
            file = new File(IMG_SAVE_SERVER_LOC + delArr[i]);
            if (!file.delete()) { //삭제 성공하면 true를 반환함.
                throw new Exception("파일 삭제 실패");
            }
        }
    }

}

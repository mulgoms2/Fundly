package com.fundly.chat.controller;

import com.fundly.chat.service.ChatService;
import com.persistence.dto.ChatRequest;
import com.persistence.dto.FileDto;
import com.persistence.dto.SelBuyMsgDetailsDto;
import com.persistence.dto.SelBuyMsgDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@Slf4j
public class ChatController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    ChatService chatService;

    public static final Path SYS_USER = Paths.get(System.getProperty("user.name"));
    public static final String IMG_SAVE_LOCATION = "/Users/" + SYS_USER + "/fundly/chat/img/";

    @GetMapping("/chat/test")
//    테스트용
    public String chatRoom() {
        return "chat/asyncAwait";
    }

    @ResponseBody
    @PostMapping("/chat/test")
    public ResponseEntity<SelBuyMsgDto> test(@RequestBody ChatRequest chatRequest) {

        SelBuyMsgDto selBuyMsgDto = chatService.joinChatRoom(chatRequest);

        return ResponseEntity.ok()
                .body(selBuyMsgDto);
    }

    @GetMapping("/chatPop")
    public String joinChatRoom(@Valid @ModelAttribute ChatRequest chatRequest, BindingResult result) {
        if (result.hasErrors()) {
//            로그인 정보를 담지 않은 사용자가 접속시. 로그인후 이용하라는 오류 메시지를 띄운 페이지로 이동한다.
            return "chat/error";
        }
//        user_id, pj_id를 통해 식별되는 채팅방을 불러온다.
        SelBuyMsgDto chatRoom = chatService.joinChatRoom(chatRequest);

        chatRequest.setSelBuyMsgDto(chatRoom);

        return "chat/chat";
    }

    //    MessageMapping을 통해 유저의 메시지 전송이 매핑되며. /chatPub/chat/{방번호} pathVariable 의 일종인 것 같다.
    @MessageMapping("/chat/{roomNum}")
    @SendTo("/chatSub/{roomNum}")
    public SelBuyMsgDetailsDto publishMessage(@DestinationVariable String roomNum, SelBuyMsgDetailsDto message, SimpMessageHeaderAccessor accessor) {
//        채팅방에 권한이 없는 유저가 메시지를 보내면 예외를 발생시켜 에러페이지로 이동시킨다.

//        채팅 저장 실패시 유저에게 메시지로 알린다.
        boolean isSucceed = chatService.saveMessage(message);
//            dto에 에러를 담아서 전달한다.
//            message.setError();

//        메시지를 토픽에 발행한다. sendTo /chatSup/{}
        return message;
    }

    @PostMapping("/chat/file")
    @ResponseBody
    public void uploadFile(@Valid FileDto file, SelBuyMsgDetailsDto message, HttpServletRequest request) {
//        유효하지 않은 파일이 오면 브라우저에 400 에러가 응답으로 전송된다.
        try {
//            이미지 파일을 서버에 저장한다.
            saveFileToDrive(file, request);
//            파일 url을 저장한다.
            chatService.saveFileMessage(file, message);
        } catch (Exception e) {
            log.error("error with uploadFile = {}", file);
            throw new RuntimeException("error with uploadFile(FileDto file, SelBuyMsgDatailsDto message)", e);
        }
//        채팅방에 이미지 경로가 담긴 메시지를 토픽에 발행한다
        simpMessagingTemplate.convertAndSend("/chatSub/" + message.getRoom_num(), message);
    }

//    @GetMapping(value = "**/file/{fileName}")
//    @ResponseBody
////    이미지 태그가 파싱될때 src 주소에 의한 get 요청이 들어온다. Resource로 이미지를 응답한다.
//    public byte[] getImageResource(@PathVariable("fileName") String fileName) {
//
//        String savedImageLocation =  "/Users/dobigulbi/chat/file/" + fileName;
//
//        try (FileInputStream fis = new FileInputStream(new File(savedImageLocation));
//             BufferedInputStream bis = new BufferedInputStream(fis))
//        {
//            byte[] bytes = bis.readAllBytes();
//
//            return bytes;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    @GetMapping(value = "**/file/{fileName}")
//    @ResponseBody
////    이미지 태그가 파싱될때 src 주소에 의한 get 요청이 들어온다. Resource로 이미지를 응답한다.
//    public Resource getImageResource(@PathVariable("fileName") String fileName) {
//
//        String savedImageLocation = "file:/" + IMG_SAVE_LOCATION + fileName;
//
//        try {
//            return new UrlResource(savedImageLocation);
////            return new UrlResource(String.format("file:%s%s", IMG_SAVE_LOCATION, fileName));
//        } catch (Exception e) {
//            log.error("error with getImageResouce = {}", fileName);
//            throw new RuntimeException("유효하지 않은 파일명 입니다.", e);
//        }
//    }

    private void saveFileToDrive(FileDto uploadFile, HttpServletRequest request) {
        String originFileName = uploadFile.getFile()
                .getOriginalFilename();
        String uuid = UUID.randomUUID()
                .toString();
        String local_storage_url = request.getContextPath() + IMG_SAVE_LOCATION + uuid + originFileName;
        String db_saved_img_url = "/chat/img/" + uuid + originFileName;

        MultipartFile file = uploadFile.getFile();

        try {
            file.transferTo(new File(local_storage_url));
        } catch (IOException e) {
            e.printStackTrace();
            log.error("ChatController.saveFileToDrive(). 파일 저장중 예외가 발생하였습니다.");
            throw new RuntimeException(e);
        }
//        저장된 파일 이름을 담는다.
        uploadFile.setFile_saved_url(db_saved_img_url);
        uploadFile.setFile_origin_url(originFileName);
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleException() {
        log.error("ChatController.handleException() 채팅 컨트롤러 수행중 예외발생 캐치됨. 에러페이지로 이동. .\n");
        return "chat/error";
    }
}

package com.fundly.user.controller;

import com.fundly.user.service.LikeService;
import com.persistence.dto.LikeDto;
import com.persistence.dto.ProjectDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class LikeController {

    @Autowired
    LikeService likeservice;
    LikeDto likedto;

//    detail.jsp의 class =likeBtn" 에 들어갈 코드
//    null이 아니면 좋아요상태에따라 빈하트/빨간하트 출력
//    <c:if test="${result != null}">
//        <a class="like">
//        <c:if test="${result.check == 0}">
//            <img src="빈하트">
//        </c:if>
//        <c:if test="${result.check == 1}">
//            <img src="빨간하트">
//        </c:if>
//        </a>
//    </c:if>

    @GetMapping("/like")
    public String like(){
        int result = 0;
        return "user/like";
    }

    @ResponseBody
    @GetMapping("/like2")
    public int likePOST(ProjectDto pjdto, HttpSession session, Model model) throws Exception {

        // 비회원일때
        int result=-1;
        String user_id = (String)session.getAttribute("user_id");
        if(user_id == null) {
            return result;
        }

        // like객체 생성하여 값 세팅
        LikeDto likedto = new LikeDto();
        likedto.setUser_id(user_id);
        likedto.setPj_id(pjdto.getPj_id());

        result = likeservice.checkLike(likedto);

        session.setAttribute("result",result);
        return result;
    }
}

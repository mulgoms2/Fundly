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

//<script>
//    $(document).ready(function() {
//        $(".like").on("click", function(){
//
//            $.ajax({
//                    url : "/product/like",
//                    type: 'GET',
//                    data: {'pj_id':'${pj_id}', 'buyer_id':'${user_id}'},
//            success:function(data){
//
//                if(data==1){
//                    like2 = true;
//                    alert("상품 찜 하셨습니다.");
//                    $('#like').attr("src","빨간하트");
//                    var result = confirm('찜목록으로 이동하시겠습니까?');
//                    if (result) {
//                        //yes
//                        //찜 리스트 페이지 생성 후 -> 찜리스트 페이지 이동으로 변경
//                        location.href='/user/like';
//
//                    }
//
//                }
//                else if(data == -1){
//                    alert("로그인이 필요한 서비스입니다. ");
//
//
//                }
//                else {
//                    like2 =false;
//                    alert("상품 찜 취소하셨습니다. ");
//                    $('#like').attr("src","빈하트");
//                }
//
//            },
//            error:function(error){
//                console.log(error);
//            }
//
//
//		 	});
//        });
//    });
//
//</script>

    @ResponseBody
    @RequestMapping(value="/like")
    public int likePOST(ProjectDto pjdto, HttpSession session, Model model) throws Exception {

        // 비회원일때
        int result=-1;
        String user_id = (String)session.getAttribute("user_id");
        if(user_id == null)
            return result;

        // like객체 생성하여 값 세팅
        LikeDto likedto = new LikeDto();
        likedto.setUser_id(user_id);
        likedto.setPj_id(pjdto.getPj_id());

        result = likeservice.checkLike(likedto);

        session.setAttribute("result",result);
        return result;
    }

    //    @GetMapping(value="like", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
//    @ResponseBody
//    public String getList(String user_id) {
//        Log.info("In Controller UserId : " + user_id);
//        return new ResponseEntity<>(likeService.getList(user_id), HttpStatus.OK);
//    }

    @GetMapping(value="like", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity getList(String user_id) throws Exception {
        log.info("In Controller UserId : " + user_id);
        return new ResponseEntity<>(likeservice.getList(likedto), HttpStatus.OK);
//        return "/user/like";
    }

}

package com.fundly.user.controller;

import com.fundly.user.service.LikeService;
import com.persistence.dto.LikeDto;
import com.persistence.dto.ProjectDto;
import com.persistence.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class LikeController {

    @Autowired
    LikeService likeservice;
    ArrayList<ProjectDto> projects = new ArrayList<>();
    ArrayList<LikeDto> likes = new ArrayList<>();
    ProjectDto project;
    UserDto user;

    int number = 4; // 게시물번호를 만들기 위한 데이터
    int lnumber = 1; // 좋아요번호를 만들기 위한 데이터

    public void makesTestData() {
        ProjectDto pj1 = new ProjectDto("P001",0);
        ProjectDto pj2 = new ProjectDto("P002",0);
        ProjectDto pj3 = new ProjectDto("P003",0);

        projects.add(pj1);
        projects.add(pj2);
        projects.add(pj3);
    }

    public void doCommand(String command) {
        if(command.equals("click like")) {
            // 좋아요 유무체크
            LikeDto target = checkLike(project.getPj_id(),"bada");

            if(target == null) {
                addLike(project);
            } else {
                deleteLike(target);
            }
        }
        if(command.equals("project  list")) {
            printProject(project);
        }
    }

    // 특정 게시물을 받아 출력하는 메서드
    public void printProject(ProjectDto pj) {
        System.out.println("===================");
        System.out.println("제목: " + pj.getPj_id());
        // 좋아요 저장소에서 pj게시물 번호로 좋아요 체크된 개수 세기
        if(checkLike(pj.getPj_id(),user.getUser_id())==null) {
        System.out.println("빨간 좋아요: " + getLikeCnt(pj));
        } else {
        System.out.println("하얀 좋아요: " + getLikeCnt(pj));
        }
        System.out.println("===================");
    }
    public void addLike(ProjectDto pj) {
//        new LikeDto(lnumber,user.getUser_id(),p1.getPj_id());
        LikeDto l1 = new LikeDto("bada",pj.getPj_id());
        likes.add(l1);
        lnumber++;
    }

    public void deleteLike(LikeDto like) {
        // ArrayList remove -> 인덱스로 삭제, 값으로 삭제
        likes.remove(like);
    }

    public LikeDto checkLike(String pj_id, String user_id) {
        for(int i =0; i < likes.size(); i++) {
            LikeDto like = likes.get(i);
            // 로그인 유저 아이디와 상세페이지 프로젝트아이디가 같은 좋아요 목록 찾기
            if(like.getPj_id().equals(pj_id) && like.getUser_id().equals(user_id)) {
                return like;
            }
        }
        return null;
    }

    public int getLikeCnt(ProjectDto pj) {
        int cnt = 0;

        for(int i =0; i<likes.size();i++) {
            LikeDto like = likes.get(i);
            if(like.getPj_id().equals(pj.getPj_id())) {
                cnt++;
            }
        }
        return cnt;
    }
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


    @RequestMapping(value="/like")
    public String like(HttpServletRequest req, Model model) throws Exception {
        String pj_id = req.getParameter("pj_id");
        String user_id = req.getParameter("user_id");

        LikeDto likedto = new LikeDto();
        likedto.setPj_id(pj_id);
        likedto.setUser_id(user_id);

        model.addAttribute("pj_id", pj_id);
        model.addAttribute("user_id", user_id);
        model.addAttribute("like_status", likeservice.checkLike(likedto));
        model.addAttribute("list", likeservice.getList(likedto));
        return "user/like";
    }

    @RequestMapping("/like2")
    public @ResponseBody List<LikeDto> ajaxTest(LikeDto likedto) throws Exception {
        return likeservice.getList(likedto);
//        // 비회원일때
//        result=-1;
//        String user_id = (String)session.getAttribute("user_id");
//        if(user_id == null) {
//            return result;
//        }

//        session.setAttribute("result",result);
//        return result;

    }
}

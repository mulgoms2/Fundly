package com.fundly.user.controller;

import com.fundly.user.service.LikeService;
import com.persistence.dto.LikeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
public class LikeController {

    @Autowired
    LikeService likeservice;

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

    @PostMapping("/like2")
    @ResponseBody
    public List<LikeDto> addLikePj(@RequestBody LikeDto likedto) throws Exception {
//        String pj_id = req.getParameter("pj_id");
//        String user_id = req.getParameter("user_id");

//        LikeDto likedto = new LikeDto();
//        likedto.setPj_id(pj_id);
//        likedto.setUser_id(user_id);
//
//        model.addAttribute("pj_id", pj_id);
//        model.addAttribute("user_id", user_id);
//        model.addAttribute("like_status", likeservice.checkLike(likedto));
//        model.addAttribute("list", likeservice.getList(likedto));
        return likeservice.getList(likedto);
    }

//    @GetMapping("/detail")
//    @ResponseBody
//    public ResponseEntity<List<LikeDto>> addLikePj(HttpServletRequest req, Model model) throws Exception {
//        String pj_id = req.getParameter("pj_id");
//        String user_id = req.getParameter("user_id");
//
//        LikeDto likedto = new LikeDto();
//        likedto.setPj_id(pj_id);
//        likedto.setUser_id(user_id);
//
//        model.addAttribute("pj_id", pj_id);
//        model.addAttribute("user_id", user_id);
//        model.addAttribute("like_status", likeservice.checkLike(likedto));
//        model.addAttribute("list", likeservice.getList(likedto));
//        List<LikeDto> likes = likeservice.getList(likedto);
//        return new ResponseEntity<>(likes, HttpStatus.OK);
//    }

//    @DeleteMapping("/item")
//    @ResponseBody
//    public ResponseEntity<List<ItemDto>> removeItem(String item_id, HttpSession session){
////        System.out.println("itemDto = " + itemDto);
////        System.out.println("itemDto.getItem_id() = " + itemDto.getItem_id());
//
//        //아이디가 일치해야만 아이템 삭제가 가능하도록
//        //String id = (String)session.getAttribute("id"); 원래는 session으로부터 로그인 아이디를 얻어와야함
//        String id = "asdf";
//        String pj_id = "pj1";
//        try {
//            int rowCnt = itemService.remove(item_id, id);
//            System.out.println("rowCnt = " + rowCnt);
//            if(rowCnt!=1) throw new Exception("item delete ERR");
//            List<ItemDto> list = itemService.getItemList(pj_id);
//            return new ResponseEntity<>(list, HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}

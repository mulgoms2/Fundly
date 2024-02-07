package com.fundly.user.service;

import com.fundly.user.model.LikeDao;
import com.persistence.dto.LikeDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class likeServiceImpl<LiketDto> implements likeService{
    @Autowired
    LikeDao likedao;
    LikeDto likedto = new LikeDto("bada","바다",0);

    //찜하기
    @SneakyThrows
    public void register(LikeDto likedto){
        likedao.insertLike(likedto);
    }

    //찜목록 가져오기
    @SneakyThrows
    public List<LikeDto> getLikeList(LikeDto likedto) {
        return likedao.getLikeList(likedto);
    }

    //찜 취소하기
    @SneakyThrows
    public boolean remove(String user_id, String pj_id) {
        return likedao.cancelLike(likedto);
    }

    //찜 상태 확인
    @SneakyThrows
    public LikeDto checkLike(LikeDto likedto) {
        return likedao.checkLike(likedto.getUser_id(), likedto.getPj_id());
    }

//    @Override
//    public void selectLike(Map<String, String> map) {
//        UserDto userdto;
//        ProjectDto pjdto;
//        userdto = new UserDto("bada","바다","1234");
//        pjdto = new ProjectDto("P001");
//        map = new HashMap<>();
//        map.put("user_id", userdto.getUser_id());
//        map.put("pj_id", pjdto.getPj_id());
//
//    }
}

package com.fundly.user.service;

import com.persistence.dto.LikeDto;
import com.persistence.dto.ProjectDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface LikeService {

    //    찜 상태 변경
    void changeLike(LikeDto likedto, ProjectDto pjdto) throws Exception;

//    void changeLike(LikeDto likedto);

    //    현재 찜한 정보 가져오기
    LikeDto getLike(LikeDto likedto) throws Exception;

    // 찜한 목록 상태별로 가져오기
    List<ProjectDto> getListWithPjStatus(LikeDto likedto, String pj_status) throws Exception;

    List<ProjectDto> getListWithPjEntire(LikeDto likedto) throws Exception;

    List<LikeDto> getPage(Map map) throws Exception;

}

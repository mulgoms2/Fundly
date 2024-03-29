package com.fundly.user.service;

import com.fundly.user.dto.LikeProjectDto;
import com.fundly.user.dto.LikeRequestDto;
import com.persistence.dto.LikeDto;
import com.persistence.dto.ProjectDto;

import java.util.List;
import java.util.Map;

public interface LikeService {

    //    찜 상태 변경
    void changeLike(LikeDto likedto, ProjectDto pjdto) throws Exception;

    //    현재 찜한 정보 가져오기
    List<LikeProjectDto> getLikeList(String user_id) throws Exception;

    // 현재 찜한 정보 가져오기 in 상세페이지
    LikeProjectDto getLike(LikeDto likedto) throws Exception;

    // 현재 프로젝트에 대한 좋아요상태 확인 in 상세페이지
    LikeDto checkLike(LikeRequestDto likereq) throws Exception;

    // 찜한 목록 개수 가져오기
    int getLikeCnt(LikeDto likedto) throws Exception;

    // 찜한 목록 상태별로 가져오기
    List<ProjectDto> getListWithPjStatus(LikeDto likedto, String pj_status) throws Exception;

    List<ProjectDto> getListWithPjEntire(LikeDto likedto) throws Exception;

    List<LikeDto> getPage(Map map) throws Exception;

}

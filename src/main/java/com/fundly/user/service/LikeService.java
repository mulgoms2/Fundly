package com.fundly.user.service;

import com.persistence.dto.LikeDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LikeService {

    //    찜 상태 변경
    int changeLike(LikeDto likedto) throws Exception;

    //    현재 찜한 정보 가져오기
    LikeDto getLike(LikeDto likedto) throws Exception;

    // 찜한 목록 전체 가져오기
    List<LikeDto> getLikeList(LikeDto likedto) throws Exception;

}

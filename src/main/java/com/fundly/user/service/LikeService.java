package com.fundly.user.service;

import com.persistence.dto.LikeDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LikeService {

    //    찜 상태 확인
    int changeLike(LikeDto likedto) throws Exception;

    //    찜목록 가져오기
    LikeDto getLike(LikeDto likedto) throws Exception;

    //    찜 취소하기
    int remove(LikeDto likedto);

}

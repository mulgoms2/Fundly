package com.fundly.user.service;

import com.persistence.dto.LikeDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LikeService {

    //    찜 상태 확인
    int checkLike(LikeDto likedto) throws Exception;

    //    찜목록 가져오기
    List<LikeDto> getList(LikeDto likedto) throws Exception;

    //    찜 취소하기
    int remove(LikeDto likedto);

}

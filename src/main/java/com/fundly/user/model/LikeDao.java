package com.fundly.user.model;


import com.persistence.dto.LikeDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface LikeDao {

    //현재 좋아요 확인(조건: 해당 유저와 해당 프로젝트 아이디 모두 일치)
    LikeDto getLike(LikeDto likedto) throws Exception;

    //회원의 현재 좋아요한 목록만 확인(조건: 좋아요상태가 1이고 해당 유저의 아이디와 일치)
    List<LikeDto> AllLikeList(LikeDto likedto) throws Exception;

    // 최근 좋아요 순
    List<LikeDto> LikeListByTime(LikeDto likedto) throws Exception;

    //처음 좋아요
    int insertLike(LikeDto likedto) throws Exception;

    //좋아요 취소
    int cancelLike(LikeDto likedto) throws Exception;

    //다시 좋아요
    int reLike(LikeDto likedto) throws Exception;

    //전체목록 삭제
    int deleteAllLike() throws Exception;
}

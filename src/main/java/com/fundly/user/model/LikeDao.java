package com.fundly.user.model;


import com.persistence.dto.LikeDto;
import com.persistence.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface LikeDao {
    //좋아요 목록 확인
    List<LikeDto> getLikeList(LikeDto likedto) throws Exception;

    //처음 좋아요
    void insertLike(LikeDto likedto) throws Exception;

    //좋아요 취소
    boolean cancelLike(LikeDto likedto) throws Exception;

    //다시 좋아요
    void reLike(LikeDto likedto) throws Exception;

    //전체목록 삭제
    boolean deleteAllLike(LikeDto likedto) throws Exception;

    //이미 좋아요한 프로젝트인지 조회
//    LikeDto checkLike(@Param("likedto") LikeDto likedto);

    LikeDto checkLike(String userId, String pjId);
}

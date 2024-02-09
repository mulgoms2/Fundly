package com.fundly.user.model;


import com.persistence.dto.LikeDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface LikeDao {
    //좋아요 목록 확인
    List<LikeDto> getLikeList(LikeDto likedto) throws Exception;

    //처음 좋아요
    int insertLike(LikeDto likedto) throws Exception;

    //좋아요 취소
    int cancelLike(LikeDto likedto) throws Exception;

    //다시 좋아요
    int reLike(LikeDto likedto) throws Exception;

    //전체목록 삭제
    int deleteAllLike() throws Exception;
}

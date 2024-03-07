package com.fundly.user.model;


import com.fundly.user.dto.LikeProjectDto;
import com.persistence.dto.LikeDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

@Mapper
public interface LikeDao {

    //현재 좋아요한 프로젝트 수
    int countLike(LikeDto likedto) throws DataAccessException;

    //현재 좋아요 확인(조건: 해당 유저와 해당 프로젝트 아이디 모두 일치)
    LikeDto checkLike(LikeDto likedto) throws DataAccessException;

    //회원의 현재 좋아요한 목록만 확인(최근 좋아요 순)
    List<LikeDto> AllLikeList(LikeDto likedto) throws DataAccessException;

    //프로젝트와 조인한 좋아요 목록
    List<LikeProjectDto> AllLikeListWithPj(String user_id) throws DataAccessException;

    //처음 좋아요
    int insertLike(LikeDto likedto) throws DataAccessException;

    //좋아요 취소
    int cancelLike(LikeDto likedto) throws DataAccessException;

    //다시 좋아요
    int reLike(LikeDto likedto) throws DataAccessException;

    //프로젝트 삭제시 좋아요목록에도 삭제
    int deleteLike(LikeDto likedto) throws DataAccessException;

    //프로젝트 삭제시 좋아요목록에도 삭제
    int deleteAllLike(LikeDto likedto) throws DataAccessException;

    List<LikeDto> selectPage(Map map) throws DataAccessException;

}

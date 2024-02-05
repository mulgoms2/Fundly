package com.fundly.user.model;


import com.persistence.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface LikeDao {
    int checkLike(UserDto userdto) throws Exception;

    void insertLike(Map map) throws Exception;

    void cancelLike() throws Exception;
    void reLike() throws Exception;
    void deleteLike() throws Exception;

}

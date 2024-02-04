package com.fundly.user.model;

import com.persistence.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserJoinDao {

    int count() throws Exception;
    int emailCheck(String email) throws Exception;

//    int useridCheck() throws Exception;

    //    @Override
    int insert(UserDto dto) throws Exception;
}
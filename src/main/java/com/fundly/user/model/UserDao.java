package com.fundly.user.model;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    //    @Override
//    int emailCheck(String email) throws Exception;

//    int useridCheck() throws Exception;

    int count() throws Exception;

    //    @Override
//    int insert(UserDto dto) throws Exception;
}

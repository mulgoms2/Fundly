package com.fundly.user.model;

import com.fundly.user.dto.UserJoinDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserJoinDao {
    int emailCheck(UserJoinDto userJoinDto) throws Exception;
    int insert(UserJoinDto userJoinDto) throws Exception;
}

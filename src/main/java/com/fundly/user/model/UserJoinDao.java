package com.fundly.user.model;

import com.fundly.user.dto.UserJoinDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserJoinDao {
    Integer emailCheck(UserJoinDto userJoinDto) throws Exception;
    Integer insert(UserJoinDto userJoinDto) throws Exception;
}

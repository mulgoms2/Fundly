package com.fundly.user.model;

import com.fundly.user.dto.OauthDto;
import com.fundly.user.dto.UserProfileDto;
import com.persistence.dto.FileDto;
import com.persistence.dto.UserDto;
import com.persistence.dto.UserHistDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserProfileDao {
    UserProfileDto userProfileinfo(UserDto userDto) throws Exception ;
    int update(UserProfileDto userProfileDto) throws Exception ;
    String getUserProfileImg(FileDto fileDto) throws Exception ;
    int saveFile(FileDto fileDto) throws Exception;
    int updateFile(FileDto fileDto) throws Exception;
}

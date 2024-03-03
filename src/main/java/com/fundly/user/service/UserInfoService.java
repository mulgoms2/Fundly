package com.fundly.user.service;

import com.persistence.dto.UserDto;

import java.util.List;

public interface UserInfoService {
    UserDto userInfo(UserDto userdto);
    String userJoindate(UserDto userDto);
}

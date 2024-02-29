package com.fundly.user.service;

import com.persistence.dto.UserDto;

public interface UserInfoService {
    UserDto userInfo(UserDto userdto);

    String userJoindate(UserDto userDto);
}

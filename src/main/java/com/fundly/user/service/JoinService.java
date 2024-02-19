package com.fundly.user.service;

import com.persistence.dto.UserDto;

public interface JoinService {
    int userJoin(UserDto userDto) throws Exception;
    int emailCheck(UserDto userdto) throws Exception;
}

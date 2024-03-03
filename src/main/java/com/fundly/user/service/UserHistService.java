package com.fundly.user.service;

import com.fundly.user.dto.UserProfileDto;
import com.persistence.dto.UserDto;

public interface UserHistService {
    int userHistinsert(UserProfileDto userProfileDto);
}

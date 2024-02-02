package com.fundly.user.model;

import com.persistence.dto.UserDto;

public interface userDao {
    //    @Override
    int emailCheck(String email) throws Exception;

    int useridCheck() throws Exception;

    //    @Override
    int insert(UserDto dto) throws Exception;
}

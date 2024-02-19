package com.fundly.user.service;

import com.persistence.dto.UserDto;

import javax.servlet.http.HttpSession;

public interface LoginService {
    UserDto Login(UserDto userdto, HttpSession session) throws Exception;
}

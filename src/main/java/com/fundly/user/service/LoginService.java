package com.fundly.user.service;

import com.fundly.user.dto.UserLoginDto;
import com.persistence.dto.UserDto;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface LoginService {
    UserLoginDto Login(UserLoginDto userLoginDto, HttpSession session, HttpServletResponse response) ;
}

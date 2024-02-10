package com.fundly.user.service;

import com.persistence.dto.UserDto;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface LoginService {

    int count() throws Exception;

//    int userCheck(String user_email, String user_pwd) throws Exception;

    UserDto Login(String user_email, String user_pwd, HttpSession session) throws Exception;

}

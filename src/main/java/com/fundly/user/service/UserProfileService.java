package com.fundly.user.service;

import com.fundly.user.dto.UserProfileDto;
import com.persistence.dto.FileDto;
import com.persistence.dto.ItemDto;
import com.persistence.dto.UserDto;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserProfileService {
    UserProfileDto userProfileInfo(UserDto userDto);
    UserProfileDto userUpdate(UserDto userDto);
    void saveImg(FileDto fileDto, String user_email, HttpServletResponse response);
    String getUserProfileImg(FileDto fileDto) throws Exception;
    String getlastPwdDate(String user_email) throws Exception;
}

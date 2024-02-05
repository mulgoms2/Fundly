package com.fundly.admin.service;

import com.fundly.admin.model.AdminDto;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    //유저정보 넣기
    //유저정보 수정
    public int insertUser(AdminDto dto) throws Exception;
    public int updateUser(AdminDto dto) throws Exception;
}

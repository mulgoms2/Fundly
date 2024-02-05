package com.fundly.admin.service;

import com.fundly.admin.model.AdminDao;
import com.fundly.admin.model.AdminDto;
import com.fundly.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;

    @Override
    public int insertUser(AdminDto dto) throws Exception {
        return adminDao.insert(dto);
    }

    @Override
    public int updateUser(AdminDto dto) throws Exception {
        return adminDao.update(dto);
    }
}

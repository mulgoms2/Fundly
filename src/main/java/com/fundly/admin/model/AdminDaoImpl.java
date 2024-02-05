//package com.fundly.admin.model;
//
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class AdminDaoImpl implements AdminDao{
//    @Autowired
//    private SqlSession session;
//
//    private static String namespace = "com.fundly.admin.model.adminMapper.";
//
//    @Override
//    public int insert(AdminDto dto) throws Exception {
//        return session.insert(namespace+"insert",dto);
//    }
//
//    @Override
//    public int update(AdminDto dto) throws Exception {
//        return session.update(namespace+"update",dto);
//    }
//}

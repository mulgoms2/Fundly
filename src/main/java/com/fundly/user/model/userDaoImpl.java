package com.fundly.user.model;

import com.persistence.dto.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class userDaoImpl implements userDao {

    @Autowired
    private SqlSession session;
    private static String namespace = "com.fundly.user.model.UserDao.";

//    @Override
    @Override
    public int emailCheck(String email) throws Exception {
        return session.selectOne(namespace+"emailcheck");
    }

    @Override
    public int useridCheck() throws Exception {
        return session.selectOne(namespace+"useridCheck");
    }
//    @Override
    @Override
    public int insert(UserDto dto) throws Exception{
        return session.insert(namespace + "insert", dto);
    }


}

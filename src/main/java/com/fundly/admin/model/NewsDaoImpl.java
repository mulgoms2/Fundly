package com.fundly.admin.model;


import com.persistence.dto.NewsDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class NewsDaoImpl implements NewsDao {
    @Autowired
    private SqlSession session;

    private static String namespace = "com.fundly.admin.model.newsMapper.";

    @Override
    public int insert(NewsDto dto) throws Exception {
        return session.insert(namespace+"insert",dto);
    }

    @Override
    public NewsDto select(Integer news_seq) throws Exception {
        return session.selectOne(namespace+"select",news_seq);
    }

    @Override
    public List<NewsDto> selectAll() throws Exception {
        return session.selectList(namespace+"selectAll");
    }

    @Override
    public int delete(Map map) throws Exception {
        return 0;
    }

    @Override
    public int delete(Integer news_seq, String reg_id) throws Exception {
        Map map = new HashMap();
        map.put("news_seq",news_seq);
        map.put("reg_id",reg_id);
        return session.delete(namespace+"delete",map);
    }

    @Override
    public int deleteAll() {
        return session.delete(namespace+"deleteAll");
    }

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace+"count");
    }

    @Override
    public int update(NewsDto dto) throws Exception {
        return session.update(namespace+"update",dto);
    }

    @Override
    public int increaseView(Integer news_seq) throws Exception {
        return session.update(namespace+"increaseView",news_seq);
    }
}

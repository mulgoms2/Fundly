package com.fundly.admin.service;

import com.fundly.admin.model.NewsDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsServiceImpl implements NewsService{
    @Override
    public int insertNews(NewsDto dto) throws Exception {
        return 0;
    }

    @Override
    public NewsDto selectNews(Integer news_seq) throws Exception {
        return null;
    }

    @Override
    public List<NewsDto> selectAllNews() throws Exception {
        return null;
    }

    @Override
    public int deleteNews(Integer news_seq, String reg_id) throws Exception {
        return 0;
    }

    @Override
    public int deleteAll() {
        return 0;
    }

    @Override
    public int count() throws Exception {
        return 0;
    }

    @Override
    public int updateNews(NewsDto dto) throws Exception {
        return 0;
    }

    @Override
    public int increaseView() {
        return 0;
    }
}

package com.fundly.admin.service;

import com.fundly.admin.model.NewsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NewsService {
    public int insertNews(NewsDto dto) throws Exception;

    public NewsDto selectNews(Integer news_seq) throws Exception;

    public List<NewsDto> selectAllNews() throws Exception;

    public int deleteNews(Integer news_seq,String reg_id) throws Exception;

    public int deleteAll();

    public int count() throws Exception;

    public int updateNews(NewsDto dto) throws Exception;

    public int increaseView();

}

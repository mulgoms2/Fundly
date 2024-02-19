package com.fundly.admin.service;

import com.persistence.dto.NewsDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface NewsService {
    public int insertNews(NewsDto dto) throws Exception;

    public NewsDto selectNews(Integer news_seq) throws Exception;

    public List<NewsDto> selectAllNews() throws Exception;

    public int deleteNews(Integer news_seq,String reg_id) throws Exception;

    public int deleteAll();

    public int count() throws Exception;

    public int updateNews(NewsDto dto) throws Exception;

    public List<NewsDto> selectPage(Integer page,Integer pageSize) throws Exception;

    public List<NewsDto> searchNews(String news_title) throws Exception;

}

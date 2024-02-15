//package com.fundly.admin.model;
//
//import com.fundly.admin.service.NewsService;
//import com.persistence.dto.NewsDto;
//import config.RootContext;
//import config.ServletContext;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@Slf4j
//@SpringJUnitWebConfig(classes = {RootContext.class, ServletContext.class})
//class NewsDaoImplTest {
//
//    @Autowired
//    NewsDao newsDao;
//    @Autowired
//    NewsService newsService;
//
//    @Test
//    void selectAll() throws Exception {
//        assertTrue(newsDao != null);
//        List<NewsDto> list = newsDao.selectAll();
//       log.debug("list = {}",list);
////        newsDao.selectAll();
//    }
//}
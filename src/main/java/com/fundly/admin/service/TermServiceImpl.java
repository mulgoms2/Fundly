package com.fundly.admin.service;

import com.fundly.admin.model.TermDao;
import com.persistence.dto.TermDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TermServiceImpl implements TermService{
    @Autowired
    TermDao termDao;
    @Override
    public int insertTerm(TermDto dto) throws Exception {
        return termDao.insert(dto);
    }

    @Override
    public TermDto selectTerm(Integer term_seq) throws Exception {
        return termDao.select(term_seq);
    }

    @Override
    public List<TermDto> selectAllTerm() throws Exception {
        return termDao.selectAll();
    }

    @Override
    public int deleteTerm(Integer term_seq, String reg_id) throws Exception {
        Map map = new HashMap();
        map.put("term_seq",term_seq);
        map.put("reg_id",reg_id);
        return termDao.delete(map);
    }

    @Override
    public int deleteAll() {
        return termDao.deleteAll();
    }

    @Override
    public int count() throws Exception {
        return termDao.count();
    }

    @Override
    public int updateTerm(TermDto dto) throws Exception {
        return termDao.update(dto);
    }

    @Override
    public int increaseView(Integer term_seq) throws Exception {
        return termDao.increaseView(term_seq);
    }

    @Override
    public List<TermDto> selectAllTitle() throws Exception {
        return termDao.selectAllTitle();
    }

    @Override
    public TermDto selectTitle(String term_title) throws Exception {
        return termDao.selectTitle(term_title);
    }

    @Override
    public TermDto termPrevNext(Integer term_seq, String term_title) throws Exception {
        Map map = new HashMap();
        map.put("term_seq",term_seq);
        map.put("term_title",term_title);
        return termDao.termPrevNext(map);
    }

    @Override
    public int termprevUpdate(Integer term_seq) throws Exception {
        return termDao.prevUpdate(term_seq);
    }

    @Override
    public List<TermDto> selectPage(Integer page, Integer pageSize) throws Exception {
        Map map = new HashMap();
        map.put("offset",(page-1)*10);
        map.put("pageSize",pageSize);
        return termDao.selectPage(map);
    }
}

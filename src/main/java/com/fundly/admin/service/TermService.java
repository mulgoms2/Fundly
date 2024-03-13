package com.fundly.admin.service;

import com.persistence.dto.TermDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TermService {
    public int insertTerm(TermDto dto) throws Exception;
    public TermDto selectTerm(Integer term_seq) throws Exception;
    public List<TermDto> selectAllTerm() throws Exception;
    public int deleteTerm(Integer term_seq,String reg_id) throws Exception;

    public int deleteAll();

    public int count() throws Exception;
    public int updateTerm(TermDto dto) throws Exception;

    public int increaseView(Integer term_seq) throws Exception;

    public List<TermDto> selectAllTitle() throws Exception;

    public TermDto selectTitle(String term_title) throws Exception;

    public TermDto termPrevNext(Integer term_seq,String term_title) throws Exception;

    public int termprevUpdate(Integer term_seq) throws Exception;

    public List<TermDto> selectPage(Integer page,Integer pageSize) throws Exception;

}

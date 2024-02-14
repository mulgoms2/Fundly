package com.fundly.admin.service;

import com.persistence.dto.InformDto;
import com.persistence.dto.NewsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InformService {
    public int insertInform(InformDto dto) throws Exception;

    public InformDto selectInform(Integer inform_seq) throws Exception;

    public List<InformDto> selectAllInform() throws Exception;

    public int deleteInform(Integer inform_seq,String reg_id) throws Exception;

    public int deleteAll();

    public int count() throws Exception;

    public int updateInform(InformDto dto) throws Exception;
}

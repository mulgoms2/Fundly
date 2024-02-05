package com.fundly.admin.service;

import com.persistence.dto.CtgGuideDto;
import com.persistence.dto.NewsDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CtgGuideService {
    public int insertCtg(CtgGuideDto dto) throws Exception;

    public CtgGuideDto selectCtg(Integer ctg_guide_seq) throws Exception;

    public List<CtgGuideDto> selectAllCtg() throws Exception;

    public int deleteCtg(Integer ctg_guide_seq,String reg_id) throws Exception;

    public int deleteAll();

    public int count() throws Exception;

    public int updateCtg(CtgGuideDto dto) throws Exception;

    public int increaseView(Integer ctg_guide_seq) throws Exception;
}

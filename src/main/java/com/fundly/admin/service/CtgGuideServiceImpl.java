package com.fundly.admin.service;

import com.fundly.admin.model.CtgGuideDao;
import com.persistence.dto.CtgGuideDto;
import com.persistence.dto.NewsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CtgGuideServiceImpl implements CtgGuideService{
    @Autowired
    CtgGuideDao ctgGuideDao;
    @Override
    public int insertCtg(CtgGuideDto dto) throws Exception {
        return ctgGuideDao.insert(dto);
    }

    @Override
    public CtgGuideDto selectCtg(Integer ctg_guide_seq) throws Exception {
        return ctgGuideDao.select(ctg_guide_seq);
    }

    @Override
    public List<CtgGuideDto> selectAllCtg() throws Exception {
        return ctgGuideDao.selectAll();
    }

    @Override
    public int deleteCtg(Integer ctg_guide_seq, String reg_id) throws Exception {
        Map map = new HashMap();
        map.put("ctg_guide_seq",ctg_guide_seq);
        map.put("reg_id",reg_id);
        return ctgGuideDao.delete(map);
    }

    @Override
    public int deleteAll() {
        return ctgGuideDao.deleteAll();
    }

    @Override
    public int count() throws Exception {
        return ctgGuideDao.count();
    }

    @Override
    public int updateCtg(CtgGuideDto dto) throws Exception {
        return ctgGuideDao.update(dto);
    }

    @Override
    public int increaseView(Integer ctg_guide_seq) throws Exception {
        return ctgGuideDao.increaseView(ctg_guide_seq);
    }
}

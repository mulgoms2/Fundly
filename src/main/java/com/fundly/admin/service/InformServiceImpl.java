package com.fundly.admin.service;

import com.fundly.admin.model.InformDao;
import com.persistence.dto.InformDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InformServiceImpl implements InformService{
    @Autowired
    InformDao informDao;
    @Override
    public int insertInform(InformDto dto) throws Exception {
        return informDao.insert(dto);
    }

    @Override
    public InformDto selectInform(Integer inform_seq) throws Exception {
        return informDao.select(inform_seq);
    }

    @Override
    public List<InformDto> selectAllInform() throws Exception {
        return informDao.selectAll();
    }

    @Override
    public int deleteInform(Integer inform_seq, String reg_id) throws Exception {
        Map map = new HashMap();
        map.put("inform_seq",inform_seq);
        map.put("reg_id",reg_id);
        return informDao.delete(map);
    }

    @Override
    public int deleteAll() {
        return informDao.deleteAll();
    }

    @Override
    public int count() throws Exception {
        return informDao.count();
    }

    @Override
    public int updateInform(InformDto dto) throws Exception {
        return informDao.update(dto);
    }
}

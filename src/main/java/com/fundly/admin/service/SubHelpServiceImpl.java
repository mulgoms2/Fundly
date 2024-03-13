package com.fundly.admin.service;

import com.fundly.admin.model.SubHelpDao;
import com.persistence.dto.NewsDto;
import com.persistence.dto.SubHelpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SubHelpServiceImpl implements SubHelpService{
    @Autowired
    SubHelpDao subHelpDao;
    @Override
    public int insertSubHelp(SubHelpDto dto) throws Exception {
        return subHelpDao.insert(dto);
    }

    @Override
    public SubHelpDto selectSubHelp(Integer sub_help_seq) throws Exception {
        subHelpDao.increaseView(sub_help_seq);
        return subHelpDao.select(sub_help_seq);
    }

    @Override
    public List<SubHelpDto> selectAllSubHelp(Integer sub_help_sort) throws Exception {
        return subHelpDao.selectAll(sub_help_sort);
    }

    @Override
    public int deleteSubHelp(Integer sub_help_seq, String reg_id) throws Exception {
        Map map = new HashMap();
        map.put("sub_help_seq",sub_help_seq);
        map.put("reg_id",reg_id);
        return subHelpDao.delete(map);
    }

    @Override
    public int deleteAll() {
        return subHelpDao.deleteAll();
    }

    @Override
    public int count(Integer sub_help_sort) throws Exception {
        return subHelpDao.count(sub_help_sort);
    }

    @Override
    public int countAll() throws Exception{
        return subHelpDao.countAll();
    }

    @Override
    public int updateSubHelp(SubHelpDto dto) throws Exception {
        return subHelpDao.update(dto);
    }

    @Override
    public int countSub() throws Exception {
        return subHelpDao.countSub();
    }

    @Override
    public List<SubHelpDto> selectAllAll() throws Exception {
        return subHelpDao.selectAllAll();
    }

    @Override
    public List<SubHelpDto> selectPage(Integer page, Integer pageSize) throws Exception {
        Map map = new HashMap();
        map.put("offset",(page-1)*10);
        map.put("pageSize",pageSize);

        return subHelpDao.selectPage(map);
    }

    @Override
    public List<SubHelpDto> searchHelp(String sub_help_title) throws Exception {
        sub_help_title = '%'+sub_help_title+'%';
        return  subHelpDao.searchHelp(sub_help_title);
    }
}

package com.fundly.admin.service;


import com.persistence.dto.SubHelpDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubHelpService {
    public int insertSubHelp(SubHelpDto dto) throws Exception;

    public SubHelpDto selectSubHelp(Integer sub_help_seq) throws Exception;

    public List<SubHelpDto> selectAllSubHelp(Integer sub_help_sort) throws Exception;

    public int deleteSubHelp(Integer sub_help_seq,String reg_id) throws Exception;

    public int deleteAll();

    public int count(Integer sub_help_sort) throws Exception;

    public int countSub() throws Exception;

    public int updateSubHelp(SubHelpDto dto) throws Exception;

}

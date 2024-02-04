package com.fundly.project.service;

import com.fundly.project.model.ItemMapper;
import com.persistence.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    ItemMapper itemMapper;

    @Override
    public int getItemCount(String pj_id) throws Exception {
        return itemMapper.count(pj_id);
    }

    @Override
    public List<ItemDto> getItemList(String pj_id) throws Exception {
        return itemMapper.selectAllFromPj(pj_id);
    }

    @Override
    public List<ItemDto> getItemList() throws Exception {
        return itemMapper.selectAll();
    }

    @Override
    public int registerItem(ItemDto itemDto) throws Exception {
        return itemMapper.insert(itemDto);
    }

}

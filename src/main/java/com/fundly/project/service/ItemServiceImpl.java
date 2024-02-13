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
    public ItemDto getItem(String item_id) throws Exception {
        return itemMapper.select(item_id);
    }


    @Override
    public int registerItem(ItemDto itemDto) throws Exception {
        return itemMapper.insert(itemDto);
    }

    @Override
    public int removeAll() throws Exception {
        return itemMapper.deleteAll();
    }

    //    @Override
//    public int remove(String item_id, String dba_reg_id) throws Exception {
//        Map map = new HashMap();
//        map.put("item_id",item_id);
//        map.put("dba_reg_id",dba_reg_id);
//        return itemMapper.delete(map);
//    }
    @Override
    public int remove(String item_id, String dba_reg_id) throws Exception {
        return itemMapper.delete(item_id, dba_reg_id);
    }
}

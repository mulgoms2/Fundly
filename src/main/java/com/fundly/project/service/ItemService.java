package com.fundly.project.service;


import com.persistence.dto.ItemDto;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ItemService {
    int getItemCount(String pj_id) throws Exception;
    List<ItemDto> getItemList(String pj_id) throws Exception;
    List<ItemDto> getItemList() throws Exception;
    ItemDto getItem(Integer item_id) throws Exception;
    int registerItem(ItemDto itemDto) throws Exception;
    int modifyItem(ItemDto itemDto) throws Exception;
    int removeAll() throws Exception;
    int remove(Integer item_id) throws Exception;
}

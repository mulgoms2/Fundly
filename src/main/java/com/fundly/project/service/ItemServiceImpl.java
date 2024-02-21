package com.fundly.project.service;

import com.fundly.project.model.GiftItemDetailMapper;
import com.fundly.project.model.ItemMapper;
import com.persistence.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{
    ItemMapper itemMapper;
    GiftItemDetailMapper giftItemDetailMapper;
    GiftService giftService;

    @Autowired
    ItemServiceImpl(ItemMapper itemMapper, GiftItemDetailMapper giftItemDetailMapper,  GiftService giftService){
        this.itemMapper = itemMapper;
        this.giftItemDetailMapper = giftItemDetailMapper;
        this.giftService = giftService;
    }

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
    public ItemDto getItem(Integer item_id) throws Exception {
        return itemMapper.select(item_id);
    }


    @Override
    public int registerItem(ItemDto itemDto) throws Exception {
        return itemMapper.insert(itemDto);
    }

    @Override
    public int modifyItem(ItemDto itemDto) throws Exception {
        return itemMapper.update(itemDto);
    }

    @Override
    public int removeAll() throws Exception {
        return itemMapper.deleteAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int remove(Integer item_id) throws Exception {
        //텀블벅은, 아이템을 삭제하면 해당 아이템을 포함하는 모든 선물도 삭제되도록 구현되어 있다.
        //해당 아이템을 포함하는 선물을 조회한다
        //아이템을 포함하는 선물이 없다면, 아이템만 삭제하고 끝
        //아이템을 포함하는 선물들이 있다면, 그 선물들을 모두 삭제해야함.
        List<String> giftList = giftItemDetailMapper.selectGift(item_id);
        if(giftList!=null){ //giftList가 비어있지 않으면(즉, 아이템을 포함하는 선물들이 조회되면)
            for(String gift_id : giftList){
                //giftMapper.delete(gift_id); //모든 선물들을 삭제
                giftService.removeGift(gift_id); //사실 이 메서드도 Tx걸려있음..
            }
        }
//        throw new Exception("Tx테스트");
        return itemMapper.delete(item_id); //아이템도 아이템 테이블에서 삭제
    }
}

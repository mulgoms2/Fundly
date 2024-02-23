package com.fundly.project.service;

import com.fundly.project.controller.GiftForm;
import com.fundly.project.model.GiftItemDetailMapper;
import com.fundly.project.model.GiftMapper;
import com.fundly.project.model.ItemMapper;
import com.persistence.dto.GiftDto;
import com.persistence.dto.GiftItemDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GiftServiceImpl implements GiftService {
    GiftMapper giftMapper;
    GiftItemDetailMapper giftItemDetailMapper;
    ItemMapper itemMapper;

    @Autowired
    GiftServiceImpl (GiftMapper giftMapper, GiftItemDetailMapper giftItemDetailMapper, ItemMapper itemMapper){
        this.giftMapper = giftMapper;
        this.giftItemDetailMapper = giftItemDetailMapper;
        this.itemMapper = itemMapper;
    }

    @Override
    public int getCount(String pj_id) throws Exception{
        return giftMapper.count(pj_id);
    }// 특정 프로젝트의 모든 선물의 갯수 구하기

    @Override
    public int registerGift(GiftForm giftForm) throws Exception {
        GiftDto giftDto = toGiftDto(giftForm);
        List<GiftItemDetailDto> itemList = toGiftItemDetailDto(giftForm);

        return registerGift(giftDto, itemList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int registerGift(GiftDto giftDto, List<GiftItemDetailDto> itemList) throws Exception{
        int rowCnt = giftMapper.insert(giftDto);

        for(int i=0; i<itemList.size(); i++){
            giftItemDetailMapper.insert(itemList.get(i));
        }
//        throw new Exception("for Tx test");
        return rowCnt;
    }// 선물 등록하기 (선물 테이블에 insert + 선물 아이템 상세 테이블에도 insert)



    @Override
    public GiftDto getGift(String gift_id) throws Exception {
        return giftMapper.select(gift_id);
    }// 특정 선물 하나 가져오기

//    @Override
//    public List<GiftDto> getAllGiftList(String pj_id) throws Exception {
//        return giftMapper.selectAllByPj(pj_id);
//    }// 특정 프로젝트의 모든 선물 리스트 가져오기

    @Override //반환타입을 List<GiftDto>에서 List<GiftForm>으로 바꿨다. 테스트 필요.
    public List<GiftForm> getAllGiftList(String pj_id) throws Exception {
        List<GiftForm> list = new ArrayList<>();
        List<GiftDto> giftDtos = giftMapper.selectAllByPj(pj_id);
        for(GiftDto giftDto : giftDtos){
            List<GiftItemDetailDto> itemDetailDtos = giftItemDetailMapper.selectItemDetail(giftDto.getGift_id());
            GiftForm giftForm = toGiftForm(giftDto,itemDetailDtos);
            list.add(giftForm);
        }
        return list;
    }// 특정 프로젝트의 모든 선물 리스트 가져오기

    @Override
    public List<GiftDto> getGiftByStatus(GiftDto giftDto) throws Exception {
        return giftMapper.selectByStatus(giftDto);
    } //특정 프로젝트의, 판매 상태에 따른 선물 리스트 가져오기


    @Override
    @Transactional
    public int modifyGiftContent(GiftDto giftDto, List<GiftItemDetailDto> afterList) throws Exception {
        //1. 선물 테이블의 정보 변경
        //2. 아이템 상세 테이블의 변경 (insert, update, delete)
        // -> Tx로 묶인다.

        //선물의 수정이 발생하면, 해당 선물의 등록된 아이템 리스트를 전부 살펴보아야 한다.
        // 새로 추가된 아이템이 있는지(insert), 기존 아이템의 수량 변경이 발생했는지(update)
        // 기존 아이템이 삭제되었는지(delete), 또는 아이템 리스트에는 변화 없이 선물 테이블에만 변경이 있을 수도 있음.
        List<GiftItemDetailDto> beforeList = giftItemDetailMapper.selectItemDetail(giftDto.getGift_id());

        //선물의 수정이 새로운 아이템을 포함(insert)하거나 기존 아이템의 수량을 변경(update)하는 경우
        for(GiftItemDetailDto dtoA : afterList){
            int index = beforeList.indexOf(dtoA);
            if(index==-1){ //기존 리스트에 없는 아이템이면
                giftItemDetailMapper.insert(dtoA); //새로운 아이템데이터를 아이템리스트에 insert
            } else { //기존 리스트에 있는 아이템이면
                GiftItemDetailDto dtoB = beforeList.get(index);
                if(dtoB.getItem_qty()!=dtoA.getItem_qty()){ //기존 수량과 입력 수량이 다르면
                    giftItemDetailMapper.update(dtoA); //수량 업데이트
                }
            }
        }
        for(GiftItemDetailDto dtoB : beforeList){
            if(!afterList.contains(dtoB)){ //새로운 리스트에 기존 아이템이 없으면
                giftItemDetailMapper.delete(dtoB.getGift_item_id()); //기존 아이템 삭제
            }
        }
        //gift테이블의 정보 수정 (이것도 변경사항이 무엇인지 체크를 해야하는걸까.. 아니면 그냥 업데이트 해도 되나)
//        throw new Exception("Tx test");
        return giftMapper.updateContentBefore(giftDto);
    }

    @Override
    public int modifyGiftStatus(GiftDto giftDto) throws Exception {
        return giftMapper.updateStatus(giftDto);
    }

    @Override
    public int modifyGiftQty(GiftDto giftDto) throws Exception {
        return giftMapper.updateQty(giftDto);
    }

    @Override
    @Transactional
    public int removeGift(String gift_id) throws Exception {
        giftItemDetailMapper.deleteAllByGift(gift_id);
//        throw new RuntimeException("Tx테스트"); //테스트용
        return giftMapper.delete(gift_id);
    }



    // toDto 메서드
    @Override
    public GiftDto toGiftDto(GiftForm giftForm) {
        GiftDto giftDto = GiftDto.builder()
                .gift_id(giftForm.getGift_id())
                .gift_name(giftForm.getGift_name())
                .pj_id(giftForm.getPj_id())
                .gift_qty_lim_yn(giftForm.getGift_qty_lim_yn())
                .gift_total_qty(giftForm.getGift_total_qty())
                .gift_max_qty_per_person(giftForm.getGift_max_qty_per_person())
                .gift_ship_due_date(giftForm.getGift_ship_due_date())
                .gift_money(giftForm.getGift_money())
                .dba_reg_id(giftForm.getDba_reg_id()) //Controller에서 세션으로 부터 얻은 user_id
                .build();

        return giftDto;
    }

    @Override
    public List<GiftItemDetailDto> toGiftItemDetailDto(GiftForm giftForm) {
        List<GiftItemDetailDto> itemList = new ArrayList<>();
        GiftItemDetailDto giftItemDetailDto;
        int num = giftForm.getItem_qty().length;
        for(int i=0; i<num; i++){
            giftItemDetailDto = GiftItemDetailDto.builder()
                    .gift_id(giftForm.getGift_id())
                    .item_id(giftForm.getItem_id()[i])
                    .item_qty(giftForm.getItem_qty()[i])
                    .build();
            itemList.add(giftItemDetailDto);
        }

        return itemList;
    }

    //toGiftForm 메서드 (view로 전달할 객체)
    public GiftForm toGiftForm(GiftDto giftDto, List<GiftItemDetailDto> list){
        GiftForm giftForm = GiftForm.builder()
                .gift_id(giftDto.getGift_id())
                .gift_name(giftDto.getGift_name())
                .pj_id(giftDto.getPj_id())
                .gift_qty_lim_yn(giftDto.getGift_qty_lim_yn())
                .gift_total_qty(giftDto.getGift_total_qty())
                .gift_curr_qty(giftDto.getGift_curr_qty())
                .gift_sold_qty(giftDto.getGift_sold_qty())
                .gift_max_qty_per_person(giftDto.getGift_max_qty_per_person())
                .gift_ship_due_date(giftDto.getGift_ship_due_date())
                .gift_money(giftDto.getGift_money())
                .dba_reg_id(giftDto.getDba_reg_id())
                .item_id(list.stream().map(GiftItemDetailDto::getItem_id).toArray(Integer[]::new))
                .item_name(list.stream().map(GiftItemDetailDto::getItem_name).toArray(String[]::new))
                .item_qty(list.stream().map(GiftItemDetailDto::getItem_qty).toArray(Integer[]::new))
                .build();

        return giftForm;
    }
}

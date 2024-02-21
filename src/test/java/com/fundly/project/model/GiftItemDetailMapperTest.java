package com.fundly.project.model;

import com.persistence.dto.GiftItemDetailDto;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringJUnitWebConfig(classes = {RootContext.class, ServletContext.class})
class GiftItemDetailMapperTest {
    @Autowired
    GiftItemDetailMapper mapper;

    GiftItemDetailDto dto;
    @Test
    @SneakyThrows
    @DisplayName("해당 아이템이 몇개의 선물에 포함되어있는지")
    void countGiftByItem() {
        int giftCnt = mapper.countGiftByItem(1);
        assertEquals(giftCnt,2);
        dto = GiftItemDetailDto.builder()
                .gift_id("3")
                .item_id(1)
                .item_qty(2)
                .build();
        int rowCnt = mapper.insert(dto);
        assertEquals(rowCnt,1);
        giftCnt = mapper.countGiftByItem(1);
        assertEquals(giftCnt,3);

    }

    @Test
    @SneakyThrows
    @DisplayName("특정 선물에 몇개의 아이템이 있는지")
    void countItemByGift() {
        int itmCnt = mapper.countItemByGift("1");
        assertEquals(itmCnt,5);
        dto = GiftItemDetailDto.builder()
                .gift_id("1")
                .item_id(7)
                .item_qty(3)
                .build();
        int rowCnt = mapper.insert(dto);
        assertEquals(rowCnt,1);
        itmCnt = mapper.countItemByGift("1");
        assertEquals(itmCnt,6);
    }

    @Test
    @SneakyThrows
    @DisplayName("특정 선물의 모든 아이템 리스트")
    void selectItemDetail() {
        List<GiftItemDetailDto> list  = mapper.selectItemDetail("1");
        assertEquals(list.size(),5);
        log.error("************list={}",list);
        dto = GiftItemDetailDto.builder()
                .gift_id("1")
                .item_id(7)
                .item_qty(3)
                .build();
        int rowCnt = mapper.insert(dto);
        assertEquals(rowCnt,1);
        list = mapper.selectItemDetail("1");
        log.error("***********list={}",list);
        assertEquals(list.size(),6);

    }

    @Test
    @SneakyThrows
    @DisplayName("특정 아이템을 포함한 선물 리스트")
    void selectGift() {
        List<String> list = mapper.selectGift(1);
        log.error("list={}",list);
        assertEquals(list.size(),2);
        dto = GiftItemDetailDto.builder()
                .gift_id("3")
                .item_id(1)
                .item_qty(2)
                .build();
        int rowCnt = mapper.insert(dto);
        assertEquals(rowCnt,1);
        list = mapper.selectGift(1);
        log.error("********list={}",list);
        assertEquals(list.size(),3);

    }

    @Test
    @SneakyThrows
    @DisplayName("특정 선물에 해당하는 아이템 insert")
    void insert() {
        dto = GiftItemDetailDto.builder()
                .gift_id("1")
                .item_id(6)
                .item_qty(3)
                .build();
        int rowCnt = mapper.insert(dto);
        assertEquals(rowCnt,1);

    }

    @Test
    @SneakyThrows
    @DisplayName("선물 수정시 아이템 update")
    void update() {
        List<GiftItemDetailDto> list = mapper.selectItemDetail("1");
        dto = list.get(0);
        dto.setItem_qty(5);
        int rowCnt = mapper.update(dto);
        assertEquals(rowCnt,1);


    }

    @Test
    @SneakyThrows
    @DisplayName("특정 선물의 특정 아이템 상세 지우기")
    void delete() {
        List<GiftItemDetailDto> list = mapper.selectItemDetail("1");
        assertEquals(list.size(),5);
        dto = list.get(0);
        int rowCnt = mapper.delete(dto.getGift_item_id());
        assertEquals(rowCnt,1);
        list = mapper.selectItemDetail("1");
        assertEquals(list.size(),4);
    }

    @Test
    @SneakyThrows
    @DisplayName("해당 선물에 속한 아이템 상세 모두 삭제")
    void deleteAllByGift() {
        List<GiftItemDetailDto> list = mapper.selectItemDetail("1");
        assertEquals(list.size(),5);
        int rowCnt = mapper.deleteAllByGift("1");
        assertEquals(rowCnt,5);
        list = mapper.selectItemDetail("1");
        assertEquals(list.size(),0);
    }


    @BeforeEach
    @Test
    @SneakyThrows
    @DisplayName("테스트 초기화 및 해당 선물의 아이템 insert")
    void deleteAll() {
        int rowCnt = 0;
        mapper.deleteAll(); // truncate table

        for(int i=0; i<10; i++){
            dto = GiftItemDetailDto.builder()
                    .gift_id(i/5<1?"1":"2")
                    .item_id(i%5+1)
                    .item_qty(getRandomQty())
                    .build();
            //log.error("dto={}",dto);
            rowCnt += mapper.insert(dto);
        }
        assertEquals(rowCnt,10);
        //log.error("rowCnt={}",rowCnt);
    }

    private int getRandomQty(){
        return (int)(Math.random()*5)+1;
    }
}
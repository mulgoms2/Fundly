package com.fundly.project.service;

import com.fundly.project.model.GiftItemDetailMapper;
import com.fundly.project.model.GiftMapper;
import com.fundly.project.model.ItemMapper;
import com.persistence.dto.GiftDto;
import com.persistence.dto.GiftItemDetailDto;
import com.persistence.dto.ItemDto;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringJUnitWebConfig(classes = {RootContext.class, ServletContext.class})
class ItemServiceImplTest2 {
    @Autowired
    ItemMapper itemMapper;
    @Autowired
    GiftMapper giftMapper;
    @Autowired
    ItemService itemService;
    @Autowired
    GiftItemDetailMapper mapper;

    ItemDto itemDto;
    GiftDto giftDto;
    GiftItemDetailDto dto;

    @BeforeEach
    @SneakyThrows
    void setUp(){
        itemMapper.deleteAll();
        giftMapper.deleteAll();
        mapper.deleteAll();

        itemDto = ItemDto.builder()
                .pj_id("pj1")
                .item_name("테스트아이템")
                .item_option_type("객관식")
                .item_option("옵션1, 옵션2, 옵션3")
                .build();
        itemService.registerItem(itemDto);

        giftDto = GiftDto.builder()
                .gift_name("테스트선물1")
                .pj_id("pj1")
                .gift_qty_lim_yn("n")
                .gift_total_qty(null)
                .gift_max_qty_per_person(null)
                .gift_ship_due_date(LocalDateTime.now())
                .gift_money(30000)
                .gift_curr_qty(null)
                .build();
        giftMapper.insert(giftDto);

        dto = GiftItemDetailDto.builder()
                .item_id(1)
                .gift_id("1")
                .item_qty(3)
                .build();
        mapper.insert(dto);

        giftDto = GiftDto.builder()
                .gift_name("테스트선물2")
                .pj_id("pj1")
                .gift_qty_lim_yn("n")
                .gift_total_qty(null)
                .gift_max_qty_per_person(null)
                .gift_ship_due_date(LocalDateTime.now())
//                .gift_ship_need_yn("y")
                .gift_money(30000)
                .gift_curr_qty(null)
                .build();
        giftMapper.insert(giftDto);
        dto = GiftItemDetailDto.builder()
                .item_id(1)
                .gift_id("2")
                .item_qty(2)
                .build();
        mapper.insert(dto);


        giftDto = GiftDto.builder()
                .gift_name("테스트선물3")
                .pj_id("pj1")
                .gift_qty_lim_yn("n")
                .gift_total_qty(null)
                .gift_max_qty_per_person(null)
                .gift_ship_due_date(LocalDateTime.now())
//                .gift_ship_need_yn("y")
                .gift_money(30000)
                .gift_curr_qty(null)
                .build();
        giftMapper.insert(giftDto);
        dto = GiftItemDetailDto.builder()
                .item_id(1)
                .gift_id("3")
                .item_qty(1)
                .build();
        mapper.insert(dto);
    }
    @Test
    @SneakyThrows
        //Tx의 경우 어떻게 테스트를 하지...??
    void remove() {

//
//        //상황 : 프로젝트("pj1")에서 테스트아이템을 삭제한다.
//        //테스트 아이템은 현재 선물(1,2,3)에 포함되어 있다.
//        //테스트 아이템을 삭제했을 때, 선물 테이블에서 1,2,3 선물이 같이 삭제되어야 함.
//        //선물-아이템 상세 테이블에서도 해당 선물 아이디에 대한 상세가 같이 삭제되어야 함.
//
        int rowCnt = itemService.remove(1);
        assertTrue(rowCnt==1);

        itemDto = itemService.getItem(1);
        assertEquals(itemDto,null);
        for(int i=1; i<=3; i++){
            giftDto = giftMapper.select("1");
            assertEquals(giftDto, null);
        }
    }

//    @Test
//    @SneakyThrows
//    @DisplayName("롤백 테스트")
//    public void failTest(){
//        //giftService.remove()또는 itemService.remove()에서 exception발생한 상황을 가정
//        Exception e = Assertions.assertThrows(Exception.class
//                , () -> itemService.remove(itemDto.getItem_id()));
//        e.printStackTrace();
//
//    }
}
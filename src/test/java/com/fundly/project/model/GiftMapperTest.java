package com.fundly.project.model;

import com.persistence.dto.GiftDto;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Slf4j
@SpringJUnitWebConfig(classes = {RootContext.class, ServletContext.class})
class GiftMapperTest {
    @Autowired
    GiftMapper giftMapper;
    GiftDto giftDto;

    @Test
    @SneakyThrows
    @DisplayName("선물 수량 세기")
    void count() {
        int cnt = giftMapper.count("pj1");
        assertEquals(cnt,5);

        giftDto = GiftDto.builder()
                .gift_name("선물세트Z")
                .pj_id("pj1")
                .gift_qty_lim_yn("y")
                .gift_total_qty(1000)
                .gift_max_qty_per_person(30)
                .gift_ship_due_date(LocalDateTime.now())
//                .gift_ship_need_yn("y")
                .gift_money(randomMoney())
                .gift_curr_qty(1000)
                .build();

        int rowCnt = giftMapper.insert(giftDto);
        assertEquals(rowCnt,1);
        cnt = giftMapper.count("pj1");
        assertEquals(cnt,6);
    }

    @Test
    @SneakyThrows
    @DisplayName("특정 선물 선택하기")
    void select() {
        List<GiftDto> list = giftMapper.selectAllByPj("pj1");
        giftDto = list.get(0);
        GiftDto giftDto2 = giftMapper.select(giftDto.getGift_id());
        assertEquals(giftDto,giftDto2);
    }

    @Test
    @SneakyThrows
    @DisplayName("특정 프로젝트의 모든 선물 가져오기")
    void selectAllByPj() {
        List<GiftDto> list = giftMapper.selectAllByPj("pj1");
        assertEquals(list.size(),5);
    }

    @Test
    @SneakyThrows
    @DisplayName("(특정 프로젝트의) 특정 상태의 모든 선물 가져오기")
    void selectByStatus() {
        giftDto = GiftDto.builder()
                .pj_id("pj1")
                .gift_status("004002") //판매중인 상태
                .build();
        List<GiftDto> list = giftMapper.selectByStatus(giftDto);
        log.error("list={}",list);
        assertEquals(list.size(),5);
    }

    @Test
    @SneakyThrows
    @DisplayName("해당 선물의 상세 내역 변경")
    void updateContentBefore() {
        List<GiftDto> list = giftMapper.selectAllByPj("pj1");
        giftDto = list.get(0);
        giftDto.setGift_money(23000);
        giftDto.setGift_max_qty_per_person(10);
        int rowCnt = giftMapper.updateContentBefore(giftDto);
        assertEquals(rowCnt,1);
        GiftDto giftDto2 = giftMapper.select(giftDto.getGift_id());
        assertEquals(giftDto.getGift_id(),giftDto2.getGift_id());
//        assertTrue(giftDto.getGift_money()!=giftDto2.getGift_money());

    }

    @Test
    @SneakyThrows
    @DisplayName("해당 선물의 판매 상태 변경")
    void updateStatus() {
        giftDto = giftMapper.selectAllByPj("pj1").get(0);
        giftDto.setGift_status("004001");
        int rowCnt = giftMapper.updateStatus(giftDto);
        assertEquals(rowCnt,1);
        GiftDto giftDto2 = giftMapper.select(giftDto.getGift_id());
        assertEquals(giftDto, giftDto2);
        assertEquals(giftDto2.getGift_status(),"004001");
    }

    @Test
    @SneakyThrows
    @DisplayName("해당 선물의 수량 변경(수정)")
    void updateQty() {
        giftDto = giftMapper.selectAllByPj("pj1").get(0);
        giftDto.setGift_total_qty(3000);
        giftDto.setGift_max_qty_per_person(100);
        int rowCnt = giftMapper.updateQty(giftDto);
        assertEquals(rowCnt,1);
        GiftDto giftDto2 = giftMapper.select(giftDto.getGift_id());
        assertEquals(giftDto,giftDto2);
    }

    @Test
    @SneakyThrows
    @DisplayName("해당 선물의 삭제")
    void delete() {
        int cnt = giftMapper.count("pj1");
        giftDto = giftMapper.selectAllByPj("pj1").get(0);
        int rowCnt = giftMapper.delete(giftDto.getGift_id());
        assertEquals(rowCnt,1);
        int cnt2 = giftMapper.count("pj1");
        assertTrue(cnt2==cnt-1);
        giftDto = giftMapper.select(giftDto.getGift_id());
        assertEquals(giftDto,null);
    }

    //    @BeforeEach
    //매 테스트 전에 초기화 하는 코드
    @BeforeEach
    @Test
    @SneakyThrows
    @DisplayName("아이템 insert")
    void initTable() {
        int rowCnt = 0;
        giftMapper.deleteAll(); // truncate table

        for(int i=0; i<10; i++){
            giftDto = GiftDto.builder()
                    .gift_name("선물세트"+(char)(65+i))
                    .pj_id(i/5<1?"pj1":"pj2")
                    .gift_qty_lim_yn("y")
                    .gift_total_qty(1000)
                    .gift_max_qty_per_person(30)
                    .gift_ship_due_date(LocalDateTime.now())
//                    .gift_ship_need_yn(i%2==0?"y":"n")
                    .gift_money(randomMoney())
                    .gift_curr_qty(1000)
                    .build();
            log.error("giftDto={}",giftDto);
            rowCnt += giftMapper.insert(giftDto);
        }
        assertEquals(rowCnt,10);
        log.error("rowCnt={}",rowCnt);


    }

    private int randomMoney(){
        return (int)(Math.random()*15000)/1000*1000+10000;
    }

}
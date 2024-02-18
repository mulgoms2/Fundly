package com.fundly.project.service;

import com.fundly.project.model.GiftItemDetailMapper;
import com.fundly.project.model.GiftMapper;
import com.persistence.dto.GiftDto;
import com.persistence.dto.GiftItemDetailDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@Slf4j
@ExtendWith(MockitoExtension.class)
class GiftServiceImplTest {
    @Mock
    GiftMapper giftMapper;
    @Mock
    GiftItemDetailMapper giftItemDetailMapper;
    @InjectMocks
    GiftServiceImpl giftService;
    GiftDto giftDto;
    GiftItemDetailDto dto;
    List<GiftDto> list;
    List<GiftItemDetailDto> itemList;

    @BeforeEach
    void setUp(){
        giftDto = GiftDto.builder()
                .gift_id(1)
                .pj_id("pj1")
                .gift_name("선물A")
                .build();

        list = new ArrayList<>(5);
        itemList = new ArrayList<>(4);
    }


    @Test
    @DisplayName("특정 프로젝트에 등록된 모든 선물 수 세기")
    @SneakyThrows
    void getCount() {
        //given
        given(giftMapper.count("pj1")).willReturn(10);
        //when
        int cnt = giftService.getCount("pj1");
        //then
        assertThat(cnt).isEqualTo(10);

    }

    @Test
    @DisplayName("선물 등록하기-성공")
    @SneakyThrows
    void registerGift() {
        //given
        given(giftMapper.insert(giftDto)).willReturn(1);
        //when
        int rowCnt = giftService.registerGift(giftDto, itemList);
        //then
        assertThat(rowCnt).isEqualTo(1);
    }

//    @Test
//    @DisplayName("선물 등록하기-실패")
//    @SneakyThrows
//    void registerGift_fail() {
//        //given
////        given(giftMapper.insert(giftDto)).willReturn(1);
//        //when
////        int rowCnt = giftService.registerGift(giftDto, itemList);
//        //then
//        Exception e = Assertions.assertThrows(Exception.class,()->giftService.registerGift(giftDto, itemList));
//        e.printStackTrace();
//    }

    @Test
    @SneakyThrows
    @DisplayName("해당 선물 가져오기")
    void getGift() {
        //given
        given(giftMapper.select(1)).willReturn(giftDto);
        //when
        GiftDto giftDto2 = giftService.getGift(1);
        //then
        assertThat(giftDto2).isEqualTo(giftDto);
    }

    @Test
    @SneakyThrows
    @DisplayName("특정 프로젝트의 모든 선물 가져오기")
    void getAllGiftList() {
        //given
        given(giftMapper.selectAllByPj("pj1")).willReturn(list);
        //when
        List<GiftDto> list2 = giftService.getAllGiftList("pj1");
        //then
        assertThat(list2).isEqualTo(list);
    }

    @Test
    @SneakyThrows
    @DisplayName("특정 프로젝트의 특정 상태 선물 모두 가져오기")
    void getGiftByStatus() {
        //given
        given(giftMapper.selectByStatus(giftDto)).willReturn(list);
        //when
        List<GiftDto> list2 = giftService.getGiftByStatus(giftDto);
        //then
        assertThat(list2).isEqualTo(list);
    }

    @Test
    @SneakyThrows
    @DisplayName("등록한 선물 수정하기-성공(Tx)")
    void modifyGiftContent() {
        //given
        given(giftMapper.updateContentBefore(giftDto)).willReturn(1);
        //when
        int rowCnt = giftService.modifyGiftContent(giftDto,itemList);
        //then
        assertThat(rowCnt).isEqualTo(1);
    }
//    @Test
//    @SneakyThrows
//    @DisplayName("등록한 선물 수정하기-실패(Tx)")
//    void modifyGiftContentFail() {
//        Exception e = Assertions.assertThrows(Exception.class,()->giftService.modifyGiftContent(giftDto, itemList));
//        e.printStackTrace();
//    }

    @Test
    @SneakyThrows
    @DisplayName("선물 상태 수정하기")
    void modifyGiftStatus() {
        //given
        given(giftMapper.updateStatus(giftDto)).willReturn(1);
        //when
        int rowCnt = giftService.modifyGiftStatus(giftDto);
        //then
        assertThat(rowCnt).isEqualTo(1);
    }

    @Test
    @SneakyThrows
    @DisplayName("선물 수량 수정하기")
    void modifyGiftQty() {
        //given
        given(giftMapper.updateQty(giftDto)).willReturn(1);
        //when
        int rowCnt = giftService.modifyGiftQty(giftDto);
        //then
        assertThat(rowCnt).isEqualTo(1);
    }

    @Test
    @SneakyThrows
    @DisplayName("선물 삭제하기-성공")
    void removeGift() {
        //given
        given(giftMapper.delete(1)).willReturn(1);
        //when
        int rowCnt = giftService.removeGift(1);
        //then
        assertThat(rowCnt).isEqualTo(1);
    }
//    @Test
//    @SneakyThrows
//    @DisplayName("선물 삭제하기-실패")
//    void removeGiftFail() {
//        Exception e = Assertions.assertThrows(Exception.class,
//                ()->giftService.removeGift(1));
//        e.printStackTrace();
//    }
}
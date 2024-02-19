package com.fundly.project.model;

import com.persistence.dto.ItemDto;
import config.RootContext;
import config.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringJUnitWebConfig(classes = {RootContext.class, ServletContext.class})
class ItemMapperTest {
    @Autowired
    ItemMapper itemMapper;

    ItemDto itemDto;

    @Test
    @DisplayName("해당 프로젝트의 모든 아이템 갯수")
    void count() throws Exception {
        int rowCnt = itemMapper.count("pj1");
        log.error("***** rowCnt={}",rowCnt);
        assertEquals(rowCnt,10);

        rowCnt = itemMapper.count("pj2");
        log.error("***** rowCnt = {}", rowCnt);
        assertEquals(rowCnt, 10);

    }

    @Test
    @DisplayName("해당 프로젝트에 등록된 모든 아이템(아이템 리스트)")
    void selectAllFromPj() throws Exception {
        List<ItemDto> list = itemMapper.selectAllFromPj("pj1");
        assertEquals(list.size(),10);
        log.error("********** list={}",list);
    }

    @Test
    @DisplayName("모든 아이템 가져오기")
    void selectAll() throws Exception {
        List<ItemDto> list = itemMapper.selectAll();
        assertEquals(list.size(),30);
        log.error("********* list={}",list);
    }

    @Test
    @DisplayName("해당 아이템 가져오기")
    void select() throws Exception {
        ItemDto itemDto = itemMapper.select(1);
        assertEquals(itemDto.getItem_id(),1);
        log.error("****** itemDto={}",itemDto);

        itemDto = new ItemDto("pj1","아이템11","객관식","옵션1, 옵션2, 옵션3");
        int rowCnt = itemMapper.insert(itemDto);
        assertEquals(rowCnt,1);
        ItemDto itemDto2 = itemMapper.select(31);
        assertEquals(itemDto.getItem_name(), itemDto2.getItem_name());
        assertEquals(itemDto.getPj_id(),itemDto2.getPj_id());

    }

    @Test
    @DisplayName("아이템 insert")
    void insert() throws Exception {
        int rowCnt = itemMapper.count("pj1");
        assertEquals(rowCnt,10);

        itemMapper.deleteAll();
        rowCnt = itemMapper.count("pj1");
        assertEquals(rowCnt, 0);

        itemDto = new ItemDto("pj1","아이템5","주관식", "각인할 메세지를 남겨주세요");
        rowCnt = itemMapper.insert(itemDto);
        assertEquals(rowCnt, 1);
        rowCnt = itemMapper.count("pj1");
        assertEquals(rowCnt,1);
    }

    @Test
    @DisplayName("해당 아이템 삭제하기")
    void delete() throws Exception {
        int rowCnt = itemMapper.count("pj1");
        assertEquals(rowCnt, 10);
        itemDto = itemMapper.select(1);
        String pj_id = itemDto.getPj_id();
        int cnt = itemMapper.count(pj_id);
        rowCnt = itemMapper.delete(itemDto.getItem_id());

        assertEquals(rowCnt,1);
        int cnt2 = itemMapper.count(pj_id);
        assertTrue(cnt2==cnt-1);

        ItemDto itemDto2 = itemMapper.select(itemDto.getItem_id());
        assertTrue(itemDto2==null); //삭제 한 후 해당 아이템을 꺼내면 꺼내지는게 없음.



    }
    @Test
    @DisplayName("해당 아이템 업데이트")
    void update() throws Exception {
        itemDto = itemMapper.select(2);
        log.error("itemDto={}",itemDto);
        itemDto.setItem_name("아이템7");
        int rowCnt = itemMapper.update(itemDto);
        assertEquals(rowCnt,1);
        ItemDto itemDto2 = itemMapper.select(itemDto.getItem_id());
        assertEquals(itemDto2.getItem_id(),itemDto.getItem_id());
        log.error("itemDto2={}",itemDto2);
        assertTrue(itemDto.getItem_name()!=itemDto2.getItem_name());

    }

    @BeforeEach
        //매 테스트 전에 초기화 하는 코드
    void initTable() throws Exception{
        itemMapper.deleteAll(); // truncate table


        for(int i=1; i<=30; i++){
            String optionType = getOptionType();
            itemDto = new ItemDto("pj"+(i%3+1),"아이템"+i,optionType,getOption(optionType));
            log.error("itemDto={}",itemDto);
            itemMapper.insert(itemDto);
        }

    }



    private String getOptionType(){
        String[] optTypeArr = {"옵션 없음", "객관식 옵션", "주관식 옵션"};
        int index = (int)(Math.random()*optTypeArr.length);
        return optTypeArr[index];
    }
    private String getOption(String optionType) {
        if(optionType.equals("옵션 없음")) return null;
        else if(optionType.equals("객관식 옵션")){
            String[] multiOptType = {"옵션1","옵션2","옵션3","옵션4","옵션5"};
            int num = (int)(Math.random()*(multiOptType.length-1))+1;
            String[] tmp = new String[num];
            for(int i=0; i<num; i++){
                tmp[i] = multiOptType[i];
            }
            return Arrays.toString(tmp).replace("[","").replace("]","");
        } else {
            return "각인할 메세지를 입력해주세요";
        }

    }

}

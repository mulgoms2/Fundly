package com.fundly.project.service;

import com.persistence.dto.ItemDto;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

@Slf4j
@SpringJUnitWebConfig(classes = {RootContext.class, ServletContext.class})
class ItemServiceImplTest {
    @Autowired
    ItemService itemService;

//    @SneakyThrows
//    @Test
//    public void getCountTest(){
//        int cnt = itemService.getItemCount();
//        System.out.println("cnt = " + cnt);
//
//    }

    @SneakyThrows
    @Test
    public void getItemList(){
        for(int i=1; i<=20; i++){
//            ItemDto itemDto = new ItemDto("abc"+((i%4)+1),getRandomItem(),getRandomColor());
            ItemDto itemDto = new ItemDto("abc"+i,"손수건","민트");
            itemService.registerItem(itemDto);
        }
//        List<ItemDto> itemDto = itemService.getItemList();
//        List<ItemDto> itemDto2 = itemService.getItemList("abc3");
//        System.out.println("itemDto = " + itemDto);
//        System.out.println("itemDto2 = " + itemDto2);
    }

    public String getRandomItem(){
        String[] itemArr = {"손수건","양말","목도리","가방","공책"};
        int tmp = (int)(Math.random()*itemArr.length);
        return itemArr[tmp];
    }
    public String getRandomColor(){
        String[] colorArr = {"빨강","노랑","초록","파랑","보라"};
        int tmp = (int)(Math.random()* colorArr.length);
        return colorArr[tmp];
    }

}
<<<<<<< HEAD
//package com.my;
//
//import com.my.config.RootContext;
//import com.my.config.ServletContext;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@Slf4j
//@SpringJUnitWebConfig(classes = {RootContext.class, ServletContext.class})
//public class SampleTest {
//
//    @Test
//    @DisplayName("샘플테스트")
//    public void test() {
//        assertTrue(true);
//    }
//}
//
=======
package com.fundly.user.model;

import config.RootContext;
import config.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringJUnitWebConfig(classes = {RootContext.class, ServletContext.class})
class userDaoImplTest {


    @Test
    void count() {
        assertTrue(true);
    }

    @Test
    void insert() {
    }
}
>>>>>>> upstream/main

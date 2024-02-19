package com.fundly.user.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LikePageHandlerTest {

    @Test
    public void test() {
        LikePageHandler ph = new LikePageHandler(250,1);
        ph.print();
        System.out.println("ph = " + ph);
         assertTrue(ph.getBeginPage() ==1);
         assertTrue(ph.getEndPage() ==10);
    }

    @Test
    public void test2() {
        LikePageHandler ph = new LikePageHandler(250,11);
        ph.print();
        System.out.println("ph = " + ph);
        assertTrue(ph.getBeginPage() ==11);
        assertTrue(ph.getEndPage() ==20);
    }

    @Test
    public void test3() {
        LikePageHandler ph = new LikePageHandler(255,25);
        ph.print();
        System.out.println("ph = " + ph);
        assertTrue(ph.getBeginPage() ==21);
        assertTrue(ph.getEndPage() ==26);
    }

}
package com.lyf.utils.httpClient.feign;

import org.junit.Test;

import java.math.BigDecimal;

//@SpringBootTest
public class TestFeignTest {

    @Test
    public void test01() {
        System.out.println("https://pbs.twimg.com/profile_images/1864249587/_D7_9E_D7_99_D7_9B_D7_90_D7_9C_20_D7_A4_D7_A8_D7_95_D7_99_D7_A0_D7_93_20_D7_A2_D7_9D_20_D7_A6_D7_A2_D7_99_D7_A8_20_D7_9E_D7_91_D7_A0_D7_99_20_D7_9E_D7_A0_D7_A9_D7_94_20_D7_91_D7_9E_D7_99_D7_96_D7_95_D7_A8_D7_9D_20_D7_A9_D7_91_D7_94_D7_95_D7_93_D7_95_normal.jpg".length());
    }

    @Test
    public void test02() {
        BigDecimal maxValue = new BigDecimal(Integer.MAX_VALUE);
        BigDecimal bigDecimal1 = new BigDecimal("429496656");
        int i = bigDecimal1.compareTo(maxValue);
        System.out.println(i);
//        Integer integer = Integer.valueOf("4294966565");
//        System.out.println(integer);
    }
}
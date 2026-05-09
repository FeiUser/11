package com.lyf.code;

import org.junit.Test;

public class Solution0103Test {
    @Test
    public void test() {
        Solution0103 solution0103 = new Solution0103();
        // all:17 need:
        System.out.println(solution0103.replaceSpaces3("Mr John Smith    ", 10));
//        System.out.println(new String("Mr John Smith    ".toCharArray(), 1, 2));
//        System.out.println(solution0103.replaceSpaces2("   1                                                 ", 5));
    }

    @Test
    public void test2() {
        int n = 10;
        for (int i = 0; i < 4; i++) {
            System.out.println(n--);
        }
        System.out.println(n);
    }

}
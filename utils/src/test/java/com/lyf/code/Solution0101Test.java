package com.lyf.code;

import org.junit.Test;

public class Solution0101Test {
    @Test
    public void test() {
        System.out.println();
        String s = "az";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            System.out.println(c);
            System.out.println((int)c);
        }
    }

    @Test
    public void test2() {
        Solution0101 solution0101 = new Solution0101();
        boolean abcdefgc = solution0101.isUnique3("abcdefgc");
        long result = 1L << 31;
        System.out.println("result:" + Long.toBinaryString(result));
        System.out.println("length:" + Long.toBinaryString(result).length());
    }
}
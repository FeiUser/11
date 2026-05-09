package com.lyf.code;

/**
 * 回文数:回文数是指一个正整数，从左向右读和从右向左读都是相同的数字序列‌。
 */
public class Solution0104 {
    public static boolean canPermutePalindrome2(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0, j = chars.length - 1; i < j; i++, j--) {
            if (chars[i] != chars[j]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(canPermutePalindrome2("123"));
        System.out.println(canPermutePalindrome2("332"));
        System.out.println(canPermutePalindrome2("3232"));
        System.out.println(canPermutePalindrome2("12321"));
        System.out.println(canPermutePalindrome2("2222"));

    }
}

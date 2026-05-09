package com.lyf.code;

/**
 *字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
 */







public class Solution0109 {

    public static boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        String s = s2 + s2;
        return s.contains(s1);
    }
    public static void main(String[] args) {
        System.out.println(isFlipedString("waterbottle", "erbottlewat erbottlewat"));
        System.out.println(isFlipedString("aa", "aba"));
        System.out.println(isFlipedString("12321", "23211"));
    }
}

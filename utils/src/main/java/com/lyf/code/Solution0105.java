package com.lyf.code;

/**
 * 字符串有三种编辑操作:插入一个英文字符、删除一个英文字符或者替换一个英文字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
 */
public class Solution0105 {
    public static boolean oneEditAway(String first, String second) {
        int j = 0;
        int diffNum = 0;
        int length1 = first.length();
        int length2 = second.length();
        int sub = length1 - length2;
        char[] chars1 = first.toCharArray();
        char[] chars2 = second.toCharArray();

        if (sub == 0) {
            for (int i = 0; i < chars1.length; i++) {
                if (chars1[i] != chars2[i]) {
                    diffNum++;
                }
            }
        } else if (sub > 0) {
            for (int i = 0; i < chars1.length; i++) {
                if (((j > length2 - 1) || chars1[i] != chars2[j])) {
                    diffNum++;
                } else {
                    j++;
                }
            }
        } else {
            for (int i = 0; i < chars2.length; i++) {
                if ((j > length1 - 1) || (chars2[i] != chars1[j])) {
                    diffNum++;
                } else {
                    j++;
                }
            }
        }
        return diffNum <= 1;
    }

    public static boolean oneEditAway2(String first, String second) {
        int j = 0;
        int diffNum = 0;
        char[] chars1 = first.toCharArray();
        char[] chars2 = second.toCharArray();
        int length1 = first.length();
        int length2 = second.length();
        int sub = length1 - length2;
        if (sub == 0) {
            for (int i = 0; i < length1; i++) {
                if (chars1[i] != chars2[i]) {
                    ++diffNum;
                }
                if (diffNum > 1) {
                    return false;
                }
            }
        } else if (sub > 0) {
            for (int i = 0; i < length1; i++) {
                if ((j > length2 - 1) || (chars1[i] != chars2[j])) {
                    ++diffNum;
                } else {
                    ++j;
                }
                if (diffNum > 1) {
                    return false;
                }
            }
        } else if (sub < 0) {
            for (int i = 0; i < length2; i++) {
                if ((j > length1 - 1) || (chars1[j] != chars2[i])) {
                    ++diffNum;
                } else {
                    ++j;
                }
                if (diffNum > 1) {
                    return false;
                }
            }
        }
        return diffNum <= 1;
    }

    public static void main(String[] args) {
        boolean b = oneEditAway("spartan", "part");
        b = oneEditAway("ppart", "part");
        System.out.println(b);
    }
}

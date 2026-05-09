package com.lyf.code;

public class Solution0103 {
    // original
    public String replaceSpaces(String S, int length) {
        char[] chars = S.toCharArray();
        int lastIndex = chars.length - 1;
        for (int i = length - 1; i >= 0; i--) {
            if (chars[i] == ' ') {
                chars[lastIndex--] = '0';
                chars[lastIndex--] = '2';
                chars[lastIndex--] = '%';
            } else {
                chars[lastIndex--] = chars[i];
            }
        }
        ++lastIndex;
        return String.valueOf(chars, lastIndex, chars.length - lastIndex);
    }

    public String replaceSpaces2(String S, int length) {
        char[] chars = S.toCharArray();
        int lastIndex = chars.length - 1;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == ' ') {
                chars[lastIndex--] = '0';
                chars[lastIndex--] = '2';
                chars[lastIndex--] = '%';
            } else {
                chars[lastIndex--] = chars[i];
            }
        }
        ++lastIndex;
        return String.valueOf(chars, lastIndex, chars.length - lastIndex);
    }

    public String replaceSpaces3(String s, int length) {
        char[] chars = s.toCharArray();
        int lastIndex = chars.length - 1;
        for (int i = length - 1; i >=0 ; i--) {
            if (chars[i] == ' ') {
                chars[lastIndex--] = '0';
                chars[lastIndex--] = '2';
                chars[lastIndex--] = '%';
            } else {
                chars[lastIndex--] = chars[i];
            }
        }
        ++lastIndex;
        return String.valueOf(chars, lastIndex, chars.length - lastIndex);
    }
}
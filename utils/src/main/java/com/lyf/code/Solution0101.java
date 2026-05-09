package com.lyf.code;

/**
 * 判定字符传中的字符是否唯一
 */
public class Solution0101 {
    public boolean isUnique(String astr) {
        char[] chars = astr.toCharArray();
        if (chars.length > 26) {
            return false;
        }

        int num = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            int index = c - 'a';
            int resutl = num & 1 << index;
            if (resutl != 0) {
                return false;
            } else {
                num = num | 1 << index;
            }
        }
        return true;
    }

    public boolean isUnique2(String astr) {
        char[] chars = astr.toCharArray();
        if (chars.length > 26) {
            return false;
        }

        int num = 0;
        for (char c : chars) {
            int index = c - 'a';
            int result = num & 1 << index;
            if (0 != result) {
                return false;
            } else {
                num = num | 1 << index;
            }
        }
        return true;
    }


    public boolean isUnique3(String astr) {
        char[] chars = astr.toCharArray();

        if (chars.length > 26) {
            return false;
        }

        int num = 0;
        for (char c : chars) {
            int index = c - 'a';
            int result = num & 1 << index;
            System.out.println(result);
            if (0 != result) {
                return false;
            } else {
                num = num | 1 << index;
            }
        }

        return true;
    }

    public boolean isUnique4(String s) {
        char[] chars = s.toCharArray();
        if (chars.length > 26) {
            return false;
        }

        int num = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            int index = c - 'a';
            int result = num & 1 << index;
            if (result != 0) {
                return false;
            } else {
                num = num | 1 << index;
            }
        }
        return true;
    }
}
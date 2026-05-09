package com.lyf.code;

public class Solution0102 {
    public boolean CheckPermutation(String s1, String s2) {
        int[] m1 = mappingToArray(s1);
        int[] m2 = mappingToArray(s2);

        for (int i = 0; i < m1.length; i++) {
            if (m1[i] != m2[i]) {
                return false;
            }
        }
        return true;
    }

    public int[] mappingToArray(String s) {
        char[] chars = s.toCharArray();
        int[] ints = new int[26];
        for (char aChar : chars) {
            int index = aChar - 'a';
            ++ints[index];
        }
        return ints;
    }


    public boolean CheckPermutation2(String s1, String s2) {
        int[] mapping = mappingToArray2(s1);
        int[] mapping2 = mappingToArray2(s2);
        for (int i = 0; i < mapping.length; i++) {
            if (mapping[i] != mapping2[i]) {
                return false;
            }
        }
        return true;
    }

    public int[] mappingToArray2(String s) {
        int[] result = new int[26];
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            int index = aChar - 'a';
            result[index]++;
        }
        return result;
    }
}
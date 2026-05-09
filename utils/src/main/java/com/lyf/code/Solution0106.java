package com.lyf.code;

/**
 * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
 */
public class Solution0106 {
    public static String compressString(String S) {
        if (S.length() == 0) {
            return "";
        }
        char[] chars = S.toCharArray();
        StringBuffer buffer = new StringBuffer();

        int num = 1;
        char lastC = chars[0], nextC = chars[0];

        buffer.append(lastC);
        for (int i = 0; i < chars.length; i++) {
            if (i == 0) {
                continue;
            }
            nextC = chars[i];
            if (nextC == lastC) {
                num++;
            } else {
                buffer.append(num);
                lastC = chars[i];
                buffer.append(lastC);
                num = 1;
            }
        }
        buffer.append(num);
        return S.length() > buffer.length() ? buffer.toString() : S;
    }

    public static String compressString2(String S) {
        if (S.length() == 0) { // 空串处理
            return S;
        }
        StringBuffer ans = new StringBuffer();
        int cnt = 1;
        char ch = S.charAt(0);
        for (int i = 1; i < S.length(); ++i) {
            if (ch == S.charAt(i)) {
                cnt++;
            } else {
                ans.append(ch);
                ans.append(cnt);
                ch = S.charAt(i);
                cnt = 1;
            }
        }
        ans.append(ch);
        ans.append(cnt);
        return ans.length() >= S.length() ? S : ans.toString();
    }

    public static String compressString3(String s) {
        StringBuffer sb = new StringBuffer();
        char[] chars = s.toCharArray();

        char tempChar = chars[0];
        int tempNum = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == tempChar) {
                tempNum++;
            } else {
                sb.append(tempChar).append(tempNum);
                tempChar = chars[i];
                tempNum = 1;
            }
        }
        sb.append(tempChar).append(tempNum);
        return sb.length() >= s.length() ? s : sb.toString();
    }

    public static void main(String[] args) {
        String s1 = compressString("aabc");
        String s2 = compressString2("aabc");
        String s3 = compressString3("aaaaabc");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}

package com.lyf.es.utils;

import org.apache.commons.codec.binary.Base64;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
    public static String getMD5(String str) throws NoSuchAlgorithmException {
        // 生成一个MD5加密计算摘要
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        // 计算md5
        md5.update(str.getBytes());
        return new BigInteger(1, md5.digest()).toString(16);
    }


    public static String encodePassword(String password) {
        String preEncode = password.substring(4) + "casia" + password.substring(0, 4);
        //   System.out.println(preEncode);
        String s1 = Base64.encodeBase64String(preEncode.getBytes());
        String start = s1.substring(0, 1);
        return s1.substring(1).trim() + start;
    }

    public static String decodePassword(String password) {
        int length = password.length();
        String s1 = password.substring(0, length-1);
        String s2 = password.substring(length-1, length);
        String encodedStr = s2 + s1;
        byte[] decodedBytes = Base64.decodeBase64(encodedStr);
        String decodedStr = new String(decodedBytes);
        int len = decodedStr.length();
        String frontFour = decodedStr.substring(len-4, len);
        int casiaIndex = decodedStr.indexOf("casia");
        String rear = decodedStr.substring(0, casiaIndex);
        return frontFour +rear;
    }

}
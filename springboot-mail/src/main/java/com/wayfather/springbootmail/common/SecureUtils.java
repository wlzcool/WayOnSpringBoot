package com.wayfather.springbootmail.common;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @ClassName SecureUtils
 * @Description TODO
 * @Author IBM
 * @Date 2019/10/9 10:00
 **/
public class SecureUtils {
    /**
     * SHA256加密
     *
     * @param text 需要加密的字符
     * @return String
     */
    public static String String2SHA256StrJava(String text) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(text.getBytes(StandardCharsets.UTF_8));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    /**
     * 将byte转为16进制
     *
     * @param bytes 需要处理的byte
     * @return String
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        String temp;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                stringBuilder.append("0");
            }
            stringBuilder.append(temp);
        }
        return stringBuilder.toString();
    }
}

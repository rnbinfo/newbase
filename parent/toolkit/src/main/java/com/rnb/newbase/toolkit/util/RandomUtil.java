package com.rnb.newbase.toolkit.util;

import java.util.Random;

public class RandomUtil extends Random {
    /**
     * 混合时需要剔除的字母
     */
    public static final char[] excludeChar = {'l','O'};
    /**
     * 所有大小写字母
     */
    public static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * 所有数字
     */
    public static final String numberChar = "0123456789";
    /**
     * 所有符号
     */
    public static final String symbolChar = "`~!@#$%^&*(){}[]-=_+,./<>?:;";

    /**
     * 返回一个定长的随机纯数字字符串(只包含数字)
     */
    public static String generateDigitalString(int length){
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(numberChar.charAt(random.nextInt(numberChar.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机纯字母字符串(只包含大小写字母)
     */
    public static String generateAlphabetString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(letterChar.charAt(random.nextInt(letterChar.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机纯字母字符串(只包含大小写字母，数字)
     */
    public static String generateNoSymbleString(int length) {
        return generateMixString(letterChar + numberChar + letterChar + numberChar, length);
    }

    /**
     * 返回一个定长的随机全部字符串(包含大小写字母、数字、符号)
     */
    public static String generateAllString(int length) {
        return generateMixString(letterChar + numberChar + letterChar + numberChar + symbolChar, length);
    }

    // 处理混合字符串
    private static String generateMixString(String allChar, int length) {
        for(char c : excludeChar) {
            allChar = deleteString(allChar, c);
        }
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(allChar.charAt(random.nextInt(allChar.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机纯大写字母字符串(只包含大小写字母)
     */
    public static String generateUpperString(int length) {
        return generateAlphabetString(length).toUpperCase();
    }

    /**
     * 返回一个定长的随机纯小写字母字符串(只包含大小写字母)
     */
    public static String generateLowerString(int length) {
        return generateAlphabetString(length).toLowerCase();
    }

    /**
     * 生成一个定长的纯0字符串
     */
    public static String generateZeroString(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append('0');
        }
        return sb.toString();
    }

    /**
     * 根据数字生成一个定长的字符串，长度不够前面补0
     */
    public static String toFixdLengthString(long num, int fixedLength) {
        StringBuffer sb = new StringBuffer();
        String strNum = String.valueOf(num);
        if (fixedLength - strNum.length() >= 0) {
            sb.append(generateZeroString(fixedLength - strNum.length()));
        } else {
            throw new RuntimeException("将数字" + num + "转化为长度为" + fixedLength + "的字符串发生异常！");
        }
        sb.append(strNum);
        return sb.toString();
    }

    /**
     * 生成指定开头的指定长度Integer
     */
    public static Long toFixdLengthInteger(String start, long num, int fixedLength) {
        StringBuffer sb = new StringBuffer(start);
        String strNum = String.valueOf(num);
        if (fixedLength - strNum.length() >= 0) {
            sb.append(generateZeroString(fixedLength - strNum.length()));
        } else {
            throw new RuntimeException("将数字" + num + "转化为长度为" + fixedLength + "的字符串发生异常！");
        }
        sb.append(strNum);
        return Long.valueOf(sb.toString());
    }

    // 删除特定字符
    private static String deleteString(String str, char delChar){
        String delStr = "";
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) != delChar){
                delStr += str.charAt(i);
            }
        }
        return delStr;
    }
}

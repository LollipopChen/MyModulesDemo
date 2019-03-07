/*
 * SuperNovaFramework
 * SNStringUtils
 * Created by Leo.Mok on 2018-03-29.
 * Copyright © 2018 Supernova Software. All rights reserved.
 */

package com.example.libbase.utils;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.example.libbase.base.SnBaseApplication;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SNStringUtils {
    private final static Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\" +
            ".\\w+([-.]\\w+)*");
    private final static Pattern phone = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,1-9]))" +
            "\\d{8}$");
    private final static Pattern IDCard = Pattern.compile("\\d{17}[0-9Xx]|\\d{15}");

    private final static Pattern EIGHT_NUMBER = Pattern.compile("^\\d{8}$");
    private final static Pattern ELEVEN_NUMBER = Pattern.compile("^\\d{11}$");

    //去掉多余空格、回车、换行符、制表符
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static String format(@StringRes int resID, String str) {
        return String.format(SnBaseApplication.getAppContext().getResources().getString(resID), str);
    }

    public static String formatNum(String sNum) {
        return formatNum(toDouble(sNum));
    }

    public static String formatNum(double dNum) {
        String num = String.valueOf(SNDoubleUtils.round2(dNum));
        if (isEmpty(num)) {
            return "0";
        }

        if (num.contains(".")) {
            String[] nums = num.split("\\.");
            char[] numChars = nums[1].toCharArray();
            if (numChars.length > 1) {
                if (numChars[0] == '0' && numChars[1] == '0') {
                    return nums[0];
                } else if (numChars[1] == '0') {
                    return nums[0] + "." + numChars[0];
                } else {
                    return num;
                }
            } else {
                if (numChars[0] == '0') {
                    return nums[0];
                } else {
                    return num;
                }
            }
        }

        return num;
    }

    /**
     * 判断给定字符串是否空白串 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     */
    public static boolean isEmpty(CharSequence input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断给定字符串是否空白串 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     */
    public static boolean isEmpty(CharSequence... strs) {
        for (CharSequence str : strs) {
            if (isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     */
    public static boolean isEmail(CharSequence email) {
        if (isEmpty(email)) {
            return false;
        }
        return emailer.matcher(email).matches();
    }

    /**
     * 判断是不是一个合法的手机号码
     */
    public static boolean isPhone(CharSequence phoneNum) {
        if (isEmpty(phoneNum))
            return false;
        return phone.matcher(phoneNum).matches();
    }

    /**
     * 判断是否是一个身份证号码
     */
    public static boolean isIDCard(CharSequence IDCardNumber) {
        if (isEmpty(IDCardNumber))
            return false;
        return IDCard.matcher(IDCardNumber).matches();
    }

    public static boolean isEightNumber(CharSequence number){
        if (isEmpty(number)){
            return false;
        }
        return EIGHT_NUMBER.matcher(number).matches();
    }

    public static boolean isElevenNumber(CharSequence number){
        if (isEmpty(number)){
            return false;
        }
        return ELEVEN_NUMBER.matcher(number).matches();
    }

    /**
     * 返回当前系统时间
     */
    public static String getDataTime(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * 对象转整
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if (obj == null) {return 0;}
        return toInt(obj.toString(), 0);
    }

    /**
     * 字符串转Float
     *
     * @param str 需要转成Float的字符串
     * @return 异常返回0
     */
    public static float toFloat(String str) {
        try {
            return Float.parseFloat(str);
        } catch (Exception e) {}
        return 0F;
    }

    /**
     * String转long
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * String转double
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static double toDouble(String obj) {
        try {
            return Double.parseDouble(obj);
        } catch (Exception e) {
        }
        return 0D;
    }

    /**
     * 字符串转布尔
     *
     * @param b
     * @return 转换异常返回 false
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 判断一个字符串是不是数字
     */
    public static boolean isNumber(CharSequence str) {
        try {
            Integer.parseInt(str.toString());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * byte[]数组转换为16进制的字符串。
     *
     * @param data 要转换的字节数组。
     * @return 转换后的结果。
     */
    public static String byteArrayToHexString(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        for (byte b : data) {
            int v = b & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase(Locale.getDefault());
    }

    /**
     * 16进制表示的字符串转换为字节数组。
     *
     * @param s 16进制表示的字符串
     * @return byte[] 字节数组
     */
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] d = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
            d[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt
                    (i + 1), 16));
        }
        return d;
    }

    private static final int DISTANCE_KM = 1000;

    public static String formatDistance(@NonNull String distance) {
        double distanceD = toDouble(distance);
        if (distanceD >= DISTANCE_KM) {
            distanceD = SNDoubleUtils.div(distanceD, DISTANCE_KM, 1);
            return SNStringUtils.formatNum(distanceD) + "km";
        } else {
            distanceD = SNDoubleUtils.round(distanceD, 1, BigDecimal.ROUND_HALF_UP);
            return SNStringUtils.formatNum(distanceD) + "m";
        }
    }
}

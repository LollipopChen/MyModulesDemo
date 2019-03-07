/*
 * SuperNovaFramework
 * SNDoubleUtils
 * Created by Leo.Mok on 2018-03-29.
 * Copyright © 2018 Supernova Software. All rights reserved.
 */

package com.example.libbase.utils;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * double的计算不精确，会有类似0.0000000000000002的误差，正确的方法是使用BigDecimal或者用整型
 * 整型地方法适合于货币精度已知的情况，比如12.11+1.10转成1211+110计算，最后再/100即可
 */
public final class SNDoubleUtils implements Serializable {
    private static final long serialVersionUID = -3345205828566485102L;

    // 默认运算精度
    private static final Integer DEF_SCALE = 2;

    /**
     * 对double数据进行取精度.
     *
     * @param value        double数据.
     * @param scale        精度位数(保留的小数位数).
     * @param roundingMode 精度取值方式.
     * @return 精度计算后的数据.
     */
    public static double round(double value, int scale, int roundingMode) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, roundingMode);
        double d = bd.doubleValue();
        bd = null;
        return d;
    }

    /**
     * 对double数据进行取精度.
     *
     * @param value double数据.
     * @param scale 精度位数(保留的小数位数).
     * @return 精度计算后的数据.
     */
    public static double round(double value, int scale) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, BigDecimal.ROUND_UP);
        double d = bd.doubleValue();
        bd = null;
        return d;
    }

    /**
     * 对double数据进行取精度.
     *
     * @param value double数据.
     * @return 精度计算后的数据.
     */
    public static String round2(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(DEF_SCALE, BigDecimal.ROUND_UP);
        double d = bd.doubleValue();
        bd = null;
        return String.valueOf(d);
    }

    /**
     * double 相加
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double sum(double d1, double d2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.add(bd2).doubleValue();
    }

    /**
     * double 相减
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double sub(double d1, double d2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.subtract(bd2).doubleValue();
    }

    /**
     * double 乘法
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double mul(double d1, double d2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.multiply(bd2).doubleValue();
    }

    /**
     * double 除法
     *
     * @param d1
     * @param d2
     * @param scale 四舍五入 小数点位数
     * @return
     */
    public static double div(double d1, double d2, int scale) {
        //在此之前，要判断分母是否为0，为0根据实际需求做相应的处理
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double div(double d1, double d2) {
        //在此之前，要判断分母是否为0，为0根据实际需求做相应的处理
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.divide(bd2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}

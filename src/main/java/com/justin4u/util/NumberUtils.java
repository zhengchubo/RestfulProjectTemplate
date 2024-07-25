package com.justin4u.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * com.justin4u.util
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2018-12-13</pre>
 */
public class NumberUtils {

    public static String format2String(Double v, int scale) {
        if (null == v || Double.isNaN(v)) {
            return null;
        }
        BigDecimal bd = new BigDecimal(v);
        return bd.setScale(scale, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
    }

    /**
     * 将对象 value 解析成 BigDecimal，如果无法解析，返回传入的默认值。
     * @param value
     * @param defaultValue
     * @return
     */
    public static BigDecimal getBigDecimal(Object value, BigDecimal defaultValue) {
        if (null == value) {
            return defaultValue;
        }
        BigDecimal ret;
        try {
            if (value instanceof BigDecimal) {
                ret = (BigDecimal) value;
            } else if (value instanceof String) {
                ret = new BigDecimal((String) value);
            } else if (value instanceof BigInteger) {
                ret = new BigDecimal((BigInteger) value);
            } else if (value instanceof Number) {
                ret = new BigDecimal(((Number) value).doubleValue());
            } else {
                ret = defaultValue;
            }
        } catch (Exception ex) {
            return defaultValue;
        }
        return ret;
    }

    /**
     * 是否可解析为BigDecimal
     * @param value
     * @return
     */
    public static boolean isBigDecimalable(Object value) {
        return null != getBigDecimal(value, null);
    }


    /**
     * 一个不为零，但是取负等于自身的数字：Integer.MIN_VALUE。
     * @return
     */
    public static Integer absEqualSelfNotZero() {
        int result = Integer.MIN_VALUE;
        boolean met = result == - result;
        if (met) {
            return result;
        } else {
            return null;
        }
    }

    /**
     * 一个整型值的绝对值是否为正？
     * 除了 Integer.MIN_VALUE （它的绝对值等于自身）外，其他的绝对值都是正数。
     * @param i
     * @return
     */
    public static boolean isAbsPositive(Integer i) {
        return Math.abs(i) > 0;
    }

    /**
     * 长整型同理。
     * @param l
     * @return
     */
    public static boolean isAbsPositive(Long l) {
        return Math.abs(l) > 0;
    }

    public static Double whoEqualsAfterPlusOne() {
        double result = Double.POSITIVE_INFINITY;
        if (result + 1 == result) {
            return result;
        }
        return null;
    }

    public static Double whoEqualsAfterMinusOne() {
        double result = Double.POSITIVE_INFINITY;
        if (result - 1 == result) {
            return result;
        }
        return null;
    }

    public static Double whoNotEqualsSelf() {
        double result = Double.NaN;
        if (result != result) {
            return result;
        }
        return null;
    }

    public static void main(String[] args) {
        String[] a = new String[]{"a,b,c"};
        Arrays.stream(a).collect(Collectors.toList());
    }
}

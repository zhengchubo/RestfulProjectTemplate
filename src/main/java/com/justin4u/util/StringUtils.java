package com.justin4u.util;

import com.justin4u.constant.JsInjectionEnum;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Justin Wu
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static String randomPositiveInt() {
        return String.valueOf(IntegerUtils.randomPositiveInt());
    }

    public static String randomSixNumeric() {
        return RandomStringUtils.randomNumeric(6);
    }

    public static final boolean isDecimal(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public static final boolean isPositiveDecimal(String s, boolean includeZero) {
        try {
            double v = Double.parseDouble(s);
            if (includeZero) {
                return v >= 0;
            }
            return v > 0;
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
    }

    public static final boolean isValidIdString(String ids) {
        if (null == ids) {
            return false;
        }
        Pattern p =  Pattern.compile("^(\\d)+(,\\d+)*$");
        Matcher m = p.matcher(ids);
        return m.find();
    }

    /**
     * 判断参数是否包含js代码注入
     * @param str
     * @return
     */
    public static boolean isJsInjection(String str) {
        boolean result = false;

        if (StringUtils.isEmpty(str)) {
            return false;
        }

        // 正则匹配，对on*****进行匹配
        Pattern p = Pattern.compile("\\bon[a-z]{1,20}?\\s*=", Pattern.CASE_INSENSITIVE);
        if (p.matcher(str).find()) {
            return true;
        }

        // 校验参数是否含有js枚举中的标签
        JsInjectionEnum[] JsInjectionEnumArr = JsInjectionEnum.class.getEnumConstants();
        for (JsInjectionEnum JsInjectionEnum : JsInjectionEnumArr) {
            if (StringUtils.containsIgnoreCase(str, JsInjectionEnum.getContent())) {
                return true;
            }
        }

        return result;
    }

    public static String hexString2Base64String(String hexStr) throws DecoderException {
        char[] charArray = hexStr.toCharArray();
        byte[] decodedHex = Hex.decodeHex(charArray);
        return Base64.encodeBase64String(decodedHex);
    }

    public static String base64String2HexString(String base64Str) {
        byte[] bytes = Base64.decodeBase64(base64Str);
        return Hex.encodeHexString(bytes);
    }


}

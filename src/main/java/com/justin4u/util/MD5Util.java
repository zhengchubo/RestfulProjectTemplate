package com.justin4u.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-05-10</pre>
 */
public class MD5Util {

    public static String encrypt(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes());
            byte[] bytes = md.digest();
            return DatatypeConverter.printHexBinary(bytes).toLowerCase();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encrypt(byte[] data) {
        if (null == data) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data);
            byte[] bytes = md.digest();
            return DatatypeConverter.printHexBinary(bytes).toLowerCase();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encrypt(Serializable data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] dataBytes = SerializationUtils.serialize(data);
            md.update(dataBytes);
            byte[] bytes = md.digest();
            return DatatypeConverter.printHexBinary(bytes).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        JSONObject json = new JSONObject();
        json.put("首联", "一叶扁舟伴水流");
        System.out.println("明文是：" + json);
        String result = encrypt(json.toString());
        System.out.println(result);
        byte[] dataBytes = SerializationUtils.serialize(json.toString());
        System.out.println(DigestUtils.md5Hex(dataBytes));
    }
}

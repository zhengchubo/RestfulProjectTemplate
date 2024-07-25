package com.justin4u.util;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * com.justin4u.util
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-01-18</pre>
 */
public class AESUtils {
    private static byte[] keyBytes = {0x31, 0x32, 0x33, 0x34, 0x35, 0x50, 0x37, 0x38, 0x39, 0x40, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46};

    public static String encrypt(String value) {
        String s = null;
        int mode = Cipher.ENCRYPT_MODE;
        try {
            Cipher cipher = initCipher(mode);
            byte[] outBytes = cipher.doFinal(value.getBytes());
            s = String.valueOf(Hex.encodeHex(outBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public static byte[] encrypt(byte[] bytes) {
        String str = new String(bytes, StandardCharsets.UTF_8);
        String encryptedStr = encrypt(str);
        if (encryptedStr == null) {
            return null;
        }
        return encryptedStr.getBytes();
    }

    public static String decrypt(String value) {
        String s = null;
        int mode = Cipher.DECRYPT_MODE;
        try {
            Cipher cipher = initCipher(mode);
            byte[] outBytes = cipher.doFinal(Hex.decodeHex(value.toCharArray()));
            s = new String(outBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public static byte[] decrypt(byte[] bytes) {
        String v = decrypt(new String(bytes, StandardCharsets.UTF_8));
        if (v == null) {
            return null;
        }
        return v.getBytes(StandardCharsets.UTF_8);
    }

    private static Cipher initCipher(int mode) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        Key key = new SecretKeySpec(keyBytes, "AES");
        cipher.init(mode, key);
        return cipher;
    }

    public static void main(String[] args) {
    }
}

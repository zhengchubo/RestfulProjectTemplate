package com.justin4u.kmc;

import com.justin4u.util.AESUtils;
import com.justin4u.util.MD5Util;
import com.justin4u.util.RSAUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.LocalDate;
import java.util.Objects;

/**
 * com.justin4u.kmc
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-06-11</pre>
 */
public class KeyManagerCenter {
    private static final int KEY_TYPE_BYTE_LENGTH = 4;

    // 预留位长为一
    private static final int RESERVE_BYTE_LENGTH = 1;
    // 非对称加密类型占位长度为一
    private static final int ASYMMETRIC_TYPE_BYTE_LENGTH = 1;
    // 对称加密类型占位长度为一
    private static final int SYMMETRIC_TYPE_BYTE_LENGTH = 1;
    // 校验类型占位长度为一
    private static final int CHECKSUM_TYPE_BYTE_LENGTH = 1;
    // 校验码占位长度为32
    private static final int CHECKSUM_BYTE_LENGTH = 32;

    private static int HEAD_BYTE_LENGTH;

    private static int MIN_BYTE_LENGTH;

    static {
        HEAD_BYTE_LENGTH = KEY_TYPE_BYTE_LENGTH + CHECKSUM_TYPE_BYTE_LENGTH + RESERVE_BYTE_LENGTH;
        MIN_BYTE_LENGTH = HEAD_BYTE_LENGTH + CHECKSUM_BYTE_LENGTH;
    }

    // TMP
    private static PrivateKey privateKey;

    public static final byte[] int2bytes(int i) {
        return ByteBuffer.allocate(4).putInt(i).array();
    }

    public static final byte[] int2byte(int i) {
        if (i < 0 || i > Byte.MAX_VALUE) {
            throw new RuntimeException("The integer value must be in range from 0 to 127");
        }
        byte[] bytes = int2bytes(i);
        byte[] result = new byte[1];
        result[0] = bytes[3];
        return result;
    }

    public static final int bytes2int(byte[] bytes) {
        return new BigInteger(bytes).intValue();
    }

    public static final String int2String(int i) {
        return new String(int2bytes(i), StandardCharsets.UTF_8);
    }


    public static final KeyTypeEnum getKeyType(InputStream is) {
        if (null == is) {
            return null;
        }
        byte[] bytes;
        try {
            bytes = IOUtils.toByteArray(is, KEY_TYPE_BYTE_LENGTH);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        if (null == bytes || bytes.length <= KEY_TYPE_BYTE_LENGTH) {
            return null;
        }
        int code = bytes2int(bytes);
        KeyTypeEnum typeEnum = KeyTypeEnum.getEnumByCode(code);
        return typeEnum;
    }

    public static final String getCheckSum(InputStream is) {
        if (null == is) {
            return null;
        }
        byte[] bytes;
        try {
            bytes = IOUtils.toByteArray(is);
        } catch (IOException e) {
            return null;
        }

        int len = bytes.length;
        if (len <= MIN_BYTE_LENGTH) {
            return null;
        }
        byte[] tailBytes = new byte[CHECKSUM_BYTE_LENGTH];
        System.arraycopy(bytes, len - CHECKSUM_BYTE_LENGTH, tailBytes, 0, CHECKSUM_BYTE_LENGTH);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static final byte[] getBody(InputStream is) {
        if (null == is) {
            return null;
        }
        byte[] bytes;
        try {
            bytes = IOUtils.toByteArray(is);
        } catch (IOException e) {
            return null;
        }
        int len = bytes.length;
        if (len <= MIN_BYTE_LENGTH) {
            return null;
        }

        int bodyLength = len - HEAD_BYTE_LENGTH - CHECKSUM_BYTE_LENGTH;
        byte[] bodyBytes = new byte[bodyLength];
        System.arraycopy(bytes, len - HEAD_BYTE_LENGTH, bodyBytes, 0, bodyLength);
        return bodyBytes;
//        return SerializationUtils.deserialize(bodyBytes);
    }

    public static final boolean checkIntegrity(InputStream is) {
        String checksum = getCheckSum(is);
        if (null == checksum) {
            return false;
        }
        byte[] bodyBytes = getBody(is);
        String realChecksum = MD5Util.encrypt(bodyBytes);
        return Objects.equals(realChecksum, checksum);
    }

    private static final String generateToken() {
        String ts = LocalDate.now().toString();
        String md5Str = MD5Util.encrypt(ts);
        try {
            KeyPair kp = RSAUtils.buildKeyPair();
            return RSAUtils.encrypt(kp.getPublic(), md5Str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final String forward(String rawText) throws Exception {
//        System.out.println(rawText.length());
//        String reserveText = AESUtils.encrypt(rawText);
//        System.out.println(reserveText.length());
//        rawText = reserveText;


        long start = System.nanoTime();
        KeyPair keyPair = RSAUtils.buildKeyPair();
        long end = System.nanoTime();
        System.out.println("generate " + (end - start)/1000000 + "ms");
        PublicKey publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();
        long startRsa = System.nanoTime();
        String strA = RSAUtils.encryptLong(publicKey, rawText);
        long endRsa = System.nanoTime();
        System.out.println("rsa " + (endRsa - startRsa) / 1000000 + "ms");
        byte[] bytesA = strA.getBytes(StandardCharsets.UTF_8);
        byte[] bytesB = new byte[bytesA.length + RESERVE_BYTE_LENGTH + ASYMMETRIC_TYPE_BYTE_LENGTH];
        byte[] bytesBHead = int2byte(KeyTypeEnum.RSA.getCode());

        long startCopy = System.nanoTime();
        System.arraycopy(bytesBHead, 0, bytesB, 0, 1);
        System.arraycopy(bytesA, 0, bytesB, RESERVE_BYTE_LENGTH + ASYMMETRIC_TYPE_BYTE_LENGTH, bytesA.length);
        long endCopy = System.nanoTime();
        System.out.println("rsa copy" + (endCopy - startCopy) / 1000000 + "ms");

        long startAes = System.nanoTime();
        byte[] encryptedBytesB = AESUtils.encrypt(bytesB);
        long endAes = System.nanoTime();
        System.out.println("aes " + (endAes - startAes) / 1000000 + "ms");

        byte[] bytesC = new byte[encryptedBytesB.length + SYMMETRIC_TYPE_BYTE_LENGTH];
        byte[] bytesCHead = int2byte(KeyTypeEnum.AES.getCode());

        startCopy = System.nanoTime();
        System.arraycopy(bytesCHead, 0, bytesC, 0, SYMMETRIC_TYPE_BYTE_LENGTH);
        System.arraycopy(encryptedBytesB, 0, bytesC, SYMMETRIC_TYPE_BYTE_LENGTH, encryptedBytesB.length);
        endCopy = System.nanoTime();
        System.out.println("aes copy " + (endCopy - startCopy) / 1000000 + "ms");

        long startMd5 = System.nanoTime();
        String digestStr = MD5Util.encrypt(bytesC);
        long endMd5 = System.nanoTime();
        System.out.println("md5 " + (endMd5 - startMd5) / 1000000 + "ms");
        System.out.println("MD5-" + digestStr);
        byte[] bytesDHead = int2byte(KeyTypeEnum.MD5.getCode());
        byte[] bytesD = new byte[CHECKSUM_TYPE_BYTE_LENGTH + bytesC.length + CHECKSUM_BYTE_LENGTH];

        startCopy = System.nanoTime();
        System.arraycopy(bytesDHead, 0, bytesD, 0, CHECKSUM_TYPE_BYTE_LENGTH);
        System.arraycopy(bytesC, 0, bytesD, CHECKSUM_TYPE_BYTE_LENGTH, bytesC.length);
        System.arraycopy(digestStr.getBytes(StandardCharsets.UTF_8), 0, bytesD, CHECKSUM_TYPE_BYTE_LENGTH + bytesC.length, CHECKSUM_BYTE_LENGTH);
        endCopy = System.nanoTime();
        System.out.println("md5 copy " + (endCopy - startCopy) / 1000000 + "ms");

        String result = new String(bytesD, StandardCharsets.UTF_8);
        System.out.println(result);
        return result;
    }

    private static final String backward(String encrytedText) throws Exception {
        byte[] bytes = encrytedText.getBytes(StandardCharsets.UTF_8);
        if (bytes == null || bytes.length <= 36) {
            return null;
        }

        int bytesLength = bytes.length;
        byte[] digestTypeBytes = new byte[CHECKSUM_TYPE_BYTE_LENGTH];
        byte[] digestBytes = new byte[CHECKSUM_BYTE_LENGTH];
        byte[] bytesMiddle = new byte[bytesLength - CHECKSUM_TYPE_BYTE_LENGTH - CHECKSUM_BYTE_LENGTH];
        System.arraycopy(bytes, 0, digestTypeBytes, 0, CHECKSUM_TYPE_BYTE_LENGTH);
        System.arraycopy(bytes, CHECKSUM_TYPE_BYTE_LENGTH, bytesMiddle, 0, bytesMiddle.length);
        System.arraycopy(bytes, bytesLength - CHECKSUM_TYPE_BYTE_LENGTH - bytesMiddle.length, digestBytes, 0, 32);
        String hash = MD5Util.encrypt(bytesMiddle);
        System.out.println("Digest type is " + KeyTypeEnum.getEnumByCode(bytes2int(digestTypeBytes)).getName());
        System.out.println("Digest value is " + hash);

        byte[] symmetricTypeBytes = new byte[1];
        byte[] symmetricValueBytes = new byte[bytesMiddle.length - 1];
        System.arraycopy(bytesMiddle, 0, symmetricTypeBytes, 0, 1);
        System.arraycopy(bytesMiddle, 1, symmetricValueBytes, 0, symmetricValueBytes.length);
        System.out.println("symmetric type is " + KeyTypeEnum.getEnumByCode(bytes2int(symmetricTypeBytes)).getName());
        byte[] symmetricDecryptedValueBytes = AESUtils.decrypt(symmetricValueBytes);

        byte[] asymmetricTypeBytes = new byte[1];
        byte[] asymmetricValueBytes = new byte[symmetricDecryptedValueBytes.length - 2];
        System.arraycopy(symmetricDecryptedValueBytes, 0, asymmetricTypeBytes, 0, 1);
        System.arraycopy(symmetricDecryptedValueBytes, 2, asymmetricValueBytes, 0, asymmetricValueBytes.length);
        String decrypted = RSAUtils.decryptLong(privateKey, new String(asymmetricValueBytes, StandardCharsets.UTF_8));


        System.out.println("encrypt type is " + KeyTypeEnum.getEnumByCode(bytes2int(asymmetricTypeBytes)).getName());
//        return AESUtils.decrypt(decrypted);
        return decrypted;
    }




}

package com.justin4u.util;

import org.apache.commons.lang3.ArrayUtils;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Objects;

/**
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-01-18</pre>
 */
public class RSAUtils {

    private static final int KEY_SIZE = 2048;

    private static final int DECRYPT_SEGMENT_SIZE = KEY_SIZE / 8;

    private static final int ENCRYPT_SEGMENT_SIZE = KEY_SIZE / 8 - 11;

    public static KeyPair buildKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(KEY_SIZE);
        return keyPairGenerator.genKeyPair();
    }

    public static String encryptLong(PublicKey publicKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//        StringBuffer sb = new StringBuffer();
        byte[] data = message.getBytes(StandardCharsets.UTF_8);
        byte[] dataReturn = new byte[]{};
        for (int i = 0; i < data.length; i += ENCRYPT_SEGMENT_SIZE) {
            byte[] subBytes = ArrayUtils.subarray(data, i, i + ENCRYPT_SEGMENT_SIZE);
            byte[] signed = cipher.doFinal(subBytes);
//            sb.append(new String(signed, StandardCharsets.UTF_8));
            dataReturn = ArrayUtils.addAll(dataReturn, signed);
        }
//        return sb.toString();
        String result = Base64.getEncoder().encodeToString(dataReturn);
        return result;
    }

    public static String encrypt(PublicKey publicKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] signed = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
        String result = Base64.getEncoder().encodeToString(signed);
        return result;
    }

    public static String decrypt(PrivateKey privateKey, String encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] signed = Base64.getDecoder().decode(encrypted);
        return new String(cipher.doFinal(signed), StandardCharsets.UTF_8);
    }

    public static String decryptLong(PrivateKey privateKey, String encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
//        StringBuffer sb = new StringBuffer();
        byte[] data = Base64.getDecoder().decode(encrypted);
        byte[] dataReturn = new byte[]{};
        for (int i = 0; i < data.length; i += DECRYPT_SEGMENT_SIZE) {
            byte[] subBytes = ArrayUtils.subarray(data, i, i + DECRYPT_SEGMENT_SIZE);
            byte[] signed = cipher.doFinal(subBytes);
//            sb.append(new String(signed, StandardCharsets.UTF_8));
            dataReturn = ArrayUtils.addAll(dataReturn, signed);
        }
//        return sb.toString();
        return new String(dataReturn, StandardCharsets.UTF_8);
    }

    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(key.getBytes(StandardCharsets.UTF_8));
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey result = keyFactory.generatePublic(spec);
        return result;
    }

    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    public static PublicKey getPublicKeyFromPrivateKey(PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        RSAPrivateCrtKey rsaPrivateKey = (RSAPrivateCrtKey) privateKey;
        PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(rsaPrivateKey.getModulus(), rsaPrivateKey.getPublicExponent()));
        return publicKey;
    }

    public static String getPublicKeyString(PublicKey pubKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory fact = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec spec = fact.getKeySpec(pubKey,
                X509EncodedKeySpec.class);
        String result = Base64.getEncoder().encodeToString(spec.getEncoded());
        return result;
    }

    public static String getPrivateKeyString(PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory fact = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec spec = fact.getKeySpec(privateKey, PKCS8EncodedKeySpec.class);
        String result = Base64.getEncoder().encodeToString(spec.getEncoded());
        return result;
    }

    public static void main(String[] args) throws Exception {
        // generate public and private keys
        KeyPair keyPair = buildKeyPair();
        PublicKey pubKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

//        pubKey = getPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtzkG79K42XE4sEzSMNzvpSKnaaJEdKmKH2fF+Zv/mAJ4+REJl34ngF6TVjraZrLrYVqne" +
//                "/RluYvl82itpatjNBegASzCOoEQONkycUHv/yQxvoa8ExZnjArbuSPv4aapIBG3PaGyY3a1TWDHgN/Hca0QxsC8SA7X9ozCWZdpGk4Md3+G" +
//                "+nzaW6CaTV06HXoCEaHOqUMxvZ2fRf5pBqdZwRoX95JSA7XUsqK1d8YVb4gsxD0ZvCHFxc4FvMJI3DrvYnFb1Y4Zj7LbyVdtaNL4RJck1XuylLJTAIAla7bg8y7klyXnIN76VVAsJAMhsKhyqnN6vnIamdIbTGQ2J7FTgwIDAQAB");
//        privateKey = getPrivateKey("LSRRBGoQrlhzzjjAUwdZa/Fph6JfY0uZWiHcQ/qSKxDgqQgL5atqOqm3M0FuOAXtfYY7+wXutYI3Z8Ei62wcJPDfpm/zni/pagbIxIim/+nsENmvu63UFChsnjiIesPv2UtK
// /0u6bXn5jBSO6WlUvSWsGozYQI/MzgUriHIskeZveLnS5xZk7ehwiaKfN9EDkoHlWZVwdk96dWIC5ISAhvWLplRkFVn+I1fkY6uw5cvvuSZ2sVuRngpXBNvAdIajpbBH2p27RBrNbYOhJdR6GkWv+vIkPOdkg76IRyO7OFYP
// /SCuOzAqsqF21dkSTGllidlpA+w25y0v08H5OChbmg==");
        String result = encryptLong(pubKey, "rdtest");
//        String result2 = encryptBak(pubKey, "admin1234");
        System.out.println(decryptLong(privateKey, result).equals("rdtest"));
//        System.out.println("admin1234".equals(decryptBak(privateKey, result2)));
        // verify the message
//        String result2 = decrypt(privateKey, result);
//        System.out.println(result2);

//        String rawText = FileUtils.readContentFromFilePath("/Users/justin/Downloads/a.sql");
        String rawText = "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";
        System.out.println(rawText.length());
        long start = System.nanoTime();
        String encrypted = encryptLong(pubKey, rawText);
        long middle = System.nanoTime();
//        System.out.println(encrypted.length());
        String decrypted = decryptLong(privateKey, encrypted);
        long end = System.nanoTime();
        System.out.println("en-" + (middle - start)/1000000 + "ms");
        System.out.println("de-" + (end - middle)/1000000 + "ms");
        System.out.println(Objects.equals(decrypted, rawText));
//        boolean result = decryptBak(privateKey, encryptBak(pubKey, rawText)).equals(rawText);
//                System.out.println(result);
    }
}

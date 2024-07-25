package com.justin4u.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.Security;

public class SM4Util {
    private static final String SECRET_KEY="43f33d4ef55b90854a056f0114267d71";
    private static final String ALGORITHM_NAME = "SM4";
    private static final String ALGORITHM_ECB_PKCS5PADDING = "SM4/ECB/PKCS5Padding";

    /**
     * SM4算法目前只支持128位（即密钥16字节）
     */
    private static final int DEFAULT_KEY_SIZE = 128;

    static {
        // 防止内存中出现多次BouncyCastleProvider的实例
        if (null == Security.getProvider(BouncyCastleProvider.PROVIDER_NAME)) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }


    /**
     * 生成密钥
     *
     * @return 密钥16位
     * @throws Exception 生成密钥异常
     */
    public static String generateKey() throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_NAME, BouncyCastleProvider.PROVIDER_NAME);
        kg.init(DEFAULT_KEY_SIZE, new SecureRandom());
        return Hex.toHexString(kg.generateKey().getEncoded());
    }

    /**
     * 加密，SM4-ECB 固定的key
     * 配置文件要加密的密码使用本方法加密
     * @param data 要加密的明文
     * @return 加密后的密文
     * @throws Exception 加密异常
     */
    public static String encrypt( String data){
        try{
            return Hex.toHexString(sm4(data.getBytes(), Hex.decode(SECRET_KEY),
                    ALGORITHM_ECB_PKCS5PADDING, null, Cipher.ENCRYPT_MODE));
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 加密，SM4-ECB-PKCS5Padding
     *
     * @param data 要加密的明文
     * @param key  密钥16字节，使用Sm4Util.generateKey()生成
     * @return 加密后的密文
     * @throws Exception 加密异常
     */
    public static String encryptByKey( String data,String key){
        try{
            return Hex.toHexString(sm4(data.getBytes(), Hex.decode(key),
                    ALGORITHM_ECB_PKCS5PADDING, null, Cipher.ENCRYPT_MODE));
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密，SM4-ECB-固定的key
     * 配置文件要解密的密码使用本方法解密
     * @param data 要解密的密文
     * @return 解密后的明文
     * @throws Exception 解密异常
     */
    public static String decrypt(String data) {
        try{
            return new String(sm4(Hex.decode(data), Hex.decode(SECRET_KEY),
                    ALGORITHM_ECB_PKCS5PADDING, null, Cipher.DECRYPT_MODE), StandardCharsets.UTF_8);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密，SM4-ECB-PKCS5Padding
     *
     * @param data 要解密的密文
     * @param key  密钥16字节，使用SM4Util.generateKey()生成
     * @return 解密后的明文
     * @throws Exception 解密异常
     */
    public static String decryptByKey(String data,String key)  {
        try{
            return new String(sm4(Hex.decode(data), Hex.decode(key),
                    ALGORITHM_ECB_PKCS5PADDING, null, Cipher.DECRYPT_MODE), StandardCharsets.UTF_8);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * SM4对称加解密
     *
     * @param input   明文（加密模式）或密文（解密模式）
     * @param key     密钥
     * @param sm4mode sm4加密模式
     * @param iv      初始向量(ECB模式下传NULL)
     * @param mode    Cipher.ENCRYPT_MODE - 加密；Cipher.DECRYPT_MODE - 解密
     * @return 密文（加密模式）或明文（解密模式）
     * @throws Exception 加解密异常
     */
    private static byte[] sm4(byte[] input, byte[] key, String sm4mode, byte[] iv, int mode)
            throws Exception {
        IvParameterSpec ivParameterSpec = null;
        if (null != iv) {
            ivParameterSpec = new IvParameterSpec(iv);
        }
        SecretKeySpec sm4Key = new SecretKeySpec(key, ALGORITHM_NAME);
        Cipher cipher = Cipher.getInstance(sm4mode, BouncyCastleProvider.PROVIDER_NAME);
        if (null == ivParameterSpec) {
            cipher.init(mode, sm4Key);
        } else {
            cipher.init(mode, sm4Key, ivParameterSpec);
        }
        return cipher.doFinal(input);
    }

    public static void main(String[] args){
        try {
            String txt = "HzN8m%cr!Vve";

            String hex = encrypt(txt);
            System.out.println(hex);

            System.out.println(decrypt(hex));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

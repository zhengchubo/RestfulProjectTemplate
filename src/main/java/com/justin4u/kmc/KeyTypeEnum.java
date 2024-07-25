package com.justin4u.kmc;

/**
 * The code of this Enum must be in range from 0 to 128.
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-05-10</pre>
 */
public enum KeyTypeEnum {
    BASE64(0, "BASE64"),

    MD5(11, "MD5"),
    SHA256(12, "SHA-256"),
    SHA512(13, "SHA-512"),
    HMAC(14, "HMAC"),

    DES(21, "DES"),
    AES(22, "AES"),
    DES3(23, "3DES"),
    RC4(24, "RC4"),
    RC5(25, "RC5"),
    RC6(26, "RC6"),
    IDEA(27, "IDEA"),

    RSA(31, "RSA"),
    DSA(32, "DSA"),
    ECC(33, "ECC"),;

    KeyTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    private int code;

    private String name;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final KeyTypeEnum getEnumByCode(int code) {
        for (KeyTypeEnum typeEnum : values()) {
            if (typeEnum.getCode() == code) {
                return typeEnum;
            }
        }
        return null;
    }
}

package com.justin4u.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
* @author sunyf
* @description {字符串压缩、解压缩工具类}
* @created 2018/12/20 16:05
* @param
**/
public class ZipStrUtil {

    static final java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();

    static final java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();

    /**
     * 字符串的压缩
     *
     * @param str
     *            待压缩的字符串
     * @return    返回压缩后的字符串
     * @throws IOException
     */
    public static String compress(String str) {
        if (null == str || str.length() <= 0) {
            return null;
        }

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             GZIPOutputStream gzip = new GZIPOutputStream(out)) {

            // 将 b.length 个字节写入此输出流
            gzip.write(str.getBytes("UTF-8"));
            gzip.close();
            // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
            return out.toString("ISO-8859-1");
        } catch (IOException e) {

        }
        return null;
    }

    /**
     * 字符串的解压
     *
     * @param str
     *            对字符串解压
     * @return    返回解压缩后的字符串
     * @throws IOException
     */
    public static String unCompress(String str) {
        if (null == str || str.length() <= 0) {
            return str;
        }

        try (ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
             GZIPInputStream gzip = new GZIPInputStream(in);
             ByteArrayOutputStream out = new ByteArrayOutputStream()){
            // 使用 buf 作为其缓冲区数组
            byte[] buffer = new byte[1024];
            int n = 0;
            while ((n = gzip.read(buffer)) >= 0) {// 将未压缩数据读入字节数组
                // 将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此 byte数组输出流
                out.write(buffer, 0, n);
            }
            // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
            return out.toString("UTF-8");
        } catch (IOException e) {

        }
        return null;
    }

    public static void main(String[] args) {

        String rawText = "H8KLCAAAAAAAAADClcKSX2vCgzAUw4XCv0rDsMOJC1LCosObw5jDhsOYQ1l9wpAVLVM7wrbCl8OgTMKoAcKbOBfDm8Kvwr/DuCc1FsO2wrA8w6TDnsO7OyHDijnCicOiNHzDi1AUZwnCqitCS8OyJQUlTcOLS0YEU8OIw6XDlBvCkMKuZy5oKSnDs1RbUEbCqMOyw6g3wpENE33CrcO4wqHDqmstw48DbsO5woHCi8KywpY/wqwfT8Kyw67CjkNXHGUnwpR3Kmp9Y8OZwrJCMcKiwrjDlsK6wobCmh7DkH7CvcONw4MUIcOXw7Edw4/CucK9fwxudMO1McK+w7PCg1XDusKpw7sAw7sPPg7CghHCr8OwwrTDvjvDvj3DqA/Dh8OJwrsLw6MOKMKJw5Emw59twqPCl3UWwqLDl8OwA8OlwrtNw59yworCnsONw79qwrfDgMOYZcORwonDgMOFQUszCC7CrlrCokFgwpzCtsK0wonCgDF/KcO1BMKmPMKWwooGcMKVw5DDlcKtwrMAc3bDizMjwoM5w5DCpTwyGFPCtsKkYQY7dkvCtCjDmMKvw4E6YsK/wpHCp19uNMKnw73CvAIAAA==";
        long start = System.nanoTime();
        String str = compress(rawText);
        long middle = System.nanoTime();
        String r = unCompress(str);
        long end = System.nanoTime();
        System.out.println("compress " + (middle - start)/1000000 + "ms");
        System.out.println("uncompress " + (end - middle)/1000000 + "ms");
        System.out.println(rawText.equals(r));
        System.out.println(str.length() + "-" + rawText.length());
    }

}
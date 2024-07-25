package com.justin4u.test;

import java.util.HashMap;
import java.util.Map;

/**
 * org.test
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-05-17</pre>
 */


public class StaticTest {
    public static void main(String[] args) throws Exception {
        /*try {
            staticFunction();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            System.out.println("finally");
        }*/

//        Map<String, Object> map = new HashMap<>();
//        Long l = (Long) map.get("foo");
//        System.out.println("--");
//        System.out.println(l);

//        System.out.println(Objects.equals("1", "1"));

        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("searchKey", "福建六建集团有限公司");
        dataMap.put("isExactlySame", true);
        dataMap.put("pageIndex", 1);
        dataMap.put("pageSize", 20);
        dataMap.put("province", "");
        dataMap.put("key", "736f2ffb18e011e6b4fb1051721d3b62");
        dataMap.put("dtype", "json");



    }

    static StaticTest st = new StaticTest();

    static {
        System.out.println("1");
    }

    {
        System.out.println("2");
    }

    StaticTest() {
        System.out.println("3");
        System.out.println("a=" + a + ",b=" + b);
    }

    public static void staticFunction() throws Exception {
        System.out.println("4");
        throw new Exception("test");
    }

    int a = 110;

    static int b = 112;
}
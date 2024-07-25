package com.justin4u.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * com.justin4u.test
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-04-23</pre>
 */
public class HashMapTest {
    public static void main(String[] args) {
//        Map<String, String> map = init();
//        entrySet(map);
//        keySet(map);
//        forEach(map);
        String a = IntStream.range(0, 100).mapToObj(i -> "*").collect(Collectors.joining(","));
        System.out.println(a);
    }

    public static Map<String, String> init() {
        Map<String, String> result = new HashMap();
        int i = 1000;
        while (i > 0) {
            result.put(String.valueOf(i), String.valueOf(i));
            i--;
        }
        return result;
    }

    public static void entrySet(Map<String, String> map) {
        long start = System.nanoTime();
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.println(entry.getKey() + "=>" + entry.getValue());
        }
        long end = System.nanoTime();
        System.err.println("entrySet" + (end - start) / 1000000 + "s");
    }

    public static void forEach(Map<String, String> map) {
        long start = System.nanoTime();
        map.forEach((k, v) -> System.out.println(k + "=>" + v));
        long end = System.nanoTime();
        System.err.println("forEach" + (end - start) / 1000000 + "s");
    }

    public static void keySet(Map<String, String> map) {
        long start = System.nanoTime();
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Object k = iterator.next();
            System.out.println(k + "=>" + map.get(k));
        }
        long end = System.nanoTime();
        System.err.println("keySet" + (end - start) / 1000000 + "s");
    }


}

package com.justin4u.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * com.justin4u.leetcode
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-07-23</pre>
 */
public class AddBinary {

    public static void main(String[] args) {
        AddBinary instance = new AddBinary();
        String str1 = "11";
        String str2 = "1";
        String str21 = "1010";
        String str22 = "1011";
        String result = instance.solution(str1, str2);
        String result2 = instance.solution(str21, str22);
        System.out.println(String.format("Result: %s+%s=%s %b", str1, str2, result, "100".equals(result)));
        System.out.println(String.format("Result2: %s+%s=%s %b", str21, str22, result2, "10101".equals(result2)));

        System.out.println('1'+'0');

        List<String> list = new ArrayList<>();
        list.add(null);
        list.get(0).toString();
    }

    public String solution(String str1, String str2) {
        StringBuffer sb = new StringBuffer();
        int carry = 0;
        int p1 = str1.length() - 1;
        int p2 = str2.length() - 1;
        while (p1 >= 0 && p2 >= 0) {

            return null;
        }
        return null;
    }
}

package com.justin4u.leetcode;

/**
 * com.justin4u.leetcode
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-09-15</pre>
 */
public class CountAndSay {

    public static void main(String[] args) {
        CountAndSay instance = new CountAndSay();
        String result = instance.solution(6);
        System.out.println(result);
    }

    public String solution(int n) {
        String str = "1";
        while (--n > 0) {
            int times = 1;
            StringBuffer sb = new StringBuffer();
            char[] chars = str.toCharArray();
            int len = chars.length;
            for (int i = 1; i < len; i++) {
                System.out.println("i:"+i);
                if (chars[i - 1] == chars[i]) {
                    times++;
                } else {
                    sb.append(times).append(chars[i - 1]);
                    times = 1;
                }
            }
            str = sb.append(times).append(chars[len - 1]).toString();
            System.out.println(sb.toString());
        }
        return str;
    }
}

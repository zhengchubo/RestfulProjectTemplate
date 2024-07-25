package com.justin4u.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * com.justin4u.leetcode
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2020-01-07</pre>
 */
public class Interval {
    int start;

    int end;

    public Interval() {
        start = 0;
        end = 0;
    }

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[%d,%d]", start, end);
    }

    protected static final List<Interval> transform(String str) {
        if (null == str) {
            return null;
        }
        List<Interval> result = new ArrayList<>();
        Arrays.stream(str.substring(2, str.length() - 2).split("\\],\\[")).forEach(item -> {
            if (item == null || item.length() < 1) {
                return;
            }
            String[] twoNumbers = item.split(",");
            if (twoNumbers == null || twoNumbers.length != 2) {
                return;
            }
            int start = Integer.parseInt(twoNumbers[0]);
            int end = Integer.parseInt(twoNumbers[1]);
            result.add(new Interval(start, end));
        });
        return result;
    }
}

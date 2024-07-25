package com.justin4u.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a TwoSum class. It should support the following operations: add and find.
 * <p>
 * add - Add the number to an internal data structure.
 * find - Find if there exists any pair of numbers which sum is equal to the value.
 * <p>
 * For example,
 * <p>
 * add(1); add(3); add(5);
 * find(4) -> true
 * find(7) -> false
 */
public class TwoSumInDataAdded {

    private Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    public static void main(String[] args) {
        TwoSumInDataAdded instance = new TwoSumInDataAdded();
        instance.add(1);
        instance.add(3);
        instance.add(5);
        System.out.println(instance.find(4));
        System.out.println(instance.find(7));
    }

    private boolean find(int i) {
        for (Integer n : map.keySet()) {
            int first = n;
            int second = i - n;
            if ((first == second && map.get(n) > 1) || (first != second && map.containsKey(second))) {
                return true;
            }
        }
        return false;
    }

    private void add(int i) {
        int count = 0;
        if (map.containsKey(i)) {
            count = map.get(i);
        }
        map.put(i, count);
    }


}

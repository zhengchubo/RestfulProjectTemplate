package com.justin4u.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * com.justin4u.leetcode
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-10-17</pre>
 *
 * Given an array of strings, group anagrams together.
 *
 * Example:
 *
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 *
 * Note:
 *
 *     All inputs will be in lowercase.
 *     The order of your output does not matter.
 */
public class GroupAnagrams {

    public static void main(String[] args) {
        GroupAnagrams instance = new GroupAnagrams();
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = instance.solution(strs);
        System.out.println(result);
    }

    public List<List<String>> solution(String[] strs) {
        if (null == strs) {
            return null;
        }
        Map<String, List<String>> sortedStrsMap = new HashMap<>();
        List<List<String>> result = new ArrayList<>();
        Arrays.stream(strs).forEach(item -> {
            char[] chars = item.toCharArray();
            Arrays.sort(chars);
            String sortedString = String.valueOf(chars);
            if (!sortedStrsMap.containsKey(sortedString)) {
                List<String> values = new ArrayList<>();
                values.add(item);
                sortedStrsMap.put(sortedString, values);
            } else {
                sortedStrsMap.get(sortedString).add(item);
            }
        });
        sortedStrsMap.forEach((k, v) -> {
            result.add(v);
        });
        return result;
    }
}

package com.justin4u.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could
 * represent.
 * <p>
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any
 * letters.
 * <p>
 * <p>
 * <p>
 * Example:
 * <p>
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 * <p>
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 */
public class LetterCombinationsByPhoneNumber {

    private Map<Character, String[]> map = new HashMap<Character, String[]>() {{
        put('2', new String[]{"a", "b", "c"});
        put('3', new String[]{"d", "e", "f"});
        put('4', new String[]{"g", "h", "i"});
        put('5', new String[]{"j", "k", "l"});
        put('6', new String[]{"m", "n", "o"});
        put('7', new String[]{"p", "q", "r", "s"});
        put('8', new String[]{"t", "u", "v"});
        put('9', new String[]{"w", "x", "y", "z"});
    }};

    public static void main(String[] args) throws Exception {
        LetterCombinationsByPhoneNumber instance = new LetterCombinationsByPhoneNumber();
        System.out.println(Arrays.toString(instance.solution("23")));
    }

    public String[] solution(String n) throws Exception {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < n.length(); i++) {
            List<String> tmp = new ArrayList<>();
            Character c = n.charAt(i);
            String[] strings = map.get(c);
            if (strings == null) {
                throw new Exception("Invalid digital number, only digits from 2 to 9 are allowed.");
            }
            if (result.size() == 0) {
                for (int j = 0; j < strings.length; j++) {
                    String str = strings[j];
                    tmp.add(str);
                }
            } else {
                for (String item : result) {
                    for (int j = 0; j < strings.length; j++) {
                        String str = strings[j];
                        tmp.add(item + str);
                    }
                }
            }
            result = tmp;
        }
        return result.toArray(new String[result.size()]);
    }

}

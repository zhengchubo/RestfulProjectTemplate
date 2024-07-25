package com.justin4u.leetcode;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * <p>
 * And then read line by line: "PAHNAPLSIIGYIR"
 * <p>
 * Write the code that will take a string and make this conversion given a number of rows:
 * <p>
 * string convert(string text, int nRows);
 * <p>
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 */
public class ZigzagConversion {
    public static void main(String[] args) {
        ZigzagConversion conversion = new ZigzagConversion();
        String str = "PAYPALISHIRING";
        String result = conversion.convert(str, 3);
        String result2 = conversion.convertByIndex(str, 3);
        System.out.println(result);
        System.out.println(result2);
    }

    private String convert(String str, int line) {
        int len = str.length();
        int base = 2 * line - 2;
        int i = 0;

        if (line == 1) {
            System.out.println(str);
        }

        String result = "";
        for (int j = 0; j < line; j++) {
            i = j;
            if (j == 0 || j == line - 1) {
                while (i < len) {
                    result += str.charAt(i);
                    i += base;
                }
            } else {
                while (i < len) {
                    result += str.charAt(i);
                    i += base - 2 * j;
                }
            }
        }

        return result;
    }

    private String convertByIndex(String str, int line) {
        if (str.length() <= 1 || str.length() == line) {
            return str;
        }

        String[] lines = new String[line];
        int row = 0;
        int step = 0;
        for (int i = 0; i < str.length(); i++) {
            if (row == 0) {
                step = 1;
            }
            if (row == line - 1 ) {
                step = -1;
            }
            lines[row] = lines[row] == null ? "" : lines[row];
            lines[row] += str.charAt(i);
            row += step;
        }

        String result = "";
        for (String l : lines) {
            result += l;
        }
        return result;
    }
}

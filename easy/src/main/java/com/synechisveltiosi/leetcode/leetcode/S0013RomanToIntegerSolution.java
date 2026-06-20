package com.synechisveltiosi.leetcode.leetcode;

import java.util.Map;

public class S0013RomanToIntegerSolution {
    Map<String, Integer> values =
            Map.of("M", 1000, "D", 500, "C", 100,
                    "L", 50, "X", 10, "V", 5, "I", 1);
    Map<Character, Integer> map = Map.of(
            'I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100,
            'D', 500, 'M', 1000
    );

    public int romanToInt1(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum += values.get(String.valueOf(s.charAt(i)));
            if (i < s.length() - 1 && values.get(String.valueOf(s.charAt(i))) < values.get(String.valueOf(s.charAt(i + 1)))) {
                sum -= 2 * values.get(String.valueOf(s.charAt(i)));
            }
        }
        return sum;
    }

    public int romanToInt2(String s) {
        String lastSymbol = s.substring(s.length() - 1);
        int lastVal = values.get(lastSymbol);
        int total = lastVal;
        for (int i = s.length() - 2; i >= 0; i--) {
            String currSymbol = s.substring(i, i + 1);
            int currVal = values.get(currSymbol);
            if (currVal < lastVal)
                total -= currVal;
            else
                total += currVal;
            lastVal = currVal;
        }
        return total;
    }

    public int romanToInt3(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            int curr = map.get(s.charAt(i));
            if (i < s.length() - 1 && map.get(s.charAt(i + 1)) > curr) {
                result -= curr;
            } else {
                result += curr;
            }
        }
        return result;
    }
}

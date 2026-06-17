package com.synechisveltiosi.leetcode;

public class S0008StringToIntegerAtoiSolution {

    public int myAtoi(String s) {
        int index = 0;
        int length = s.length();

        // Skip leading whitespace
        while (index < length && s.charAt(index) == ' ') {
            index++;
        }

        int sign = 1;
        // Determine sign
        if (index < length) {
            char currentChar = s.charAt(index);
            if (currentChar == '-') {
                sign = -1;
                index++;
            } else if (currentChar == '+') {
                index++;
            }
        }

        int result = 0;
        // Build integer while digits are found
        while (index < length && Character.isDigit(s.charAt(index))) {
            int digit = s.charAt(index) - '0';
            // Check for overflow before multiplication
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return sign > 0
                        ? Integer.MAX_VALUE
                        : Integer.MIN_VALUE;
            }
            result = result * 10 + digit;
            index++;
        }

        return sign * result;
    }
}

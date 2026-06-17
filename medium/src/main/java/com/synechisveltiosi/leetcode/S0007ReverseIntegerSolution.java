package com.synechisveltiosi.leetcode;

public class S0007ReverseIntegerSolution {
    public int reverse(int x) {
        int reverse = 0;
        while (x != 0) {
            int lastDigit = x % 10;
            if (reverse > Integer.MAX_VALUE / 10 || reverse < Integer.MIN_VALUE / 10)
                return 0;
            reverse = reverse * 10 + lastDigit;
            x = x / 10;
        }

        return reverse;
    }
}

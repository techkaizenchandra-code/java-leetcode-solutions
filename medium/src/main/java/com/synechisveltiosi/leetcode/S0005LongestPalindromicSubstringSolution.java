package com.synechisveltiosi.leetcode;

public class S0005LongestPalindromicSubstringSolution {
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        int start = 0;
        int end = 0;
        for (int center = 0; center < s.length(); center++) {
            int l1 = expandAroundCenter(s.toCharArray(), center, center);
            int l2 = expandAroundCenter(s.toCharArray(), center, center + 1);
            int l = Math.max(l1, l2);
            if (l > end - start + 1) {
                start = center - (l - 1) / 2;
                end = center + l / 2;
            }
        }
        return s.substring(start, end + 1);

    }

    private int expandAroundCenter(char[] charArray, int left, int right) {
        while (left >= 0 && right < charArray.length && charArray[left] == charArray[right]) {
            left--;
            right++;
        }
        return right - left - 1;
    }

}

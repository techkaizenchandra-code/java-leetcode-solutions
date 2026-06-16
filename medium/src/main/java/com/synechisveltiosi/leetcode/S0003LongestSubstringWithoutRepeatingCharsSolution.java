package com.synechisveltiosi.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class S0003LongestSubstringWithoutRepeatingCharsSolution {

    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        int left = 0;
        int right = 0;
        int n = s.length();
        boolean[] seen = new boolean[128];
        while (right < n) {
            char ch = s.charAt(right);
            if (seen[ch]) {
                seen[s.charAt(left++)] = false;
            } else {
                seen[ch] = true;
                maxLen = Math.max(maxLen, right - left + 1);
                right++;
            }
        }
        return maxLen;
    }

    public int lengthOfLongestSubstring2(String s) {
        int maxLen = 0;
        int left = 0;
        int right = 0;
        int n = s.length();
        Set<Character> seen = new HashSet<>();
        while (right < n) {
            char ch = s.charAt(right);
            if (seen.contains(ch)) {
                seen.remove(s.charAt(left++));
            } else {
                seen.add(ch);
                maxLen = Math.max(maxLen, right - left + 1);
                right++;
            }
        }
        return maxLen;
    }

    public int lengthOfLongestSubstring3(String s) {
        Map<Character, Integer> lastSeenIndexByChar = new HashMap<>();
        int windowStart = 0;
        int maxLen = 0;
        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            char currentChar = s.charAt(windowEnd);
            Integer prevIndex = lastSeenIndexByChar.put(currentChar, windowEnd);
            if (prevIndex != null && prevIndex >= windowStart) {
                windowStart = prevIndex + 1;
            }
            maxLen = Math.max(maxLen, windowEnd - windowStart + 1);
        }
        return maxLen;
    }
}

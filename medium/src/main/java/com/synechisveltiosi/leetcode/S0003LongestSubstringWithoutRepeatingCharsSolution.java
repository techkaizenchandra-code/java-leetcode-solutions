package com.synechisveltiosi.leetcode;

import java.util.*;

public class S0003LongestSubstringWithoutRepeatingCharsSolution {
    private S0003LongestSubstringWithoutRepeatingCharsSolution() {
        /* This utility class should not be instantiated */
    }


    static class IntegerArraySolution {
        public int lengthOfLongestSubstring(String s) {
            int[] lastSeenIndex = new int[128];
            Arrays.fill(lastSeenIndex, -1);
            int windowStart = 0;
            int longestLength = 0;

            for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
                char currentChar = s.charAt(windowEnd);
                // Duplicate character found inside current window

                if (lastSeenIndex[currentChar] >= windowStart) {
                    windowStart = lastSeenIndex[currentChar] + 1;
                }

                lastSeenIndex[currentChar] = windowEnd;
                int currentWindowLength = windowEnd - windowStart + 1;
                longestLength = Math.max(longestLength, currentWindowLength);
            }
            return longestLength;
        }
    }

    static class BooleanArraySolution {
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
    }

    static class SetSolution {
        public int lengthOfLongestSubstring(String s) {
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
    }

    static class MapSolution {
        public int lengthOfLongestSubstring(String s) {
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
}

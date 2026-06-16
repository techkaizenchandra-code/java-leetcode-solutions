package com.synechisveltiosi.leetcode;

public class S0005LongestPalindromicSubstringSolution {

    static class BruteForceCheckAllSubstring {
        public String longestPalindrome(String s) {
            if (s == null || s.isEmpty()) {
                return "";
            }

            int textLength = s.length();

            // Try longer substrings first. The first palindrome we find is guaranteed
            // to be the longest because substring length decreases after each pass.
            for (int length = textLength; length > 0; length--) {
                for (int start = 0; start <= textLength - length; start++) {
                    int endExclusive = start + length;
                    if (isPalindrome(s, start, endExclusive)) {
                        return s.substring(start, endExclusive);
                    }
                }
            }

            return "";
        }

        private boolean isPalindrome(String s, int start, int endExclusive) {
            int left = start;
            int right = endExclusive - 1;

            // Compare characters from both ends and move toward the middle.
            // Any mismatch means this substring cannot be a palindrome.
            while (left < right) {
                if (s.charAt(left++) != s.charAt(right--)) {
                    return false;
                }
            }
            return true;
        }
    }


    static class ExpandAroundCenterSolution {
        public String longestPalindrome(String s) {
            if (s == null || s.isEmpty()) {
                return "";
            }

            int bestStart = 0;
            int bestEndInclusive = 0;

            // Every palindrome has a center. For each index, check both possible centers:
            // odd length like "racecar" and even length like "abba".
            for (int center = 0; center < s.length(); center++) {
                int oddPalindromeLength = expandAroundCenter(s, center, center);
                int evenPalindromeLength = expandAroundCenter(s, center, center + 1);
                int currentLength = Math.max(oddPalindromeLength, evenPalindromeLength);

                // Convert the palindrome length around this center into start/end indexes.
                if (currentLength > bestEndInclusive - bestStart + 1) {
                    bestStart = center - (currentLength - 1) / 2;
                    bestEndInclusive = center + currentLength / 2;
                }
            }

            return s.substring(bestStart, bestEndInclusive + 1);
        }

        private int expandAroundCenter(String s, int left, int right) {
            // Keep expanding while both sides are inside the string and still equal.
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }

            // After the loop, left and right are one step outside the palindrome.
            return right - left - 1;
        }
    }

    static class DynamicProgrammingSolution {
        public String longestPalindrome(String s) {
            if (s == null || s.isEmpty()) {
                return "";
            }

            int textLength = s.length();
            boolean[][] isPalindrome = new boolean[textLength][textLength];
            int bestStart = 0;
            int bestEndInclusive = 0;

            // A single character is always a palindrome.
            for (int index = 0; index < textLength; index++) {
                isPalindrome[index][index] = true;
            }

            // Handle two-character palindromes before using the general formula.
            for (int start = 0; start < textLength - 1; start++) {
                int end = start + 1;
                if (s.charAt(start) == s.charAt(end)) {
                    isPalindrome[start][end] = true;
                    bestStart = start;
                    bestEndInclusive = end;
                }
            }

            // For length >= 3, s[start..end] is a palindrome when:
            // 1. first and last characters match, and
            // 2. the inside substring s[start + 1..end - 1] is already a palindrome.
            for (int length = 3; length <= textLength; length++) {
                for (int start = 0; start <= textLength - length; start++) {
                    int end = start + length - 1;
                    if (s.charAt(start) == s.charAt(end) && isPalindrome[start + 1][end - 1]) {
                        isPalindrome[start][end] = true;
                        bestStart = start;
                        bestEndInclusive = end;
                    }
                }
            }

            return s.substring(bestStart, bestEndInclusive + 1);
        }
    }

    static class ManachersAlgorithmSolution {
        public String longestPalindrome(String text) {
            if (text == null || text.isEmpty()) {
                return "";
            }

            // Insert separators so odd and even length palindromes are handled the same way.
            // Example: "abba" becomes "#a#b#b#a#".
            String transformed = preprocess(text);
            int length = transformed.length();

            // palindromeRadius[i] stores how far the palindrome expands from index i
            // in the transformed string.
            int[] palindromeRadius = new int[length];

            // center and rightBoundary describe the palindrome that currently reaches
            // farthest to the right.
            int center = 0;
            int rightBoundary = 0;

            for (int current = 0; current < length; current++) {

                // mirror is the matching position of current on the left side of center.
                // We reuse its known radius when current is inside rightBoundary.
                int mirror = 2 * center - current;

                if (current < rightBoundary) {
                    palindromeRadius[current] = Math.min(
                            rightBoundary - current,
                            palindromeRadius[mirror]
                    );
                }

                // Try to expand beyond the reused radius.
                expandPalindrome(transformed, palindromeRadius, current);

                // If this palindrome goes farther right, make it the new reference window.
                if (current + palindromeRadius[current] > rightBoundary) {
                    center = current;
                    rightBoundary = current + palindromeRadius[current];
                }
            }

            // Find the center with the largest radius.
            int longestRadius = 0;
            int longestCenter = 0;

            for (int i = 0; i < length; i++) {
                if (palindromeRadius[i] > longestRadius) {
                    longestRadius = palindromeRadius[i];
                    longestCenter = i;
                }
            }

            // Convert the transformed-string center/radius back to an index in the original text.
            int startIndex = (longestCenter - longestRadius) / 2;

            return text.substring(startIndex, startIndex + longestRadius);
        }

        private void expandPalindrome(
                String transformed,
                int[] palindromeRadius,
                int center
        ) {
            // Grow the radius while both next outer characters match.
            while (
                    center + palindromeRadius[center] + 1 < transformed.length()
                            && center - palindromeRadius[center] - 1 >= 0
                            && transformed.charAt(center + palindromeRadius[center] + 1)
                            == transformed.charAt(center - palindromeRadius[center] - 1)
            ) {
                palindromeRadius[center]++;
            }
        }

        private String preprocess(String text) {
            StringBuilder transformed = new StringBuilder(text.length() * 2 + 1);

            // Separators remove the need for separate odd/even palindrome logic.
            transformed.append('#');

            for (char ch : text.toCharArray()) {
                transformed.append(ch).append('#');
            }

            return transformed.toString();
        }
    }
}

package com.synechisveltiosi.leetcode;

public class S0010RegularExpressionMatchingSolution {
    private S0010RegularExpressionMatchingSolution() {
        /* This utility class should not be instantiated */
    }

    static class BottomUpDynamicProgrammingSolution {
        public boolean isMatch(String text, String pattern) {
            int textLength = text.length();
            int patternLength = pattern.length();
            boolean[][] dp = new boolean[textLength + 1][patternLength + 1];
            dp[textLength][patternLength] = true;

            for (int textIndex = textLength; textIndex >= 0; textIndex--) {
                for (int patternIndex = patternLength - 1; patternIndex >= 0; patternIndex--) {
                    boolean currentMatch =
                            textIndex < textLength
                                    && (pattern.charAt(patternIndex) == '.'
                                    || text.charAt(textIndex) == pattern.charAt(patternIndex));

                    if (hasStar(pattern, patternIndex, patternLength)) {
                        processStarCase(dp, textIndex, patternIndex, currentMatch);
                    } else {
                        dp[textIndex][patternIndex] = currentMatch && dp[textIndex + 1][patternIndex + 1];
                    }
                }
            }
            return dp[0][0];

        }

        private boolean hasStar(String pattern, int patternIndex, int patternLength) {
            return patternIndex + 1 < patternLength
                    && pattern.charAt(patternIndex + 1) == '*';

        }

        private void processStarCase(
                boolean[][] dp,
                int textIndex,
                int patternIndex,
                boolean currentMatch) {

            dp[textIndex][patternIndex] = dp[textIndex][patternIndex + 2];
            if (currentMatch) {
                dp[textIndex][patternIndex] |= dp[textIndex + 1][patternIndex];
            }

        }
    }
}

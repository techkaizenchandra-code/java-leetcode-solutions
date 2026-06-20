package com.synechisveltiosi.leetcode;

public class S0010RegularExpressionMatchingSolution {
    private S0010RegularExpressionMatchingSolution() {
        /* This utility class should not be instantiated */
    }

    static class BottomUpDynamicProgrammingSolution {
        public boolean isMatch(String text, String pattern) {
            int textLength = text.length();
            int patternLength = pattern.length();

            // dp[textIndex][patternIndex] means:
            // does text[textIndex...] match pattern[patternIndex...]?
            boolean[][] dp = new boolean[textLength + 1][patternLength + 1];

            // Empty text matches an empty pattern.
            dp[textLength][patternLength] = true;

            // Fill the table from the end because each state depends on
            // states to its right and/or below.
            for (int textIndex = textLength; textIndex >= 0; textIndex--) {
                for (int patternIndex = patternLength - 1; patternIndex >= 0; patternIndex--) {
                    // Current characters match when pattern has '.' or both
                    // characters are equal. textIndex must still be in range.
                    boolean currentMatch =
                            textIndex < textLength
                                    && (pattern.charAt(patternIndex) == '.'
                                    || text.charAt(textIndex) == pattern.charAt(patternIndex));

                    if (hasStar(pattern, patternIndex, patternLength)) {
                        // Handle "x*" as either zero occurrences or one more
                        // matching occurrence of x.
                        processStarCase(dp, textIndex, patternIndex, currentMatch);
                    } else {
                        // Without '*', both current characters must match, then
                        // the remaining suffixes must also match.
                        dp[textIndex][patternIndex] = currentMatch && dp[textIndex + 1][patternIndex + 1];
                    }
                }
            }

            // Full text matches full pattern if the state from both starts is true.
            return dp[0][0];

        }

        private boolean hasStar(String pattern, int patternIndex, int patternLength) {
            // A star belongs to the current pattern character only when it is
            // the next character, for example "a*".
            return patternIndex + 1 < patternLength
                    && pattern.charAt(patternIndex + 1) == '*';

        }

        private void processStarCase(
                boolean[][] dp,
                int textIndex,
                int patternIndex,
                boolean currentMatch) {

            // Zero occurrences: skip the current character and its '*'.
            dp[textIndex][patternIndex] = dp[textIndex][patternIndex + 2];
            if (currentMatch) {
                // One or more occurrences: consume one text character, keep
                // patternIndex unchanged so '*' can match more characters.
                dp[textIndex][patternIndex] |= dp[textIndex + 1][patternIndex];
            }

        }
    }
}

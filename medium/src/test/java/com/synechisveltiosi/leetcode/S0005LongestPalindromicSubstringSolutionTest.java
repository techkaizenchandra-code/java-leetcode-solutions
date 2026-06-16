package com.synechisveltiosi.leetcode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class S0005LongestPalindromicSubstringSolutionTest {

    private static S0005LongestPalindromicSubstringSolution.BruteForceCheckAllSubstring bruteForceCheckAllSubstring;
    private static S0005LongestPalindromicSubstringSolution.ExpandAroundCenterSolution expandAroundCenterSolution;
    private static S0005LongestPalindromicSubstringSolution.DynamicProgrammingSolution dynamicProgrammingSolution;
    private static S0005LongestPalindromicSubstringSolution.ManachersAlgorithmSolution manachersAlgorithmSolution;

    @BeforeAll
    static void setup() {
        bruteForceCheckAllSubstring = new S0005LongestPalindromicSubstringSolution.BruteForceCheckAllSubstring();
        expandAroundCenterSolution = new S0005LongestPalindromicSubstringSolution.ExpandAroundCenterSolution();
        dynamicProgrammingSolution = new S0005LongestPalindromicSubstringSolution.DynamicProgrammingSolution();
        manachersAlgorithmSolution = new S0005LongestPalindromicSubstringSolution.ManachersAlgorithmSolution();
    }

    private static Stream<Arguments> longestPalindromeInputs() {
        return Stream.of(
                Arguments.of("a", Set.of("a")),
                Arguments.of("ac", Set.of("a", "c")),
                Arguments.of("babad", Set.of("bab", "aba")),
                Arguments.of("cbbd", Set.of("bb")),
                Arguments.of("racecar", Set.of("racecar")),
                Arguments.of("forgeeksskeegfor", Set.of("geeksskeeg"))
        );
    }


    @ParameterizedTest
    @MethodSource("longestPalindromeInputs")
    void longestPalindromeBruteForce(String input, Set<String> expected) {
        String actual = bruteForceCheckAllSubstring.longestPalindrome(input);
        assertTrue(
                expected.contains(actual),
                () -> "Expected one of " + expected + " but got " + actual
        );
    }

    @ParameterizedTest
    @MethodSource("longestPalindromeInputs")
    void longestPalindromeExpandAroundCenter(String input, Set<String> expected) {
        String actual = expandAroundCenterSolution.longestPalindrome(input);
        assertTrue(
                expected.contains(actual),
                () -> "Expected one of " + expected + " but got " + actual
        );
    }

    @ParameterizedTest
    @MethodSource("longestPalindromeInputs")
    void longestPalindromeDynamicProgramming(String input, Set<String> expected) {
        String actual = dynamicProgrammingSolution.longestPalindrome(input);
        assertTrue(
                expected.contains(actual),
                () -> "Expected one of " + expected + " but got " + actual
        );
    }

    @ParameterizedTest
    @MethodSource("longestPalindromeInputs")
    void longestPalindromeManachersAlgorithm(String input, Set<String> expected) {
        String actual = manachersAlgorithmSolution.longestPalindrome(input);
        assertTrue(
                expected.contains(actual),
                () -> "Expected one of " + expected + " but got " + actual
        );
    }

    @Test
    void longestPalindromeReturnsEmptyStringForNullOrEmptyInput() {
        assertEquals("", bruteForceCheckAllSubstring.longestPalindrome(null));
        assertEquals("", bruteForceCheckAllSubstring.longestPalindrome(""));

        assertEquals("", expandAroundCenterSolution.longestPalindrome(null));
        assertEquals("", expandAroundCenterSolution.longestPalindrome(""));

        assertEquals("", dynamicProgrammingSolution.longestPalindrome(null));
        assertEquals("", dynamicProgrammingSolution.longestPalindrome(""));

        assertEquals("", manachersAlgorithmSolution.longestPalindrome(null));
        assertEquals("", manachersAlgorithmSolution.longestPalindrome(""));
    }

}

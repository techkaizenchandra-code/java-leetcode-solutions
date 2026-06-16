package com.synechisveltiosi.leetcode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class S0005LongestPalindromicSubstringSolutionTest {

    private static S0005LongestPalindromicSubstringSolution solution;

    @BeforeAll
    static void setup() {
        solution = new S0005LongestPalindromicSubstringSolution();
    }

    @ParameterizedTest
    @MethodSource("longestPalindromeInputs")
    void longestPalindrome2(String input, Set<String> expected) {
        String actual = solution.longestPalindrome(input);
        assertTrue(
                expected.contains(actual),
                () -> "Expected one of " + expected + " but got " + actual
        );
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

}
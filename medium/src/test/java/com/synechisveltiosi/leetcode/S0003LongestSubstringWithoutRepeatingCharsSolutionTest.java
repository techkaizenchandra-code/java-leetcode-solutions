package com.synechisveltiosi.leetcode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class S0003LongestSubstringWithoutRepeatingCharsSolutionTest {

    private static S0003LongestSubstringWithoutRepeatingCharsSolution solution;

    @BeforeAll
    static void init() {
        solution = new S0003LongestSubstringWithoutRepeatingCharsSolution();
    }

    @ParameterizedTest
    @MethodSource("longestSubstringWithoutRepeatingCharInputs")
    void lengthOfLongestSubstring(String s, int expectedMaxLen) {
        assertEquals(expectedMaxLen, solution.lengthOfLongestSubstring(s));
    }

    @ParameterizedTest
    @MethodSource("longestSubstringWithoutRepeatingCharInputs")
    void lengthOfLongestSubstring2(String s, int expectedMaxLen) {
        assertEquals(expectedMaxLen, solution.lengthOfLongestSubstring2(s));
    }


    @ParameterizedTest
    @MethodSource("longestSubstringWithoutRepeatingCharInputs")
    void lengthOfLongestSubstring3(String s, int expectedMaxLen) {
        assertEquals(expectedMaxLen, solution.lengthOfLongestSubstring3(s));
    }

    private static Stream<Arguments> longestSubstringWithoutRepeatingCharInputs() {
        return Stream.of(
                Arguments.of("abcabcbb", 3),
                Arguments.of("bbbbb", 1),
                Arguments.of("pwwkew", 3),
                Arguments.of("", 0),
                Arguments.of(" ", 1),
                Arguments.of("dvdf", 3),
                Arguments.of("abba", 2)
        );
    }
}
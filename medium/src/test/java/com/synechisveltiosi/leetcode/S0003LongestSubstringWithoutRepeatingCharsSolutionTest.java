package com.synechisveltiosi.leetcode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class S0003LongestSubstringWithoutRepeatingCharsSolutionTest {

    private static S0003LongestSubstringWithoutRepeatingCharsSolution.IntegerArraySolution integerArraySolution;
    private static S0003LongestSubstringWithoutRepeatingCharsSolution.BooleanArraySolution booleanArraySolution;
    private static S0003LongestSubstringWithoutRepeatingCharsSolution.SetSolution setSolution;
    private static S0003LongestSubstringWithoutRepeatingCharsSolution.MapSolution mapSolution;

    @BeforeAll
    static void init() {
        integerArraySolution = new S0003LongestSubstringWithoutRepeatingCharsSolution.IntegerArraySolution();
        booleanArraySolution = new S0003LongestSubstringWithoutRepeatingCharsSolution.BooleanArraySolution();
        setSolution = new S0003LongestSubstringWithoutRepeatingCharsSolution.SetSolution();
        mapSolution = new S0003LongestSubstringWithoutRepeatingCharsSolution.MapSolution();
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

    @ParameterizedTest
    @MethodSource("longestSubstringWithoutRepeatingCharInputs")
    void lengthOfLongestSubstringIntegerArray(String s, int expectedMaxLen) {
        assertEquals(expectedMaxLen, integerArraySolution.lengthOfLongestSubstring(s));
    }

    @ParameterizedTest
    @MethodSource("longestSubstringWithoutRepeatingCharInputs")
    void lengthOfLongestSubstringBooleanArray(String s, int expectedMaxLen) {
        assertEquals(expectedMaxLen, booleanArraySolution.lengthOfLongestSubstring(s));
    }

    @ParameterizedTest
    @MethodSource("longestSubstringWithoutRepeatingCharInputs")
    void lengthOfLongestSubstringSet(String s, int expectedMaxLen) {
        assertEquals(expectedMaxLen, setSolution.lengthOfLongestSubstring(s));
    }

    @ParameterizedTest
    @MethodSource("longestSubstringWithoutRepeatingCharInputs")
    void lengthOfLongestSubstringMap(String s, int expectedMaxLen) {
        assertEquals(expectedMaxLen, mapSolution.lengthOfLongestSubstring(s));
    }
}
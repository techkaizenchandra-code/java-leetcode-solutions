package com.synechisveltiosi.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.Arguments;

import org.junit.jupiter.params.provider.MethodSource;

class S0008StringToIntegerAtoiSolutionTest {

    private final S0008StringToIntegerAtoiSolution solution = new S0008StringToIntegerAtoiSolution();

    @ParameterizedTest(name = "[{index}] myAtoi(\"{0}\") = {1}")
    @MethodSource("atoiTestCases")
    void myAtoi(String input, int expected) {
        assertEquals(expected, solution.myAtoi(input));
    }

    static Stream<Arguments> atoiTestCases() {

        return Stream.of(

                // Examples from problem statement

                Arguments.of("42", 42),
                Arguments.of("   -042", -42),
                Arguments.of("1337c0d3", 1337),
                Arguments.of("0-1", 0),
                Arguments.of("words and 987", 0),
                // Positive sign
                Arguments.of("+1", 1),
                Arguments.of("+42", 42),
                // Leading zeros
                Arguments.of("000123", 123),
                Arguments.of("-000123", -123),
                // Stop at first non-digit
                Arguments.of("4193 with words", 4193),
                Arguments.of("-42abc", -42),
                // Sign only
                Arguments.of("+", 0),
                Arguments.of("-", 0),
                // Empty / whitespace
                Arguments.of("", 0),
                Arguments.of("     ", 0),
                // Invalid sign combinations
                Arguments.of("+-12", 0),
                Arguments.of("-+12", 0),
                // Integer boundaries
                Arguments.of("2147483647", Integer.MAX_VALUE),
                Arguments.of("-2147483648", Integer.MIN_VALUE),
                // Overflow

                Arguments.of("2147483648", Integer.MAX_VALUE),
                Arguments.of("999999999999999999999", Integer.MAX_VALUE),

                // Underflow

                Arguments.of("-2147483649", Integer.MIN_VALUE),
                Arguments.of("-999999999999999999999", Integer.MIN_VALUE)

        );

    }
}
package com.synechisveltiosi.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class S0007ReverseIntegerSolutionTest {

    private final S0007ReverseIntegerSolution solution =

            new S0007ReverseIntegerSolution();

    @ParameterizedTest(name = "[{index}] reverse({0}) = {1}")
    @MethodSource("reverseIntegerTestCases")
    void shouldReverseIntegerCorrectly(int input, int expected) {
        assertEquals(expected, solution.reverse(input));

    }

    static Stream<Arguments> reverseIntegerTestCases() {

        return Stream.of(

                // Positive number

                Arguments.of(123, 321),

                // Negative number

                Arguments.of(-123, -321),

                // Trailing zero

                Arguments.of(120, 21),

                // Zero

                Arguments.of(0, 0),

                // Single digit

                Arguments.of(7, 7),

                // Palindrome

                Arguments.of(1221, 1221),

                // Integer overflow (positive)

                Arguments.of(1534236469, 0),

                // Integer overflow (negative)

                Arguments.of(-1563847412, 0),

                // Max int reversed overflows

                Arguments.of(Integer.MAX_VALUE, 0),

                // Min int reversed overflows

                Arguments.of(Integer.MIN_VALUE, 0)

        );


    }
}
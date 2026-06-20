package com.synechisveltiosi.leetcode.leetcode;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class S0013RomanToIntegerSolutionTest {

    private final S0013RomanToIntegerSolution solution =
            new S0013RomanToIntegerSolution();

    @ParameterizedTest(name = "{0} -> {1}")
    @MethodSource("romanNumeralTestCases")
    void shouldConvertRomanToInteger(
            String roman,
            int expected) {

        assertAll(
                () -> assertEquals(expected, solution.romanToInt1(roman)),
                () -> assertEquals(expected, solution.romanToInt2(roman)),
                () -> assertEquals(expected, solution.romanToInt3(roman))
        );
    }

    private static Stream<Arguments> romanNumeralTestCases() {
        return Stream.of(
                // Single symbols
                Arguments.of("I", 1),
                Arguments.of("V", 5),
                Arguments.of("X", 10),
                Arguments.of("L", 50),
                Arguments.of("C", 100),
                Arguments.of("D", 500),
                Arguments.of("M", 1000),

                // Repeated symbols
                Arguments.of("II", 2),
                Arguments.of("III", 3),
                Arguments.of("XX", 20),
                Arguments.of("XXX", 30),

                // Subtractive notation
                Arguments.of("IV", 4),
                Arguments.of("IX", 9),
                Arguments.of("XL", 40),
                Arguments.of("XC", 90),
                Arguments.of("CD", 400),
                Arguments.of("CM", 900),

                // LeetCode examples
                Arguments.of("III", 3),
                Arguments.of("LVIII", 58),
                Arguments.of("MCMXCIV", 1994),

                // Additional cases
                Arguments.of("MMXXIV", 2024),
                Arguments.of("MMMDCCXLIX", 3749),
                Arguments.of("MMMCMXCIX", 3999)
        );
    }
}
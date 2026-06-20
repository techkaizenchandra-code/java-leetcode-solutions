package com.synechisveltiosi.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class S0012IntegerToRomanSolutionTest {

    private final S0012IntegerToRomanSolution solution =
            new S0012IntegerToRomanSolution();

    @ParameterizedTest(name = "{0} -> {1}")
    @MethodSource("romanNumeralTestCases")
    void shouldConvertIntegerToRomanUsingImplementation1(
            int input,
            String expectedRoman) {

        assertEquals(expectedRoman, solution.intToRoman1(input));
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @MethodSource("romanNumeralTestCases")
    void shouldConvertIntegerToRomanUsingImplementation2(
            int input,
            String expectedRoman) {

        assertEquals(expectedRoman, solution.intToRoman2(input));
    }

    private static Stream<Arguments> romanNumeralTestCases() {
        return Stream.of(
                Arguments.of(1, "I"),
                Arguments.of(2, "II"),
                Arguments.of(3, "III"),
                Arguments.of(4, "IV"),
                Arguments.of(5, "V"),
                Arguments.of(9, "IX"),
                Arguments.of(10, "X"),
                Arguments.of(40, "XL"),
                Arguments.of(50, "L"),
                Arguments.of(90, "XC"),
                Arguments.of(100, "C"),
                Arguments.of(400, "CD"),
                Arguments.of(500, "D"),
                Arguments.of(900, "CM"),
                Arguments.of(1000, "M"),
                Arguments.of(58, "LVIII"),
                Arguments.of(1994, "MCMXCIV"),
                Arguments.of(2024, "MMXXIV"),
                Arguments.of(3749, "MMMDCCXLIX"),
                Arguments.of(3999, "MMMCMXCIX")
        );
    }
}
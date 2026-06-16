package com.synechisveltiosi.leetcode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class S0006ZigZagConversionSolutionTest {

    private static S0006ZigZagConversionSolution.TraversalSimulationSolution traversalSimulationSolution;
    private static S0006ZigZagConversionSolution.CyclePatternSolution cyclePatternSolution;


    @BeforeAll
    static void init() {
        traversalSimulationSolution = new S0006ZigZagConversionSolution.TraversalSimulationSolution();
        cyclePatternSolution = new S0006ZigZagConversionSolution.CyclePatternSolution();

    }

    @ParameterizedTest(name = "[{index}] convert(\"{0}\", {1}) = \"{2}\"")
    @MethodSource("testCases")
    void shouldConvertStringInZigZagPatternWithSimulation(String input, int numRows, String expected) {

        assertEquals(expected, traversalSimulationSolution.convert(input, numRows));

    }

    @ParameterizedTest(name = "[{index}] convert(\"{0}\", {1}) = \"{2}\"")
    @MethodSource("testCases")
    void shouldConvertStringInZigZagPatternWithCyclePattern(String input, int numRows, String expected) {

        assertEquals(expected, cyclePatternSolution.convert(input, numRows));

    }

    private static Stream<Arguments> testCases() {
        return Stream.of(

                // LeetCode examples

                Arguments.of("PAYPALISHIRING", 3, "PAHNAPLSIIGYIR"),

                Arguments.of("PAYPALISHIRING", 4, "PINALSIGYAHRPI"),

                // Single row

                Arguments.of("PAYPALISHIRING", 1, "PAYPALISHIRING"),

                // numRows >= length

                Arguments.of("ABC", 3, "ABC"),

                Arguments.of("ABC", 5, "ABC"),

                // Small inputs

                Arguments.of("A", 1, "A"),

                Arguments.of("AB", 1, "AB"),

                Arguments.of("AB", 2, "AB"),

                // General cases

                Arguments.of("ABCDE", 2, "ACEBD"),

                Arguments.of("HELLOWORLD", 3, "HOLELWRDLO"),

                // Repeated characters

                Arguments.of("AAAAAA", 3, "AAAAAA")

        );

    }

}
package com.synechisveltiosi.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class S0011ContainerWithMostWaterSolutionTest {

    @ParameterizedTest(name = "[{index}] heights={0} => maxArea={1}")
    @MethodSource("testCases")
    void shouldReturnCorrectMaxAreaForBruteForce(
            int[] heights,
            int expected) {

        var solution =
                new S0011ContainerWithMostWaterSolution.BruteForceSolution();

        assertEquals(expected, solution.maxArea(heights));
    }

    @ParameterizedTest(name = "[{index}] heights={0} => maxArea={1}")
    @MethodSource("testCases")
    void shouldReturnCorrectMaxAreaForTwoPointer(
            int[] heights,
            int expected) {

        var solution =
                new S0011ContainerWithMostWaterSolution.TwoPointerSolution();

        assertEquals(expected, solution.maxArea(heights));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(
                        new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7},
                        49),

                Arguments.of(
                        new int[]{1, 1},
                        1),

                Arguments.of(
                        new int[]{4, 3, 2, 1, 4},
                        16),

                Arguments.of(
                        new int[]{1, 2, 1},
                        2),

                Arguments.of(
                        new int[]{2, 3, 4, 5, 18, 17, 6},
                        17),

                Arguments.of(
                        new int[]{1, 2, 4, 3},
                        4),

                Arguments.of(
                        new int[]{5, 5, 5, 5},
                        15),

                Arguments.of(
                        new int[]{1, 3, 2, 5, 25, 24, 5},
                        24),

                Arguments.of(
                        new int[]{2, 2},
                        2),

                Arguments.of(
                        new int[]{10000, 10000},
                        10000)
        );
    }
}
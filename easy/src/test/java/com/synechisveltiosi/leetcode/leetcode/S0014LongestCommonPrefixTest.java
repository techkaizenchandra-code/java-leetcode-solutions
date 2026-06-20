package com.synechisveltiosi.leetcode.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class S0014LongestCommonPrefixTest {

    static Stream<Arguments> longestCommonPrefixCases() {
        return Stream.of(
                Arguments.of(
                        new String[]{"flower", "flow", "flight"},
                        "fl"
                ),
                Arguments.of(
                        new String[]{"dog", "racecar", "car"},
                        ""
                ),
                Arguments.of(
                        new String[]{"interspecies", "interstellar", "interstate"},
                        "inters"
                ),
                Arguments.of(
                        new String[]{"throne", "throne"},
                        "throne"
                ),
                Arguments.of(
                        new String[]{"a"},
                        "a"
                ),
                Arguments.of(
                        new String[]{"", ""},
                        ""
                ),
                Arguments.of(
                        new String[]{"prefix", "prefixes", "prefixation"},
                        "prefix"
                ),
                Arguments.of(
                        new String[]{"ab", "a"},
                        "a"
                ),
                Arguments.of(
                        new String[]{"abc", "abc", "abc"},
                        "abc"
                ),
                Arguments.of(
                        new String[]{"cir", "car"},
                        "c"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("longestCommonPrefixCases")
    void horizontalScanningSolution_ShouldReturnExpectedPrefix(
            String[] input,
            String expected
    ) {
        var solution =
                new S0014LongestCommonPrefix.HorizontalScanningSolution();

        assertEquals(expected, solution.longestCommonPrefix(input));
    }

    @ParameterizedTest
    @MethodSource("longestCommonPrefixCases")
    void verticalScanningSolution_ShouldReturnExpectedPrefix(
            String[] input,
            String expected
    ) {
        var solution =
                new S0014LongestCommonPrefix.VerticalScanningSolution();

        assertEquals(expected, solution.longestCommonPrefix(input));
    }

    @ParameterizedTest
    @MethodSource("longestCommonPrefixCases")
    void bruteForceSolution_ShouldReturnExpectedPrefix(
            String[] input,
            String expected
    ) {
        var solution =
                new S0014LongestCommonPrefix.BruteForceSolution();

        assertEquals(expected,
                solution.longestCommonPrefix2(input.clone()));
    }
}
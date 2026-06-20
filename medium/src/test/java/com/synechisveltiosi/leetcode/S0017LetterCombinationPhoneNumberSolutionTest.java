package com.synechisveltiosi.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class S0017LetterCombinationPhoneNumberSolutionTest {

    @ParameterizedTest
    @MethodSource("letterCombinationProvider")
    void shouldGenerateLetterCombinations(
            String digits,
            List<String> expected) {

        S0017LetterCombinationPhoneNumberSolution solution =
                new S0017LetterCombinationPhoneNumberSolution();

        List<String> actual = solution.letterCombinations(digits);

        assertEquals(expected, actual);
    }

    static Stream<org.junit.jupiter.params.provider.Arguments> letterCombinationProvider() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(
                        "",
                        List.of()
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        "2",
                        List.of("a", "b", "c")
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        "3",
                        List.of("d", "e", "f")
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        "23",
                        List.of(
                                "ad", "ae", "af",
                                "bd", "be", "bf",
                                "cd", "ce", "cf"
                        )
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        "79",
                        List.of(
                                "pw", "px", "py", "pz",
                                "qw", "qx", "qy", "qz",
                                "rw", "rx", "ry", "rz",
                                "sw", "sx", "sy", "sz"
                        )
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        "22",
                        List.of(
                                "aa", "ab", "ac",
                                "ba", "bb", "bc",
                                "ca", "cb", "cc"
                        )
                )
        );
    }
}
package com.synechisveltiosi.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class S0015ThreeSumSolutionTest {

    static Stream<TestCase> threeSumProvider() {
        return Stream.of(
                new TestCase(
                        new int[]{-1, 0, 1, 2, -1, -4},
                        List.of(
                                List.of(-1, -1, 2),
                                List.of(-1, 0, 1)
                        )
                ),
                new TestCase(
                        new int[]{0, 1, 1},
                        List.of()
                ),
                new TestCase(
                        new int[]{0, 0, 0},
                        List.of(
                                List.of(0, 0, 0)
                        )
                ),
                new TestCase(
                        new int[]{0, 0, 0, 0},
                        List.of(
                                List.of(0, 0, 0)
                        )
                ),
                new TestCase(
                        new int[]{-2, 0, 1, 1, 2},
                        List.of(
                                List.of(-2, 0, 2),
                                List.of(-2, 1, 1)
                        )
                ),
                new TestCase(
                        new int[]{-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6},
                        List.of(
                                List.of(-4, -2, 6),
                                List.of(-4, 0, 4),
                                List.of(-4, 1, 3),
                                List.of(-4, 2, 2),
                                List.of(-2, -2, 4),
                                List.of(-2, 0, 2)
                        )
                ),
                new TestCase(
                        new int[]{1, 2, 3, 4},
                        List.of()
                ),
                new TestCase(
                        new int[]{-5, -4, -3, -2},
                        List.of()
                ),
                new TestCase(
                        new int[]{},
                        List.of()
                ),
                new TestCase(
                        new int[]{0},
                        List.of()
                ),
                new TestCase(
                        new int[]{0, 1},
                        List.of()
                )
        );
    }

    @ParameterizedTest
    @MethodSource("threeSumProvider")
    void shouldFindThreeSumUsingOptimalSolution(TestCase testCase) {
        var solution = new S0015ThreeSumSolution.OptimalSolution();

        var actual = normalize(solution.threeSum(testCase.input()));
        var expected = normalize(testCase.expected());

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("threeSumProvider")
    void shouldFindThreeSumUsingBruteForceSolution(TestCase testCase) {
        var solution = new S0015ThreeSumSolution.BruteForceSolution();

        var actual = normalize(solution.threeSum(testCase.input()));
        var expected = normalize(testCase.expected());

        assertEquals(expected, actual);
    }

    private static List<List<Integer>> normalize(List<List<Integer>> triples) {
        return triples.stream()
                .map(triple -> triple.stream()
                        .sorted()
                        .toList())
                .sorted(Comparator
                        .comparing((List<Integer> t) -> t.get(0))
                        .thenComparing(t -> t.get(1))
                        .thenComparing(t -> t.get(2)))
                .toList();
    }

    record TestCase(int[] input, List<List<Integer>> expected) {
    }
}
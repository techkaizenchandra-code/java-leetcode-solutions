package com.synechisveltiosi.leetcode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class S0004MedianOfTwoSortedArraysSolutionTest {

    private static S0004MedianOfTwoSortedArraysSolution solution;
    @BeforeAll
    static void setup() {
        solution = new S0004MedianOfTwoSortedArraysSolution();
    }

    @ParameterizedTest
    @MethodSource("medianInputs")
    void findMedianSortedArrays(int[] nums1, int[] nums2, double expected) {
        assertEquals(expected, solution.findMedianSortedArrays(nums1, nums2));
    }

    @Test
    void findMedianSortedArraysRejectsTwoEmptyInputs() {
        assertThrows(
                IllegalArgumentException.class,
                () -> solution.findMedianSortedArrays(new int[] {}, new int[] {})
        );
    }

    private static Stream<Arguments> medianInputs() {
        return Stream.of(
                Arguments.of(new int[] {1, 3}, new int[] {2}, 2.0),
                Arguments.of(new int[] {1, 2}, new int[] {3, 4}, 2.5),
                Arguments.of(new int[] {}, new int[] {1}, 1.0),
                Arguments.of(new int[] {2, 2}, new int[] {2, 2}, 2.0),
                Arguments.of(new int[] {Integer.MAX_VALUE}, new int[] {Integer.MAX_VALUE}, Integer.MAX_VALUE)
        );
    }
}
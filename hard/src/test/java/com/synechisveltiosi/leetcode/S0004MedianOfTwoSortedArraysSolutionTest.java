package com.synechisveltiosi.leetcode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class S0004MedianOfTwoSortedArraysSolutionTest {

    private static S0004MedianOfTwoSortedArraysSolution.BinarySearchRecursiveKthSmallestSolution binarySearchRecursiveKthSmallestSolution;
    private static S0004MedianOfTwoSortedArraysSolution.BinarySearchSolution binarySearchSolution;
    private static S0004MedianOfTwoSortedArraysSolution.TwoPointerPartialMergeSolution twoPointerPartialMergeSolution;

    @BeforeAll
    static void setup() {
        binarySearchRecursiveKthSmallestSolution = new S0004MedianOfTwoSortedArraysSolution.BinarySearchRecursiveKthSmallestSolution();
        binarySearchSolution = new S0004MedianOfTwoSortedArraysSolution.BinarySearchSolution();
        twoPointerPartialMergeSolution = new S0004MedianOfTwoSortedArraysSolution.TwoPointerPartialMergeSolution();
    }

    private static Stream<Arguments> medianInputs() {
        return Stream.of(
                Arguments.of(new int[]{1, 3}, new int[]{2}, 2.0),
                Arguments.of(new int[]{1, 2}, new int[]{3, 4}, 2.5),
                Arguments.of(new int[]{}, new int[]{1}, 1.0),
                Arguments.of(new int[]{2, 2}, new int[]{2, 2}, 2.0)
        );
    }

    @ParameterizedTest
    @MethodSource("medianInputs")
    void findMedianSortedArraysKthSmallestRecursive(int[] nums1, int[] nums2, double expected) {
        assertEquals(expected, binarySearchRecursiveKthSmallestSolution.findMedianSortedArrays(nums1, nums2));
    }

    @ParameterizedTest
    @MethodSource("medianInputs")
    void findMedianSortedArraysBinarySearch(int[] nums1, int[] nums2, double expected) {
        assertEquals(expected, binarySearchSolution.findMedianSortedArrays(nums1, nums2));
    }

    @ParameterizedTest
    @MethodSource("medianInputs")
    void findMedianSortedArraysTwoPointerPartialMerge(int[] nums1, int[] nums2, double expected) {
        assertEquals(expected, twoPointerPartialMergeSolution.findMedianSortedArrays(nums1, nums2));
    }

    @Test
    void findMedianSortedArraysRejectsNullInputs() {
        assertThrows(NullPointerException.class, () -> twoPointerPartialMergeSolution.findMedianSortedArrays(null, new int[]{1}));
        assertThrows(NullPointerException.class, () -> twoPointerPartialMergeSolution.findMedianSortedArrays(new int[]{1}, null));
    }
}

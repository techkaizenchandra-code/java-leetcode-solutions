package com.synechisveltiosi.leetcode;

import java.util.Objects;

public class S0004MedianOfTwoSortedArraysSolution {
    private S0004MedianOfTwoSortedArraysSolution() {
        /* This utility class should not be instantiated */
    }


    static class BinarySearchRecursiveKthSmallestSolution {

        /**
         * Finds the median by asking for the middle element, or the two middle
         * elements, from the sorted order formed by both arrays.
         *
         * <p>This approach does not merge the arrays. It uses
         * {@link #findKthSmallest(int[], int, int[], int, int)} to recursively
         * discard values that cannot be the kth smallest element.</p>
         *
         * <ul>
         *     <li>For an odd total length, the median is the
         *     {@code (totalLength / 2 + 1)}th smallest value.</li>
         *     <li>For an even total length, the median is the average of the
         *     {@code (totalLength / 2)}th and
         *     {@code (totalLength / 2 + 1)}th smallest values.</li>
         * </ul>
         *
         * <p>Time Complexity: {@code O(log(m + n))}</p>
         * <p>Space Complexity: {@code O(log(m + n))} because of the recursive
         * call stack.</p>
         */
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {

            int totalLength = nums1.length + nums2.length;
            if (totalLength % 2 == 1) {
                return findKthSmallest(nums1, 0, nums2, 0, totalLength / 2 + 1);
            }
            double leftMiddle = findKthSmallest(nums1, 0, nums2, 0, totalLength / 2);
            double rightMiddle = findKthSmallest(nums1, 0, nums2, 0, totalLength / 2 + 1);

            return (leftMiddle + rightMiddle) / 2.0;

        }


        /**
         * Returns the kth smallest element from the remaining portions of
         * {@code nums1} and {@code nums2}.
         *
         * <p>{@code index1} and {@code index2} mark the first active positions
         * in both arrays. Values before those indexes have already been
         * discarded from consideration.</p>
         *
         * <p>At each recursive step:</p>
         *
         * <ol>
         *     <li>Look {@code halfK = k / 2} positions ahead in both arrays.</li>
         *     <li>Compare {@code pivot1} and {@code pivot2}.</li>
         *     <li>Discard {@code halfK} values from the array with the smaller
         *     pivot because those values cannot be the kth smallest value.</li>
         *     <li>Search for the remaining {@code k - halfK}th value.</li>
         * </ol>
         *
         * <p>If one array does not have enough remaining values for a pivot,
         * {@code Integer.MAX_VALUE} is used so values are discarded from the
         * other array first.</p>
         *
         * <p>Base cases:</p>
         *
         * <ul>
         *     <li>If {@code nums1} is exhausted, return the kth remaining value
         *     from {@code nums2}.</li>
         *     <li>If {@code nums2} is exhausted, return the kth remaining value
         *     from {@code nums1}.</li>
         *     <li>If {@code k == 1}, return the smaller current value.</li>
         * </ul>
         *
         * <p>Time Complexity: {@code O(log(k))}</p>
         * <p>Space Complexity: {@code O(log(k))} because of the recursive call
         * stack.</p>
         */
        private int findKthSmallest(int[] nums1, int index1, int[] nums2, int index2, int k) {

            // nums1 is exhausted
            if (index1 >= nums1.length) {
                return nums2[index2 + k - 1];
            }
            // nums2 is exhausted
            if (index2 >= nums2.length) {
                return nums1[index1 + k - 1];
            }
            // Smallest remaining element
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }
            int halfK = k / 2;
            int pivot1Index = index1 + halfK - 1;
            int pivot2Index = index2 + halfK - 1;

            int pivot1 = pivot1Index < nums1.length ? nums1[pivot1Index] : Integer.MAX_VALUE;
            int pivot2 = pivot2Index < nums2.length ? nums2[pivot2Index] : Integer.MAX_VALUE;

            if (pivot1 <= pivot2) {
                return findKthSmallest(nums1, index1 + halfK, nums2, index2, k - halfK);
            }
            return findKthSmallest(nums1, index1, nums2, index2 + halfK, k - halfK);

        }
    }


    static class BinarySearchSolution {
        private static int getLeftMax(int[] numbers, int partitionIndex) {
            return partitionIndex == 0 ? Integer.MIN_VALUE : numbers[partitionIndex - 1];
        }

        private static int getRightMin(int[] numbers, int partitionIndex) {
            return partitionIndex == numbers.length ? Integer.MAX_VALUE : numbers[partitionIndex];
        }

        private static double calculateMedian(int totalLength, int leftMax, int rightMin) {
            if (totalLength % 2 == 1) {
                return leftMax;
            }
            return ((double) leftMax + rightMin) / 2.0;
        }

        private static void validateInput(int[] nums1, int[] nums2) {
            Objects.requireNonNull(nums1, "nums1 must not be null");
            Objects.requireNonNull(nums2, "nums2 must not be null");
            if (nums1.length + nums2.length == 0) {
                throw new IllegalArgumentException("At least one input array must contain a value");
            }
        }

        /**
         * Finds the median by binary searching the partition point in the
         * shorter array.
         *
         * <p>The goal is to split both arrays into left and right partitions
         * such that:</p>
         *
         * <ul>
         *     <li>The left side contains {@code (m + n + 1) / 2} elements.</li>
         *     <li>The right side contains the remaining elements.</li>
         *     <li>Every value on the left side is less than or equal to every
         *     value on the right side.</li>
         * </ul>
         *
         * <p>For each binary-search step:</p>
         *
         * <pre>
         * pX = left + (right - left) / 2
         * pY = leftPartitionSize - pX
         * </pre>
         *
         * <p>{@code pX} partitions the shorter array and {@code pY} partitions
         * the longer array. Boundary helpers provide sentinels for empty sides:</p>
         *
         * <ul>
         *     <li>{@code getLeftMax(...)} returns {@code Integer.MIN_VALUE}
         *     when the left side is empty.</li>
         *     <li>{@code getRightMin(...)} returns {@code Integer.MAX_VALUE}
         *     when the right side is empty.</li>
         * </ul>
         *
         * <p>A partition is valid when:</p>
         *
         * <pre>
         * maxLeftX <= minRightY
         * maxLeftY <= minRightX
         * </pre>
         *
         * <p>Once the valid partition is found:</p>
         *
         * <ul>
         *     <li>For an odd total length, the median is the maximum value on
         *     the left side.</li>
         *     <li>For an even total length, the median is the average of the
         *     maximum left value and minimum right value.</li>
         * </ul>
         *
         * <p>If {@code maxLeftX > minRightY}, the partition in the shorter array
         * is too far right, so move {@code right} left. Otherwise, move
         * {@code left} right.</p>
         *
         * <p>Time Complexity: {@code O(log(min(m, n)))}</p>
         * <p>Space Complexity: {@code O(1)}</p>
         */
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            validateInput(nums1, nums2);

            int[] shorter = nums1.length <= nums2.length ? nums1 : nums2;
            int[] longer = nums1.length <= nums2.length ? nums2 : nums1;

            return findMedian(shorter, longer);
        }

        private double findMedian(int[] nums1, int[] nums2) {
            int m = nums1.length;
            int n = nums2.length;
            int totalLength = m + n;
            int leftPartitionSize = (totalLength + 1) / 2;

            int left = 0;
            int right = m;

            while (left <= right) {
                int pX = left + (right - left) / 2;
                int pY = leftPartitionSize - pX;

                int maxLeftX = getLeftMax(nums1, pX);
                int maxLeftY = getLeftMax(nums2, pY);

                int minRightX = getRightMin(nums1, pX);
                int minRightY = getRightMin(nums2, pY);

                if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                    return calculateMedian(totalLength, Math.max(maxLeftX, maxLeftY), Math.min(minRightX, minRightY));
                }
                if (maxLeftX > minRightY) {
                    right = pX - 1;
                } else {
                    left = pX + 1;
                }
            }

            throw new IllegalArgumentException("Input arrays must be sorted in non-decreasing order");
        }
    }

    static class TwoPointerPartialMergeSolution {
        /**
         * Finds the median by partially merging the two sorted arrays with two
         * pointers.
         *
         * <p>This approach follows the same order as a normal merge step from
         * merge sort, but it stops as soon as the middle index is reached.
         * Because the median only depends on the middle value, or the two
         * middle values, the full merged array is not needed.</p>
         *
         * <p>Pointer usage:</p>
         *
         * <ul>
         *     <li>{@code p1} points to the next unread value in {@code nums1}.</li>
         *     <li>{@code p2} points to the next unread value in {@code nums2}.</li>
         *     <li>{@code current} stores the latest selected value from the
         *     merged order.</li>
         *     <li>{@code previous} stores the value selected immediately before
         *     {@code current}.</li>
         * </ul>
         *
         * <p>At each step, the smaller available value from {@code nums1[p1]}
         * and {@code nums2[p2]} is selected, then that array's pointer moves
         * forward. The loop runs only until {@code targetIndex}, which is the
         * middle position in the merged order.</p>
         *
         * <ul>
         *     <li>For an odd total length, {@code current} is the median.</li>
         *     <li>For an even total length, the median is the average of
         *     {@code previous} and {@code current}.</li>
         * </ul>
         *
         * <p>Time Complexity: {@code O((m + n) / 2)}, which simplifies to
         * {@code O(m + n)}.</p>
         * <p>Space Complexity: {@code O(1)}</p>
         */
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int totalLength = nums1.length + nums2.length;
            int targetIndex = totalLength / 2;
            int p1 = 0;
            int p2 = 0;
            int previous = 0;
            int current = 0;
            for (int i = 0; i <= targetIndex; i++) {
                previous = current;
                if (p1 < nums1.length && (p2 >= nums2.length || nums1[p1] <= nums2[p2])) {
                    current = nums1[p1++];

                } else {
                    current = nums2[p2++];
                }
            }

            return totalLength % 2 == 0 ? (previous + current) / 2.0 : current;

        }
    }

}

package com.synechisveltiosi.leetcode;

import java.util.Objects;

public class S0004MedianOfTwoSortedArraysSolution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        validateInput(nums1, nums2);
        int[] smaller = nums1.length <= nums2.length ? nums1 : nums2;
        int[] larger = nums1.length <= nums2.length ? nums2 : nums1;
        return findMedian(smaller, larger);

    }

    private double findMedian(int[] smaller, int[] larger) {
        int m = smaller.length;
        int n = larger.length;
        int left = 0;
        int right = m;
        while (left <= right) {
            int pX = (left + right) / 2;
            int pY = (m + n + 1) / 2 - pX;

            int maxLeftX = pX == 0 ? Integer.MIN_VALUE : smaller[pX - 1];
            int maxLeftY = pY == 0 ? Integer.MIN_VALUE : larger[pY - 1];

            int minRightX = pX == m ? Integer.MAX_VALUE : smaller[pX];
            int minRightY = pY == n ? Integer.MAX_VALUE : larger[pY];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if ((m + n) % 2 == 0) {
                    return (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2.0;
                } else {
                    return Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) {
                right = pX - 1;
            } else {
                left = pX + 1;
            }
        }
        throw new IllegalArgumentException("No median solution");
    }

    private static void validateInput(int[] nums1, int[] nums2) {
        Objects.requireNonNull(nums1, "nums1 must not be null");
        Objects.requireNonNull(nums2, "nums2 must not be null");
        if (nums1.length + nums2.length == 0) {
            throw new IllegalArgumentException("At least one input array must contain a value");
        }
    }
}

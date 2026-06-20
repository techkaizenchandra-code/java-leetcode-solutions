package com.synechisveltiosi.leetcode;

public class S0011ContainerWithMostWaterSolution {
    private S0011ContainerWithMostWaterSolution() {
        /* This utility class should not be instantiated */
    }

    static class BruteForceSolution {
        public int maxArea(int[] height) {
            int maxArea = 0;
            for (int left = 0; left < height.length; left++) {
                for (int right = left + 1; right < height.length; right++) {
                    int width = right - left;
                    maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * width);
                }
            }
            return maxArea;
        }
    }

    static class TwoPointerSolution {
        public int maxArea(int[] heights) {
            int left = 0;
            int right = heights.length - 1;
            int maxArea = 0;
            while (left < right) {
                maxArea = Math.max(maxArea, calculateArea(left, right, heights));
                if (heights[left] <= heights[right])
                    left++;
                else
                    right--;
            }
            return maxArea;

        }

        private int calculateArea(int left, int right, int[] heights) {
            int width = right - left;
            int height = Math.min(heights[left], heights[right]);
            return width * height;
        }
    }
}

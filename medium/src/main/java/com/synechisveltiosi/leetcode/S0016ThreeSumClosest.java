package com.synechisveltiosi.leetcode;

import java.util.Arrays;

public class S0016ThreeSumClosest {
    public int threeSumClosest1(int[] nums, int target) {
        int diff = Integer.MAX_VALUE;
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n && diff != 0; ++i) {
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(target - sum) < Math.abs(diff)) {
                    diff = target - sum;
                }
                if (sum < target) ++left;
                else --right;
            }
        }
        return target - diff;
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closestSum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {

                int currentSum = nums[i] + nums[left] + nums[right];

                if (isCloser(currentSum, closestSum, target))
                    closestSum = currentSum;

                if (currentSum == target)
                    return target;

                if (currentSum < target)
                    left++;
                else
                    right--;


            }

        }

        return closestSum;

    }

    private boolean isCloser(int candidate, int currentBest, int target) {
        return Math.abs(target - candidate) < Math.abs(target - currentBest);

    }

}

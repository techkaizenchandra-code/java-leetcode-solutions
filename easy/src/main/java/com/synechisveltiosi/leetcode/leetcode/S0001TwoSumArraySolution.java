package com.synechisveltiosi.leetcode.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class S0001TwoSumArraySolution {
    private S0001TwoSumArraySolution() {
        /* This utility class should not be instantiated */
    }


    static class BruteForceSolution {
        public int[] twoSumBruteForce(int[] nums, int target) {
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (target == nums[i] + nums[j]) {
                        return new int[]{i, j};
                    }
                }
            }
            return new int[0];
        }
    }

    static class HashTableSolution {
        public int[] twoSumWithMap(int[] nums, int target) {
            Map<Integer, Integer> valueByIndex = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int complement = target - nums[i];
                if (valueByIndex.containsKey(complement)) {
                    return new int[]{valueByIndex.get(complement), i};
                }
                valueByIndex.put(nums[i], i);
            }
            return new int[0];
        }
    }

    static class TwoPointerSolution {
        public int[] twoSumWithTwoPointer(int[] nums, int target) {
            Pair[] pairs = IntStream.range(0, nums.length)
                    .mapToObj(idx -> new Pair(idx, nums[idx]))
                    .toArray(Pair[]::new);

            Arrays.sort(pairs, Comparator.comparingInt(Pair::val));

            int left = 0;
            int right = pairs.length - 1;
            while (left < right) {
                int sum = pairs[left].val() + pairs[right].val();
                if (sum == target) {
                    return new int[]{pairs[left].idx(), pairs[right].idx()};
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
            return new int[0];
        }

        record Pair(int idx, int val) {
        }
    }
}
package com.synechisveltiosi.leetcode;

import java.util.*;

public class S0015ThreeSumSolution {
    private S0015ThreeSumSolution() {
        /* This utility class should not be instantiated */
    }

    static class OptimalSolution {
        private List<List<Integer>> list;

        public List<List<Integer>> threeSum(int[] nums) {
            return new AbstractList<List<Integer>>() {
                public List<Integer> get(int index) {
                    init();
                    return list.get(index);
                }

                public int size() {
                    init();
                    return list.size();
                }

                public void init() {
                    if (list != null) return;
                    int j = nums.length - 1;
                    sorted(nums, 0, j);
                    Set<List<Integer>> tempRes = new HashSet<>();
                    for (int i = 0; i < j; i++) {
                        if (nums[i] > 0) break;
                        if (i != 0 && nums[i] == nums[i - 1]) continue;
                        int left = i + 1;
                        int right = j;
                        while (left < right) {
                            int sum = nums[i] + nums[left] + nums[right];
                            if (sum > 0) right--;
                            if (sum < 0) left++;
                            if (sum == 0) {
                                tempRes.add(List.of(nums[i], nums[left++], nums[right--]));
                                while (left < right && nums[left] == nums[left - 1]) left++;
                            }
                        }
                    }
                    list = new ArrayList<>(tempRes);
                }
            };
        }

        void sorted(int[] nums, int low, int high) {
            if (low < high) {
                int partition = partitionFinder(nums, low, high);
                sorted(nums, low, partition - 1);
                sorted(nums, partition + 1, high);
            }
        }

        int partitionFinder(int[] arr, int low, int high) {
            int pivot = arr[low];
            int i = low;
            int j = high;
            while (i < j) {
                while (arr[i] <= pivot && i < high) i++;
                while (arr[j] > pivot && j > low) j--;
                if (i < j) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            arr[low] = arr[j];
            arr[j] = pivot;
            return j;
        }
    }

    static class BruteForceSolution {
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            int n = nums.length;
            Set<List<Integer>> set = new HashSet<>();
            for (int i = 0; i < n - 2; i++) {
                int left = i + 1;
                int right = n - 1;
                while (left < right) {
                    int sum = nums[i] + nums[left] + nums[right];
                    if (sum == 0) set.add(Arrays.asList(nums[i], nums[left++], nums[right--]));
                    else if (sum < 0) left++;
                    else right--;
                }
            }
            return List.copyOf(set);
        }
    }
}

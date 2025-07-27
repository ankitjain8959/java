package com.ankit.leetcode;

import java.util.Arrays;
import java.util.HashMap;

/*  1. Two Sum
 Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 You may assume that each input would have exactly one solution, and you may not use the same element twice.
 You can return the answer in any order.
 */
public class TwoSum {

  public static void main(String[] args) {
    TwoSum ts = new TwoSum();
    int[] nums = {3, 3};
    int target = 6;
    System.out.println(Arrays.toString(ts.twoSum(nums, target)));
  }


  // Time Complexity: O(n) -> Each lookup and insert operation in the HashMap is O(1) on average
  // Space Complexity: O(n) -> for storing up to n elements in the map.
  public int[] twoSum(int[] nums, int target) {
    HashMap<Integer, Integer> numMap = new HashMap<>();
    for(int i=0; i<nums.length; i++) {
      int diff = target - nums[i];
      if(numMap.containsKey(diff)) {
        return new int[] {numMap.get(diff) ,i};
      }
      numMap.put(nums[i], i);
    }
    return new int[]{};
  }

  // Time Complexity: O(n2) -> The outer loop runs n times (i from 0 to n-1), and the inner loop also runs up to n times (j from n-1 down to 1), independent of i.
  // Space Complexity: O(1) -> not using any additional data structures. Only a fixed-size array of 2 elements is returned.
  public int[] twoSumOn2(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
      for (int j = nums.length - 1; j > 0; j--) {
        if (nums[i] + nums[j] == target) {
          return new int[]{i, j};
        }
      }
    }
    return new int[]{};
  }

}

package com.ankit.leetcode;

import java.util.Arrays;
import java.util.HashSet;

/* 217. Contains Duplicate
* Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
* */
public class ContainsDuplicate {

  public static void main(String[] args) {
    ContainsDuplicate cd = new ContainsDuplicate();
    int[] nums = {1,2,3,1};
    System.out.println(cd.containsDuplicateUsingHashSet(nums));
    System.out.println(cd.containsDuplicate(nums));
  }

  // O(n) space complexity & O(n) time complexity
  public boolean containsDuplicateUsingHashSet(int[] nums) {
    HashSet<Integer> set = new HashSet<>();
    for(int num: nums) {
      set.add(num);
    }

    return set.size() != nums.length;
  }

  // O(1) space complexity & O(n) time complexity
  public boolean containsDuplicate(int[] nums) {
    Arrays.sort(nums);
    for(int i=1; i<nums.length; i++) {
      if(nums[i] == nums[i-1]) {
        return true;
      }
    }
    return false;
  }
}

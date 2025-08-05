package com.ankit.leetcode;

import java.util.Arrays;

/* 268. Missing Number
* Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
 * */
public class MissingNumber {

  public static void main(String[] args) {
    MissingNumber mn = new MissingNumber();
    int[] nums = {9,6,4,2,3,5,7,0,1};
    System.out.println(mn.missingNumber(nums));
  }

  public int missingNumber(int[] nums) {
    int arraySize = nums.length;
    Arrays.sort(nums);
    for(int i=0; i<arraySize; i++) {
      if(nums[i] != i) {
        return i;
      }
    }
    return arraySize;
  }
}

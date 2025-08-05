package com.ankit.leetcode;

import java.util.Arrays;

/* 414. Third Maximum Number
* Given an integer array nums, return the third distinct maximum number in this array. If the third maximum does not exist, return the maximum number.
* */
public class ThirdMaximumNumber {

  public static void main(String[] args) {
    ThirdMaximumNumber tmn = new ThirdMaximumNumber();
    int[] nums = {3,2,1};
    System.out.println(tmn.thirdMaxWithSorting(nums));
  }

  public int thirdMaxWithSorting(int[] nums) {
    int[] filteredArray = Arrays.stream(nums)
        .sorted()
        .distinct().toArray();

    int arraySize = filteredArray.length;

    if(arraySize < 3 && arraySize > 0) {
      return filteredArray[arraySize - 1];
    } else if (arraySize >= 3) {
      return filteredArray[arraySize - 1 - 2];
    }
    return arraySize;
  }

}

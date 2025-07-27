package com.ankit.leetcode;

import java.util.HashMap;

/* 136. Single Number
* Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
* */
public class SingleNumber {

  public static void main(String[] args) {
    SingleNumber sn = new SingleNumber();
    int[] nums = {4, 1, 2, 1, 2};
    System.out.println(sn.singleNumber(nums));
  }

  public int singleNumberNotOptimized(int[] nums) {
    HashMap<Integer, Integer> map = new HashMap();
    for (int eachNum : nums) {
      map.put(eachNum, map.getOrDefault(eachNum, 0) + 1);
    }

    return map.entrySet().stream()
        .filter(entry -> entry.getValue() == 1)
        .map(entry -> entry.getKey())
        .findFirst()
        .orElse(0);
  }

  public int singleNumber(int[] nums) {
    int result = 0;

    for(int num: nums) {
      result = result ^ num;
    }
    return result;
  }
}
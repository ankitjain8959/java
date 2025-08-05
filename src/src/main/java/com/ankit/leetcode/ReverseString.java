package com.ankit.leetcode;

import java.util.Arrays;

/* 344: Reverse String
* Write a function that reverses a string. The input string is given as an array of characters s.
* You must do this by modifying the input array in-place with O(1) extra memory.
* */
public class ReverseString {


  public static void main(String[] args) {
    ReverseString rs = new ReverseString();
    char[] s = {'h', 'e', 'l', 'l', 'o'};
    rs.reverseString(s);
  }

  public void reverseString(char[] s) {
    int left = 0, right = s.length - 1;
    while (left < right) {
      char temp = s[left];
      s[left] = s[right];
      s[right] = temp;
      left++;
      right--;
    }
    System.out.println(Arrays.toString(s));
  }
}

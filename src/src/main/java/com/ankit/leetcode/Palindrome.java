package com.ankit.leetcode;

/* 9. Palindrome Number
 Given an integer x, return true if x is a palindrome, and false otherwise (without converting into String)
 Input: x= 121; Output: true
 Input: x= -121; Output: false
 Input: x= 10; Output: false
 Input: x= 0; Output: true
* */
public class Palindrome {

  public static void main(String[] args) {
    Palindrome p = new Palindrome();
    int x = 121;
    System.out.println(p.isPalindrome(x));
  }

  // Without converting into String
  public boolean isPalindrome(int x) {
    if(x < 0 || (x%10==0  && x!=0)) {
      return false;
    }

    int originalNum = x;
    int reversedNum = 0;

    while(originalNum > 0) {
      int digit = originalNum % 10;
      reversedNum = reversedNum * 10 + digit;
      originalNum = originalNum/10;
    }
    return x == reversedNum;
  }

  // With converting into String
  public boolean isPalindromeNotOptimized(int x) {
    if(x < 0) {
      return false;
    }

    String xStr = String.valueOf(x);
    String xStrReverse = new StringBuilder(xStr).reverse().toString();
    return xStr.equals(xStrReverse);
  }

}

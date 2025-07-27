package com.ankit.leetcode;

import java.util.Arrays;
import java.util.List;

/* 242. Valid Anagram
* Given two strings s and t, return true if t is an anagram of s, and false otherwise.
* Input: s = "anagram", t = "nagaram" -> Output: true
* Input: s = "rat", t = "car" -> Output: false
 * */
public class Anagram {

  public static void main(String[] args) {
    Anagram ag = new Anagram();
    String s = "rat";
    String t = "car";
    System.out.println(ag.isAnagram(s, t));
  }

  public boolean isAnagram(String s, String t) {
    if(s.length() != t.length()) {
      return false;
    }

    List<String> listS = Arrays.stream(s.split(""))
        .sorted()
        .toList();

    List<String> listT = Arrays.stream(t.split(""))
        .sorted()
        .toList();

    return listS.equals(listT);
  }

}

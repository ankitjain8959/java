package com.ankit.leetcode;

import java.util.Arrays;

/* 58. Length of Last Word
* Given a string s consisting of words and spaces, return the length of the last word in the string.
* Input: s = "Hello World" -> Output: 5
* Input: s = "   fly me   to   the moon  " -> Output: 4
* Input: s = "luffy is still joyboy" -> Output: 6
 * */
public class LengthOfLastWord {

  public static void main(String[] args) {
    LengthOfLastWord lw = new LengthOfLastWord();
    String s = "luffy is still joyboy ";
    System.out.println(lw.lengthOfLastWord(s));
  }

  public int lengthOfLastWord(String s) {
    String[] strArray = s.split("\\W+");
    String lastWord = strArray[strArray.length - 1];
    return lastWord.length();
  }

}

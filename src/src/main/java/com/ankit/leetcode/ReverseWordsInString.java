package com.ankit.leetcode;

public class ReverseWordsInString {

  public static void main(String[] args) {
    ReverseWordsInString rw = new ReverseWordsInString();
    String s = "a good   example";
    System.out.println(rw.reverseWords(s));
  }

  public String reverseWords(String s) {
    String[] strArray = s.trim().split("\\s+");
    String[] reversedStrArray = new String[strArray.length];
    for(int i=0; i< strArray.length; i++) {
      reversedStrArray[i] = strArray[strArray.length - 1 - i];
    }
    return String.join(" ", reversedStrArray);
  }

  public String reverseWordsUsingStringBuilder(String s) {
    String[] strArray = s.trim().split("\\s+");
    StringBuilder sb = new StringBuilder();
    for(int i=0; i< strArray.length; i++) {
      sb.append(strArray[strArray.length - 1 - i]);
      if(i != strArray.length-1) {
        sb.append(" ");
      }
    }
    return sb.toString();
  }
}

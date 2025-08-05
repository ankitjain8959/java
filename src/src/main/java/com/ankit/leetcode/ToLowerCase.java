package com.ankit.leetcode;

public class ToLowerCase {


  public static void main(String[] args) {
    ToLowerCase l = new ToLowerCase();
    String s1 = "";
    String s2 = " ";
    String s3 = "Ankit";
    String s4 = "Ankit\\";
    String s5 = null;
    System.out.println(l.toLowerCase(s1));
    System.out.println(l.toLowerCase(s2));
    System.out.println(l.toLowerCase(s3));
    System.out.println(l.toLowerCase(s4));
    System.out.println(l.toLowerCase(s5));
  }

  public String toLowerCase(String s) {
    return s == null ? null : s.toLowerCase();
  }
}

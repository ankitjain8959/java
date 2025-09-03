package com.ankit.practice.string;

public class StringMethods {


  public static void main(String[] args) {
    StringMethods sm = new StringMethods();
    String str1 = "Ankit Dimple";
    System.out.println(sm.subString(str1));

    String str2 = " ";
    System.out.println(sm.checkEmptyString(str2));
    System.out.println(sm.checkBlankString(str2));
  }


  public String subString(String str) {
    // using in-built subString()
    // return str.substring(0, 5);

    // without using in-built subString()
    char[] subStr = new char[5];
    for (int i = 0; i < 5; i++) {
      subStr[i] = str.charAt(i);
    }
    return new String(subStr);
  }


  public boolean checkEmptyString(String str) {
    return str.isEmpty();                     // checks the length of string is 0
  }

  public boolean checkBlankString(String str) {
    return str.isBlank();                     // checks for empty string without whitespaces
  }
}

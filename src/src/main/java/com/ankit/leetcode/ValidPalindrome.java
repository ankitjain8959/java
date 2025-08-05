package com.ankit.leetcode;

/*
 *
 * */
public class ValidPalindrome {

  public static void main(String[] args) {
    ValidPalindrome vp = new ValidPalindrome();
    String str = "Damosel, a poem? A carol? Or a cameo pale? (So mad!)";
    System.out.println(vp.isPalindrome(str));
  }

  public boolean isPalindrome(String s) {
    String str = s.replaceAll("[~`<>/?!;_@#$%^&*'\".,\\\\:+={}\\[\\]|\\-\\s()]", "").toLowerCase();
    boolean result = true;
    if (str.isEmpty()) {
      return result;
    }
    char[] strArray = str.toCharArray();
    int lastIndex = strArray.length-1;
    for(int i=0; i<=strArray.length/2 ; i++) {
      if(strArray[i] != strArray[lastIndex]) {
        result = false;
        break;
      }
      lastIndex--;
    }
    return result;
  }


  public boolean isPalindrome_usingReverseFunction(String s) {
    String str = s.replaceAll("[?!;@#$%^&*\".,:\\s]", "").toLowerCase();
    StringBuilder sb = new StringBuilder(str).reverse();
    return sb.toString().equals(str);
  }
}

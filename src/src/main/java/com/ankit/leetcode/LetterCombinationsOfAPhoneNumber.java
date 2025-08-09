package com.ankit.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class LetterCombinationsOfAPhoneNumber {

  public static void main(String[] args) {
    LetterCombinationsOfAPhoneNumber lc = new LetterCombinationsOfAPhoneNumber();
    String digits = "";
    System.out.println(lc.letterCombinations_WithoutBacktracking(digits));
  }

  public List<String> letterCombinations_WithoutBacktracking(String digits) {
    HashMap<Integer, String> mapping = mapping();
    if(digits == null || digits.trim().isEmpty()) {
      return List.of();
    }

    String[] strArray = digits.trim().split("");

    if(strArray.length==1) {
      int intValue = Integer.parseInt(strArray[0]);
      if (intValue == 1) return List.of();
      String str = mapping.get(intValue);
      String[] split = str.split("");
      return Arrays.stream(split)
          .collect(Collectors.toList());
    }

    if(strArray.length==2) {
      String str1 = mapping.get(Integer.parseInt(strArray[0]));
      String str2 = mapping.get(Integer.parseInt(strArray[1]));
      String[] split1 = str1.split("");
      String[] split2 = str2.split("");
      return Arrays.stream(split1)
          .flatMap(sp1 -> Arrays.stream(split2)
              .map(sp2 -> sp1+sp2))
          .collect(Collectors.toList());
    }

    if(strArray.length==3) {
      String str1 = mapping.get(Integer.parseInt(strArray[0]));
      String str2 = mapping.get(Integer.parseInt(strArray[1]));
      String str3 = mapping.get(Integer.parseInt(strArray[2]));
      String[] split1 = str1.split("");
      String[] split2 = str2.split("");
      String[] split3 = str3.split("");
      return Arrays.stream(split1)
          .flatMap(sp1 -> Arrays.stream(split2)
              .flatMap(sp2 -> Arrays.stream(split3)
                  .map(sp3 -> sp1+sp2+sp3)))
          .collect(Collectors.toList());
    }

    if(strArray.length==4) {
      String str1 = mapping.get(Integer.parseInt(strArray[0]));
      String str2 = mapping.get(Integer.parseInt(strArray[1]));
      String str3 = mapping.get(Integer.parseInt(strArray[2]));
      String str4 = mapping.get(Integer.parseInt(strArray[3]));
      String[] split1 = str1.split("");
      String[] split2 = str2.split("");
      String[] split3 = str3.split("");
      String[] split4 = str4.split("");
      return Arrays.stream(split1)
          .flatMap(sp1 -> Arrays.stream(split2)
              .flatMap(sp2 -> Arrays.stream(split3)
                  .flatMap(sp3 -> Arrays.stream(split4)
                      .map(sp4 -> sp1+sp2+sp3+sp4))))
          .collect(Collectors.toList());
    }

    return List.of();
  }


  public static HashMap<Integer, String> mapping() {
    HashMap<Integer, String> mapping = new HashMap<>();
    mapping.put(2, "abc");
    mapping.put(3, "def");
    mapping.put(4, "ghi");
    mapping.put(5, "jkl");
    mapping.put(6, "mno");
    mapping.put(7, "pqrs");
    mapping.put(8, "tuv");
    mapping.put(9, "wxyz");
    return mapping;
  }

}

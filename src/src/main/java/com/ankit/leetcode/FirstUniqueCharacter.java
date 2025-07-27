package com.ankit.leetcode;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/* 387. First Unique Character in a String
* Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
* Input: s = "leetcode" Output: 0
* Input: s = "loveleetcode" Output: 2
* Input: s = "aabb" Output: -1
 * */
public class FirstUniqueCharacter {

  public static void main(String[] args) {
    FirstUniqueCharacter f = new FirstUniqueCharacter();
    String s = "leetcode";
    System.out.println(f.firstUniqChar(s));
  }


  public int firstUniqChar(String s) {
    LinkedHashMap<String, Long> map = Arrays.stream(s.split(""))
        .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));

    String str = map.entrySet().stream()
        .filter(entry -> entry.getValue() == 1)
        .map(entry -> entry.getKey())
        .findFirst()
        .orElse(null);

    return str!=null ? s.indexOf(str) : -1;
  }

}

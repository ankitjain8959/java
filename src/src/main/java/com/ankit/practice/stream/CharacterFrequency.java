package com.ankit.practice.stream;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CharacterFrequency {

  public static void main(String[] args) {
    CharacterFrequency cf = new CharacterFrequency();

//    String str = "Bob hit a ball!";
//    Map<Character, Integer> resultMap1 = cf.findCharacterFrequencyBeforeJava8(str.toLowerCase());
//    System.out.println("Result1: " + resultMap1);
//
//    Map<Character, Long> resultMap2 = cf.findCharacterFrequencyAfterJava8(str.toLowerCase());
//    System.out.println("Result2: " + resultMap2);
//
//
//    List<String> list = List.of("Ankit", "Dimple", "Ankit");
//    Map<String, Integer> resultMap3 = cf.findFrequencyInListBeforeJava8(list);
//    System.out.println("Result3: " + resultMap3);
//
//    Map<String, Long> resultMap4 = cf.findFrequencyInListAfterJava8(list);
//    System.out.println("Result4: " + resultMap4);
//
//
//    String[] strArray = {"Ankit", "Dimple", "Ankit"};
//    Map<String, Integer> resultMap5 = cf.findFrequencyInArrayBeforeJava8(strArray);
//    System.out.println("Result5: " + resultMap5);
//
//    Map<String, Long> resultMap6 = cf.findFrequencyInArrayAfterJava8(strArray);
//    System.out.println("Result6: " + resultMap6);

    String inputString = "Java Character Frequency";
    String[] strArray = inputString.replaceAll(" ", "").toLowerCase().split("");

    System.out.println("All Duplicate Character: " + cf.allDuplicateCharacters(strArray));
    System.out.println("First Duplicate Character: " + cf.firstDuplicateCharacter(strArray));
    System.out.println("Last Duplicate Character: " + cf.lastDuplicateCharacter(strArray));
    System.out.println("First Non Repeated Character: " + cf.firstNonRepeatedCharacter(strArray));
  }

  public Map<Character, Integer> findCharacterFrequencyBeforeJava8(String str) {
    Map<Character, Integer> map = new HashMap<>();
    for(int i=0; i<str.length(); i++) {
      char ch = str.charAt(i);
      map.put(ch, map.getOrDefault(ch, 0) + 1);
    }

  /*
    char[] ch = str.toLowerCase().toCharArray();
    for(char eachCh: ch) {
      map.put(eachCh,  map.getOrDefault(eachCh, 0) + 1);
    }
  */

    return map;
  }

  public Map<Character, Long> findCharacterFrequencyAfterJava8(String str) {
    return str.chars()
        .mapToObj(ch -> (char) ch)
        .collect(Collectors.groupingBy(cha -> cha, Collectors.counting()));
  }



  public Map<String, Integer> findFrequencyInListBeforeJava8(List<String> listStr) {
    Map<String, Integer> map = new HashMap<>();
    for(String str: listStr) {
      map.put(str, map.getOrDefault(str, 0) + 1);
    }
    return map;
  }

  public Map<String, Long> findFrequencyInListAfterJava8(List<String> listStr) {
    return listStr.stream().collect(Collectors.groupingBy(st -> st, Collectors.counting()));
  }


  public Map<String, Integer> findFrequencyInArrayBeforeJava8(String[] strArray) {
    Map<String, Integer> map = new HashMap<>();
    for(String st: strArray) {
      map.put(st, map.getOrDefault(st, 0) + 1);
    }
    return map;
  }

  public Map<String, Long> findFrequencyInArrayAfterJava8(String[] strArray) {
    return Arrays.stream(strArray).collect(Collectors.groupingBy(st -> st, Collectors.counting()));
  }

  public Set<String> allDuplicateCharacters(String[] strArray) {
    HashSet<String> set = new HashSet<>();
    return Arrays.stream(strArray)
        .filter(str -> !set.add(str))
        .collect(Collectors.toSet());
  }

  public String firstDuplicateCharacter(String[] strArray) {
    HashSet<String> set = new HashSet<>();
    return Arrays.stream(strArray)
        .filter(str -> !set.add(str))
        .findFirst()
        .orElse("No Duplicate Character Found");
  }

  public String lastDuplicateCharacter(String[] strArray) {
    Map<String, Long> resultMap = Arrays.stream(strArray)
        .collect(Collectors.groupingBy(str -> str, LinkedHashMap::new, Collectors.counting()));

    List<String> list = resultMap.entrySet().stream()
        .filter(m -> m.getValue() > 1)
        .map(m -> m.getKey())
        .toList();

    return list.get(list.size()-1);
  }

  public String firstNonRepeatedCharacter(String[] strArray) {
    Map<String, Long> resultMap = Arrays.stream(strArray)
        .collect(Collectors.groupingBy(str -> str, LinkedHashMap::new, Collectors.counting()));

    return resultMap.entrySet().stream()
        .filter(entry -> entry.getValue() == 1)
        .map(entry -> entry.getKey())
        .findFirst()
        .orElse("No Character Found");
  }
}

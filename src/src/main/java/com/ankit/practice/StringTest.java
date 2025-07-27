package com.ankit.practice;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringTest {

  public static void main(String[] args) {
    StringTest cj = new StringTest();
    System.out.println("String after joining: " + cj.joinStringAndAddPrefixSuffix());
    System.out.println("Check if two strings are Anagram: " + cj.anagram());
    System.out.println("Only Duplicate Element and it's count:" + cj.findDuplicateElements());
    System.out.println("Count Elements:" + cj.countAllElement());
  }

  public String joinStringAndAddPrefixSuffix() {
    List<String> listStr = List.of("Ankit", "and", "Dimple", "are", "together");
    return listStr.stream()
        .collect(Collectors.joining(" ", "[", "]"));

    /*
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    int size = listStr.size();
    int lastIndex = size - 1;
    int idx = 0;

    while(idx < lastIndex) {
      sb.append(listStr.get(idx)).append(",");
      idx++;
    }
    sb.append(listStr.get(lastIndex)).append("]");
    return sb.toString();
    */
  }


  public boolean anagram() {
    String s1 = "RaceCar";
    String s2 = "CarRace";
    var l1 = Stream.of(s1.split("")).sorted().collect(Collectors.joining());
    var l2 = Stream.of(s2.split("")).sorted().collect(Collectors.joining());

    return l1.equalsIgnoreCase(l2);
  }


  public Entry<String, Long> findDuplicateElements() {
    List<String> names = Arrays.asList("BB", "AA", "AA", "CC");
    Map<String, Long> map = names.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    return map.entrySet().stream()
        .max((entryA, entryB) -> entryA.getValue().compareTo(entryB.getValue()))
        .orElse(null);
  }


  public Map<String, Long> countAllElement() {
    List<String> names = Arrays.asList("AA", "BB", "AA", "CC");
    return names.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
  }
}

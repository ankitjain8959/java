package com.ankit.practice.stream;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;


public class RemoveDuplicates {
  private static final Logger LOGGER = Logger.getLogger("RemoveDuplicates");

  
  public static void main(String[] args) {
    RemoveDuplicates rd = new RemoveDuplicates();
    List<String> str = List.of("Ankit", "Dimple", "Ankit", "John", "Buck", "Dimple");

    LOGGER.info("Start Time 1: " + System.currentTimeMillis());
    List<String> result1 = rd.removeDuplicatesUsingDistinct(str);
    LOGGER.info("Result 1: " + result1);
    LOGGER.info("End Time 1: " + System.currentTimeMillis());

    LOGGER.info("Start Time 2: " + System.currentTimeMillis());
    Set<String> result2 = rd.removeDuplicatesUsingSet(str);
    LOGGER.info("Result 2: " + result2);
    LOGGER.info("Start Time 2: " + System.currentTimeMillis());

    LOGGER.info("Start Time 3: " + System.currentTimeMillis());
    Set<String> result3 = rd.removeDuplicatesUsingMap(str);
    LOGGER.info("Result 3: " + result3);
    LOGGER.info("Start Time 3: " + System.currentTimeMillis());
  }

  // If insertion order is to be preserved; use distinct() as it uses LinkedHashSet internally which maintains insertion order
  public List<String> removeDuplicatesUsingDistinct(List<String> str) {
    return str.stream()
        .distinct()
        .toList();
  }

  // If insertion order is not important, then use this approach as it's faster compared to above
  public Set<String> removeDuplicatesUsingSet(List<String> str) {
    return new HashSet<>(str);        //uses HashMap internally
  }

  public Set<String> removeDuplicatesUsingMap(List<String> str) {
    Map<String, Integer> map = new HashMap<>();
    for(String st: str) {
      if(map.getOrDefault(st, 0) == 0) {
       map.put(st, 1);
      }
    }
    return map.keySet();
  }

}


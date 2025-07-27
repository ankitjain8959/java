package com.ankit.practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Limit {

  public static void main(String[] args) {
    List<Integer> listOfIntegers = Arrays.asList(45, 12, 56, 15, 24, 75, 31, 89);
    List<Integer> sortedListOfIntegers = listOfIntegers.stream().sorted().toList();
    System.out.println(sortedListOfIntegers);
    List<Integer> threeMinListOfIntegers = listOfIntegers.stream().sorted().limit(3).toList();
    System.out.println(threeMinListOfIntegers);
    List<Integer> threeMaxListOfIntegers = listOfIntegers.stream().sorted(Comparator.reverseOrder()).limit(3).toList();
//    List<Integer> threeMaxListOfIntegers = listOfIntegers.stream().sorted().skip(listOfIntegers.size() - 3).toList();
    System.out.println(threeMaxListOfIntegers);
  }

}

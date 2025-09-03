package com.ankit.practice.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/*
  Problem Statement: Given a list of integers, separate odd & even numbers
*/
public class SeparateOddEven {

  public static void main(String[] args) {
    SeparateOddEven soe = new SeparateOddEven();
    List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    soe.separateOddEvenBeforeJava8(numbers);
    soe.separateOddEvenWithJava8(numbers);
  }


  public void separateOddEvenBeforeJava8(List<Integer> numbers) {
    List<Integer> evenNumbers = new ArrayList<>();
    List<Integer> oddNumbers = new ArrayList<>();
    for (Integer number : numbers) {
      if (number % 2 == 0) {
        evenNumbers.add(number);
      } else {
        oddNumbers.add(number);
      }
    }

    System.out.println("Even numbers list: " + evenNumbers);
    System.out.println("Odd numbers list: " + oddNumbers);
  }

  public void separateOddEvenWithJava8(List<Integer> numbers) {
    // Using individual streams to filter even and odd numbers
    List<Integer> evenNumbers = numbers.stream()
        .filter(number -> number % 2 == 0)
        .toList();
    List<Integer> oddNumbers = numbers.stream()
        .filter(number -> number % 2 != 0)
        .toList();
    System.out.println("Even numbers list: " + evenNumbers);
    System.out.println("Odd numbers list: " + oddNumbers);

    // Using a Map to store even and odd numbers
    Map<String, List<Integer>> map = new HashMap<>();
    List<Integer> evenList = new ArrayList<>();
    List<Integer> oddList = new ArrayList<>();
    for(Integer number: numbers) {
      if(number % 2 == 0) {
        evenList.add(number);
      } else {
        oddList.add(number);
      }
    }
    map.put("Even", evenList);
    map.put("Odd", oddList);

    System.out.println("Even numbers list: " + map.get("Even"));
    System.out.println("Odd numbers list: " + map.get("Odd"));

    // Using partitioningBy to separate odd and even numbers
    var resultMap = numbers.stream()
        .collect(Collectors.partitioningBy(number -> number % 2 == 0));
    System.out.println("Even numbers list: " + resultMap.get(true));
    System.out.println("Odd numbers list: " + resultMap.get(false));
  }
}

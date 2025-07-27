package com.ankit.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FilterValues {

  public static void main(String[] args) {
    FilterValues f = new FilterValues();
    List<Integer> list = Arrays.asList(15, 9, 8, 11, 13, 2, 10, 20, 15);
    System.out.println("Result1: " + f.filterValuesWithoutStream(list));
    System.out.println("Result2: " + f.filterValuesWithStream(list));

    List<String> listOfStrings = Arrays.asList("One", "Two", "Three", "Four", "Five", "Six");
    System.out.println("Last Element Of List: " + f.lastElementOfList(listOfStrings));

    List<String> listOfStringsWithNumbers = Arrays.asList("One", "2wo", "3hree", "Four", "5ive", "Six");
    System.out.println("List Of Strings With Numbers: " + f.filterStringsWithNumber(listOfStringsWithNumbers));

  }

  public List<Integer> filterValuesWithoutStream(List<Integer> list) {
    List<Integer> resultList = new ArrayList<>();
    for(Integer i: list) {
      if(i%5==0) {
        resultList.add(i);
      }
    }
    Collections.sort(resultList);
    return resultList;
  }


  public List<Integer> filterValuesWithStream(List<Integer> list) {
    return list.stream()
        .filter(num -> num%5==0)
        .sorted()
        .collect(Collectors.toList());
  }

  public String lastElementOfList(List<String> listOfStrings) {
    return listOfStrings.get(listOfStrings.size() - 1);
  }


  public List<String> filterStringsWithNumber(List<String> listOfStringsWithNumber) {
//    return listOfStrings.stream().filter(str -> Character.isDigit(str.charAt(0))).toList();

    return listOfStringsWithNumber.stream()
        .filter(str -> str.matches("^\\d.*"))
        .toList();
  }
}

package com.ankit.practice;

import java.util.Arrays;
import java.util.List;

public class MinMax {

  public static void main(String[] args) {
    MinMax mm = new MinMax();
    List<Integer> list = Arrays.asList(15, 9, 8, 11, 13, 2, 10, 20, 15, 20);
    System.out.println("Result1: " + mm.maxWithoutStream(list));
    System.out.println("Result2: " + mm.maxWithStream(list));
    System.out.println("Result3: " + mm.maxWithStreamReduce(list));

    System.out.println("Result4: " + mm.minWithoutStream(list));
    System.out.println("Result5: " + mm.minWithStream(list));
    System.out.println("Result6: " + mm.minWithStreamReduce(list));
  }

  public Integer maxWithoutStream(List<Integer> list) {
//    list.sort((n1,n2) -> n1.compareTo(n2));
    list.sort(Integer::compareTo);
    return list.get(list.size() - 1);
  }


  public Integer maxWithStream(List<Integer> list) {
//    return list.stream().max((n1,n2) -> n1.compareTo(n2)).orElse(0);
    return list.stream()
        .max(Integer::compareTo)
        .orElse(0);
  }

  public Integer maxWithStreamReduce(List<Integer> list) {
    return list.stream()
        .reduce((a, b) -> (a >= b) ? a : b)
        .orElse(0);
  }


  public Integer minWithoutStream(List<Integer> list) {
    list.sort(Integer::compareTo);
    return list.get(0);
  }


  public Integer minWithStream(List<Integer> list) {
    return list.stream()
        .min(Integer::compareTo)
        .orElse(0);
  }

  public Integer minWithStreamReduce(List<Integer> list) {
    return list.stream()
        .reduce((n1, n2) -> n1<=n2 ? n1 : n2)
        .orElse(0);
  }
}

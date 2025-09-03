package com.ankit.practice.stream;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MergeArrays {
  public static void main(String[] args) {
    MergeArrays merge = new MergeArrays();
    int[] arr1 = {1, 3, 5, 7};
    int[] arr2 = {2, 4, 6, 8};
    int[] list1 = merge.mergeArraysWithoutStream(arr1, arr2);
    System.out.println(Arrays.toString(list1));

    int[] list2 = merge.mergeArraysWithStream(arr1, arr2);
    System.out.println(Arrays.toString(list2));
  }


  public int[] mergeArraysWithoutStream(int[] arr1, int[] arr2) {
    int[] result = new int[arr1.length + arr2.length];
    for(int i=0; i < arr1.length; i++) {
      result[i] = arr1[i];
    }

    for(int i=0; i < arr2.length; i++) {
      result[i+arr1.length] = arr2[i];
    }

    Arrays.sort(result);
    return result;
  }


  public int[] mergeArraysWithStream(int[] arr1, int[] arr2) {
    return IntStream.concat(IntStream.of(arr1), IntStream.of(arr2))
        .sorted().distinct().toArray();
  }

}

package com.ankit.practice.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Integers {

  public static void main(String[] args) {
    Integers integers = new Integers();
    int num = 15623;
    int sum = integers.sumOfAllDigits(num);
    System.out.println(sum);

    List<Integer> listOfIntegers = Arrays.asList(45, 12, 56, 15, 24, 75, 31, 89);
    int result = integers.secondLargestNumber(listOfIntegers);
    System.out.println(result);

    List<String> listOfStrings = Arrays.asList("Java", "Python", "C#", "HTML", "Kotlin", "C++", "COBOL", "C");
    listOfStrings.stream().sorted((s1,s2) -> s1.length()>s2.length() ? 0: (s1.length()==s2.length() ? 0 : -1)).forEach(System.out::println);


    int[] intArray = new int[] {45, 12, 56, 15, 24, 75, 31, 89};
    int sumArray = IntStream.of(intArray).sum();
    System.out.println(sumArray);
    OptionalDouble avgArray = IntStream.of(intArray).average();
    System.out.println(avgArray.orElse(0.0));


    String str = "Java Concept Of The Day";
    String resultStr = integers.reverseEachStringWithStream(str);
    System.out.println(resultStr);


    int sum1 = integers.sumOfFirstTenNumbers();
    System.out.println(sum1);

    int[] array = new int[] {5, 1, 7, 3, 9, 6};
    int[] reverseArray = integers.reverseArrayWithoutStream(array);
    System.out.println(Arrays.toString(reverseArray));

    int[] reverseArray2 = integers.reverseArrayWithStream(array);
    System.out.println(Arrays.toString(reverseArray2));


    List<String> listOfStrings1 = Arrays.asList("Pen", "Eraser", "Note Book", "Pen", "Pencil", "Pen", "Note Book", "Pencil");
    String r = integers.mostRepeatedElement(listOfStrings1);
    System.out.println(r);

    System.out.println(integers.palindrome("ROTATOR"));


    System.out.println("First 10 Odd Numbers: " + Arrays.toString(integers.firstTenEvenNumbers()));
    System.out.println("First 10 Odd Numbers: " + Arrays.toString(integers.firstTenOddNumbers()));
    System.out.println("Fibonacci Series: " + Arrays.toString(integers.fibonacciSeries()));
    System.out.println("Numbers Starting with 1: " + integers.numbersStartingWithOne());
    System.out.println("Duplicate Elements in a List: " + integers.duplicateElements());
    System.out.println("Maximum Element in an Array: " + integers.maxElement());
    System.out.println("Sort Decimal without stream: " + integers.sortDecimalWithoutStream());
    System.out.println("Sort Decimal with stream: " + integers.sortDecimalWithStream());
  }


  public int sumOfAllDigits(int num) {
    return Stream.of(String.valueOf(num).split("")).mapToInt(n -> Integer.parseInt(n)).sum();
  }

  public int secondLargestNumber(List<Integer> listOfIntegers) {
    return listOfIntegers.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(0);
  }

  public String reverseEachStringWithStream(String str) {
    return Stream.of(str.split(" ")).map(s -> new StringBuilder(s).reverse()).collect(Collectors.joining());
  }

  public int sumOfFirstTenNumbers() {
    return IntStream.rangeClosed(1, 10).sum();
  }


  public int[] reverseArrayWithoutStream(int[] arr) {
    int len = arr.length;
    int[] reverseArray = new int[len];
    for(int i=0; i<len;i++) {
      reverseArray[i] = arr[len-1-i];
    }

    return reverseArray;
  }

  public int[] reverseArrayWithStream(int[] arr) {
    return IntStream.rangeClosed(1, arr.length).map(num -> arr[arr.length - num]).toArray();
  }


  public String mostRepeatedElement(List<String> list) {
    Map<String, Long> group = list.stream().collect(Collectors.groupingBy(n -> n, Collectors.counting()));
    var r = group.entrySet().stream().max((o1, o2) -> o1.getValue().compareTo(o2.getValue())).get();
    return r.getKey();
  }


  public boolean palindrome(String str) {
//    StringBuilder sb = new StringBuilder(str).reverse();
//    return sb.toString().equalsIgnoreCase(str);

//    boolean result = true;
//    for(int i=0; i<str.length()/2; i++) {
//      if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
//        result = false;
//        break;
//      }
//    }
//    return result;

    return IntStream.rangeClosed(0, str.length()/2).noneMatch(i -> str.charAt(i) != str.charAt(str.length()-1-i));
  }





  public void extractDuplicateElements() {
    List<Integer> listOfIntegers = Arrays.asList(111, 222, 333, 111, 555, 333, 777, 222);
//    Map<Integer, Long> map = listOfIntegers.stream().collect(Collectors.groupingBy(n -> n, Collectors.counting()));
//    map.entrySet().stream().filter(m -> m.getValue() > 1).forEach(m1 -> System.out.println(m1.getKey()));

    HashSet<Integer> set = new HashSet<>();
    System.out.println(listOfIntegers.stream().filter(element -> !set.add(element)).toList());
  }



  public int[] firstTenEvenNumbers() {
    return IntStream.rangeClosed(1, 10)
        .map(num -> num * 2)
        .toArray();
  }

  public int[] firstTenOddNumbers() {
    return IntStream.rangeClosed(1, 20)
        .filter(num -> num%2!=0)
        .toArray();
  }

  public int[] fibonacciSeries() {
    // 0 1 1 2 3 5 8 13
    int[] arr = new int[10];
    arr[0] = 0;
    arr[1] = 1;
    for(int i=2; i<10; i++) {
      arr[i] = arr[i-1] + arr[i-2];
    }
    return arr;
  }


  public List<Integer> numbersStartingWithOne() {
    List<Integer> myList = Arrays.asList(10,15,8,49,25,98,32);

    return myList.stream()
        .filter(num -> String.valueOf(num).startsWith("1"))
        .toList();
  }


  public List<Integer> duplicateElements() {
    List<Integer> myList = Arrays.asList(10,15,8,49,25,98,98,32,15);
    Map<Integer, Long> map = myList.stream()
        .collect(Collectors.groupingBy(Function.identity(), () -> new LinkedHashMap<>(), Collectors.counting()));

    return map.entrySet().stream()
        .filter(entry -> entry.getValue() > 1)
        .map(entry -> entry.getKey())
        .toList();
  }

  public int maxElement() {
    int[] arr = {12,19,20,88,0,9};

    return Arrays.stream(arr)
        .max()
        .orElse(0);
  }

  public List<Double> sortDecimalWithoutStream() {
    List<Double> list = Arrays.asList(10.2, 9.1, 11.3);       //Arrays.asList creates a mutable list (i.e. modifiable list)
    list.sort((l1, l2) -> -l1.compareTo(l2));   // This won't work when List.of() is used because List.of() returns an Immutable list (i.e. unmodifiable list)
    return list;
  }


  public List<Double> sortDecimalWithStream() {
    List<Double> list = Arrays.asList(10.2, 9.1, 11.3);
    return list.stream()
        .sorted((l1, l2) -> - l1.compareTo(l2)).collect(Collectors.toList());

    /*
    return list.stream()
        .sorted(Collections.reverseOrder()).collect(Collectors.toList());
    */
  }
}

package com.ankit.impl;

import com.ankit.interfaces.MyPredicate;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class PredicateImpl {

  public static void main(String[] args) {

    // MyPredicate<T> is a functional interface i.e. it has only one abstract method. It has a single argument function that returns a boolean.
    MyPredicate<Integer> customImpl = i -> i < 0;
    System.out.println(customImpl.isNegative(-10));
    System.out.println(customImpl.isNegative(10));


    // For the above purpose, we can use a predefined functional interface in Java i.e java.util.function.Predicate<T>. It is a functional interface that represents a single argument function that returns a boolean.
    //Predicate with Integer
    Predicate<Integer> intPredicate = i -> i < 0;
    System.out.println(intPredicate.test(-10));
    System.out.println(intPredicate.test(10));


    //Predicate with String
    Predicate<String> stringPredicate = s -> s.startsWith("A");
    System.out.println(stringPredicate.test("Ankit"));
    System.out.println(stringPredicate.test("Dimple"));

    // BiPredicate
    BiPredicate<String, String> biPredicate = (s1, s2) -> s1.equals(s2);
    System.out.println(biPredicate.test("Ankit", "Ankit"));
    System.out.println(biPredicate.test("Ankit", "Dimple"));
  }
}

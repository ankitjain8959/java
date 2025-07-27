package com.ankit.impl;

import com.ankit.interfaces.MyFunction;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionExample {

  public static void main(String[] args) {
    // MyFunction is a custom functional interface that has a single abstract method apply() which takes a String argument and returns an Integer.
    MyFunction<String, Integer> myFunction = str -> str.length();
    System.out.println(myFunction.apply("Hello, World!"));

    // Using the predefined functional interface Function<T, R> from java.util.function package
    Function<String, Integer> functionJava = str -> str.length();
    System.out.println(functionJava.apply("Hello, Java!"));

    // Using BiFunction to concatenate two strings and return their combined length
    BiFunction<String, String, Integer> biFunction = (str1, str2) -> str1.length() + str2.length();
    System.out.println(biFunction.apply("Hello, ", "World!"));
  }
}

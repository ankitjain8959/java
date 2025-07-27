package com.ankit.impl;

import com.ankit.interfaces.MyUnaryOperator;
import java.util.function.UnaryOperator;

public class UnaryOperatorImpl {

  public static void main(String[] args) {
    //MyUnaryOperator is a functional interface that takes a single argument and returns a value of the same type.
    MyUnaryOperator<String> myUnaryOperator = s -> s.toUpperCase();
    System.out.println(myUnaryOperator.apply("hello world"));

    // Using the predefined functional interface UnaryOperator<T> from java.util.function package
    UnaryOperator<String> unaryOperatorJava = s -> s.toUpperCase();
    System.out.println(unaryOperatorJava.apply("hello java"));

    // Using BinaryOperator to concatenate two strings
    UnaryOperator<Integer> binaryOperator = i -> i + 20;
    System.out.println(binaryOperator.apply(10));
  }
}

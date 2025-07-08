package com.ankit.interfaces;

@FunctionalInterface
public interface MyConsumer <String> {
  void store(String str);
}

/*
  Consumer:
  The above defined custom functional interface is similar to the predefined functional interface Consumer<T> in Java.
  Consumer<T> is a functional interface that represents an operation that accepts a single input argument and returns no result.
  It has a single abstract method accept(T t) that takes an argument of type T and performs some operation on it.
  So, instead of creating a custom functional interface, we can use the predefined functional interface Consumer<T> which is available in the java.util.function package.
  Consumer<T> is useful when we want to perform an operation on a single value without returning any result.

  BiConsumer:
  Similarly, there is one more predefined functional interface BiConsumer<T, U> which represents an operation that accepts two input arguments and returns no result.
  BiConsumer<T, U> is useful when we want to perform an operation on two values without returning any result.
  Very useful for Map operations where we want to perform an operation on each key-value pair in the map.
*/

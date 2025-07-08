package com.ankit.interfaces;

@FunctionalInterface
public interface MyFunction<String, Integer> {
  Integer apply(String str);

}

/*
  Function:
  The above defined custom functional interface is similar to the predefined functional interface Function<T, R> in Java.
  Function<T, R> is a functional interface that represents a function that takes an argument of type T and returns a value of type R.
  It has a single abstract method apply(T t) that takes an argument of type T and returns a value of type R.
  So, instead of creating a custom functional interface, we can use the predefined functional interface Function<T, R> which is available in the java.util.function package.
  Function<T, R> is useful when we want to transform a value of type T into a value of type R.

  BiFunction<T, U, R>:
  Similarly, there is one more predefined functional interface BiFunction<T, U, R> which represents a function that takes two arguments of types T and U and returns a value of type R.
  BiFunction<T, U, R> is useful when we want to transform two values of types T and U into a value of type R.
*/

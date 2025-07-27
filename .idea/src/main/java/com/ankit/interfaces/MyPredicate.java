package com.ankit.interfaces;

@FunctionalInterface
public interface MyPredicate<Integer> {
  boolean isNegative(Integer t);

}

/*
Predicate:
The above defined custom functional interface is similar to the pre-defined functional interface Predicate<T> in Java.
Predicate is a functional interface that represents a single argument function that returns a boolean.
So, instead of creating a custom functional interface, we can use the predefined functional interface Predicate<T> which is available in the java.util.function package.
Predicate is useful when we want to evaluate a condition on a single value and return a boolean based on that condition.

BiPredicate:
Similarly, there is one more predefined functional interface BiPredicate<T, U> which represents a function that takes two arguments and returns a boolean.
BiPredicate is useful when we want to compare two values and return a boolean based on the comparison.
*/



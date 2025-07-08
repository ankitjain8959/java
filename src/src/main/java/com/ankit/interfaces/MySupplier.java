package com.ankit.interfaces;

@FunctionalInterface
public interface MySupplier<StringBuilder> {
  StringBuilder getStringBuilder();

}

/*
Supplier:
The above defined custom functional interface is similar to the predefined functional interface Supplier<T> in Java.
Supplier is a functional interface that represents a supplier of results.
It has a single abstract method get() that returns a value of type T.
So, instead of creating a custom functional interface, we can use the predefined functional interface Supplier<T> which is available in the java.util.function package.
Supplier is useful when we want to provide a value without taking any input. It is often used in lazy initialization or when we want to generate a value on demand.
*/
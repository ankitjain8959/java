package com.ankit.impl;

import com.ankit.interfaces.MySupplier;
import java.util.function.Supplier;

public class SupplierImpl {

  public static void main(String[] args) {

    // MySupplier<T> is a custom functional interface that has a single abstract method getStringBuilder() which returns a StringBuilder object.
    MySupplier<StringBuilder> supplier = () -> new StringBuilder("Hello, World!");
    System.out.println(supplier.getStringBuilder().toString());

    // Using the predefined functional interface Supplier<T> from java.util.function package to create a StringBuilder object
    Supplier<StringBuilder> supplierJava = () -> new StringBuilder("Hello, Java!");
    System.out.println(supplierJava.get().toString());

    // Using the predefined functional interface Supplier<T> to generate a random integer
    Supplier<Double> supplierDouble = () -> Math.random();
    System.out.println("Random Double: " + supplierDouble.get() + 0.1);
  }
}

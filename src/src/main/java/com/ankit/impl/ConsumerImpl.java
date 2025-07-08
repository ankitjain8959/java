package com.ankit.impl;

import com.ankit.interfaces.MyConsumer;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ConsumerImpl {

  public static void main(String[] args) {
    // MyConsumer<T> is a custom functional interface that has a single abstract method store() which takes a single argument of type String and returns void.
    MyConsumer<String> myConsumer = s -> System.out.println(s);
    myConsumer.store("Ankit");

    // Using the predefined functional interface Consumer<T> from java.util.function package
    Consumer<String> consumerJava = s -> System.out.println("Hello, " + s);
    consumerJava.accept("Dimple");

    BiConsumer<String, String> biConsumerJava = (s1, s2) -> System.out.println("Hello, " + s1 + " and " + s2);
    biConsumerJava.accept("Ankit", "Dimple");
  }

}

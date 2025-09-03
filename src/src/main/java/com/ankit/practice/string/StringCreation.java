package com.ankit.practice.string;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

/*
 * String can be created in 2 ways,
 * 1. by using double quotes (also, known as String literals) - Objects are stored in the `string constant pool` (special area of heap memory)
 * 2. by using the `String` class constructor i.e. using `new` keyword - Objects are stored in the heap memory; unless using an intern() method which forces the storage to  `string constant pool`
 *
 * Use tools such as: VisualVM or JProfiler for Heap Dump and want to verify the number of instances created
 * */
public class StringCreation {

  public static void main(String[] args) throws IOException {
    StringCreation sc = new StringCreation();
    sc.getHashCode();

    // to verify the number of objects created in JVisualVM monitor
    sc.checkNumberOfObjectCreated_3Instances();
  }

  public void getHashCode() {
    String str1 = "Ankit";
    String str2 = new String("Ankit");
    String str3 = "Ankit";
    String str4 = new String("Ankit");
    String str5 = new String("Ankit").intern();

    System.out.println(str1 + "-default-" + System.identityHashCode(
        str1));        // System.identifyHashCode calls default hashCode from Object class even if you override it; which return an int value of the object's memory address
    System.out.println(str1 + "-string-"
        + str1.hashCode());                       // calling overridden hashCode method from String implementation; which has its own calculation of hashCode

    System.out.println(str2 + "-default-" + System.identityHashCode(str2));
    System.out.println(str1 + "-string-" + str2.hashCode());

    System.out.println(str2 + "-default-" + System.identityHashCode(str3));
    System.out.println(str1 + "-string-" + str2.hashCode());

    System.out.println(str4 + "-default-" + System.identityHashCode(str4));
    System.out.println(str1 + "-string-" + str2.hashCode());

    System.out.println(str5 + "-default-" + System.identityHashCode(str5));
    System.out.println(str5 + "-string-" + str5.hashCode());
  }


  /*
  * 1 instance is created for the above code
    Line 1: "test" → String literal "test" is created in the String Pool during the class load time (inside the heap’s special area called the string intern pool). Managed by JVM, and JVM adds it if it doesn’t already exist & <String str1 = "test"> does not create a new object however it returns the reference to the existing "test' int he pool
    Line 2: "test" → already exists in the String Pool, so no new literal is created.
  * */
  public void checkNumberOfObjectCreated_1Instance() throws IOException {
    String str1 = "test1";                            //Line1
    String str2 = "test1";                            //Line2

    System.out.println("Objects created. Press Enter to exit...");
    System.in.read();                                 // Wait so we can inspect the JVM in JVisualVM
  }

  /*
  * 2 instances are created for the above code
    Line 1: "test2" → String literal "test2" is created in the String Pool during the class load time (inside the heap’s special area called the string intern pool). Managed by JVM, and JVM adds it if it doesn’t already exist.
    Line 2: "test2" → already exists in the String Pool, so no new literal is created & new String("test2") → creates another String object in the heap (outside the pool) with the same content as the pooled "test2".
  * */
  public void checkNumberOfObjectCreated_2Instances() throws IOException {
    String str1 = "test2";                                   //Line1
    String str2 = new String("test2");                //Line2

    System.out.println("Objects created. Press Enter to exit...");
    System.in.read();                                         // Wait so we can inspect the JVM in JVisualVM
  }

  /*
  * 3 instances are created for the above code
    Line 1: "test3" → String literal "test3" is created in the String Pool during the class load time (inside the heap’s special area called the string intern pool). Managed by JVM, and JVM adds it if it doesn’t already exist & new String("test3") → creates a new String object in the heap (outside the pool) with the same content as the pooled "test3".
    Line 2: "test3" → already exists in the String Pool, so no new literal is created & new String("test3") → creates another String object in the heap (outside the pool) with the same content as the pooled "test3".
  * */
  public void checkNumberOfObjectCreated_3Instances() throws IOException {
    String str1 = new String("test3");                //Line1
    String str2 = new String("test3");                //Line2

    System.out.println("Objects created. Press Enter to exit...");
    System.in.read();                                         // Wait so we can inspect the JVM in JVisualVM
  }
}

package com.ankit.practice.string;

/*
 * equals() is a method & == is an operator
 * equals() check for content comparison whereas == is used for comparing references (if the objects point to the same memory location)
 *
 * Ideally, I would use == often to check if an object reference is null => if(obj == null) and I would use equals for content comparison
 *
 * */
public class StringComparison {

  public static void main(String[] args) {
    String str1 = "Ankit";
    String str2 = new String("Ankit");
    String str3 = "Ankit";
    String str4 = new String("Ankit");
    String str5 = new String("Ankit").intern();  //force a string to be stored in the String pool instead of heap memory

    System.out.println(str1 == str2);                    //false
    System.out.println(str1.equals(str2));               //true

    System.out.println(str1 == str3);                    //true
    System.out.println(str1.equals(str3));               //true

    System.out.println(str2 == str4);                    //false
    System.out.println(str2.equals(str4));               //true

    System.out.println(str1 == str5);                    //true
    System.out.println(str1.equals(str5));               //true
  }
}

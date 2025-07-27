package com.ankit.stream;


import java.util.Arrays;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Terminal operations are the final step in the stream processing pipeline and produce a result or a side effect.
 * Examples of terminal operations include `reduce()`, `collect()`, and `average()`.
 *
 * This class contains methods to showcase the usage of:
 * 1. `reduce()` - Combines elements of a stream into a single result.
 * 2. `average()` - Calculates the average of numeric elements in a stream.
 * 3. `collect()` - Transforms elements of a stream into a collection or other data structure.
 *
 * **Key Concepts:**
 * - Terminal operations trigger the processing of the stream pipeline.
 * - Intermediate operations (e.g., `filter`, `map`) are lazy and do not produce results until a terminal operation is invoked.
 * - Streams are consumed after a terminal operation and streams cannot be reused.
 * - Terminal operations can be used without any intermediate operations, but intermediate operations cannot be used without a terminal operation because they do not produce a result until a terminal operation is invoked.
 *
 * <pre>
 * **Examples:**
 * - Concatenating strings using `reduce()`.
 * - Calculating the average of numeric values using `average()`.
 * - Collecting elements into a `List`, `Map`, or `Set` using `collect()`.
 *</pre>
 *
 * **Dependencies:**
 * - Java Streams API
 * - `java.util.stream` package
 * - `java.util.Arrays` for array manipulation
 * - `java.util.TreeMap` for sorted map operations
 * - `java.util.stream.Collectors` for predefined collectors
 * - `java.util.stream.IntStream` for specialized numeric streams
 * - `java.util.stream.Stream` for general-purpose streams
 *
 * **Author:** Ankit
 */
public class TerminalOperations {

  public static void main(String[] args) {
    reduceTerminalOperation();
    averageTerminalOperation();
    collectTerminalOperation();
  }


  /**
  reduce() method combines or reduces a stream into a single object (therefore, a reduction operation).

  1. Signature of reduce(), with identity (i.e. with initial value) and with accumulator:
  T reduce(T identity, BinaryOperator<T> accumulator);
  where, identity is the initial value and also what is returned if the stream is empty. This means that there will always be a result & thus Optional is not the return type.
  and accumulator combines the current result with the current value in the stream.


  2. Signature of reduce(), without identity (i.e. without initial value) and with only accumulator:
  Optional<T> reduce(BinaryOperator<T> accumulator);
  where, accumulator combines the current result with the current value in the stream.
  When you leave out the identity, an Optional is returned, which will be empty if the stream is empty. This means that there may not be a result.


  3. Signature of reduce() with an identity, with accumulator, and with combiner:
  T reduce(T identity, BiFunction accumulator, BinaryOperator combiner);
  We use this version of reduce() when we are dealing with different types, allowing us to create intermediate reductions and then combine them at the end. Also, this is useful when we are dealing with parallel streams, as it allows us to combine results from different threads.
  **/
  public static void reduceTerminalOperation() {
    // Using reduce() with an identity (i.e. with initial value) and with accumulator - to concatenate strings
    String concatStr1 = Stream.of("A", "n", "k", "i", "t")
        .reduce("Hello ", (str1, str2) -> str1 + str2);
    System.out.println("Reduced String: " + concatStr1);


    // Using reduce() with an identity (i.e. with initial value) and with accumulator - to sum integers
    Integer sum = Stream.of(2,4,6)
        .reduce(0, (n1, n2) -> n1 + n2);
    System.out.println("Reduced Integer: " + sum);

    // Using reduce() without an identity (i.e. without initial value) and with only accumulator - to concatenate strings
    String concatStr2 = Stream.of("A", "n", "k", "i", "t")
        .reduce((str1, str2) -> str1 + str2)
        .orElse("No elements in the stream");
    System.out.println("Reduced String without initial value: " + concatStr2);


    // Using reduce() with an identity, with accumulator, and with combiner - to count & add the number of characters in each string
    int countCharacters = Stream.of("Ankit", "Dimple", "John", "Carry")
        .reduce(0, (n, str) -> n + str.length(), (n1, n2) -> n1 + n2);
    System.out.println("Reduced Count of Characters: " + countCharacters);
  }

  /**
    average() is a terminal operation that calculates the average of the elements in the stream and returns a double value. (Also, a reduction operation)
    It is a specialized operation for numeric streams, such as IntStream, LongStream, and DoubleStream.
    It returns an OptionalDouble, which contains the average value if the stream is not empty, or an empty OptionalDouble if the stream is empty.
  */
  public static void averageTerminalOperation() {
    // Using average() to calculate the average of an array of int primitives
    int[] numbers1 = {1, 2, 3, 4, 5};                     // Can use specialized stream i.e. IntStream here as the array is of primitive type int, to create the stream
    double average1 = IntStream.of(numbers1)
        .average()
        .orElse(0.0);                               // orElse is used to provide a default value if the stream is empty
    System.out.println("Average: " + average1);


    // Using average() to calculate the average of an array of Integer objects
    Integer[] numbers2 = {1, 2, 3, 4, 5};                 // cannot use specialized stream i.e. IntStream here as the array is of type Integer and not int
    double average2 = Arrays.stream(numbers2)
        .mapToInt(Integer::intValue)                      // Convert Integer to int
        .average()
        .orElse(0.0);                               // orElse is used to provide a default value if the stream is empty
    System.out.println("Average: " + average2);
  }

  /**
    collect() is a terminal operation that transforms the elements of a stream into a different form, usually a collection such as a List, Set, or Map. (It is also a reduction operation)

   1. Signature of collect() with a Supplier, Accumulator, and Combiner:
   <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner);
   where,
    - supplier is a function that creates a new result container (e.g., a List, Set, or Map).
    - accumulator is a function that adds an element to the result container.
    - combiner is a function that combines two result containers into one, used in parallel processing.

   This version of collect() is used when you want complete control over how collecting should work. i.e. When predefined collectors (e.g., Collectors.toList()) do not meet your requirements, you can use this approach to define custom collection behavior.
   Also, we can use this form when you need to collect elements into a mutable container. It is also useful in parallel processing.

   2. Signature of collect() with a Collector:
    <R, A> R collect(Collector<? super T, A, R> collector);
    where,
    - collector is a predefined or custom Collector that encapsulates the logic for collecting elements from the stream.
    - Collecting to Map is a common use case, where you can use Collectors.toMap() to collect elements into a Map. It takes two functions: one for the key and one for the value.
   */
  public static void collectTerminalOperation() {
    // Using collect() with a Supplier, Accumulator, and Combiner - to collect String into a StringBuilder
    StringBuilder word = Stream.of("A", "n", "k", "i", "t")
        .collect(() -> new StringBuilder(),
            (sb, str) -> sb.append(str),
            (sb1, sb2) -> sb1.append(sb2));
    System.out.println("Collected String: " + word.toString());


    // Collectors.joining: Using collect() with a predefined collector (Collectors.joining) - to collect String and join them into a single String
    String stringJoining = Stream.of("Ankit", "Dimple")
        .collect(Collectors.joining(", ", "[", "]"));
    System.out.println("Collected String using joining(): " + stringJoining);


    // Using collect() with a predefined collector (Collectors.averagingInt) - to calculate average of an array of String lengths
    Double averageLength = Stream.of("Ankit", "Dimple", "John")
        .collect(Collectors.averagingInt(s -> s.length()));
    System.out.println("Average Length of Strings: " + averageLength);

    // Collectors.toMap: Using collect() with a predefined collector (Collectors.toMap) - to collect unique string into a Map with the String as key and its length as value
    // toMap returns a Map where the keys are the result of applying the key mapper function (in this case, the String itself) and the values are the result of applying the value mapper function (in this case, the length of the String). The returned Map is usually a HashMap, unless specified otherwise.
    var mapCollector = Stream.of("Ankit", "Dimple", "John")
        .collect(Collectors.toMap(
            str -> str,                 // key mapper
            str -> str.length()         // value mapper
        ));
    System.out.println("Collected Map: " + mapCollector);
    System.out.println("Collected Map - returned Class: " + mapCollector.getClass());

    // Collectors.toMap: Using collect() with a predefined collector (Collectors.toMap) - to collect String into a Map with its length as key and the String as value and merging values with the same key
    var mapCollectorWithMerge = Stream.of("Ankit", "Dimple", "John", "Carry")
        .collect(Collectors.toMap(
            str -> str.length(),        // key mapper
            str -> str,                 // value mapper
            (existingValue, newValue) -> existingValue + "," + newValue     // merge function for duplicate keys (if you don't use merge function where there are duplicate keys, it will result into IllegalStateException: Duplicate key 5 (attempted merging values Ankit and Carry)). Merge function - what to do with the values when there are duplicate keys
        ));
    System.out.println("Collected Map with Merging: " + mapCollectorWithMerge);
    System.out.println("Collected Map with Merging - returned Class: " + mapCollectorWithMerge.getClass());

    // Collectors.toMap: Using collect() with a predefined collector (Collectors.toMap) - to collect String (may contain duplicates) into a Map with the String as key and its length as value and the keys should be sorted in ascending order
    TreeMap<String, Integer> mapCollectorWithSortedKeys = Stream.of("Ankit", "Dimple", "John", "Carry", "Ankit")
        .collect(Collectors.toMap(
            str -> str,                 // key mapper
            str -> str.length(),        // value mapper
            (existingValue, newValue) -> existingValue + newValue,  // merge function for duplicate keys
            () -> new TreeMap<>()                                                 // supplier for the Map implementation (TreeMap will sort the keys in ascending order)
        ));
    System.out.println("Collected Sorted Map: " + mapCollectorWithSortedKeys);
    System.out.println("Collected Sorted Map - returned Class: " + mapCollectorWithSortedKeys.getClass());

    // Collectors.groupingBy: Using collect() with a predefined collector (Collectors.groupingBy) - to group Strings by their length
    // groupingBy returns a Map where the keys are the result of applying the classifier function (in this case, the length of the String) and the values are Lists of items that match the key. But, the returned map is usually a HashMap, unless specified otherwise.
    var groupedByLength = Stream.of("Ankit", "Dimple", "Carry", "John", "Carry")
        .collect(Collectors.groupingBy        // Grouping by takes in a Function that determines the keys in the Map, and the values are List (default, but can be changed) of entries that matches the key
            (s -> s.length()));
    System.out.println("Grouped by Length: " + groupedByLength);
    System.out.println("Grouped by Length - returned Class: " + groupedByLength.getClass());

    // Collectors.groupingBy: Using collect() with a predefined collector (Collectors.groupingBy) - to group Strings by their length and values should only be unique
    var groupedByLengthWithUniqueValues = Stream.of("Ankit", "Dimple", "Carry", "John", "Carry")
        .collect(Collectors.groupingBy          // Grouping by takes in a Function that determines the keys in the Map, and the values are Set of entries that matches the key
            (
            s -> s.length(),              // key mapper
            Collectors.toSet()                  // collector for the values or what to do with the values (default is List if not given, but we can use Set to ensure uniqueness of values)
            ));
    System.out.println("Grouped by Length with Unique Values: " + groupedByLengthWithUniqueValues);
    System.out.println("Grouped by Length with Unique Values - returned Class: " + groupedByLengthWithUniqueValues.getClass());

    // Collectors.groupingBy: Using collect() with a predefined collector (Collectors.groupingBy) - to group Strings by their length and values should be a List of Strings; keys are sorted in ascending order
    var groupedByLengthWithSortedValues = Stream.of("Ankit", "Dimple", "Carry", "John", "Carry")
        .collect(Collectors.groupingBy(
            s -> s.length(),              // key mapper
            () -> new TreeMap<>(),              // supplier for the Map implementation (TreeMap will sort the keys in ascending order)
            Collectors.toList()                 // collector for the values (default is List if not given, but we can use TreeSet to ensure uniqueness of values and sort them in ascending order)
        ));
    System.out.println("Grouped by Length with Sorted Values: " + groupedByLengthWithSortedValues);
    System.out.println("Grouped by Length with Sorted Values - returned Class: " + groupedByLengthWithSortedValues.getClass());

    // Collectors.partitioningBy: Using collect() with a predefined collector (Collectors.partitioningBy) - to partition Strings into two groups based on their length (even or odd)
    // partitioningBy returns a Map with Boolean keys (true or false) and values that are Lists of items that match the key. The returned map is usually a HashMap, unless specified otherwise.
    // It is a specialized form of groupingBy that is used when you want to partition the stream into two groups - true and false.
    var partitionedByLength = Stream.of("Ankit", "Dimple", "Carry", "John", "Carry")
        .collect(Collectors.partitioningBy(
            s -> s.length() % 2 == 0,   // classifier function to determine the keys (true for even length, false for odd length)
            Collectors.toList()               // collector for the values (default is List if not given)
        ));
    System.out.println("Partitioned by Length: " + partitionedByLength);

    // Collectors.partitioningBy: Using collect() with a predefined collector (Collectors.partitioningBy) - to partition Strings into two groups based on their length (even or odd) and values should be unique
    var partitionedByLengthWithUniqueValues = Stream.of("Ankit", "Dimple", "Carry", "John", "Carry")
        .collect(Collectors.partitioningBy(
            s -> s.length() % 2 == 0,   // classifier function to determine the keys (true for even length, false for odd length)
            Collectors.toSet()                // collector for the values (default is List if not given, but we can use Set to ensure uniqueness of values)
        ));
    System.out.println("Partitioned by Length with Unique Values: " + partitionedByLengthWithUniqueValues);
  }
}

package com.ankit.stream;


import java.util.List;

/*
Intermediate operations are actions you can perform on a Java Stream that transform or filter its elements.
They return a new Stream and can be chained together to form a pipeline of operations.
They are lazy, meaning they do not process data or produce a result until a terminal operation is invoked.

Common intermediate operations include:
- `filter`: Filters elements based on a predicate.
Example: `stream.filter(x -> x > 10)` filters elements greater than 10.

- distinct: Removes duplicate elements from the stream. (also known as a Stateful operation as it must keep track of all elements seen so far)
Example: `stream.distinct()` returns a stream with unique elements.

- sorted: Sorts the elements in natural order or by a specified comparator.
Example: `stream.sorted()` sorts the elements in ascending order.

- limit: Limits the number of elements in the stream. (also known as a short-circuit operation as it can terminate the stream early when the limit is reached)
Example: `stream.limit(5)` returns a stream with at most 5 elements.

- skip: Skips the first n elements in the stream.
Example: `stream.skip(3)` skips the first 3 elements.

- map: Transforms each element of the stream into another value. It produces a one-to-one mapping meaning, each input element corresponds to exactly one output element.
Example: `stream.map(String::toUpperCase)` converts each string to uppercase.

- flatMap: Flattens nested streams into a single stream.
Example: `stream.flatMap(List::stream)` flattens a stream of lists into a single stream of elements.
 */
public class IntermediateOperations {

  public static void main(String[] args) {
    filterTerminalOperation();
    distinctTerminalOperation();
    sortedTerminalOperation();
    limitTerminalOperation();
    skipTerminalOperation();
    mapTerminalOperation();
  }

  public static void filterTerminalOperation() {
    List<Integer> list = List.of(3, 2, 4, 1, 5, 7, 4, 3);
    list.stream()
        .filter(num -> num < 5)        // Filters elements less than 5
        .forEach(System.out::print);          // This will print numbers less than 5
    System.out.println(" - End of filterTerminalOperation");
  }

  public static void distinctTerminalOperation() {
    List<Integer> list = List.of(3, 2, 4, 1, 5, 7, 4, 3);
    list.stream()
        .distinct()                           // Removes duplicates from the stream
        .forEach(System.out::print);          // This will print unique numbers
    System.out.println(" - End of distinctTerminalOperation");
  }

  public static void sortedTerminalOperation() {
    List<Integer> list = List.of(3, 2, 4, 1, 5, 7, 4, 3);
    list.stream()
        .distinct()                   // Ensure uniqueness before sorting
        .sorted()                     // Sorts the stream in natural order
        .forEach(System.out::print);  // This will print numbers in sorted order
    System.out.println(" - End of sortedTerminalOperation");
  }

  public static void limitTerminalOperation() {
    List<Integer> list = List.of(3, 2, 4, 1, 5, 7, 4, 3);
    list.stream()
        .distinct()                   // Ensure uniqueness before limiting
        .sorted()                     // Sorts the stream in natural order
        .limit(3)             // Limits the stream to the first 3 elements
        .forEach(System.out::print);  // This will print the first 3 unique sorted numbers
    System.out.println(" - End of limitTerminalOperation");
  }

  public static void skipTerminalOperation() {
    List<Integer> list = List.of(3, 2, 4, 1, 5, 7, 4, 3);
    list.stream()
        .distinct()                     // Ensure uniqueness before skipping
        .sorted()                       // Sorts the stream in natural order
        .skip(3)                     // Skips the first 3 elements
        .forEach(System.out::print);    // This will print the remaining elements after skipping the first 3
    System.out.println(" - End of skipTerminalOperation");
  }

  public static void mapTerminalOperation() {
    List<Integer> list = List.of(3, 2, 4, 1, 5, 7, 4, 3);
    list.stream()
        .map(num -> num * 2)          // Transforms each element by multiplying it by 2
        .forEach(System.out::print);         // This will print each number multiplied by 2
    System.out.println(" - End of mapTerminalOperation");
  }
}

# Stream API in Java: helps process data efficiently
1. Stream is a `sequence of data that can be processed with operations`. Streams can be created from various data sources, such as `collections`, `arrays`, or `I/O channels`.
2. Stream is not a data structure (i.e. not another way of storing or organizing data like, an array or a Collection), Streams are all about processing data (like collections) in an efficient manner.
3. Streams don’t change the original data structure, they only provide the result as per the pipelined methods.
4. You can perform multiple intermediate operations on a stream of data, and they are lazy, meaning they are not executed until a terminal operation is invoked.
5. Streams are no longer available after a terminal operation is invoked. They can be used only once.

## Stream Pipelines: sequence of operations (intermediate + terminal) performed on data in the stream
`Stream pipelines` means a sequence of operations (intermediate + terminal) that are performed on the data in the stream.  
A stream pipeline consists of a source, zero or more intermediate operations, and a terminal operation.
- **Source**: The data source from which the stream is created (e.g., a collection, array, or I/O channel).
- **Intermediate Operations**: Operations that transform the stream into another stream. They are lazy and do not produce a result until a terminal operation is invoked. Examples include `filter`, `map`, `flatmap`, `sorted`.
    - **Stateless Intermediate Operations**: These operations do not depend on the state of the stream and can be applied independently to each element. Examples include `filter`, `map`, and `sorted`.
    - **Stateful Intermediate Operations**: These operations require knowledge of the entire stream to produce a result. Examples include `distinct`, `sorted`, and `limit`.
- **Terminal Operation**: An operation that produces a result or a side effect, such as `forEach`, `collect`, or `reduce`. Terminal operations can be further categorized into:
    - **Non-Short-Circuiting Operations**: These operations process all elements in the stream and produce a result. Examples include `collect`, `reduce`, and `toArray`.
    - **Short-Circuiting Operations**: Some terminal operations can short-circuit the processing of the stream, meaning they can stop processing as soon as a certain condition is met. Examples include `findFirst`, `anyMatch`, and `allMatch`.

**Note:**
- Terminal operations trigger the processing of the stream pipeline and produce a result or side effect.
- Terminal operations can be used without any intermediate operations, but intermediate operations cannot be used without a terminal operation because they do not produce a result until a terminal operation is invoked.

![Stream Pipeline](src/src/main/resources/images/Stream-Pipeline.png)

## Creating Finite Streams
1. **From Arrays**: You can create a stream from an array using the `Arrays.stream()` method.
```java
String[] nameArray = {"Alice", "Bob", "Charlie"};
Stream<String> nameStream = Arrays.stream(nameArray);
```

2. **From Collections**: You can create a stream from a collection using the `stream()` method.
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
Stream<String> nameStream = names.stream();
```

3. **From Static Methods**: You can create a stream using static methods like `Stream.of()`.
```java
Stream<String> nameStream = Stream.of("Alice", "Bob", "Charlie");
```

4. **From Files**: You can create a stream from a file using the `Files.lines()` method.
```java
Path path = Paths.get("file.txt");
Stream<String> fileStream = Files.lines(path);
```

## Creating Infinite Streams
1. **Using `Stream.iterate()`**: This method generates an infinite stream by applying a function to each element.
```java
Stream<Integer> infiniteStream = Stream.iterate(0, n -> n + 1);
```
2. **Using `Stream.generate()`**: This method generates an infinite stream by repeatedly applying a supplier function.
```java
Stream<Double> randomStream = Stream.generate(Math::random);
```

Infinite Stream can be limited using methods (Short-circuiting operation) like `limit()`, which restricts the number of elements in the stream.
```java
Stream<Integer> limitedStream = Stream.iterate(0, n -> n + 1).limit(10);
```

## Specialized Streams
Java provides specialized streams for handling primitive data types more efficiently. These specialized streams are designed to avoid boxing and unboxing overhead associated with using generic streams. The specialized streams are:
- **IntStream**: A stream for handling `int` values.
- **LongStream**: A stream for handling `long` values.
- **DoubleStream**: A stream for handling `double` values.

### Terminal Operations on Streams
| Type          | Description                                                                   | Example                                                       |
|---------------|-------------------------------------------------------------------------------|---------------------------------------------------------------|
| **reduce**    | Combines or reduces elements of the stream into a single result               | `Optional<Integer> sum = stream.reduce((a, b) -> a + b);`     |
| **average**   | Returns the average of the elements in the stream (only for numeric streams). | `OptionalDouble average = intStream.average();`               |
| **collect**   | Converts the stream into a collection or other data structure.                | `List<String> list = stream.collect(Collectors.toList());`    |
| **forEach**   | Performs an action for each element in the stream.                            | `stream.forEach(System.out::println);`                        |
| **count**     | Counts the number of elements in the stream.                                  | `long count = stream.count();`                                |
| **findFirst** | Returns the first element of the stream, if present.                          | `Optional<String> first = stream.findFirst();`                |
| **findAny**   | Returns any element of the stream, if present.                                | `Optional<String> any = stream.findAny();`                    |
| **allMatch**  | Checks if all elements match a given predicate.                               | `boolean allMatch = stream.allMatch(s -> s.length() > 3);`    |
| **anyMatch**  | Checks if any element matches a given predicate.                              | `boolean anyMatch = stream.anyMatch(s -> s.startsWith("A"));` |
| **noneMatch** | Checks if no elements match a given predicate.                                | `boolean noneMatch = stream.noneMatch(s -> s.isEmpty());`     |
| **toArray**   | Converts the stream into an array.                                            | `String[] array = stream.toArray(String[]::new);`             |
| **max**       | Returns the maximum element of the stream according to a given comparator.    | `Optional<String> max = stream.max(String::compareTo);`       |
| **min**       | Returns the minimum element of the stream according to a given comparator.    | `Optional<String> min = stream.min(String::compareTo);`       |

> Reduction are a special type of terminal operation that combines elements of the stream into a single result. Example: `reduce`, `count`, `min`, `max`.


`Important`  
**Mutable vs Immutable Objects**
1. Mutable Objects: Can be changed after creation (e.g., StringBuilder, List, Map). Used in collect() to accumulate results efficiently.
2. Immutable Objects: Cannot be changed after creation (e.g., String, Integer). Used in reduce() to ensure thread safety and avoid side effects.

### Intermediate Operations on Streams
| Type         | Description                                                                                   | Example                                                                         |
|--------------|-----------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------|
| **filter**   | Filters elements based on a predicate, returning a new stream with matching elements.         | `Stream<String> filtered = stream.filter(s -> s.startsWith("A"));`              |
| **distinct** | Removes duplicate elements from the stream, returning a new stream with unique elements.      | `Stream<String> distinct = stream.distinct();`                                  |
| **map**      | Transforms each element using a function, returning a new stream with transformed elements.   | `Stream<Integer> lengths = stream.map(String::length);`                         |
| **flatMap**  | Flattens nested streams into a single stream, useful for handling collections of collections. | `Stream<String> flatMapped = stream.flatMap(s -> Arrays.stream(s.split(" ")));` |
| **sorted**   | Sorts the elements of the stream in natural order or using a comparator.                      | `Stream<String> sorted = stream.sorted();`                                      |
| **limit**    | Limits the number of elements in the stream to a specified count.                             | `Stream<String> limited = stream.limit(5);`                                     |
| **skip**     | Skips the first n elements of the stream, returning a new stream with the remaining elements. | `Stream<String> skipped = stream.skip(3);`                                      |
| **peek**     | Performs an action on each element of the stream without modifying it, useful for debugging.  | `stream.peek(System.out::println);`                                             |


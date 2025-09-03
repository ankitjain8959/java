# String: Sequence of characters
String is a sequence of characters in Java. It is one of the most commonly used data types in Java programming.  
  
In Java,
- Strings are immutable and final, meaning once created, their values cannot be changed. Therefore, it is thread-safe. Any operation that seems to modify a string actually creates a new string and the original string remains unchanged.  Garbage collection will eventually remove the original string if there are no references to it.    
- Strings are stored in the heap memory. SCP (String Constant Pool) is a special area in the heap memory where string literals are stored.  
- String class (a final class) is a part of the `java.lang` package, which is automatically imported in every Java program.  

## How to create a string in Java
String can be created by 2 ways,  
- by string literal: using double quotes  
- by new keyword: using the `String` class constructor  

```java
String str1 = "Hello, World!";              // Using double quotes; string is stored in the String pool (special area of heap memory)
String str2 = new String("Hello, World!");  // Using String class constructor; string is stored in the heap memory (However, during class load time, JVM creates a string literal in the String pool if it does not already exist)
```

## Where are strings stored in Java: heap memory
Strings in Java are stored in the heap memory. SCP (String Constant Pool) is a special area in the heap memory.   

`String literals`:
When a string is created using double quotes, it is stored in the `String pool` or `String constant pool` (SCP), which is a special area of the heap memory. If a string with the same value already exists in the pool, then the reference to the string object from the string pool is returned instead of creating a new object. Otherwise, the new string object is added to the string pool, and the respective reference will be returned. This helps to save memory and improve performance.  

`String objects created using the `new` keyword:
When a string is created using the `new` keyword, it is stored in the heap memory but not in the String pool. This means that even if a string with the same value exists in the pool, a new object will be created in the heap memory.  

## How to force a string to be stored in the String pool
To force a string to be stored in the String pool, you can use the `intern()` method of the `String` class. This method checks if a string with the same value already exists in the String pool. If it does, it returns the reference to that string; otherwise, it adds the string to the pool and returns its reference.

```java
String str1 = "Hello, World!";
String str2 = new String("Hello, World!").intern(); // str2 will refer to the same string object as str1
```

## How to check number of objects created in the heap memory? (using JVisualVM or JProfiler tool)
To check the number of string objects created in the heap memory, you can use a memory profiler tool such as VisualVM or JProfiler. These tools allow you to monitor the memory usage of your Java application, including the number of objects created in the heap memory.  
Example of using JVisualVM:  
1. Run your Java application with the System.in.read() method to keep it running.
2. Open JVisualVM and connect to your Java application.
3. Go to the "Memory" tab and take a heap dump.
4. Analyze the heap dump to see the number of string objects created in the heap memory or, in the dump view click on OQL Console (Object Query Language) and run the query `select s from java.lang.String s where s.toString() == "abc"` to see all "abc" string objects created in the heap memory.  

## String methods
- hashCode: String overrides the `hashCode()` method from the `Object` class.  
  This method computes and returns the hash code of the string, which is an integer value that uniquely identifies the string object in memory.  
  The hash code is computed using a `polynomial rolling hash` formula, which is efficient and helps in quick lookups in hash-based data structures like `HashMap` and `HashSet`.  
  The hash code is used to determine the bucket location for storing the string in these data structures, thus improving performance for operations like search, insert, and delete.

Mathematically, the hash code is computed as:
```
hashCode = c[0] * 31^(n-1) + c[1] * 31^(n-2) + ... + c[n-1] * 31^0
```
where `c[i]` is the character at index `i` in the string and `n` is the length of the string.  
Multiplication is done in 32-bit integer arithmetic, and the result is truncated to fit in a 32-bit integer.  


```java
int hashCode() {
    int hash = 0;
    for (int i = 0; i < length(); i++) {
        hash = 31 * hash + charAt(i);
    }
    return hash;
}
```

- equals: String overrides the `equals()` method from the `Object` class.  
  This method compares two strings for equality based on their content (i.e., the sequence of characters).  
  It returns `true` if both strings have the same length and all corresponding characters are equal; otherwise, it returns `false`.  
  This method is case-sensitive, meaning "Hello" and "hello" are considered different strings. if you want to compare strings ignoring case, you can use the `equalsIgnoreCase()` method.

- compareTo: String implements the `Comparable` interface and overrides the `compareTo()` method.  
  This method compares two strings lexicographically (i.e., based on the Unicode value of each character).  
  It returns a negative integer, zero, or a positive integer if the first string is less than, equal to, or greater than the second string, respectively.  
  This method is also case-sensitive, meaning "Hello" is considered less than "hello" because 'H' has a lower Unicode value than 'h'.

- isEmpty: This method checks if the string is empty (i.e., has a length of 0).  
  It returns `true` if the string is empty; otherwise, it returns `false`.  
  An empty string is different from a null string, which means the string reference is not pointing to any object.

- isBlank: This method checks if the string is blank (i.e., contains only whitespace characters or is empty).  
  It returns `true` if the string is blank; otherwise, it returns `false`.  
  This method is useful for validating user input or checking if a string contains meaningful content.



# StringBuffer and StringBuilder: Mutable strings
Each time we manipulate a string, a new String object is created, and all previous objects will be garbage collected if there are no references to them, placing a strain on the garbage collector and leading to performance issues.  
To overcome this issue, Java provides two classes: `StringBuffer` and `StringBuilder`, which are mutable strings. They allow you to modify the string without creating a new object, thus improving performance and reducing memory usage.  
- `StringBuffer` is synchronized, meaning it is thread-safe (meaning, StringBuffer has all methods synchronized) and can be used in multithreaded environments.  
  Therefore, StringBuffer allows only one thread to access a method at once, so it is not possible to call StringBuffer methods from two threads simultaneously, which means it takes more time to access.  
  
- `StringBuilder` is not synchronized, meaning it is not thread-safe but is faster than `StringBuffer`.   


## How to create a StringBuffer or StringBuilder
You can create a `StringBuffer` or `StringBuilder` object using the following constructors:

```java
StringBuffer sb = new StringBuffer(); // Creates an empty StringBuffer with a default capacity of 16 characters
StringBuffer sbWithCapacity = new StringBuffer(50); // Creates an empty StringBuffer with a specified capacity
StringBuffer sbWithString = new StringBuffer("Hello, World!"); // Creates a StringBuffer with the specified string with a capacity of 16 + length of the string

StringBuilder sbuilder = new StringBuilder(); // Creates an empty StringBuilder with a default capacity of 16 characters
StringBuilder sbuilderWithCapacity = new StringBuilder(50); // Creates an empty StringBuilder with a specified capacity
StringBuilder sbuilderWithString = new StringBuilder("Hello, World!"); // Creates a StringBuilder with the specified string with a capacity of 16 + length of the string
```

## StringBuffer and StringBuilder methods
Both `StringBuffer` and `StringBuilder` provide similar methods for manipulating strings. Here are some commonly used methods:
- `append(String str)`: Appends the specified string to the end of the current string.
- `insert(int offset, String str)`: Inserts the specified string at the specified position in the current string.
- `delete(int start, int end)`: Deletes the characters from the specified start index to the end index (exclusive).
- `replace(int start, int end, String str)`: Replaces the characters from the specified start index to the end index (exclusive) with the specified string.
- `reverse()`: Reverses the characters in the current string.
- `toString()`: Converts the `StringBuffer` or `StringBuilder` to a `String` object.
- `length()`: Returns the length of the current string.
- `charAt(int index)`: Returns the character at the specified index in the current string.
- `substring(int start, int end)`: Returns a substring from the specified start index to the end index (exclusive).
- `indexOf(String str)`: Returns the index of the first occurrence of the specified string in the current string.
- `lastIndexOf(String str)`: Returns the index of the last occurrence of the specified string in the current string.


# CharSequence vs String vs Character vs char
- `CharSequence` is an interface in Java that represents a sequence of characters. It is implemented by the `String`, `StringBuilder`, and `StringBuffer` classes.  
- `String` is a class that represents an immutable sequence of characters. It is the most commonly used class for representing text in Java.  
- `char` is a primitive data type in Java that represents a single character.
- `Character` is a wrapper class for the primitive data type `char`, which represents a single character. Use `Character` when you need to work with `char` as an object, such as in collections or when you need to use methods provided by the `Character` class.  

## Where are they stored in memory?
- `CharSequence`: It is an interface, so it does not have a specific memory location. However, classes that implement this interface (like `String`, `StringBuilder`, and `StringBuffer`) are stored in the heap memory.
- `String`: Stored in the heap memory. If created using a string literal, it is stored in the String pool (SCP) in the heap memory. If created using the `new` keyword, it is stored in the heap memory.
- `Character`: Stored in the heap memory.
- `char`: It is a primitive data type and is stored in the stack memory when declared as a local variable. If it is a field of an object, it is stored in the heap memory along with the object.  


# Text blocks in Java (since Java 15)
- A text block is a multiline String literal that is enclosed in triple double quotes (`"""`).  
- Text blocks were introduced in Java 13 as a preview feature and became a standard feature in Java 15.  
- It makes it easier to create and read multiline strings in Java, especially when dealing with formatted text, such as JSON, XML, SQL or HTML.  
- It helps to avoid the need for lots of `\n` and string concatenations `+`. Every new line in a text block automatically becomes a new line in the resulting string.  
- Text blocks supports escape sequences like `\n` (new line), `\t` (tab), `\"` (double quote), `\\` (backslash), `\` (suppress newline), `\s` (space) etc.
- In terms of memory, text blocks are treated like regular strings. If a text block is created using a string literal, it is stored in the String pool (SCP) in the heap memory. If created using the `new` keyword, it is stored in the heap memory.

```java
String s = """Hello""";   // ❌ invalid (must start on a new line after the opening triple quotes)
String s = """
       Hello""";         // ✅ valid (no rule on closing triple quotes; it can be on the same line as the last line of text)
```
# Integer
The `Integer` class in Java is a wrapper class for the primitive data type `int`. 
This class provides methods to - convert between integers and strings, as well as methods for parsing strings into integers.  


# Integer methods
- parseInt: The `parseInt()` method is a static method of the `Integer` class that converts a string representation of an integer into an actual integer value.  
  It takes a string as input and returns the corresponding integer value. If the string cannot be parsed as an integer (e.g., it contains non-numeric characters), it throws a `NumberFormatException`.

```java
int value = Integer.parseInt("123");
```
- toString: The `toString()` method is an instance method of the `Integer` class that converts an integer value into its string representation.  
  It returns a string that represents the integer value.

```java
String str = Integer.toString(123);
```
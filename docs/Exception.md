# Exception
An exception is an event that occurs during the execution of a program that disrupts the normal flow of the program's instructions.

# Exception Handling in Java
When an error occurs within a method, the method creates an object (an exception object) and hands it off to the runtime system. 
The runtime system then looks for a `block of code that can handle the exception`, which is called an `exception handler`. If no handler is found, the program terminates.
The search begins with the method in which the error occurred and proceeds through the call stack in the reverse order in which the methods were called.  

> Java provides a structured way to handle exceptions using try, catch, finally, throw, and throws keywords.

## Ways to Handle Exceptions
1. try-catch Block
2. try-finally Block
3. try-catch-finally Block
4. try-with-resources (Java 7+) for autocloseable resources
5. throw keyword: to explicitly throw an exception
6. throws keyword: to declare an exception might be thrown from a method
7. Custom Exception Classes: Create your own exception classes by extending `Exception` or `RuntimeException`.

# Exception Hierarchy

        ---> Throwable <---
        |    (checked)     |
        |                  |
        |                  |
    ---> Exception           Error
    |    (checked)        (unchecked)
    |
    RuntimeException
    (unchecked)


# Checked vs Unchecked (or, Runtime) Exceptions
`Checked exceptions`:
- Checked exceptions are exceptions that the compiler forces you to handle.
- They are checked at compile-time, meaning you must either catch them in a try-catch block or declare them in the method signature using the `throws` keyword.

Examples include `IOException` and `SQLException`.
```java
public void readFile(String fileName) throws IOException {
    // code that may throw IOException
}
```

`Unchecked exceptions`: (or Runtime exceptions)
- Unchecked exceptions are exceptions that the compiler does not force you to handle. 
- They are not checked at compile-time & occur at runtime, meaning you are not required to catch them or declare them in the method signature.
- They are typically used for programming errors that can be avoided by the programmer.  

Examples include `NullPointerException`, `ArrayIndexOutOfBoundsException`, and `ArithmeticException`.
```java
public void divide(int a, int b) {
    if (b == 0) {
        throw new ArithmeticException("Division by zero");
    }
    // code that performs division
}
```

# Errors vs Exceptions
Both are subclasses of `Throwable`, but they are different in intent and handling.  

`Errors`:  
- Represent serious problems that a reasonable application should not try to catch.
- Examples include `OutOfMemoryError`, `StackOverflowError`, and `VirtualMachineError`.
- They are unchecked and are typically not recoverable.


`Exceptions`:  
- Represent conditions that a reasonable application might want to catch.
- Exceptions can be Checked (must be handled or declared) or Unchecked (do not need to be handled).


# Centralized Exception Handling (Enterprise level approach)
- Service layer throws exceptions.
- Controllers don’t catch them directly.
- Instead, use a global or centralized exception handler (e.g., `@ControllerAdvice` in Spring) to handle exceptions across the application.

Note: Keep controllers thin and do not cluster them with try-catch blocks. Push the responsibility of handling exceptions to @ControllerAdvice.  

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  @ExceptionHandler(IOException.class)
  public ResponseEntity<String> handleIOException(IOException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("I/O Error: " + ex.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGeneral(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
  }
}
```
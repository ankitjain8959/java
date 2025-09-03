# Interface
1. Interface defines a contract for classes, without specifying implementation.
2. A class "signs" the contract by with the keyword "implements". When implementing an interface, the class agrees to adhere to the contract defined by the interface.
3. Classes:
    - Can extend one class.
    - Can implement multiple interfaces.
4. Interfaces themselves are implicitly `public` and `abstract`, meaning they cannot be instantiated directly.
5. Interfaces can contain:
    - Abstract methods (implicitly public and abstract).
    - Static methods (not inherited by implementation classes).
    - Default methods (can be inherited, optional to override).
6. Variables in interfaces are implicitly `public static final`.

# Functional Interface
1. A functional interface is an interface with exactly one abstract method. This is known as a Single Abstract Method (SAM) interface.
2. default methods, static methods, methods inherit from Object are excluded from the abstract method count. Therefore,functional interface can have multiple default or static methods.
3. The `@FunctionalInterface` annotation is used to indicate that an interface is intended to be a functional interface. This annotation is optional but helps in ensuring that the interface adheres to the functional interface contract. If an interface is annotated with `@FunctionalInterface`, it must have exactly one abstract method. If it has more than one abstract method, the compiler will generate an error.

```
@FunctionalInterface
public interface MyFunctionalInterface {
    void myMethod(); // Single abstract method
 
    default void defaultMethod() {
        System.out.println("Default method implementation");
    }
    
    static void staticMethod() {
        System.out.println("Static method implementation");
    }
}
```

# Lambda Expressions: helps represent or implement functional interfaces in a `concise` way
1. Lambda expressions helps to `represent or implement functional interfaces in a more concise way`. They allow you to define the behavior of a functional interface without the need for an explicit class implementation.
2. Functional interfaces are generally used as the assignment target for a lambda expression or method reference.
3. Lambda expressions can be used to create instances of functional interfaces without the need for an explicit class implementation.
4. Lambdas make heavy use of compiler inference, relying on the functional interface's structure to determine method targets.

```
@FunctionalInterface
public interface MyFunctionalInterface {
    void myMethod(String message);
}

public class Main {
    public static void main(String[] args) {
        // Using a lambda expression to implement the functional interface
        MyFunctionalInterface myFunc = (message) -> System.out.println("Message: " + message);
        
        // Calling the method defined in the functional interface
        myFunc.myMethod("Hello, World!");
    }
}
```
Left side () = input parameters (empty if none).  
Arrow -> separates parameters from the method body.
5. In Java, lambda expressions can only access:  
   ✔️ Parameters or Local variables of the enclosing method or block; and those variables must be `final` or `effectively final`

## What is Effectively Final?
A variable is considered effectively final if:  
✅ It is assigned once.  
✅ Its value is never changed after initialization.  
✅ You don't explicitly use the final keyword, but the compiler treats it as final.

```
public class LambdaScopeExample {

   public static void main(String[] args) {
      int z = 5;  // Effectively final

      Runnable lambda = () -> {
         System.out.println("Value of z: " + z);
      };

      lambda.run();
   }
}

// value of z is never modified after it's initialized, so it is effectively final.
```

# Method References: helps make lambda expressions even more `concise`
1. Method references `helps to make lambda expressions even more concise` in cases where the lambda expression is simply calling an existing method.
2. **_If a lambda expression is just a call to an existing method, you can use a method reference to refer to that method directly._**
3. **_If a lambda parameter is simply passed to another method, then the redundancy of specifying the parameter twice can be removed by using Method Reference._**
4. Syntax for method references `ClassNameorObjectName::methodName`

- Reference to a static method (i.e. Refers to a static method of a class)
```
// Lambda expression
(args) -> Class.staticMethod(args);

// Method reference
Class::staticMethod;
```

- Reference to an instance method of a particular object (i.e. Calls instance method on a specific object)
```
// Lambda expression
(args) -> obj.instanceMethod(args);

// Method reference
obj::instanceMethod;
```

- Reference to an instance method of an arbitrary object of a particular type (i.e. Calls instance method on objects passed as arguments)
```
// Lambda expression
(args) -> obj.instanceMethod(args);

// Method reference
ClassName::instanceMethod;
```

- Constructor reference (i.e. Calls a constructor, often used with Supplier or Function interfaces)
```
// Lambda expression
(args) -> new ClassName(args);

// Method reference
ClassName::new;
```

# Pre-defined Functional Interfaces
1. Java provides several pre-defined functional interfaces in the `java.util.function` package, which can be used with lambda expressions and method references.
2. Examples:
    - `Predicate<T>`: boolean-valued function of one argument. Used for single-condition checks.
    - `BiPredicate<T, U>`: boolean-valued function of two arguments. Used for situations where you need to evaluate a condition based on two inputs
    - `Supplier<T>`: represents a supplier of results. Useful when you need to generate or provide a value without any input.
    - `Consumer<T>`: represents an operation that accepts a single input argument and returns no result.
    - `BiConsumer<T, U>`: represents an operation that accepts two input arguments and returns no result.
    - `Function<T, R>`: represents a function that accepts one argument and produces a result.
    - `BiFunction<T, U, R>`: represents a function that accepts two arguments and produces a result.
    - `UnaryOperator<T>`: a specialization of `Function` for the case where the input and output types are the same.
    - `BinaryOperator<T>`: a specialization of `BiFunction` for the case where both the inputs and the output types are the same.

## Pre-Defined Functional Interfaces Summary

| Interface                 | Abstract Method          | Arguments              | Return Type | Typical Use Case                                         |
|---------------------------|--------------------------|------------------------|-------------|----------------------------------------------------------|
| **Predicate\<T>**         | `boolean test(T t)`      | 1 argument of type `T` | `boolean`   | Evaluating a condition, filtering                        |
| **BiPredicate\<T, U>**    | `boolean test(T t, U u)` | 2 arguments `T`, `U`   | `boolean`   | Comparing two values, complex conditions                 |
| **Supplier\<T>**          | `T get()`                | No arguments           | `T`         | Supplying or generating values on demand                 |
| **Consumer\<T>**          | `void accept(T t)`       | 1 argument of type `T` | `void`      | Performing an action with input, e.g., printing, logging |
| **BiConsumer\<T, U>**     | `void accept(T t, U u)`  | 2 arguments `T`, `U`   | `void`      | Performing actions with two inputs                       |
| **Function\<T, R>**       | `R apply(T t)`           | 1 argument `T`         | `R`         | Transforming a value from `T` to `R`                     |
| **BiFunction\<T, U, R>**  | `R apply(T t, U u)`      | 2 arguments `T`, `U`   | `R`         | Combining or transforming two inputs to produce result   |
| **UnaryOperator\<T>**     | `T apply(T t)`           | 1 argument `T`         | `T`         | specialization of `Function`                             |
| **BinaryOperator\<T>**    | `T apply(T t1, T t2)`    | 2 arguments `T1`, `T2` | `T`         | specialization of `BiFunction`                           |


### Example Usage of Pre-defined Functional Interfaces
Predicate Example
```
Predicate<String> isEmpty = str -> str.isEmpty();
System.out.println(isEmpty.test(""));  // true
```


BiPredicate Example
```
BiPredicate<String, String> equalsIgnoreCase = (s1, s2) -> s1.equalsIgnoreCase(s2);
System.out.println(equalsIgnoreCase.test("hello", "HELLO"));  // true
```

Supplier Example
```
Supplier<Double> randomSupplier = () -> Math.random();
System.out.println(randomSupplier.get());  // Prints random number
```

Consumer Example
```
Consumer<String> printer = msg -> System.out.println(msg);
printer.accept("Hello World");
```

BiConsumer Example
```
BiConsumer<String, Integer> printer = (name, age) -> System.out.println(name + " is " + age + " years old");
printer.accept("Alice", 30);
```

Function Example
```
Function<String, Integer> lengthMapper = str -> str.length();
System.out.println(lengthMapper.apply("Lambda"));  // 6
```

BiFunction Example
```
BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;
System.out.println(sum.apply(5, 7));  // 12
```

UnaryOperator Example
```
UnaryOperator<String> toUpperCase = str -> str.toUpperCase();
System.out.println(toUpperCase.apply("hello"));  // HELLO
```

BinaryOperator Example
```
BinaryOperator<Integer> add = (a, b) -> a + b;
System.out.println(add.apply(5, 7));  // 12
```

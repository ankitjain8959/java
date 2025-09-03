# OOP vs Procedural Programming
- `Object Oriented Programming`: is a programming paradigm that uses "objects" to design applications and programs.  
   It revolves around the concept of bundling data (attributes) and methods (functions) that operate on that data into single units called classes and objects.  
   The core principles of OOP, often referred to as the **four pillars**, are `encapsulation`, `inheritance`, `polymorphism`, and `abstraction`.

- `Procedural Programming`: is a programming paradigm that uses a sequence of instructions, or procedures (functions) that operate on data, to solve a problem.
  _**Note:**_ Java can also be procedural when you use static methods everywhere.

```java
public class BankProcedural {
    public static double deposit(double balance, double amount) {
        return balance + amount;
    }
}
```
Here, deposit is just a function — it’s not tied to any specific account object.

| Aspect            | Object-Oriented                             | Procedural                               |
|-------------------|---------------------------------------------|------------------------------------------| 
| **Focus**         | Objects (data + behavior together)          | Functions/procedures operating on data   | 
| **Data location** | Inside objects                              | Outside functions (passed as args)       | 
| **Flow**          | Object collaboration (bottom-down approach) | Sequential execution (top-down approach) | 
| **Examples**      | Java, C++, Python OOP                       | Java with static methods, C, Pascal      |


# Java: OOP Language (but not a Pure OOP Language)
For a language to be OOP language, everything has to be an Object.

> Java is OOP language by design, but it's not a pure OOP language since it supports primitives and static members, that are not tied to Objects or Instances.

Additional Notes:
- `Primitive`: is a data type that holds the actual value directly and stored on stack.
- `Object`: instance of a class and stored on heap memory. It has methods & fields and are accessed via a reference variable.
- `Static members`: fields, block or functions are not tied to instances. You can call them without creating an object. They are shared across all the instances and there will be only 1 copy in the memory. They are more procedural than object-oriented.


# Memory Management in Java
[Memory Management](docs/MemoryManagement.md#memory-management-in-java) in Java is the process of allocating and de-allocating memory for objects.

# Java Data Types - Default Values

| Data Type   | Primitive Default (Class Field) | Primitive Default (Local Var in Method) | Wrapper Default (Class Field) | Wrapper Default (Local Var in Method) | **Total Value Range**                                                |
|-------------|---------------------------------|-----------------------------------------|-------------------------------|---------------------------------------|----------------------------------------------------------------------|
| **byte**    | `0`                             | ❌ Must initialize                       | `null`                        | ❌ Must initialize                     | `-128 to 127`                                                        |
| **short**   | `0`                             | ❌ Must initialize                       | `null`                        | ❌ Must initialize                     | `-32,768 to 32,767`                                                  |
| **int**     | `0`                             | ❌ Must initialize                       | `null`                        | ❌ Must initialize                     | `-2,147,483,648 to 2,147,483,647`                                    |
| **long**    | `0L`                            | ❌ Must initialize                       | `null`                        | ❌ Must initialize                     | `-9,223,372,036,854,775,808 to 9,223,372,036,854,775,807`            |
| **float**   | `0.0f`                          | ❌ Must initialize                       | `null`                        | ❌ Must initialize                     | \~ `±3.40282347E+38` (approx. 6–7 decimal digits precision)          |
| **double**  | `0.0d`                          | ❌ Must initialize                       | `null`                        | ❌ Must initialize                     | \~ `±1.79769313486231570E+308` (approx. 15 decimal digits precision) |
| **char**    | `'\u0000'` (null char, value 0) | ❌ Must initialize                       | `null`                        | ❌ Must initialize                     | `'\u0000'` (0) to `'\uffff'` (65,535)                                |
| **boolean** | `false`                         | ❌ Must initialize                       | `null`                        | ❌ Must initialize                     | `true`, `false`                                                      |


**_Important Notes:_**    
1. Fields: Class level variables (fields) have default values as shown in the table above.
2. Local Variables: Local variables (inside methods; either primitives or wrapper or any objects) do not have default values and must be initialized before use, otherwise it will throw a compile-time exception at the time of access.
3. Objects (wrapper or any other object) can be initialized to `null` (which means no object is assigned) and can be used later without any compile time exception.  
4. If fields (class level variables) are declared as `static`, they will have the same default values as mentioned in the table above. `static` just means that the field is shared across all instances of the class, and it can be accessed without creating an instance of the class. `static` members can even be accessed using the object reference, but it is not recommended because it can lead to confusion about whether the member is tied to the class or the instance. Even if you use object reference, the compiler translates it into class based (ClassName.varName) internally.  
5. If fields or local variables are declared as `final`, they must be initialized before it is accessed, otherwise it will throw compile time exception.  


# Wrapper Classes and Caching
To avoid memory bloat and improve performance, the JVM caches small commonly used wrapper objects. 
Therefore, always use .equals() for object value (i.e. wrapper values) comparison instead of ==, which checks for reference equality.  

| Wrapper       | Cached Range                                      |
|---------------|---------------------------------------------------|
| **Byte**      | -128 to 127 (all values)                          |
| **Short**     | -128 to 127                                       |
| **Integer**   | -128 to 127 (extendable with -XX:AutoBoxCacheMax) |
| **Long**      | -128 to 127                                       |
| **Float**     | ❌ Not cached                                      |
| **Double**    | ❌ Not cached                                      |
| **Character** | 0 to 127 (Unicode)                                |
| **Boolean**   | true, false                                       |
| **String**    | All string literals (String pool)                 |

Note:  
1. Primitives: No caching needed (they are raw values, always directly compared using ==)  
2. Wrappers: Yes, caching is implemented (because they are objects with identity)  

# Important Terminologies (Java 8)
   - `Interface`: Contract for classes, without specifying implementation.  
   - `Functional Interface`: An interface with exactly one abstract method, used for lambda expressions.  
   - `Lambda Expressions`: helps represent or implement functional interfaces in a `concise` way.  
   - `Method References`: helps represent or implement functional interfaces in an even more `concise` way  
   - `Pre-defined Functional Interfaces`: Java provides several pre-defined functional interfaces in the `java.util.function` package, which can be used with lambda expressions and method references.  
   - `Streams`: helps process data efficiently.  `Stream pipelines`: Sequence of operations (intermediate + terminal) that are performed on the data in the stream.  


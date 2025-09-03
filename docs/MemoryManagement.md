# Computer Memory (RAM & ROM)
Computer memory is a hardware component that stores data and instructions temporarily or permanently for processing by the computer's CPU (Central Processing Unit). It is a crucial part of a computer system, enabling it to perform tasks efficiently.
- `RAM (Random Access Memory)`: A type of volatile memory that temporarily stores data and instructions that the CPU needs while executing programs. It allows for quick read and write access, enabling fast data retrieval and processing. However, data in RAM is lost when the computer is powered off.
- `ROM (Read-Only Memory)`: A type of non-volatile memory that permanently stores data and instructions that are essential for the computer's startup and basic operations. Data in ROM cannot be modified or erased easily, making it suitable for storing firmware (BIOS) or low-level-system instructions, such as those needed for initializing hardware & to boot up the computer.

| Feature    | **RAM (Random Access Memory)**                    | **ROM (Read-Only Memory)**                                                     |
|------------|---------------------------------------------------|--------------------------------------------------------------------------------|
| Purpose    | Stores data **temporarily** while programs run    | Stores **permanent instructions** needed for startup                           |
| Volatility | **Volatile** – loses everything when power is off | **Non-volatile** – data stays even when power is off                           |
| Read/Write | You can **read and write** freely                 | Normally **read-only** (very hard to change)                                   |
| Speed      | Much **faster** than ROM                          | Generally **slower** than RAM; because it's designed for permanence than speed |
| Example    | A whiteboard you write and erase on               | A printed book you can only read                                               |

# JVM (Java Virtual Machine): Software that runs Java applications
- JVM is a software that is installed on machine's hard drive or SSD. When you run a Java application, the JVM executable and libraries are loaded from your storage device into your computer's RAM.
- JVM is used to run Java applications. It provides a runtime environment that allows Java bytecode to be executed on any platform without modification.
- JVM is platform-dependent, meaning that there are different implementations of the JVM for different operating systems (Windows, Linux, macOS, etc.). However, the Java bytecode remains the same across all platforms, making Java a "write once, run anywhere" language.
> JVM efficiently uses `RAM` (Random Access Memory) for memory allocations for Java applications.

# Memory Management in Java
- In Java applications, memory allocation and de-allocation are not managed directly by developers (unlike C or C++). Instead, this responsibility is handled automatically by the `Java Virtual Machine (JVM)` during runtime.  
- When objects are no longer needed or referenced, the JVM's `Garbage Collector (GC)` automatically reclaims the memory used by those objects, preventing memory leaks and optimizing memory usage.

## JVM Memory Areas
JVM divides memory into several distinct areas, each with specific responsibilities.

| Memory Area                                                                              | What is stored                                                             | Comments                                                                   | Managed by             | Lives in |
|------------------------------------------------------------------------------------------|----------------------------------------------------------------------------|----------------------------------------------------------------------------|------------------------|----------|
| Heap Memory <br/> = Young generation `(Eden space + 2 Survivor Spaces)` + Old generation | Objects, Instance variables of objects, Arrays and String                  | Objects are allocated on the heap when created using `new`.                | Garbage Collector (GC) | RAM      |
| Stack Memory                                                                             | Primitive data types, Method calls, Local variables, References to objects | NA                                                                         | JVM, per thread        | RAM      |
| Metaspace / Method Area                                                                  | Class metadata (class definition & method definition), Static variables    | Replaced PermGen in Java 8+                                                | JVM                    | RAM      |
| Program Counter Register                                                                 | Current execution line per thread                                          | Keeps track of the JVM instruction for each thread currently executing     | JVM                    | RAM      |
| Native Method Stack                                                                      | Native libraries, JNI (Java Native Interface) data                         | Used for processing native code, typically via JNI (Java Native Interface) | JVM                    | RAM      |


**Additional Notes:**  
1. `Young Generation` and `Old Generation` are parts of Heap Memory.  
2. `Young Generation` is where new objects are allocated. When this area fills up, a minor garbage collection occurs to reclaim memory from objects that are no longer reachable. Objects that survive multiple minor GCs are promoted to the Old Generation. Young Generation is further divided into `Eden Space` (for new objects initial allocation) and `2 Survivor Spaces` (used to hold objects that have survived previous garbage collections)  
3. `Old Generation` is where long-lived objects are stored. When this area fills up, a major garbage collection occurs, which is typically more time-consuming than a minor GC.  

### Example of Memory Allocation in Java
```java
public class MemoryDemo {
  static int staticVar = 100;                                     // static variable (class-level) stored in Metaspace
  int instanceVar;                                                // non-static variable (per object) stored in Heap

  public MemoryDemo(int val) {
    this.instanceVar = val;
  }

  public void show() {
    int localVar = 50;                                            // local variable (method-level) stored in Stack
    String str = "Hello";                                         // String literal stored in String Pool (inside Heap)
    String objStr = new String("World");                          // String object in heap stored in Heap
    System.out.println(localVar + " " + str + " " + objStr);
  }

  public static void main(String[] args) {
    MemoryDemo obj1 = new MemoryDemo(10);                               // object 1 stored in Heap
    MemoryDemo obj2 = new MemoryDemo(20);                               // object 2 stored in Heap
    obj1.show();                                                        // method call creates a new stack frame in Stack
    obj2.show();                                                        // method call creates a new stack frame in Stack
  }
}
```
1. Metaspace: (class metadata area)
- Class definitions/bytecode (MemoryDemo, String, System, etc.)
- static variables (staticVar = 100)
- Method definitions/bytecode (MemoryDemo constructor, show method, main method)

2. Heap Memory: (object storage area)
- obj1 → stores instance variable instanceVar = 10.
- obj2 → stores instance variable instanceVar = 20.
- "Hello" literal → stored in String Pool (inside Heap).
- objStr = new String("World") → "World" object in Heap.

3. Stack Memory: (method call storage area per thread)
- For main thread:
  - args reference (points to array in Heap).
  - obj1, obj2 references → pointing to objects in Heap.
- When calling obj1.show():
  - A new stack frame is created for show().
  - localVar = 50 stored in stack frame.
  - str reference → points to "Hello" in Heap (string pool).
  - objStr reference → points to new "World" in Heap.

4. Program Counter Register: (execution tracking per thread)
- Keeps track of the current instruction being executed in the thread, such as which line of code is currently running.
- This register is updated as the program executes, allowing the JVM to know where it is in the code execution flow.
- It is not directly accessible in Java code, but it is crucial for the JVM to manage thread execution.
- For example, when executing `obj1.show()`, it points to the instruction in the show method. When executing `System.out.println(...)`, it points to the instruction in the println method.

5. Native Method Stack: (native code storage area)
- If the program uses any native libraries (e.g., through JNI), the native method stack would store references to those native methods and their parameters.
- For example, System.out.println() calls native I/O methods to print to the console, which would be handled in the native method stack.

# Garbage Collector
Garbage collector frees memory by removing objects that are no longer reachable or needed by the application.  

**Unreachable Objects**  
An object becomes unreachable if it does not contain any reference to it.  

```java
Integer i = new Integer(4);        // the new Integer object is reachable via the reference in 'i'  
i = null;                          // the Integer object is no longer reachable. 

// After i = null, integer object in the heap area is eligible for garbage collection.
```

## How to make an object eligible for garbage collection?
Even though the programmer is not responsible for destroying useless objects, but it is highly recommended to make an object unreachable (thus eligible for GC) if it is no longer required.  

**Ways to make an object eligible for garbage collection:**  
- Nullifying the reference variable (obj = null).
- Reassigning the reference variable to another object (obj = new Object()).
- An object created inside the method (eligible after method execution).  

```java
public class GCDemo {
  public static void main(String[] args) {
    GCDemo obj1 = new GCDemo();   // obj1 references a new GCDemo object
    GCDemo obj2 = new GCDemo();   // obj2 references another new GCDemo object

    obj1 = null;                  // The first GCDemo object is now eligible for garbage collection
    obj2 = new GCDemo();          // The second GCDemo object is now eligible for garbage collection

    createObject();               // The GCDemo object created inside this method is eligible for garbage collection after method execution
  }

  public static void createObject() {
    GCDemo obj3 = new GCDemo();   // obj3 references a new GCDemo object
    // obj3 becomes unreachable after this method ends, making the object eligible for garbage collection
  }
}
```

## Can you invoke the garbage collector manually?
Once an object is eligible for garbage collection, it may not be destroyed immediately.  
The garbage collector runs at the JVM's discretion, and you cannot predict when it will occur.  
You can suggest the JVM to perform garbage collection by calling `System.gc()` or `Runtime.getRuntime().gc()`, but it is not guaranteed that the garbage collector will run immediately or at all. The JVM decides whether to honor it.  

**Note:** Before destroying an object, the garbage collector calls the finalize() method to perform cleanup activities.
finalize() method is a protected method of the Object class (and is deprecated in Java 9), which can be overridden in your class to perform cleanup before the object is garbage collected.  

## GC Performance Metrics
GC performance is evaluated based on the following parameters:  
- `Throughput`: Percentage of time an application spends on actual work as opposed to time spent on GC. Higher throughput means more time spent executing application code.  
- `Latency`: Time during which the GC pauses the application or Time taken by the application to respond to user requests. Lower latency means better responsiveness.  

## Types of GC
There are `7 types of garbage collectors`, each with its own algorithm and strategy for managing memory, available in the JVM, some of which are obsolete now.
The choice of garbage collector can impact the performance and responsiveness of Java applications.  

| Garbage Collector              | Definition                                                              | Description                                                                                                    | Use Case                                                                                                                                           | Introduced In                     | How to enable?                                    |
|--------------------------------|-------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------|---------------------------------------------------|
| Serial GC                      | A simple & single-threaded GC; Original GC algorithm                    | It pauses all running application threads during GC                                                            | Suitable for small applications or embedded systems where memory is limited                                                                        | Java 1.2                          | java -XX:+UseSerialGC -jar Application.jar        |
| Parallel GC                    | A multi-threaded GC (Uses multiple threads for both minor and major GC) | pauses all running application threads; high throughput but long pauses                                        | Ideal for applications with high throughput requirements & can tolerate pause time                                                                 | Java 5; default in Java 8         | java -XX:+UseParallelGC -jar Application.jar      |
| CMS (Concurrent Mark-Sweep) GC | A multi-threaded & low-latency GC                                       | Performs MOST of it's work concurrently with application threads; minimizes long pauses                        | Suitable for applications that require low latency but can afford some CPU overhead                                                                | Java 4                            | java -XX:+UseConcMarkSweepGC -jar Application.jar |
| G1 (Garbage-First) GC          | A server-style garbage collector                                        | divides the heap into equal-sized regions and prioritizes the collection of regions with the most garbage.     | Designed for applications with large heaps and multi-core processors, balancing throughput and low pause times.                                    | Java 7; default in Java 9 & later | java -XX:+UseG1GC -jar Application.jar            |
| Z GC                           | A scalable, low-latency GC                                              | designed to handle large heaps with minimal pause times                                                        | Suitable for applications requiring large memory and low latency, such as big data and real-time systems.                                          | Java 11                           | java -XX:+UseZGC -jar Application.jar             |
| Shenandoah GC                  | A low-pause-time GC                                                     | aims to reduce pause times by performing more work concurrently with the application; has high CPU consumption | Ideal for applications that require consistent low latency and can afford some CPU overhead.                                                       | Java 12                           | java -XX:+UseShenandoahGC -jar Application.jar    |
| Epsilon GC                     | A no-op garbage collector                                               | does not reclaim any memory                                                                                    | Primarily used for testing and performance tuning, as it allows developers to analyze memory usage without the interference of garbage collection. | Java 11                           | java -XX:+UseEpsilonGC -jar Application.jar       |
  

**Q. When can CMS GC pause the application threads?**  
`CMS GC handles Old Generation only` & is mostly done concurrently with application threads. Minor GCs for Young Generation are still handled by the Parallel GC, which may cause longer pauses.  
CMS (Concurrent Mark Sweep) GC can pause application threads, but only for short periods and at specific times during its garbage collection cycle.  
1. `Initial Mark Pause`: At the very beginning of the CMS cycle, all application threads pause briefly while CMS marks objects that are directly reachable from the program's roots (like global variables and thread stacks). This helps CMS start the garbage collection accurately.
2. `Remark Pause`: After CMS works concurrently alongside the application for some time, it pauses the application threads again to catch any changes made to object references during the concurrent phase. This pause is usually longer than the initial mark but still short.
Between these pauses, CMS performs the majority of its work concurrently without stopping the application, minimizing total pause time.  
  
**Q. Which GC is the default in Java?**
1. The default garbage collector in Java 8 is the `Parallel GC`.  
2. The default garbage collector in Java 9 and later versions is the `G1 (Garbage-First) GC`.  

## How to request or get a GC log?
You can request a GC log by adding specific JVM options when starting your Java application. The options vary depending on the version of Java you are using.

| Java Version       | GC Log Options                                                 | Example Command                                                                     |
|--------------------|----------------------------------------------------------------|-------------------------------------------------------------------------------------|
| Java 8 and earlier | -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:<file_path> | java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:gc.log -jar Application.jar |
| Java 9 and later   | -Xlog:gc*:file=<file_path>                                     | java -Xlog:gc*:file=gc.log -jar Application.jar                                     |

## How to check which GC is being used by the JVM?
Simplest way to check which garbage collector (GC) is being used by the JVM is by checking the GC logs.
Note: You can't check the GC type in VisualVM (with Visual GC plugin).

# Monitoring and Profiling Tools
JVM provides several tools to monitor and profile Java applications, helping developers analyze performance, memory usage, and garbage collection behavior. Some popular tools include:

| Tool      | Description                                                                                                                            | Use Case                                                            |
|-----------|----------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------|
| VisualVM  | A graphical tool for monitoring and profiling Java applications, providing insights into memory usage, CPU usage, and thread activity. | Useful for real-time monitoring and profiling of Java applications. |
| JConsole  | A graphical monitoring tool that provides information about memory usage, thread activity, and CPU usage of Java applications.         | Suitable for basic monitoring of Java applications.                 |
| Visual GC | A plugin for VisualVM that provides a visual representation of garbage collection activity and memory usage in the JVM.                | Helps visualize GC behavior and memory usage patterns.              |

# Important JVM parameters for memory management
- `-Xms<size>`: Sets the initial heap size.
- `-Xmx<size>`: Sets the maximum heap size.
- `-XX:MetaspaceSize=<size>`: Sets the initial size of the Metaspace.
- `-XX:MaxMetaspaceSize=<size>`: Sets the maximum size of the Metaspace.
- -XX:MaxRAM=<size>: Sets the maximum amount of memory the JVM can use.
- and many more...

Note: Default values are set dynamically by the JVM during startup based on the machine’s available physical memory and platform capabilities.

# Are there any in-built tools to verify the JVM parameters?
JVM installation provides built-in tools to check VM arguments and JVM parameters for running Java processes.
1. `jps`: (Java Virtual Machine Process Status Tool)
    - Lists running Java processes and their JVM arguments.
    - Example: `jps -v` includes JVM arguments like -Xmx, -Xms, etc., for each process.

2. `jinfo`: (JVM Information Tool)
3. `jcmd`: (Java Command Tool)  
   and more...
# How to Create an Immutable Object in Java?
An `immutable object` is an object whose state cannot be changed after it is created.  
For example, String is an immutable class and, once instantiated, the value of a String object never changes.  

In Java, you can create an immutable object by following these 5 principles:  
1. **Declare the class as `final`**: This prevents the class from being subclassed, which could potentially modify its behavior.
2. **Make all fields `private` and `final`**: This ensures that the fields cannot be accessed directly from outside the class and cannot be changed after initialization.
3. **Initialize all fields in the `constructor`**: Set the values of the fields when the object is created.
4. **Use `defensive copying` & return copy from getters**: If the class contains mutable objects, ensure that you create defensive copies in the constructor and any methods (such as getters) that return these objects.
5. **Avoid `setters`**: Do not provide setter methods for variables that would allow modification of the object's state after creation.


# Shallow Copy vs Deep Copy/Defensive Copy
`Shallow copy`: Copies the object’s fields as-is. If a field is a reference to another object, the reference is copied and not the actual object. So, the new object is dependent on the original object.
Meaning, both original and copy points to the same referenced objects. Changes in one affect the other for those references.  

**Note:** A shallow copy is useful when the object’s fields are simple types (e.g. integers, strings, etc.) and doesn’t have any references to other objects.
Object class provides a `clone()` method that, by default, performs a shallow copy of the object.  

```java
public class User implements Cloneable {

  public String name;
  public Department department;

  public User(String name, Department department) {
    this.name = name;
    this.department = department;
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
}
```

```java
User user = new User("John", new Department(111, "test"));
User deepCopyUser = (User) user.clone();

System.out.println(user.department == deepCopyUser.department);     //true     
```

`Deep copy`: Creates a completely independent copy of the object, including new copies of any referenced objects.  
Changes in the original won’t affect the copy, and vice versa.  

Deep copy is useful when the object’s fields are references to other objects, and when we want the new object to be completely independent.  
It is typically implemented by manually overriding the clone() method and creating a new object with copying each field’s values from the original object to the new object.  

```java
public class User implements Cloneable {

  public String name;
  public Department department;

  public User(String name, Department department) {
    this.name = name;
    this.department = department;
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    return new User(this.name, new Department(this.department.id, this.department.zone));
  }
}
```

```java
User user = new User("John", new Department(111, "test"));
User deepCopyUser = (User) user.clone();

System.out.println(user.department == deepCopyUser.department);    //false
```
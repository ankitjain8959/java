# Serialization & Deserialization
`Serialization`  
It is the process of converting a Java object into a JSON string (or byte stream) that can be sent over the network or saved.
Example: If dealing with web requests & responses, when you return an object from a REST controller, Spring uses a serializer (usually Jackson's ObjectMapper) to convert that object into JSON. 
  
`Deserialization`  
It is the reverse process, where a JSON string (or byte stream) is converted into a Java object.
Example: If dealing with web requests & responses, when you send a request with a JSON body, Spring uses a deserializer to convert the JSON into a Java object.

```
Deserialize = Download = JSON → Java ("D" for download. You are bringing or downloading data into your application)

Serialize = Send = Java → JSON ("S" for Send. You are sending data out of your application)
```

# Jackson library & ObjectMapper class
`Jackson` is a popular Java library used for converting Java objects to and from JSON.  
By default, Spring Boot uses `Jackson` library for serialization and deserialization.  
`ObjectMapper` is the core class from Jackson library used for this purpose.  

When the microservice receives or returns data via REST APIs, Jackson automatically converts:
• Incoming JSON (from requests) into Java objects => download data into your application => deserialization  
• Outgoing Java objects (responses) into JSON => sending data out of your application => serialization  


**ObjectMapper** <br>
ObjectMapper is the core class from the Jackson library that enables actual conversion between Java objects and JSON.

Purpose of ObjectMapper: <br>
1. Serialize Java objects to JSON:
> String json = objectMapper.writeValueAsString(myObject);

This is useful when sending JSON responses from your service.

2. Deserialize JSON to Java objects:
> MyObject obj = objectMapper.readValue(jsonString, MyObject.class);

This is useful when processing incoming API requests or reading data from MongoDB or external APIs.


# Transient Fields
1. `transient` keyword in Java
- In Java, the `transient` keyword is used to indicate that a field should not be serialized; when converting an object to JSON (or a byte stream).
- When an object is serialized, any field marked as `transient` is skipped. This is especially important for security reasons, as it prevents sensitive data (such as passwords, token) from being exposed in the serialized JSON.
- During deserialization, transient fields are not populated from the JSON data and will have their default values (e.g., null for objects, 0 for integers).

```java
class User implements Serializable {
    private String username;
    private transient String password;            // Skipped during Java serialization
}
```

2. `@Transient` annotation from JPA
- The `@Transient` annotation is used in JPA (Java Persistence API) to indicate that a field should not be persisted to the database.
- The field can still be serialized to JSON by Jackson, unless it is also marked as `transient` or annotated with `@JsonIgnore`.
```java
@Entity
class User {
    private String username;
    
    @Transient
    private String sessionToken;           // Not persisted to DB, but will be serialized to JSON
}
```

3. `@JsonIgnore` annotation from Jackson
- The `@JsonIgnore` annotation is used to indicate that a field should be ignored during JSON serialization and deserialization by Jackson.
- When a field is annotated with `@JsonIgnore`, it will not be included in the JSON output when the object is serialized, and it will not be populated when deserializing from JSON. However, the field will still be persisted to the database unless it is also marked as `@Transient`.

```java
class User {
    private String username;
    
    @JsonIgnore
    private String password;                // Skipped during JSON serialization/deserialization
}
```

## Summary
| Keyword/Annotation | Purpose                                      | Affects Serialization | Affects Persistence |
|--------------------|----------------------------------------------|-----------------------|---------------------|
| transient          | Prevents field from being serialized         | Yes                   | No                  |
| @Transient         | Prevents field from being persisted to DB    | No                    | Yes                 |
| @JsonIgnore        | Prevents field from being serialized to JSON | Yes                   | No                  |

### Rule of Thumb for Spring Boot apps
1. For APIs → use `@JsonIgnore`.
2. For DB entities → use `@Transient`.
3. Use `transient` keyword only if you’re explicitly serializing Java objects (rare in modern Spring Boot projects).  


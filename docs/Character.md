# Character
The `Character` class in Java is a wrapper class for the primitive data type `char` which represents a single 16-bit Unicode character.  
It provides methods to manipulate and inspect characters, such as checking if a character is a digit, letter, or whitespace, converting between uppercase and lowercase, and more.

# Static methods of the Character class
- isDigit & isLetter & isAlphabetic:
  `isDigit()` checks if a given character is a digit (0-9). Returns `true` if the character is a digit; otherwise, it returns `false`.
  `isLetter()` checks if a given character is a letter (a-z, A-Z). Returns `true` if the character is a letter; otherwise, it returns `false`.
  `isAlphabetic()` checks if a given character is an alphabetic character (which includes all standard letters and some additional alphabetic characters such as Roman numerals). Returns `true` if the character is alphabetic; otherwise, it returns `false`.

```java
boolean isDigit = Character.isDigit('5');               // true
boolean isNotDigit = Character.isDigit('a');            // false

boolean isLetter = Character.isLetter('A');             // true
boolean isNotLetter = Character.isLetter('1');          // false

boolean isAlphabetic = Character.isAlphabetic('Ⅻ');    // true
boolean isNotAlphabetic = Character.isAlphabetic('1');  // false
```

- isUpperCase & toUpperCase: 
  `isUpperCase()` checks if a given character is an uppercase letter (A-Z). Returns `true` if the character is uppercase; otherwise, it returns `false`.
  `toUpperCase()` converts a given character to its uppercase equivalent. If the character is already uppercase or not a letter, it returns the character unchanged.

```java
boolean isUpperCase = Character.isUpperCase('A'); // true
boolean isNotUpperCase = Character.isUpperCase('a'); // false

char upperCaseChar = Character.toUpperCase('a'); // 'A'
char unchangedChar = Character.toUpperCase('A'); // 'A'
```

- isLowerCase & toLowerCase: 
  `isLowerCase()` checks if a given character is a lowercase letter (a-z). Returns `true` if the character is lowercase; otherwise, it returns `false`.
  `toLowerCase()` converts a given character to its lowercase equivalent. If the character is already lowercase or not a letter, it returns the character unchanged.

```java
boolean isLowerCase = Character.isLowerCase('a'); // true
boolean isNotLowerCase = Character.isLowerCase('A'); // false

char lowerCaseChar = Character.toLowerCase('A'); // 'a'
char unchangedChar = Character.toLowerCase('a'); // 'a'
```

- isWhitespace: The `isWhitespace()` method is a static method of the `Character` class that checks if a given character is a whitespace character (space, tab, newline, etc.).  
  It returns `true` if the character is a whitespace; otherwise, it returns `false`.

```java
boolean isWhitespace = Character.isWhitespace(' '); // true
boolean isNotWhitespace = Character.isWhitespace('A'); // false
```


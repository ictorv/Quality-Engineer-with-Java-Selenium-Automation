# Whale Fishing Club Points Calculator - Java String Manipulation

## Objective
To work with strings in Java for parsing participant data and calculating points in a fishing competition.

## Concept Explanation
Strings in Java are immutable sequences of characters used for text manipulation and representation. They support various operations such as concatenation, splitting, and parsing, and are encoded in formats like ASCII or UTF-8.

This application demonstrates:
- Extracting and parsing participant data from a single input string.
- Performing validation on age and fish counts.
- Converting string-based fish counts to numerical values.
- Calculating scores based on the size and number of fish caught.

## Scenario
The Whale Fishing Club (WFC) is hosting a fishing competition. Each participant's input includes their name, age, and the number of big, medium, and small fish caught.

The application must:
1. Validate that the participant is at least 18 years old.
2. Validate that all fish counts are non-negative.
3. Calculate the total points using the following rules:
   - Big fish = 10 points each
   - Medium fish = 6 points each
   - Small fish = 3 points each
4. Print the final score in the format:
   ```
   <name> scored <points> points
   ```

## Input Format
The input should be a single string in the format:
```
<name>:<age>:<big fish>:<medium fish>:<small fish>
```

## Constraints
- Age must be >= 18. If not, print:
  ```
  <age> is an invalid age
  ```
- Fish counts must be >= 0. If any are negative, print:
  ```
  <fish count> is an invalid input
  ```
- Do not use `System.exit(0)` to terminate the program.

## Sample Input and Output

### Example 1
**Input:**
```
Enter the details  
Hari:20:5:9:15  
```

**Output:**
```
Hari scored 149 points
```

**Explanation:**
```
Big = 5 * 10 = 50  
Medium = 9 * 6 = 54  
Small = 15 * 3 = 45  
Total = 149
```

---

### Example 2
**Input:**
```
Enter the details  
Quil:15:2:7:2  
```

**Output:**
```
15 is an invalid age
```

---

### Example 3
**Input:**
```
Enter the details  
Allan:22:-1:2:3  
```

**Output:**
```
-1 is an invalid input
```

---

## Notes
- Follow proper class, attribute, and method naming conventions in Java.
- Ensure object-oriented design is used as per the specifications.


# WordZee Verifier - Java String Manipulation

## Objective
To work with strings in Java by validating and manipulating sentences and words.

## Concept Explanation
Strings are sequences of characters used to store textual data. In Java, strings are immutable and offer various built-in methods for operations such as concatenation, searching, comparison, and manipulation.

In this project:
- Sentences and words are taken as input strings.
- Input validation ensures only alphabetic characters and whitespace are present.
- Words are searched in the sentence.
- If found, the word is reversed in the sentence.
- Invalid inputs are flagged with appropriate messages.

## Scenario
**WordZee** is a memory-enhancement game used to help children learn spelling. The teacher asks the student to reverse a word from a given sentence. You, as a Java developer, help the teacher by implementing a program that:

- Validates the sentence and the word.
- Reverses the word if it's valid and present in the sentence.
- Displays appropriate messages if any constraints are violated.

## Constraints

- If the sentence contains numbers or special characters:
  ```
  <sentence> is an invalid sentence
  ```

- If the word contains numbers or special characters:
  ```
  <word> is an invalid word
  ```

- If the word is not found in the sentence:
  ```
  <word> is not in the sentence
  ```

## Sample Input and Output

### Example 1
**Input:**
```
Enter a sentence  
He ate a delicious pizza for dinner  
Enter a word  
ate  
```

**Output:**
```
He eta a delicious pizza for dinner
```

---

### Example 2
**Input:**
```
Enter a sentence  
She is studying diligently for her exams  
Enter a word  
e1fn  
```

**Output:**
```
e1fn is an invalid word
```

---

### Example 3
**Input:**
```
Enter a sentence  
The cat chased the mouse across the room  
Enter a word  
phoo  
```

**Output:**
```
phoo is not in the sentence
```

---

### Example 4
**Input:**
```
Enter a sentence  
I like coo9@667  
```

**Output:**
```
I like coo9@667 is an invalid sentence
```

---

## Notes
- Do not use `System.exit(0)` to terminate the program.
- Follow object-oriented principles while implementing the solution.
- Ensure class names, attributes, and methods are meaningful and follow Java conventions.

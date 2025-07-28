# Description

Objective:
To work with collections.
Concept Explanation:
Java Collections provide classes and interfaces to manage groups of objects efficiently. They offer dynamic resizing, efficient search, and various data structures like lists, sets, maps, and queues. Collections simplify tasks like sorting, searching, and iteration, improving code readability and maintainability.

Concept Implementation:
Collections like ArrayList and HashMap are used to streamline the analysis of student paragraphs. ArrayList stores words extracted from each paragraph, maintaining their order. HashMap counts word occurrences efficiently, enabling accurate analysis. By leveraging these collections, the program efficiently processes and analyzes multiple student paragraphs, providing detailed feedback to Babitha.

Scenario: 
Babitha, an experienced typist, conducts daily practice tests for her students to enhance their typing skills. Each day, students compose a paragraph based on a given article within a specified time limit and submit it to Babitha for evaluation. Babitha emphasizes the use of specific special characters like , ; : . ? ! @ \# \$ % in the submitted paragraphs, as these characters help to split words in a sentence. Any other special characters are considered part of the word itself.

To assess the students' performance, Babitha meticulously counts the total number of words used in each paragraph and tallies the count for each individual word. Based on this detailed analysis, she provides feedback to her students.
As the number of students increases, Babitha encounters challenges in manually processing and analyzing each student's paragraph. To streamline this process, she seeks to automate the analysis by developing a Java program. 
The program should display the words used in each paragraph, along with the number of times each word appears, in lowercase and sorted alphabetically.

Note:
The output should be alphabetically sorted and presented in lowercase.

In the Sample Input / Output provided, the highlighted text in bold corresponds to the input given by the user, and the rest of the text represents the output. 
Ensure to follow the object-oriented specifications provided in the question description.
Ensure to provide the names for the classes, attributes, and methods as specified in the question description
Adhere to the code template, if provided.
Do not use System.exit(0) to terminate the code.

Sample Input 1
Enter the paragraph typed
Great great GReat: or good, OR' kind, or fair!!!
Sample Output 1
Total number of words 9
Words with the count
fair - 1
good - 1
great - 3
kind - 1
or - 2
or' - 1

Sample Input 2:
Enter the paragraph typed
The brown\#brown@BROWN?,dog.
Sample Output 2:

Total number of words 5
Words with the count
brown - 3
dog - 1

the - 1

provide readme format of this just copy and paste

Certainly! Here’s a README in markdown format, ready to copy and paste:

# Student Paragraph Analyzer

## Objective

To automate the analysis of student-typed paragraphs using Java Collections, providing word counts and detailed feedback for each student efficiently.

## Problem Statement

Babitha, a typist, conducts daily typing practice for her students. Each student submits a paragraph, and Babitha needs to:

- Count the total number of words,
- Count the frequency of each unique word,
- Consider certain special characters (, ; : . ? ! @ \# \$ %) as word separators,
- Treat any other special character (like ') as part of a word,
- Display the results alphabetically and in lowercase.

Manual processing is tedious, so the process should be automated.

## Collection Usage

- **ArrayList**: Stores all extracted words while preserving their order from each paragraph.
- **HashMap**: Maps each unique word (in lowercase) to its frequency, ensuring efficient look-up and counting.


## Features

- Reads a paragraph from user input.
- Segregates words using the specified special characters as delimiters.
- Preserves other special characters within words.
- Converts words to lowercase for uniform counting.
- Sorts the unique words alphabetically in the output.
- Displays the total word count and each word’s frequency.


## Sample Input \& Output

### Sample Input 1:

```
Enter the paragraph typed
Great great GReat: or good, OR' kind, or fair!!!
```

**Output:**

```
Total number of words 9
Words with the count
fair - 1
good - 1
great - 3
kind - 1
or - 2
or' - 1
```


### Sample Input 2:

```
Enter the paragraph typed
The brown#brown@BROWN?,dog.
```

**Output:**

```
Total number of words 5
Words with the count
brown - 3
dog - 1
the - 1
```


## Notes

- Only special characters `, ; : . ? ! @ # $ %` split words.
- All other special characters are included in words.
- Output is sorted alphabetically by word (in lowercase).
- Avoid using `System.exit(0)` in your implementation.


## Implementation Hints

- Use regular expressions to split words by the required delimiters.
- Store words in lowercase in the `ArrayList` and count them with a `HashMap`.
- Sort the keys of the map before displaying the output.



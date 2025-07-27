
# Student Record Management using ArrayList

## 🎯 Objective
To work with array list, collections.

## 📚 Concept Explanation
ArrayList in Java is a resizable array that implements the List interface. It allows dynamic resizing, supports generics for type safety, and offers methods for adding, removing, and accessing elements. It's ordered, permits duplicates, and handles both primitive types and objects.

## 🛠 Concept Implementation
Here, utilizing an ArrayList to handle student data. It offers functionalities to add, display, and remove students via user input. Validations ensure correct menu selections, and messages handle scenarios like an empty list.

## 🎓 Scenario
The MVM college has planned to develop a simple student name list record management system using Java. The system should allow the user to add, remove, and display student records of all students in the system. You as a Java developer, develop a program as per the requirement.

Implement the above scenario using an ArrayList.

## 🧩 Component Specification: Student

### Type: Class
#### Attributes
- `List<String> studentList`

> Getter and setter methods are provided as a part of code skeleton.

> Note: The class and methods should be declared as `public` and all the attributes should be declared as `private`.

---

## 📌 Requirement 1: Add student to the ArrayList

| Component Name       | Type    | Method                                     | Responsibilities                      |
|----------------------|---------|--------------------------------------------|----------------------------------------|
| Add a student        | Student | `public void addStudentToList(String student)` | This method is used to add a student to the ArrayList. |

## 📌 Requirement 2: Sort the student in the ArrayList in alphabetical order

| Component Name       | Type    | Method                                     | Responsibilities                      |
|----------------------|---------|--------------------------------------------|----------------------------------------|
| Sort the students    | Student | `public void sortStudentList()`            | This method is used to sort the students in the ArrayList in alphabetical order. |

## 📌 Requirement 3: Remove the student from the ArrayList

| Component Name       | Type    | Method                                     | Responsibilities                      |
|----------------------|---------|--------------------------------------------|----------------------------------------|
| Remove a student     | Student | `public void removeStudentFromList(String student)` | This method is used to remove a student from the ArrayList. |

---

## 🖥️ UserInterface Class

1. Create a `main` method with the menu as described in the sample Input and Output.
2. Option 1: Add — Adds a student into the `studentList`.
3. Option 2: Display — Shows student names in alphabetical order. If empty, show "The student list is empty".
4. Option 3: Remove — Removes student. If empty, show "The student list is empty".
5. Option 4: Exit — Show "Thank you for using the application" and end the program.
6. Only options 1 to 4 are valid.

> ⚠️ Please don't use `System.exit(0)` to terminate the program.

---

## 💬 Sample Input and Output 1:
```
1.Add
2.Display
3.Remove
4.Exit
Enter your choice
1
Enter the student name
John
1.Add
2.Display
3.Remove
4.Exit
Enter your choice
1
Enter the student name
Rose
1.Add
2.Display
3.Remove
4.Exit
Enter your choice
1
Enter the student name
Peter
1.Add
2.Display
3.Remove
4.Exit
Enter your choice
2
John
Peter
Rose
1.Add
2.Display
3.Remove
4.Exit
Enter your choice
3
Enter the student name
Rose
1.Add
2.Display
3.Remove
4.Exit
Enter your choice
2
John
Peter
1.Add
2.Display
3.Remove
4.Exit
Enter your choice
4
Thank you for using the application
```

---

## 💬 Sample Input and Output 2:
```
1.Add
2.Display
3.Remove
4.Exit
Enter your choice
1
Enter the student name
harry
1.Add
2.Display
3.Remove
4.Exit
Enter your choice
1
Enter the student name
Ravi
1.Add
2.Display
3.Remove
4.Exit
Enter your choice
2
Ravi
harry
1.Add
2.Display
3.Remove
4.Exit
Enter your choice
4
Thank you for using the application
```

---

## 💬 Sample Input and Output 3:
```
1.Add
2.Display
3.Remove
4.Exit
Enter your choice
3
Enter the student name
sofi
The student list is empty
1.Add
2.Display
3.Remove
4.Exit
Enter your choice
4
Thank you for using the application
```

---

## 💬 Sample Input and Output 4:
```
1.Add
2.Display
3.Remove
4.Exit
Enter your choice
2
The student list is empty
1.Add
2.Display
3.Remove
4.Exit
Enter your choice
4
Thank you for using the application
```

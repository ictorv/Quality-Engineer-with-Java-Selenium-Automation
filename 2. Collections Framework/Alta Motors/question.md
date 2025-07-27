
# Description

Objective:
To work with hash set in collections.
Concept Explanation:
A HashSet in Java ensures unique elements using a hash table for efficient operations. It's unordered, automatically ignores duplicates, and ideal for tasks prioritizing element uniqueness over order.

Concept Implementation:
The DepartmentGrouping class manages employee IDs and department names with a HashSet. It validates and adds employee IDs, finding corresponding department names. The UserInterface class facilitates user interaction for inputting details and displays results. Validation ensures the input count is valid, showing error messages if needed.

Scenario:
Alta Motors is one of the famous manufacturing companies in the city. They wanted to group the employee IDs with their respective Department names. Help them find valid employee IDs and group them into their corresponding departments.
Write a Java program to implement the above scenario.  
The above scenario should be implemented using a HashSet.
Component Specification: DepartmentGrouping  
Type (Class) 
Attributes 
Methods 
DepartmentGrouping 
HashSet<String> employeeSet 
Include the getter and setter method. 
Note: The class and methods should be declared as public, and all the attributes should be declared as private.

Requirement 1: Add the valid employeeId to the HashSet. 
As per this requirement, the system should be able to add valid employeeIds to the HashSet.  
Component Name 
Type (Class)  
Methods 
Responsibilities  
Add valid employeeId to the HashSet. 
DepartmentGrouping
public void addEmployeeId(String details)  
This method should extract the employeeId from the given input and add only the valid employeeIds to the HashSet.
Validation condition:
The employeeId should begin with the letter 'P',' Q', 'M', or 'S' followed by 3-digit number. Else the employeeId is invalid and should not be added to the HashSet.
Only valid employeeIds are added to the HashSet.
 
 
Requirement 2: Find the department name for a valid employeeIds in the HashSet.
As per this requirement, the system should be able to find the department name of the employeeIds present in the HashSet.  
Component Name 
Type (Class)  
Methods 
Responsibilities
Find the department name for the employeeIds in the HashSet
DepartmentGrouping
public HashSet<String> findDepartmentNameWithEmployeeId()
This method is used to find the department name of the employeeIds in the HashSet.
The employeeIds that start with letter 'P' belong to the "Product" department.
The employeeIds that start with letter 'Q' belong to the "Quality Control" department
The employeeIds that start with letter 'M' belong to the "Maintenance" department
The employeeIds that start with letter 'S' belong to the "Sales" department
(Department name is case sensitive)
Append the employeeId and the department name.
Eg: If employeeId is P987 and the department is Production, the appended string should be P987:Production.
Store the appended string in another HashSet and return the same.
 
In the UserInterface class, call the above methods and display the output as given in the sample input/output. 
If the returning HashSet from findDepartmentNameWithEmployeeId() method is empty, then display, "All the provided employee ids are invalid".
Note:
The number of details should be greater than 0. Display "<number of details> is an invalid input" if this condition fails. 
In the Sample Input / Output provided, the highlighted text in bold corresponds to the input given by the user and the rest of the text represents the output. 
Ensure to follow the object-oriented specifications provided in the question. 
Ensure to provide the names for classes, attributes, and methods as specified in the question. 
Adhere to the code template, if provided. 
Please don't use System.exit(0) to terminate the program.
 
SAMPLE INPUT / OUTPUT 1:
Enter the number of details
4
Enter the details
P567:Paul
M568:John
Q910:Lilly
S178:Dass
Q910:Quality Control
P567:Product
M568:Maintenance
S178:Sales
 
SAMPLE INPUT / OUTPUT 2:
Enter the number of details
3
Enter the details
E980:Nancy
M876:Maria
Q1234:Tara
M876:Maintenance
(Explanation: The first (E980) and last(Q1234) are invalid employeeIds)
 
SAMPLE INPUT / OUTPUT 3:
Enter the number of details
2
Enter the details
W1235:Riya
O999:Sagar
All the provided employee ids are invalid
 
SAMPLE INPUT / OUTPUT 4:
Enter the number of details
-2
-2 is an invalid input

for this one also

Here is a ready-to-copy README in markdown format for the **Alta Motors Employee Department Grouping** scenario, matching your requirements:

# Alta Motors Employee Department Grouping

## Objective

Automate the process of grouping valid employees by their department using `HashSet` in Java, ensuring only unique and valid employee IDs are considered.

## Problem Statement

Alta Motors wants to group employee IDs by their respective departments. You need to implement:

- **Validation**: Only employee IDs beginning with `P`, `Q`, `M`, or `S` followed by 3 digits are valid.
- **Uniqueness**: Store only unique valid employee IDs.
- **Department Identification**: Determine the department name for each valid employee ID.
- **Output**: For each valid employee, display `employeeId:DepartmentName`.


## Concept Explanation

- **HashSet**: Used to store unique employee IDs for efficient addition and lookup.
- **Validation**: Only IDs matching the format are considered.
- **Department Mapping**: Department name is determined by the starting character of the ID.
- **Encapsulation**: Follows standard OOP principles; attributes are private with public getters/setters.


## Implementation Structure

### 1. `DepartmentGrouping` Class

- **Attributes:**
    - `private HashSet<String> employeeSet`
- **Methods:**
    - `public HashSet<String> getEmployeeSet()`
    - `public void setEmployeeSet(HashSet<String> employeeSet)`
    - `public void addEmployeeId(String details)`
        - Adds valid employee IDs to the HashSet from the string, ignoring invalid ones.
    - `public HashSet<String> findDepartmentNameWithEmployeeId()`
        - Returns a HashSet with strings in the format `employeeId:DepartmentName` for each valid ID.


### 2. `UserInterface` Class

- Handles inputs from the user.
- Calls methods of `DepartmentGrouping`.
- Handles and displays output as per specs, including error messages.


## Validations

- The number of details (input count) must be greater than 0. Display `<number> is an invalid input` if not.
- Only employee IDs matching the pattern are allowed:
    - Start with `P`, `Q`, `M`, or `S`
    - Followed by exactly three digits (e.g., `P567`)
- All other IDs are ignored (not added to set or output).
- If no valid employee IDs found, output:
`"All the provided employee ids are invalid"`


## Mapping Employee ID to Department

| ID starts with | Department Name |
| :--: | :-- |
| P | Product |
| Q | Quality Control |
| M | Maintenance |
| S | Sales |

*Note: Department name is case-sensitive as given above.*

## Sample Input/Output

### Sample 1

```
Enter the number of details
4
Enter the details
P567:Paul
M568:John
Q910:Lilly
S178:Dass
P567:Product
M568:Maintenance
Q910:Quality Control
S178:Sales
```


### Sample 2

```
Enter the number of details
3
Enter the details
E980:Nancy
M876:Maria
Q1234:Tara
M876:Maintenance
```

(First and last are invalid. Only `M876` is output.)

### Sample 3

```
Enter the number of details
2
Enter the details
W1235:Riya
O999:Sagar
All the provided employee ids are invalid
```


### Sample 4

```
Enter the number of details
-2
-2 is an invalid input
```
# Water Tank Alarm Capacity Calculator - Java Control Structures

## Objective
To work with control structures in Java.

## Concept Explanation
Control structures such as `if`, `else if`, and `switch` help determine the program flow based on conditions. They are essential in enabling decision-making within a Java application.

In this task:
- `if` statements are used to validate user input.
- The volume of a cylindrical tank is calculated using the formula:
  ```
  Volume = Pi * radius * radius * height
  ```
- The alarm capacity is calculated as 3/4th of the total volume.
- Results are printed using `System.out.printf` with two decimal precision for better readability.

## Scenario
Mr. Smith aims to reduce water waste by triggering an alarm when his cylindrical water tank is 75% full. The program should:

1. Take `radius` and `height` as inputs from the user.
2. If either is less than or equal to 0, print:
   ```
   Invalid measurement
   ```
3. Otherwise, compute:
   - Total volume using: `3.14 * radius * radius * height`
   - Alarm capacity as: `3/4 * total_volume`
4. Print both values with **2 decimal places**.

## Constraints
- Use Pi as `3.14`
- Use `System.out.printf("%.2f", value);` for output formatting
- Avoid using `System.exit(0);`
- Follow Java naming conventions and object-oriented principles

## Sample Input and Output

### Example 1
**Input:**
```
Enter the radius  
28  
Enter the height  
42  
```

**Output:**
```
Total capacity is 103393.92 litres  
Capacity for alarm is 77545.44 litres
```

---

### Example 2
**Input:**
```
Enter the radius  
0  
Enter the height  
25  
```

**Output:**
```
Invalid measurement
```

---

## Notes
- Ensure proper class, method, and attribute naming.
- Use object-oriented design if specified in the full specification.


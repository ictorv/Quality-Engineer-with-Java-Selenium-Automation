
# BMI Calculator

## 📌 Objective
To work with **Java data types** by building a simple **BMI (Body Mass Index)** calculator. This project demonstrates how to use various data types like `String` and `double` to accept user input and calculate BMI.

## 📖 Concept Explanation
Java supports different data types, which define the kind of data a variable can hold:
- `int` for integers
- `double` for decimal numbers
- `char` for characters
- `boolean` for true/false values
- `String` for text

In this project, we use `String` for storing a name and `double` for representing height and weight (as these can be decimal values). 

## 🛠 Concept Implementation
The program:
- Takes **user input**: name (`String`), height (`double`), and weight (`double`)
- Calculates **BMI** using the formula:

  \[
  \text{BMI} = \frac{\text{weight}}{\text{height}^2}
  \]

- Classifies the BMI into four categories:
  - BMI < 18.5 → Under weight
  - 18.5 ≤ BMI < 25 → Normal weight
  - 25 ≤ BMI ≤ 30 → Over weight
  - BMI > 30 → Obese

- Displays the results formatted with 2 decimal points.

## 📦 Features
- Object-Oriented structure
- Uses Java standard input
- Accurate BMI calculation
- Proper output formatting
- Categorization based on BMI range

## 💻 Sample Input/Output

### Input 1
```
Enter your name  
Sarah  
Enter your height in meters  
1.6  
Enter your weight in kilograms  
53  
```

### Output 1
```
Hello Sarah, Your BMI is 20.70  
You are under the category Normal weight
```

---

### Input 2
```
Enter your name  
Kria  
Enter your height in meters  
1  
Enter your weight in kilograms  
80  
```

### Output 2
```
Hello Kria, Your BMI is 80.00  
You are under the category Obese
```

---

## 🚫 Constraints
- Inputs are expected to be in valid format (no input validation required).
- Use `Scanner` for input.
- BMI should be **rounded to two decimal places**.
- Do not use `System.exit(0);` in your code.
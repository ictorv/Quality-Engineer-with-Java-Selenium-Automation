# Musical Instrument Store – Abstraction \& Billing
## Objective

Practice Java abstraction by modeling a musical instrument store's billing system, using abstract classes and inheritance.

## Concept Explanation

- **Abstraction:** Show only essential object features using abstract classes/methods.
- **Abstract Class (`MusicalInstrument`):** Encapsulates common properties and defines an abstract method for bill calculation.
- **Concrete Subclass (`StringInstrument`):** Represents string instruments; implements the abstract billing.
- **Customer Integration:** Every instrument is linked to a customer.
- **User Interface:** Manages input, object creation, and output.


## Implementation Requirements

### 1. `Customer` class

- **private int customerId**
- **private String customerName**
- **private long mobileNumber**
- Public getters, setters, and constructor.


### 2. Abstract `MusicalInstrument` Class

- **protected int instrumentId**
- **protected String instrumentName**
- **protected String instrumentType**
- **protected double price**
- **protected Customer customerObj**
- Public getters/setters (as provided in the template).
- A public **5-arg constructor** for all attributes.
- Abstract method:
`public abstract double calculateTotalBill();`


### 3. `StringInstrument` Class (extends `MusicalInstrument`)

- **private int numberOfStrings**
- **private String stringMaterial**
- Public getters/setters (as provided in the template).
- Public **7-arg constructor** for all attributes and super attributes.
- Must override `calculateTotalBill()` as follows:
    - If stringMaterial (`Nylon` or `Steel`, case-insensitive):
        - Use price per string (Nylon: 15, Steel: 20).
        - `totalBill = price + (numberOfStrings * pricePerString)`
        - If price > 20,000, apply 30% discount:
            - `totalBill = totalBill - (totalBill * 0.30)`
            - Set the (super) price to discounted total
        - Return the bill.
        - In UI: If discount is applied, show **You have a discount! and your Total Bill is `<amount>`**
    - If invalid stringMaterial, return -1. UI should show **Invalid Input**.


### 4. `UserInterface` Class

- Main method.
- Reads inputs:
    - Line 1: `customerId:customerName:mobileNumber`
    - Line 2: `instrumentId:instrumentName:instrumentType:price:numberOfStrings:stringMaterial`
- Parses, instantiates objects, calls `calculateTotalBill()`, and prints the correct message as per the total and the discount rule.

----

## Sample Input/Output

### Sample 1

```
Enter the Customer Details
2468:John:9988665544
Enter the musical Instrument Details
9101:ElectricGuitar:StringInstrument:25000:6:Steel
You have a discount! and your Total Bill is 17584.0
```


### Sample 2

```
Enter the Customer Details
1234:Alice:9876543210
Enter the musical Instrument Details
5678:AcousticGuitar:StringInstrument:15000:5:Nylon
Total Bill is 15075.0
```


### Sample 3

```
Enter the Customer Details
1357:Emma:9551234567
Enter the musical Instrument Details
2468:ClassicalGuitar:StringInstrument:18000:4:Chromium
Invalid Input
```


## Notes

- **Follow all class, attribute, and method names exactly as described.**
- **Discount applies only when initial price is above 20,000.**
- **If stringMaterial is not `Nylon` or `Steel`, print `Invalid Input`.**
- **Do not use `System.exit(0)`.**
- **All classes and methods must be declared public.**
- **Discounted price is set back to the price attribute.**
- **Show prompt messages as per samples.**


## Project Structure

```
Customer.java
MusicalInstrument.java
StringInstrument.java
UserInterface.java
```

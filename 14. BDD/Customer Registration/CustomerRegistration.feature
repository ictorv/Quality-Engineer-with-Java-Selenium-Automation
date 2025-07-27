#DO NOT ALTER THE GIVEN Feature or Scenario
Feature: Customer Registration For Online Course
  Scenario Outline: Customer Registration
#Match the Statements with Step Definition file.
Given Start firefox browser and open the application
When Text "CUSTOMER REGISTRATION FOR ONLINE COURSE" is present in h1 tag
When customer details are "<Name>", "<Gender>", "<Address>", "<Email>", "<MobileNumber>", "<Location>" and "<Course>"
Then click register and display "<Name>"
Examples: 
|Name|Gender|Address|Email|MobileNumber|Location|Course|
|Raj|Male|Sarojini Street|raj@gmail.com|9873216540|Coimbatore|Cucumber|

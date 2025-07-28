#DO NOT ALTER THE GIVEN Feature or Scenario
 
Feature: Adding To Address Book
 
  Scenario: Add Address
 
#Match the Statements with Step Definition file.
Given Start firefox browser and open the application
When Text "ADDRESS BOOK" is present in h1 tag
When address details are given Name as "Raj" Type as "Temporary" Address as "Sarojini Street" Email as "raj@gmail.com" Mobile Number as "8765432109" Location as "Bangalore"
Then click add and display "Raj"
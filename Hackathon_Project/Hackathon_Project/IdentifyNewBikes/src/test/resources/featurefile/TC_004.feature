@smoke @regression
Feature: ZigWheels Honda Used Cars

  Scenario Outline: Searching for Used Cars in Chennai
    Given the user is on the ZigWheels Homepage 
    And user will hover on More in header of website
    And user will click on used cars
    Then user will select loction as Chennai
    And user apply Popular Models filter
    Then all the popular cars are displayed
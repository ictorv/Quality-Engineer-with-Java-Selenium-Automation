@smoke
Feature: ZigWheels Home Page 

  Scenario Outline: Searching for bikes with varying input
    Given the user is on the ZigWheels Homepage
    When the user enters random data in the search box
    Then the element not found error message will be printed
    And checking home page validation

@smoke
	Scenario Outline: Validation of regression testing
  	When user will hover on More in header of website
  And user will click on used cars
  	Then checking usedCars page validation
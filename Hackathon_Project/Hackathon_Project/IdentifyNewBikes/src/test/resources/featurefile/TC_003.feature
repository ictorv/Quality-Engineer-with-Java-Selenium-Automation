@smoke @regression
Feature: User Login through Apple

  Scenario: Login through Apple with invalid details 
    Given the user is on the ZigWheels Homepage
    When the user clicks on the Login/Signup
    Then the user clicks on the Apple login option
    And user enters Phone Number
    And user enter the incorrect password
    Then display a invalid cradentials errorMessage 
    And the user closes the Apple login page
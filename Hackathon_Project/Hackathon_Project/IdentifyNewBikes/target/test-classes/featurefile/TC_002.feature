@smoke @regression
Feature: ZigWheels Google Login

  Scenario Outline: User attempting to log in with invalid credentials
    Given the user is on the ZigWheels Homepage
    When the user clicks on the Login/Signup
    And the user clicks on the Google login option
    Then the user is redirected to the Google login page
    When the user enters empty credentials
    Then an error message is displayed on the Google login page
    When the user enters an invalid email for Google login
    Then an invalid email message is displayed on the Google login page
    And the user closes the Google login page
Feature: Forum Navigation and Join Community

  Scenario: Select car details and join the community forum
    Given user will hover on More in header of website
    When the user will click on the Forum
    Then the user will be navigated to the Forum Community page 
    And the user selects "Car" from the vehicle type dropdown
    And the user selects "Toyota" as the make
    And the user selects "Fortuner" as the model
    And the user clicks the "Search" button
    And the user clicks the "Join Community" button
    Then the company community page should be displayed
    When the user clicks on the FB login option
    And the user enters Invalid credentials
    And the user clicks on login
    Then an error message is displayed on the FB login page
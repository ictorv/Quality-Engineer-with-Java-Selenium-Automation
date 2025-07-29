Feature: Display new scooters for selected make

  Scenario: User filters new scooters by brand and views available options
    Given the user is on the ZigWheels Homepage
    When the user clicks on the Scooters
    And the user selects TVS as the make
    And the user clicks the search button
    Then the new TVS scooters are displayed 
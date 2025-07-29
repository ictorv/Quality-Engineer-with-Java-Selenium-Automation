@smoke @regression
Feature: ZigWheels Used Cars Filters displying

  Scenario Outline: Searching for Used Cars in Chennai and displaying
    Given user will hover on More in header of website
    And user will click on used cars
    Then user will select loction as Chennai
    And user will click on More filters button
    Then display the avaliable filters and verify
Feature: Store States Display
Scenario: Verify store states are displayed and written to Excel
	Given the user is on the home page
    When I hover over the Stores tab
    Then I should see a list of store states
    And I write the store state names to the Excel sheet "Cities"
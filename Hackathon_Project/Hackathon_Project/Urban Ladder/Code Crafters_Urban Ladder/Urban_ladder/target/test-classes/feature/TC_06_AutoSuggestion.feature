Feature: Auto-suggestion validation on Urban Ladder
 
  Scenario: Enter keyword and capture auto-suggestions into Excel
    And user closes the popup if present
    When user enters "bed" into the search field
    Then user should see auto-suggestions
    And user writes all auto-suggestion texts to Excel
 
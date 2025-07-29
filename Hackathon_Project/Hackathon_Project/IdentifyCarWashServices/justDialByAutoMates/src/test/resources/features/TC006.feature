Feature: Popular Categories Search

  @TC006
  Scenario: Find categories listed under search terms from JSON
    When I click on "Popular Categories"
    And I search for each term in the "search_terms" list from JSON
    Then I should find the matching categories
    And I should capture and display the search results

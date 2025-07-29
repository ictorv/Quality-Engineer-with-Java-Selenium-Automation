Feature: Display details of top tourist spots including hotels, restaurants, coffee shops, travel agents, and visiting spots
 
  @TC007
  Scenario Outline: Display details of top tourist spots using Excel row data
    When I scroll down to "<rowIndex>" from Excel
    And I choose the first option
    Then I should see the location page
    And I capture the top 5 results in each category: Hotels, Restaurants, Coffee Shops, Travel Agents, and Visiting Spots
 
  Examples:
    | rowIndex |
    | 1        |
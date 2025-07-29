Feature: Top 5 Restaurants

@TC005
Scenario Outline: Display top 5 restaurants near me which are open now with South Indian cuisine and online delivery
  When I search for "Restaurants" near "<row_index>"
  Then I should see a list of restaurants
  And I filter the restaurants which are open now and have South Indian cuisine and online delivery
  And I display the top 5 restaurants with their names, ratings, and number of votes

  Examples:
    | row_index |
    |         1 |
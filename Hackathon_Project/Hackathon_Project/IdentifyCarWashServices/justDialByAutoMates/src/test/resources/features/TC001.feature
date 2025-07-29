Feature: Car Wash Services Automation Workflows (Data-Driven via Apache POI)
 
 @TC001
  Scenario Outline: Display top 5 car washing services near me with high ratings and votes
    When I set the location as "<row_index>"
    And I search for "<row_index>"
    Then I should see a list of car wash services
    And I filter the services with rating more than "<row_index>"
    And I filter the people votings greater than twenty and display the top five services with their names and phone numbers
    And I checking the list has five entries only and the search result contains the word car wash
 
    Examples:
      | row_index |
      |         1 |
Feature: Verify Gym/Fitness Submenu Options

@TC003
Scenario Outline: Discover submenus of Gym in Fitness Category
  When I scroll down to Popular Categories
  And I choose the "<row_index>" category
  Then I should find the subtypes of Gym and Fitness
  And I should capture and display them

  Examples:
    | row_index |
    |         1 |
		
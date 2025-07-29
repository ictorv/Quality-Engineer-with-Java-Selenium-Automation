Feature: Display Theaters Showing Top Movies

	@TC009
  Scenario Outline: Display the top 6 theaters showing the top movie with filters from Excel
    When I scroll down to "Latest Movies & Reviews"
    And I click on the "More Options" button
    Then I should see the Movies page
    And I filter the movies using Excel row "<rowIndex>" for Languages, Format, and Genre
    Then I should see the page with theaters showing this movie
    And I should capture the top 6 theater names and their show timings

  Examples:
    | rowIndex |
    | 1        |

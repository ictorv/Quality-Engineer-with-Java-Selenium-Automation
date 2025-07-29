Feature: TC008 - Explore City-to-City Bus Options and Apply Travel Filters


	@TC008
  Scenario Outline: Show top 5 filtered buses between cities and validate travel options per Excel row
    Given I launch the travel platform home
    When I select the "Bus" booking category
    And I enter the origin as "Coimbatore" and the destination as "Chennai"
    And I choose a journey date and trigger the bus search
    Then I enable the filters "Non-AC" and "Sleeper"
    And I extract and display the top 5 buses with operator name, departure time, and fare using Excel row "<rowIndex>"
    And I validate that both filter checkboxes are functional using Excel row "<rowIndex>"
		And I store the result Pass or Fail in Excel

  Examples:
    | rowIndex |
    | 1        |

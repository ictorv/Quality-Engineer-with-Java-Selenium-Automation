Feature: Display Mobile Recharge Plans from XML

	@TC010
  Scenario Outline: Display the top 5 mobile data plans using XML input
    When I scroll down to "Mobile Recharge Options"
    And I click on the "Type" service menu
    Then I should see the Mobile Recharge page
    And I enter a valid "MobileNumber"
    And I apply the "Plan" filter
    Then I should see the available recharge plans
    And I capture and display the top 5 results



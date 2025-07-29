Feature: Car Wash
 
 @TC002
  Scenario Outline: Register for Free Listing with invalid phone number and capturing the error message
    When I Navigate to the free listing page
    And I fill the phone number field with an invalid phone number "<phone_Number>" from Excel
    And I submit the form
    Then I should see an error message for the invalid phone number
    And I capture and display the error message
    And I am checking the error message
 
    Examples:
      | phone_Number |
      |            1 |
Feature: Social media link verification
 
  Scenario: Verify presence and functionality of social media links
    Given the user is on the home page
    Then Social media icons should be visible in the footer
    And Each social media icon should redirect to the correct platform
    And The URLs should be printed in the console
    And The URLs should be written to the "SocialMedia" Excel sheet
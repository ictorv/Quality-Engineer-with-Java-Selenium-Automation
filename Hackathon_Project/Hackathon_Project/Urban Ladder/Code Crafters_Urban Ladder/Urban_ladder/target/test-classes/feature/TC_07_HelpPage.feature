Feature: Extract Help Page FAQs on Urban Ladder
 
  Scenario: Navigate to Help section and write FAQs to Excel
    And User navigates to the Help section
    When User expands all available help questions
    Then User should extract all visible FAQs and save them into Excel
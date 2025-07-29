@web
Feature: Google Search Page

  Scenario: Get title of Google search page
    Given I open the Google homepage
    Then the page title should be "Google"

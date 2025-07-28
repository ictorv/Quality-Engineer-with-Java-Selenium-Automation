@api
Feature: Verify API GET request

  Scenario: Verify status code 200 for population API
    Given I send a GET request to the population API
    Then the response status code should be 200

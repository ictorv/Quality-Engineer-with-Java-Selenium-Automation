Feature: EMI Calculator Loan Verification

Scenario: read data
    Given Read data

  Scenario: Open landing page
    When the user opens the landing page

  Scenario: Verify landing page
    Then the landing page should display Home Loan Amount

  Scenario: Verify Home Loan EMI
    Then the user verifies Home Loan EMI and amount

  Scenario: Verify Personal Loan EMI
    Then the user verifies Personal Loan EMI and amount

  Scenario: Verify Car Loan interest
    Then the user verifies Car Loan interest and amount

  Scenario: Verify Home Loan EMI Calculator
    Then the user verifies Home Loan EMI Calculator details


  Scenario: Check EMI Calculator UI
    Then the user checks EMI Calculator UI

  Scenario: Check Loan Amount UI
    Then the user checks Loan Amount UI

  Scenario: Check Loan Tenure UI
    Then the user checks Loan Tenure UI

  Scenario: Check Interest Rate UI
    Then the user checks Interest Rate UI

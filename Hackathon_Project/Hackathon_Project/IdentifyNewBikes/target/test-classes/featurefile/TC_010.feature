Feature: ZegWheels EMI calculator check

  Scenario Outline: Checking EMI calculator for sample data
    Given the user is on the ZigWheels Homepage
    And clicks on the NEW CARS
    Then Scroll to the Quick Research and click on the EMI Calculator
    Then set Make, Model and Verient
    And click on Check EMI
    Then Adjust the Downpayment using the Slider
    And Set the Loan Tenure by select duration in years
    And Set the Intrest rate using the Slider
    Then Display the value showed below Your monthly EMI
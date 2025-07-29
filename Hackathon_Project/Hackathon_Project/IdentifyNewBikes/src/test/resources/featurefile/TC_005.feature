@smoke @regression
Feature: ZigWheels Honda Upcoming Bikes

  Scenario Outline: Searching for Honda Upcoming Bikes and display result 
    Given the user is on the ZigWheels Homepage 
   	And the user enters invalid data in the search box
   	And display the searchTerm not found message
    Then the user clicks New Bikes
    And enter Honda in Search New Bikes
    And click Search button
    And click on readmore button
    And filters the results for upcoming bikes priced under 4 lakhs
    Then FAQs about upcoming Honda models should be printed
    And display the upcoming bikes
		And display result
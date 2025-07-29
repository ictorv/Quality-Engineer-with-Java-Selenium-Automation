Feature: Find Service Centers for given loction and make

  Scenario: Select the Location and Make and find the location of Service Center
  Given the user is on  Home Page and clicks on New Cars
  And the user clicks on Service Centers from Quick Research
  Then the user enters the city
	And the user enters the Car Make
	Then the user click search button 
	And the user clicks on direction to get the location of service center
	And the URL of the loction direction is captured
Feature: Search being at home and collect all the category
 
	Scenario: All categories under the category option
		Given the user is on the home page
		When the user searches for "being at home"
		Then the user should be redirected to being at home page
		And the user clicks the category option
		Then the user see list of category
		And the user collect all the categories
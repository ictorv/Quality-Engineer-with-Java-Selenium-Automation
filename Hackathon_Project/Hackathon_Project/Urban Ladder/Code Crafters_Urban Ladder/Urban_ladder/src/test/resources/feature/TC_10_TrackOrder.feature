Feature: Go to Track Order and entering details for tracking
 
	Scenario: Input the details and retreive information
		Given the user is on the home page
		When the user clicks on the Track Order
		Then the user is redirected to new page
		And the user enters the order number and phone number and gets the response
 
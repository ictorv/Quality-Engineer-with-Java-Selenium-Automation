Feature: Checking Top Bike Brands Displayed
	Scenario: Checking whether the Top Bike Brands logo is Displayed in the Home Page
		Given the user is on the ZigWheels Homepage
		Then scroll to the Top Car brands in India
		Then check whether all 12 Brands are displayed or not
		Then validate the retrived Brands
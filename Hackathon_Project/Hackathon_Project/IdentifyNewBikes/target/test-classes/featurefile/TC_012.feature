Feature: Check the Fuctionality of ZigWheels Logo

  Scenario: The user is on home page and clicks all the header elements and then on the logo to navigate back to home page
  Given the user is on the ZigWheels Homepage
  And the user clicks on news and reviews
  Then the user clicks on logo
  And the user clicks on new cars
  Then the user clicks on logo
  And the user clicks on new bikes
  Then the user clicks on logo
  And the user clicks on scooters
  Then the user clicks on logo
  And the result pass or fail is printed
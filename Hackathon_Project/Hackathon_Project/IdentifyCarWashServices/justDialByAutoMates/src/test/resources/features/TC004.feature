Feature: Find Top 5 Hostels to Stay

@TC004
Scenario Outline: Display top 5 hostels near me which are top rated and JD Verified
  When I search for Hostels using "<row_index>"
  Then I should see a list of hostels
  And I filter the hostels which are top rated and JD Verified
  And I display the top 5 hostels with their names and locations
  And I verify the location contains coimbatore and assert the result

  Examples:
    | row_index |
    |         1 |
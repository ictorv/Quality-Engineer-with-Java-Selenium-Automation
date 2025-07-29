Feature: Add item to cart and verify item name and retrieve price

  Scenario: Search and verify item in cart
    Given the user is on the home page
    When the user searches for "chair"
    And clicks the first item and switches to new window
    And adds the item to cart
    Then the item name in cart should match the search result
    And print the item price
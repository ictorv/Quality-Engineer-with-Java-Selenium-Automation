Feature: Filter and extract Bookshelves data from Urban Ladder

  Scenario: User filters bookshelves under ₹15,000 and saves the top products to Excel
    Given the user is on the home page
    When User navigates to Bookshelves section from homepage
    And User handles any popup appearing on screen
    And User sets price filter for bookshelves under 15,000
    And User selects Open Storage type from bookshelf categories
    And User filters results to show only In Stock items
    And User fetches top 6 bookshelf products with price
    Then Product details are written to BookShelves Excel sheet
package testCases;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import TestBase.BaseClass;
import pageObjects.HomePage;
import pageObjects.SearchBooks;
import utilities.DataProviders;
import utilities.ExtentReportManager;

public class TC001_SearchResultSortingTest extends BaseClass{

	// This test verifies the sorting and result functionality of a book search on the website.
	// It uses data-driven testing via TestNG's DataProvider and logs steps via ExtentReports and Log4j.
	@Test(dataProvider = "SearchData", dataProviderClass = DataProviders.class)
	public void booksSearchPage(String searchText, String sortCriteria, String noOfBooks) {
		
		logger.info("***** Starting TC001_SearchResultSortingTest *****");
		
		try {
			// Fetches the ExtentTest instance for logging report steps
			ExtentTest test = ExtentReportManager.getTest();
			
			// Step 1: Launch the homepage and log UI state
			logger.info("Launching HomePage...");
			HomePage homePage = new HomePage(driver);
			new BaseClass().captureScreen("HomePage");
			test.log(Status.INFO, "Launching HomePage");
		
			// Step 2: Enter search text into the search bar
			logger.info("Entering Search Ierm");
			homePage.setSearchBar(searchText);
			new BaseClass().captureScreen("Entering Search Item");
			test.log(Status.INFO, "Entering Search Item");
			
			// Step 3: Click on search button
			logger.info("Clicking search button");
			homePage.setSearchBtn();
			
			// Step 4: Navigate to search results page
			logger.info("Navigating to SearchBooks page...");
			new BaseClass().captureScreen("Viewing results");
			test.log(Status.INFO, "Entering Search Item");
			
			// Step 5: Access total results count
			SearchBooks searchBooks = new SearchBooks(driver);
			int totalBooksDisplayed = searchBooks.getTotalResults();
			
			logger.info("Total books displayed: {}", totalBooksDisplayed);
			
			// Step 6: Apply sorting criteria
			logger.info("Sorting results");
			searchBooks.setSort(sortCriteria);
			new BaseClass().captureScreen("Viewing sorted results");
			test.log(Status.INFO, "View Sorted Results");
			
			// Step 7: Get names and prices of top N books
			int n = (int) Double.parseDouble(noOfBooks);
			logger.info("Fetching names and prices of top 5 books...");
			Map<String, String> booksNameAndPrice = searchBooks.getNameAndPrice(n);
			
			// Step 8: Export the name and price of each book to Excel
			searchBooks.printNameAndPrice(booksNameAndPrice);
			
			// Step 9: Log book name and price details
			for (String bookName : booksNameAndPrice.keySet()) {
			    String price = booksNameAndPrice.get(bookName);
			    logger.info("Book: {} | Price: {}", bookName, price);
			}
	
			// Step 10: Assert that result count is greater than 10
			logger.info("Verifying total books displayed is greater than 10");
			Assert.assertTrue(totalBooksDisplayed>10, "Expected more than 10 books to be displayed");
			logger.info("✅ TC001_SearchResultSortingTest completed successfully");
		} catch(Exception e) {
			// Handles unexpected exceptions and captures failure logs/screenshots
			logger.error("❌ Test TC001_SearchResultSortingTest failed due to an unexpected exception: {}", e.getMessage(), e);
			logger.debug("Debug logs...");
			Assert.fail();
		}
		logger.info("***** Finished TC001_SearchResultSortingTest *****");
	}
}

package stepsDefinitions;

import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.SearchResultPageRestaurant;
import testBase.CucumberBase;
import testRunner.TestRunner;
import utilities.DataReader;
import utilities.ExcelUtilityClass;

public class TC005_steps {

    private final Logger logger = CucumberBase.getLogger();
    private final WebDriver driver = TestRunner.getDriver();
    private final HomePage homePage = new HomePage(driver);
    private final SearchResultPageRestaurant restaurant = new SearchResultPageRestaurant(driver);

    private final String sheetName = "TC005";
    private final String path = System.getProperty("user.dir") + "\\testData\\Identify-Car-Wash-Services_TestData.xlsx";
    private final ExcelUtilityClass excel = new ExcelUtilityClass(path, sheetName);
    private HashMap<String, String> row;
    private int rowIndex;

    @When("I search for \"Restaurants\" near {string}")
    public void searchRestaurantsNear(String rowIndexStr) {
        rowIndex = Integer.parseInt(rowIndexStr);

        try {
            DataReader reader = new DataReader(path, sheetName);
            row = reader.getRowData(rowIndex);
            reader.close();
        } catch (Exception e) {
            logger.error("Failed to load test data for row " + rowIndexStr, e);
            Assert.fail("Could not load data");
        }

        String location = row.get("Location");
        String searchQuery = row.get("Search");

        homePage.clickMayBeLaterButton();
        logger.info("------ Clicked May Be Later Button ------");
        homePage.setLocationName(location);
        homePage.clickLocationFirstDropDown();
        logger.info("------ Set location: " + location + " ------");

        homePage.setSearch(searchQuery);
        homePage.clickSearchFirstDropDown();
        logger.info("------ Searching for: " + searchQuery + " ------");
    }

    @Then("I should see a list of restaurants")
    public void verifyRestaurantsVisible() {
        logger.info("------ Restaurant listing page loaded ------");
    }

    @And("I filter the restaurants which are open now and have South Indian cuisine and online delivery")
    public void applyRestaurantFilters() {
        restaurant.selectFilter(row.get("Filter"));
        restaurant.setCuisine(row.get("Cuisines"));
        restaurant.selectFilter(row.get("Filter1"));
        restaurant.selectFilter(row.get("Filter2"));
        logger.info("------ Applied filters: Cuisines, Cuisine, Delivery, Open Now ------");
    }

    @And("I display the top 5 restaurants with their names, ratings, and number of votes")
    public void displayTopRestaurants() {
        List<String> results = restaurant.getRestaurantSearchResults();
        List<Integer> voteCounts = restaurant.getNoOfRatings();

        logger.info("------ Displaying top 5 restaurants ------");
        System.out.println("----------------------------------------------------------------------------------------------------");
        
        int count = rowIndex;
        for (String result : results) {
            excel.setCellData(result, count, 6); // ✅ Log result to Excel based on rowIndex
            String[] value = result.split(" , ");
            System.out.println("\nHotel Name: " + value[0] + "\nRating(5): " + value[1] + "\nNumber Of Ratings: " + value[2]);
            count++;
        }

        System.out.println("----------------------------------------------------------------------------------------------------");

        boolean flag = voteCounts.stream().allMatch(v -> v >= 20);

        if (flag) {
            excel.setCellData("The number rating is greater than 20", rowIndex, 8);
            excel.setCellData("Pass", rowIndex, 9);
            logger.info("------ Test Case Passed ------");
            Assert.assertTrue(true);
        } else {
            excel.setCellData("The number rating is less than 20", rowIndex, 8);
            excel.setCellData("Fail", rowIndex, 9);
            logger.error("------ Test Case Failed ------");
            Assert.fail("One or more restaurants have ratings below 20");
        }

        logger.info("----------------------------------------------------------------------------------------");
    }
}

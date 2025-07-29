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
import pages.SearchResultPageHostels;
import testBase.CucumberBase;
import testRunner.TestRunner;
import utilities.DataReader;
import utilities.ExcelUtilityClass;

public class TC004_steps {

    private final Logger logger = CucumberBase.getLogger();
    private final WebDriver driver = TestRunner.getDriver();
    private final HomePage homePage = new HomePage(driver);
    private final SearchResultPageHostels hostelPage = new SearchResultPageHostels(driver);

    private final String sheetName = "TC004";
    private final String path = System.getProperty("user.dir") + "\\testData\\Identify-Car-Wash-Services_TestData.xlsx";
    private ExcelUtilityClass excel;
    private HashMap<String, String> row;
    private List<String> results;
    private int rowIndex;

    @When("I search for Hostels using {string}")
    public void searchHostels(String rowIndexStr) {
        logger.info("------ Starting TC004_Hostels ------");
        rowIndex = Integer.parseInt(rowIndexStr);

        try {
            DataReader reader = new DataReader(path, sheetName);
            row = reader.getRowData(rowIndex);
            reader.close();
            excel = new ExcelUtilityClass(path, sheetName);
        } catch (Exception e) {
            logger.error("Failed to load data from row " + rowIndexStr, e);
            Assert.fail("Could not read data from Excel for row " + rowIndexStr);
        }

        homePage.clickMayBeLaterButton();
        homePage.setLocationName(row.get("Location"));
        homePage.clickLocationFirstDropDown();
        homePage.setSearch(row.get("Search"));
        homePage.clickSearchFirstDropDown();

        logger.info("------ Searching hostels in " + row.get("Location") + " ------");
    }

    @Then("I should see a list of hostels")
    public void verifyHostelResultsVisible() {
        hostelPage.selectFilter(row.get("Filter1"));
        hostelPage.selectFilter(row.get("Filter2"));
        hostelPage.selectFilter(row.get("Filter3"));

        results = hostelPage.getHotelResultList();
        Assert.assertFalse(results.isEmpty(), "Hostel results should not be empty");
        logger.info("------ Hostel search results retrieved ------");
    }

    @And("I filter the hostels which are top rated and JD Verified")
    public void applyHostelFilters() {
        logger.info("------ Filters applied: Top Rated, JD Verified, Trusted ------");
    }

    @And("I display the top 5 hostels with their names and locations")
    public void displayTopFiveHostels() {
        int limit = Math.min(results.size(), 5);
        int count = rowIndex;

        logger.info("------ Displaying Top 5 Hostels ------");
        System.out.println("--------------------------------------------------------------------------------------------");

        for (int i = 0; i < limit; i++) {
            String value = results.get(i);
            String[] data = value.split(" , ");
            System.out.println("\nHostel Name: " + data[0] + "\nLocation: " + data[1]);
            excel.setCellData(value, count, 5); // write across columns starting at 5
            count++;
        }

        System.out.println("--------------------------------------------------------------------------------------------");
    }

    @And("I verify the location contains coimbatore and assert the result")
    public void validateFinalResults() {
        boolean isValid = true;

        for (String entry : results) {
            if (!entry.toLowerCase().contains("coimbatore")) {
                isValid = false;
                break;
            }
        }

        if (isValid) {
            excel.setCellData("The Search result Contains coimbatore in Location", rowIndex, 7);
            excel.setCellData("Pass", rowIndex, 8);
            logger.info("------ Test Case Passed ------");
            Assert.assertTrue(true);
        } else {
            excel.setCellData("The Search result does Not Contains coimbatore in Location", rowIndex, 7);
            excel.setCellData("Fail", rowIndex, 8);
            logger.error("------ Test Case Failed ------");
            Assert.fail();
        }

        excel.closeWorkbook();
        logger.info("--------------------------------------------------------------------------------------------");
    }

}

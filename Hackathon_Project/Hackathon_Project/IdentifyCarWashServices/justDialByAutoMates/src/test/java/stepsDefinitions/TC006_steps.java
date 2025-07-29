package stepsDefinitions;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import testBase.CucumberBase;
import testRunner.TestRunner;
import utilities.DataReader;
import utilities.ExcelUtilityClass;

public class TC006_steps {

    private final Logger logger = CucumberBase.getLogger();
    private final WebDriver driver = TestRunner.getDriver();
    private final HomePage homePage = new HomePage(driver);

    private final String jsonPath = ".\\testData\\Identify-Car-Wash-Services_TestDataJSON";
    private final String excelPath = ".\\testData\\Identify-Car-Wash-Services_TestData.xlsx";
    private final String sheetName = "TC006";

    private final ExcelUtilityClass excel = new ExcelUtilityClass(excelPath, sheetName);
    private final DataReader reader = new DataReader(excelPath, sheetName);
    private List<String> searchTerms;

    @When("I click on \"Popular Categories\"")
    public void clickPopularCategories() {
        logger.info("------ Starting TC006_CategorySearchResults ------");
        homePage.clickMayBeLaterButton();
        logger.info("------ Clicked May Be Later Button ------");
        homePage.clickCloseButton();
        logger.info("------ Closed pop-up ------");
        homePage.clickPopularCategoryButton();
        logger.info("------ Clicked Popular Category Button ------");
    }

    @And("I search for each term in the \"search_terms\" list from JSON")
    public void searchEachTermFromJson() {
        logger.info("------ Fetching search terms from JSON ------");
        searchTerms = reader.getJsonSearchTerms(jsonPath, "search_terms");
        
        int count = 1;
        for (int i = 0; i < searchTerms.size(); i++) {
            String search = searchTerms.get(i);
            int rowIndex = i + 1; // 💡 Excel row index (assuming header is row 0)

            logger.info("------ Searching: " + search + " ------");
            homePage.setSearchValue(search);
            List<String> results = homePage.getCategorySearchResults();

            String resultString = String.join("   ", results);
            System.out.println("\n" + search.toUpperCase() + " Search Result:\n" + resultString);
            excel.setCellData(resultString, count, 1);
            count++;

            homePage.clickSearchResultClose();

            if (results.size() >= 1) {
                excel.setCellData("Each Search Result has at least one match", rowIndex, 3);
                excel.setCellData("Pass", rowIndex, 4);
                logger.info("------ Search Passed for: " + search + " ------");
                Assert.assertTrue(true);
            } else {
                excel.setCellData("No results found for search term: " + search, rowIndex, 3);
                excel.setCellData("Fail", rowIndex, 4);
                logger.error("------ Search Failed for: " + search + " ------");
                Assert.fail("No results found for: " + search);
            }
        }
    }

    @Then("I should find the matching categories")
    public void confirmSearchResultsVisible() {
        logger.info("------ Category Search Results were validated per term ------");
    }

    @And("I should capture and display the search results")
    public void displayCapturedResults() {
        logger.info("------ Search results displayed and logged to Excel ------");
    }
}

package stepsDefinitions;

import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.MoviesPage;
import pages.SearchResultMoviePage;
import testBase.CucumberBase;
import testRunner.TestRunner;
import utilities.DataReader;
import utilities.ExcelUtilityClass;

public class TC009_steps {

    private final Logger logger = CucumberBase.getLogger();
    private final WebDriver driver = TestRunner.getDriver();

    private final String sheetName = "TC009";
    private final String path = System.getProperty("user.dir") + "\\testData\\Identify-Car-Wash-Services_TestData.xlsx";

    private final ExcelUtilityClass excel = new ExcelUtilityClass(path, sheetName);
    private final HomePage homePage = new HomePage(driver);
    private final MoviesPage movies = new MoviesPage(driver);
    private final SearchResultMoviePage movieSearch = new SearchResultMoviePage(driver);

    private HashMap<String, String> row;
    private int rowIndex;
    private int resultCounter = 0; // for writing theater results per row

    @When("I scroll down to \"Latest Movies & Reviews\"")
    public void scrollToMoviesSection() {
        homePage.clickMayBeLaterButton();
        logger.info("------ Clicked May Be Later Button ------");
    }

    @And("I click on the \"More Options\" button")
    public void clickMoreOptionsButton() {
        homePage.clickMoviesSection();
        logger.info("------ Clicked Movies Section ------");
    }

    @Then("I should see the Movies page")
    public void verifyMoviesPageVisible() {
        movies.waitUntilHeadingTagAppears();
        logger.info("------ Movies Page Heading Detected ------");
    }

    @And("I filter the movies using Excel row {string} for Languages, Format, and Genre")
    public void filterMoviesByExcelRow(String rowIndexStr) {
        rowIndex = Integer.parseInt(rowIndexStr);

        try {
            DataReader reader = new DataReader(path, sheetName);
            row = reader.getRowData(rowIndex);
            reader.close();
        } catch (Exception e) {
            logger.error("Failed to load Excel data for TC009, row " + rowIndexStr, e);
        }

        String lang = row.get("Languages");
        String format = row.get("Format");
        String genre = row.get("Genre");

        movies.clickChosenLanguages(lang);
        logger.info("------ Selected Languages: " + lang + " ------");

        movies.clickChosenFormat(format);
        logger.info("------ Selected Format: " + format + " ------");

        movies.clickChosenJonour(genre);
        logger.info("------ Selected Genre: " + genre + " ------");

        movies.clickApplyFilterButton();
        logger.info("------ Applied Movie Filters ------");

        SoftAssert softAssert = new SoftAssert();
        WebElement genreCheckbox = movies.getComedyJonourFilterCheckBox();
        boolean enabled = genreCheckbox != null && genreCheckbox.isEnabled();

        if (enabled) {
            excel.setCellData("Languages, Format, Genre checkbox are Enabled", rowIndex, 5);
            softAssert.assertTrue(true);
            logger.info("------ Verified Genre Checkbox Enabled ------");
        } else {
            excel.setCellData("Languages, Format, Genre checkbox are NOT Enabled", rowIndex, 5);
            softAssert.fail();
            logger.warn("------ Genre Checkbox NOT Enabled ------");
        }

        movies.waitUntilFilteredHeadingTagAppears();
        movies.clickFirstMovie();
        logger.info("------ First Filtered Movie Selected ------");

        softAssert.assertAll();
    }

    @Then("I should see the page with theaters showing this movie")
    public void confirmTheaterPageLoaded() {
        movieSearch.waitUntilMovieNameToAppear();
        String movieName = movieSearch.getMovieName();
        System.out.println("Movie Name: " + movieName);
       // excel.setCellData("Movie: " + movieName, rowIndex, 2);
    }

    @And("I should capture the top 6 theater names and their show timings")
    public void captureTopTheaters() {
        List<String> details = movieSearch.getMovieDetails();

        for (String info : details) {
            excel.setCellData(info, rowIndex + resultCounter, 3); // write below base row
            resultCounter++;

            String[] parts = info.split(" , ");
            System.out.println("\nTheatre Name: " + parts[0] + "\nLocation: " + parts[1] + "\nShow Timings: " + parts[2]);
        }

        if (!details.isEmpty()) {
            excel.setCellData("Pass", rowIndex, 6);
            logger.info("------ Successfully logged top theaters ------");
        } else {
            excel.setCellData("Fail", rowIndex, 6);
            logger.warn("------ No theater data available to log ------");
        }

        logger.info("----------------------------------------------------------------------------------------");
    }
}

package stepsDefinitions;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.*;
import pages.BusBookingPage;
import pages.HomePage;
import pages.SearchResultPageBusBooking;
import testRunner.TestRunner;
import utilities.ExcelUtilityClass;

public class TC008_steps {

    WebDriver driver = TestRunner.getDriver();
    HomePage homePage;
    BusBookingPage busPage;
    SearchResultPageBusBooking busResultPage;
    ExcelUtilityClass excel;
    int rowIndex;
    String path = System.getProperty("user.dir") + "\\testData\\Identify-Car-Wash-Services_TestData.xlsx";
    String sheetName = "TC008";

    @Given("I launch the travel platform home")
    public void launchTravelHomePage() {
        homePage = new HomePage(driver);
        homePage.clickMayBeLaterButton();
    }

    @When("I select the {string} booking category")
    public void selectBookingCategory(String category) {
        homePage.clickServiceMenu(category);
    }

    @And("I enter the origin as {string} and the destination as {string}")
    public void inputDepartureAndDestination(String departure, String destination) {
        busPage = new BusBookingPage(driver);
        busPage.setDepartureCity(departure);
        busPage.setDestinationCity(destination);
    }

    @And("I choose a journey date and trigger the bus search")
    public void chooseDateAndSearch() {
        busPage.clickDateOfJourney();
        busPage.clickNextMonth();
        busPage.selectDate();
        busPage.clickSearchButton();
    }

    @Then("I enable the filters {string} and {string}")
    public void enableBusFilters(String filter1, String filter2) {
        busResultPage = new SearchResultPageBusBooking(driver);
        busResultPage.clickBusType();
        if (filter1.equalsIgnoreCase("Non-AC")) busResultPage.clickNonAcFilterCheckBox();
        if (filter2.equalsIgnoreCase("Sleeper")) busResultPage.clickSleeperFilterCheckBox();
        busResultPage.clickBusType(); // Close filter menu
    }

    @And("I extract and display the top 5 buses with operator name, departure time, and fare using Excel row {string}")
    public void extractTopFiveBuses(String rowIndexStr) {
        rowIndex = Integer.parseInt(rowIndexStr);
        excel = new ExcelUtilityClass(path, sheetName);
        List<String> results = busResultPage.getBusDetails();

        int max = Math.min(5, results.size());
        for (int i = 0; i < max; i++) {
            String data = results.get(i);
            String[] parts = data.split(" , ");
            excel.setCellData(data, rowIndex + i, 2);
            System.out.println(" Operator: " + parts[0] + "\n Departure: " + parts[1] + "\n Fare: " + parts[2]);
            System.out.println("--------------------------------------------------");
        }
        excel.closeWorkbook();
    }

    @And("I validate that both filter checkboxes are functional using Excel row {string}")
    public void validateFiltersState(String rowIndexStr) {
        rowIndex = Integer.parseInt(rowIndexStr);
        WebElement nonAc = busResultPage.getNonAcFilter();
        WebElement sleeper = busResultPage.getSleeperFilterCheckBox();

        excel = new ExcelUtilityClass(path, sheetName);
        boolean valid = nonAc.isEnabled() && sleeper.isEnabled();
        excel.setCellData(valid ? "Both Non-AC and Sleeper Check box are Enabled" : "Both Non-AC and Sleeper Check box are Not Enabled", rowIndex, 4);
        Assert.assertTrue(valid, "Both Non-AC and Sleeper Check box are Enabled");
        excel.closeWorkbook();
    }

    @And("I store the result Pass or Fail in Excel")
    public void logFilterResult() {
        WebElement nonAc = busResultPage.getNonAcFilter();
        WebElement sleeper = busResultPage.getSleeperFilterCheckBox();

        excel = new ExcelUtilityClass(path, sheetName);
        boolean valid = nonAc.isEnabled() && sleeper.isEnabled();
        excel.setCellData(valid ? "Pass" : "Fail", rowIndex, 5);
        excel.closeWorkbook();
        System.out.println(" Result stored for row: " + rowIndex);
    }
}

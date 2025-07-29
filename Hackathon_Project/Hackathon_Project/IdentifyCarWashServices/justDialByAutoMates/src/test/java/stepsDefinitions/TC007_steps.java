package stepsDefinitions;

import java.util.List;

import org.testng.Assert;
import io.cucumber.java.en.*;
import testRunner.TestRunner;

import pages.HomePage;
import pages.TouristPlacesPage;
import utilities.ExcelUtilityClass;
import org.openqa.selenium.WebDriver;

public class TC007_steps {

    WebDriver driver = TestRunner.getDriver();  // Access WebDriver from TestRunner
    HomePage homePage;
    TouristPlacesPage tourist;
    ExcelUtilityClass excel;
    String location;
    int counter;
    String path = ".\\testData\\Identify-Car-Wash-Services_TestData.xlsx";

    @When("I scroll down to {string} from Excel")
    public void i_scroll_down_to_rowIndex(String rowIndex) {
        int row = Integer.parseInt(rowIndex);
        excel = new ExcelUtilityClass(path, "TC007");
        String[][] data = excel.getSheetData(2, 1);
        location = data[row - 1][0];

        homePage = new HomePage(driver);
        homePage.clickMayBeLaterButton();
        homePage.setLocationName(location);
        excel.closeWorkbook();
    }

    @And("I choose the first option")
    public void i_choose_first_option() {
        homePage.clickLocationFirstDropDown();
        homePage.clickTopTouristPlace();
        tourist = new TouristPlacesPage(driver);
        tourist.clickCloseButtonForLocation();
    }

    @Then("I should see the location page")
    public void i_should_see_location_page() {
        System.out.println("User navigated to location page...");
    }

    @And("I capture the top 5 results in each category: Hotels, Restaurants, Coffee Shops, Travel Agents, and Visiting Spots")
    public void i_capture_all_top_results() {
        excel = new ExcelUtilityClass(path, "TC007");

        boolean allPassed = true;
        allPassed &= captureAndValidate(tourist.getHotelDetails(), 1, "Hotel");
        allPassed &= captureAndValidate(tourist.getRestaurantDetails(), 2, "Restaurant");
        allPassed &= captureAndValidate(tourist.getCoffeeShop(), 3, "Coffee Shop");
        allPassed &= captureAndValidate(tourist.getTravelAgentDetails(), 4, "Travel Agent");
        allPassed &= captureAndValidate(tourist.getThingsToDo(), 5, "Things To Do");

        if (allPassed) {
            excel.setCellData("All the fields have 5 Results", 1, 7);
            excel.setCellData("Pass", 1, 8);
        } else {
            excel.setCellData("All the fields do Not have 5 Results", 1, 7);
            excel.setCellData("Fail", 1, 8);
            Assert.fail("Some categories returned empty results");
        }

        excel.closeWorkbook();
    }

    private boolean captureAndValidate(List<String> dataList, int columnIndex, String label) {

        if (dataList.isEmpty()) {
            excel.setCellData(label + " results not available", 1, columnIndex);
            return false;
        }
        System.out.println("\nTop " + label + "s in " + location + "\n");

        counter =1;
        for(String value:dataList) {
            excel.setCellData(value, counter, columnIndex);
            String[] values = value.split(" , ");
            System.out.println(label + " Name : " + values[0] + "\nRating : " + values[1]);
            counter++;
        }

        return true;
    }
}

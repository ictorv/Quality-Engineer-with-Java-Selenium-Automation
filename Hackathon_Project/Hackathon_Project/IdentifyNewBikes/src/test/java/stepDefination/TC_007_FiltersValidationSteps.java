package stepDefination;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObject.FilterUsedCarsPage;
import utilities.JSONUtils;

public class TC_007_FiltersValidationSteps {

    WebDriver driver = BaseClass.getDriver();
    FilterUsedCarsPage filterPage = new FilterUsedCarsPage(driver);
    JSONUtils data = new JSONUtils("Filters.json");
    List<String> expectedFilters = new ArrayList<>();
    List<String> displayedFilters = filterPage.printDisplayedFilters(); 

    public void readData() throws Exception {
        for (int i = 1; i <= 9; i++) {
            String value = data.getValue("Filter" + i);
            expectedFilters.add(value);
        }
        BaseClass.getLogger().info("The expectedFilters and actual filter are validated");
        System.out.println("Expected Filters from JSON: " + expectedFilters);
    }
    
    public void writeData() throws Exception {
        readData(); // Load expected filters from JSON
        filterPage.clickMore(); // Ensure filters are visible
        List<String> displayedFilters = filterPage.printDisplayedFilters(); // Get actual filters

        int i = 1;
        for (String expectedFilter : expectedFilters) {
            String status = displayedFilters.contains(expectedFilter) ? "Pass" : "Fail";
            data.setValue("Filter" + i + "Status", status); // Store status separately
            i++;
        }

        data.saveJsonFile(); // Save once after all updates
    }

    @And("user will click on More filters button")
    public void moreButton() {
        filterPage.clickMore();
    }

    @Then("display the avaliable filters and verify")
    public void display_the_available_filters_and_verify() throws Exception {
            writeData();
    }
}

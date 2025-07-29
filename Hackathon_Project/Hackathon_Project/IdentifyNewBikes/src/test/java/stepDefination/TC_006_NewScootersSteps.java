package stepDefination;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import factory.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.TVSscooters;
import utilities.ExcelUtils;
import pageObject.HomePage;
import pageObject.NewScooterPage;

public class TC_006_NewScootersSteps {

	WebDriver driver = BaseClass.getDriver();
	NewScooterPage scooterPage = new NewScooterPage(driver);
	HomePage home = new HomePage(driver);
	TVSscooters make = new TVSscooters(driver);
	int i = 0;
	String path = "TestData.xlsx"; // Ensure this matches your Excel file name
    String sheetName = "NewScooters"; // Ensure this matches your sheet name
    ExcelUtils excelUtils = new ExcelUtils(path, sheetName);
    String scooterMake;
    
    public void readData() throws IOException {
    	scooterMake = excelUtils.getCellData(2, 0).toString();
    }
    
    public void writeData() throws IOException {
    	for(String newScooters : make.getNewScooters()) {
			excelUtils.setCellData(i+2, 1, newScooters );
			i++;
		}
    }

	@When("the user clicks on the Scooters")
	public void the_user_clicks_on_the_tab() {
		home.clickScooters(); 
	}

	@And("the user selects TVS as the make")
	public void the_user_selects_as_the_make() throws IOException {
		readData();
		scooterPage.selectScooterMake(scooterMake);
	}

	@And("the user clicks the search button")
	public void the_user_clicks_the_search_button() {
		scooterPage.clickSearchButton();
	}

	@Then("the new TVS scooters are displayed")
	public void the_user_should_see_a_list_of_new_scooters() throws IOException {
		writeData();
		BaseClass.getLogger().info("New TVS Scooters are displayed in Excel Sheet");
	}
}

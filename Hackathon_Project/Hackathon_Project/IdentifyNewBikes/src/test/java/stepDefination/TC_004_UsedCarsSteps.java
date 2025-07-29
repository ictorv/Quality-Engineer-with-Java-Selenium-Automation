package stepDefination;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import factory.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.HomePage;
import pageObject.UsedCarPage;
import utilities.ExcelUtils;

public class TC_004_UsedCarsSteps {
	WebDriver driver = BaseClass.getDriver();
	UsedCarPage usedCars= new UsedCarPage(driver);
	HomePage home = new HomePage(driver);
	
	int i = 0;
	String path = "TestData.xlsx"; // Ensure this matches your Excel file name
    String sheetName = "UsedCars"; // Ensure this matches your sheet name
    ExcelUtils excelUtils = new ExcelUtils(path, sheetName);
    String location;
    
    public void readData() throws IOException {
    	location = excelUtils.getCellData(2, 0).toString();
    }
    
    public void writeData() throws IOException {
    	
    	for(WebElement usedcars : usedCars.Used_Cars()) {
			String car = usedcars.getText();
			excelUtils.setCellData(i+2, 4, car );
			i++;
		}
    	int size = usedCars.Used_Cars().size();
		String noOfCars = Integer.toString(size);
    	String resNoOfCars = usedCars.loadedCars();
    	
    	excelUtils.setCellData(2,1,noOfCars);
    	excelUtils.setCellData(2,2,resNoOfCars);
    	
    	if (noOfCars.equals(resNoOfCars)) {
			System.out.println("\nTest Pass");
			excelUtils.setCellData(2,3, "Passed");
			excelUtils.fillGreenColor(2,3);
		}else {
			System.out.println("\nTest Fail!!!");
			excelUtils.setCellData(2,3, "Failed");
            excelUtils.fillRedColor(2,3);
		}
    }
    
	@When("user will hover on More in header of website")
	public void more() {
		home.more();
	}

	@And("user will click on used cars")
	public void user_will_click_on_used_cars() {
		home.usedCars();
	}

	@Then("user will select loction as Chennai")
	public void user_click_select_loction_as_chennai() throws IOException {
		readData();
		usedCars.Location(location);
	}

	@And("user apply Popular Models filter")
	public void user_apply_popular_models_filter() {
		usedCars.PopularFilter();
	}

	@Then("all the popular cars are displayed")
	public void all_the_popular_cars_are_displayed() throws IOException {
		writeData();
	}
}

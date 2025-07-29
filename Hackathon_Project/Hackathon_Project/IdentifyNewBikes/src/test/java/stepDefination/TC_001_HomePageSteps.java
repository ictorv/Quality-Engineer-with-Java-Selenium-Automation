package stepDefination;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.BasePage;
import pageObject.HomePage;
import pageObject.UsedCarPage;

public class TC_001_HomePageSteps {

	WebDriver driver = BaseClass.getDriver();
	HomePage home = new HomePage(driver);
	UsedCarPage usedcar = new UsedCarPage(driver);
	String searchTerm = BasePage.randomeString();
	Hooks screenShot = new Hooks();
	
	@Given("the user is on the ZigWheels Homepage")
	public void the_user_is_on_ZigWheels() {
		home.refresh();
		BaseClass.getLogger().info("The user will be in ZigWheels Home Page");
	}

	@When("the user enters random data in the search box")
	public void userEntersSearchTerm1() {
		home.invalidData(searchTerm);
		BaseClass.getLogger().info("This will show element not found");
	}

	@Then("the element not found error message will be printed")
	public void messageShouldBeDisplayed1() {
		home.errorMessage();
	}

	@And("checking home page validation")
	public void homePageValidation() throws MalformedURLException {
		home.homeValidation();
	}

	@Then("checking usedCars page validation")
	public void usedCarsValidation() throws MalformedURLException {
		usedcar.usedCarsValidation();
	}
}

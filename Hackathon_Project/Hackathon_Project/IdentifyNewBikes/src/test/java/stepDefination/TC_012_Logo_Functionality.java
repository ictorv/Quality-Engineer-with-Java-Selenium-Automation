package stepDefination;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObject.HomePage;

public class TC_012_Logo_Functionality {
	WebDriver driver = BaseClass.getDriver();
	HomePage home= new HomePage(driver);
	
	
	@And("the user clicks on news and reviews")
	public void news_and_reviews() {
		home.clickNewsAndReviews();
	}
	
	@And("the user clicks on new cars")
	public void new_cars() {
		home.clickNewCars();
	}
	
	@And("the user clicks on new bikes")
	public void new_bikes() {
		home.clickNewBikes();
	}
	
	@And("the user clicks on scooters")
	public void scooters() {
		home.clickScooters();
	}
	
	@Then("the user clicks on logo")
	public void click_logo() {
		home.logo();
	}
	
	@And("the result pass or fail is printed")
	public void result() {
		
	}
}

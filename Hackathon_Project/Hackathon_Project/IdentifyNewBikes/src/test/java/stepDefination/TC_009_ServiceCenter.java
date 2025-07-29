package stepDefination;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObject.ForceMotorsPage;
import pageObject.HomePage;
import pageObject.NewCarsPage;
import pageObject.ServiceCentersPage;
import utilities.JSONUtils;

public class TC_009_ServiceCenter {
	
	WebDriver driver= BaseClass.getDriver();
	HomePage home = new HomePage(driver);
	NewCarsPage ncp= new NewCarsPage(driver);
	ServiceCentersPage scp= new ServiceCentersPage(driver);
	ForceMotorsPage fmp= new ForceMotorsPage(driver);
	JSONUtils data = new JSONUtils("ServiceCenter.json");
	
	String[] Data= new String[3];
	String url;
	public void readData() {
		Data[0]=data.getValue("QuickResearch");
		Data[1]=data.getValue("City");
		Data[2]=data.getValue("CarMake");
	}
	
	public void writeData() {
		data.setValue("URL:", url);
		data.saveJsonFile();
	}
	
	@Given("the user is on  Home Page and clicks on New Cars")
	public void user_on_homePage() {
		home.clickNewCars();
	}
	
	@And("the user clicks on Service Centers from Quick Research")
	public void user_clicks_on_Service_Center() {
		readData();
		ncp.serviceCenters(Data[0]);
	}
	
	@Then("the user enters the city")
	public void enter_city() {
		scp.city(Data[1]);
	}
	
	@And("the user enters the Car Make")
	public void enter_make() {
		scp.carMake(Data[2]);
	}
	
	@Then("the user click search button")
	public void click_search() {
		scp.search();
	}
	
	@And("the user clicks on direction to get the location of service center")
	public void click_direction() {
		fmp.location();
	}
	
	@And("the URL of the loction direction is captured")
	public void direction_url() {
		url=fmp.url();
		writeData();
		BaseClass.getLogger().info("The Map URL is captured Successfully");
	}
	
	
}

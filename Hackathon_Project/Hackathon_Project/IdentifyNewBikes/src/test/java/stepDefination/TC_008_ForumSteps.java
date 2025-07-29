package stepDefination;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.FBsLoginPage;
import pageObject.ForumPage;
import pageObject.HomePage;
import pageObject.ToyotaCommunityPage;
import utilities.JSONUtils;

public class TC_008_ForumSteps {
	WebDriver driver= BaseClass.getDriver();
	HomePage home = new HomePage(driver);
	ForumPage forum = new ForumPage(driver);
	ToyotaCommunityPage tcp = new ToyotaCommunityPage(driver);
	FBsLoginPage fb = new FBsLoginPage(driver);
	JSONUtils data = new JSONUtils("ForumData.json");
	
	String email;
    String vehicleType;
    String company;
    String model;
    String expectedMessage;
    String errorText;
    
    public void readData() throws Exception {
		// Load data from JSON
        email = data.getValue("email");
        vehicleType = data.getValue("vehicleType");
        company = data.getValue("company");
        model = data.getValue("model");
        expectedMessage = data.getValue("expectedErrorMessage");
	}
    
    public void writeData() {
    	data.setValue("Incorrect Error Message:", errorText);
    	data.saveJsonFile();
    }
    

	@When("the user will click on the Forum")
	public void user_will_click_on_used_cars() {
		home.forum();
	}

	@Then("the user will be navigated to the Forum Community page")
	public void froum_Community_page_validation() {
		forum.validationZigWheels();
		BaseClass.getLogger().info("The user is navigated to the Froum Community page");
	}

	@And("the user selects \"Car\" from the vehicle type dropdown")
	public void vehicle_Type_Dropdown() throws Exception {
		readData();
		Assert.assertNotNull("vehicleType is null. Check JSON data.", vehicleType);
		forum.vehicleType(vehicleType);
	}

	@And("the user selects \"Toyota\" as the make")
	public void selectCompany() {
		forum.company(company);
	}

	@And("the user selects \"Fortuner\" as the model")
	public void selectModel() {
		forum.model(model);
	}

	@And("the user clicks the \"Search\" button")
	public void hitSearch() {
		forum.Search();
	}

	@And("the user clicks the \"Join Community\" button")
	public void joinCommunity() {
		tcp.JoinCommunity();
	}

	@Then("the company community page should be displayed")
	public void froum_Company_page_validation() {
		tcp.validationCompany();
		BaseClass.getLogger().info("The user is navigated to the Forum Community page");
	}
	
	@When("the user clicks on the FB login option")
	public void click_fb_login() {
		forum.faceBook();
	}
	
	@And("the user enters Invalid credentials")
	public void fb() throws InterruptedException {
		fb.invalidEmail(email);
	}
	
	@And("the user clicks on login")
	public void click_login() {
		fb.clickLogin();
	}
	
	@Then("an error message is displayed on the FB login page")
	public void errormesage() {
	    errorText = fb.errorMessage();
	    writeData();
	    BaseClass.getLogger().info("The error message is displayed: "+ errorText);
	}
}

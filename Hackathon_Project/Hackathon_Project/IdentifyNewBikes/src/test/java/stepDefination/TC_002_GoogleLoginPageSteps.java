package stepDefination;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.GoogleLoginPage;
import pageObject.HomePage;
import utilities.XMLUtils;

public class TC_002_GoogleLoginPageSteps {

	WebDriver driver = BaseClass.getDriver();
	HomePage home = new HomePage(driver);
	GoogleLoginPage google = new GoogleLoginPage(driver);
	public static String[] data=new String[2];
	
	public static void getLoginData() throws Exception {
		XMLUtils reader = new XMLUtils("GoogleLoginTests.xml");
		data[0] = reader.getValue("Email");
		data[1] = reader.getValue("invalidEmail");
	}
	
	
	@When("the user clicks on the Login\\/Signup")
	public void the_user_clicks_on_the_login_signup_button_before_google_login() {
		home.login();
	}

	@And("the user clicks on the Google login option")
	public void the_user_clicks_on_the_google_login_option() {
		google.googleLogin();
	}

	@Then("the user is redirected to the Google login page")
	public void the_user_is_redirected_to_the_google_login_page() {
		BaseClass.getLogger().info("The User is in the Google Login Page");
	}

	@When("the user enters empty credentials")
	public void the_user_enters_empty_credentials() throws Exception {
		getLoginData();
		System.out.println(data[0]);
		google.emptyEmail(data[0]);
	}

	@Then("an error message is displayed on the Google login page")
	public void an_error_message_is_displayed_on_the_google_login_page() {
		google.errorMessage();
	}

	@When("the user enters an invalid email for Google login")
	public void the_user_enters_an_invalid_email_for_google_login() {
		google.invalidEmail(data[1]);
	}

	@Then("an invalid email message is displayed on the Google login page")
	public void an_invalid_email_message_is_displayed_on_the_google_login_page() {
		google.emailInvalidMessage();
	}


	@And("the user closes the Google login page")
	public void the_user_closes_the_google_login_page() {
		google.closeAndReturnToMain();
	}

}

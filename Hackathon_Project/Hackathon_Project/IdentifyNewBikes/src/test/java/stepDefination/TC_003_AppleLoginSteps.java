package stepDefination;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.Then;
import pageObject.AppleLoginPage;
import utilities.XMLUtils;

public class TC_003_AppleLoginSteps {
	WebDriver driver = BaseClass.getDriver();
	AppleLoginPage apple = new AppleLoginPage(driver);
	
	public static String[] data=new String[2];
	public static void getLoginData() throws Exception {
		XMLUtils reader = new XMLUtils("AppleLoginTests.xml");
		data[0] = reader.getValue("PhoneNumber");
		data[1] = reader.getValue("Password");
	}
	
	@Then("the user clicks on the Apple login option")
	public void the_user_clicks_on_the_apple_login_option() {
		apple.Apple();
	}

	@Then("user enters Phone Number")
	public void user_provide_phone_number_in_apple_login_page() throws Exception {
		getLoginData();
		apple.PhoneNo(Long.parseLong(data[0]));
	}

	@Then("user enter the incorrect password")
	public void user_enter_the_incorrect_password_in_apple_login_page() {
		apple.Password(data[1]);
	}

	@Then("display a invalid cradentials errorMessage")
	public void display_a_invalid_error_message_in_apple_login_page() {
		apple.ErrorMessage();
	}

	@Then("the user closes the Apple login page")
	public void the_user_closes_the_apple_login_page() {
		apple.closeAndReturnToMain();
	}
}
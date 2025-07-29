package com.urban.stepDefinitions;

import com.urban.base.BaseClass;
import com.urban.pageObject.HomePage;

import io.cucumber.java.en.Given;

public class BaseStep {
	public static HomePage homePageObject;
	
	@Given("the user is on the home page")
	public void the_user_is_on_the_home_page() {
	    // Write code here that turns the phrase above into concrete actions
		homePageObject = new HomePage(BaseClass.getDriver());
	}
}

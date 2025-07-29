package com.test.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class rediffHomePage {
	WebDriver driver;
	
	//Constructor
	public rediffHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Locators
	@FindBy(xpath="//a[@title='Create Rediffmail Account']") WebElement btn_create_account;
	
	//Actions
	public void click_create_account() {
		//Clicks create account link in home page
		btn_create_account.click();
	}
}

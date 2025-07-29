package com.facebook.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Facebook_Login_Page {

	public WebDriver driver;

	@FindBy(partialLinkText = "Create new account")
	public WebElement signUpBtn;

	public Facebook_Login_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickSignUpBtn() {
		
		signUpBtn.click();

	}

}

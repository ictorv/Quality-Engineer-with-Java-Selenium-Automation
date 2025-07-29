package com.ebay.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ebay.Utility.DriverManager;

public class homePage {
	WebDriver driver;
	//Constructors
	public homePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);;
	}
	
	//Locators
	@FindBy(xpath = "//form//a")
	WebElement Advance_loc;
	
	//Actions
	public void clickAdvanceButton() {
		Advance_loc.click();
	}
}

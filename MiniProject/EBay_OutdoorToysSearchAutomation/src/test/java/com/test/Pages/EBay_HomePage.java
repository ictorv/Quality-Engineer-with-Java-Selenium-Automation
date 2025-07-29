package com.test.Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EBay_HomePage {
	WebDriver driver;
	//Constructors
	public EBay_HomePage(WebDriver driver){
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

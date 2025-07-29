package testObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DemoQaHomePage {

	// WebDriver instance to be used for this page
	WebDriver driver;

	// constructor to initiate driver
	public DemoQaHomePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// locators

	@FindBy(xpath = "(//div[contains(@class,'top-card')])[3]")
	WebElement alertbtn;

	@FindBy(xpath = "(//div[contains(@class,'top-card')])[2]")
	WebElement formBtn;

	// actions

	// Method to click on the "Alerts Windows" section
	public void navigateToAlertsSection() {
		alertbtn.click();
	}

	// Method to click on the "Forms Window" section
	public void navigateToFormsSection() {
		formBtn.click();
	}

}

package testObjectRepository;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DemoQaAlertWindow {

	WebDriver driver;

	// constructor initiate driver
	public DemoQaAlertWindow(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// locators
	@FindBy(xpath = "(//*[contains(@class,'btn-light')])[12]")
	WebElement alertLeftPanelButton;

	@FindBy(xpath = "//button[@id='promtButton']")
	WebElement promptAlertButton;

	// actions
	// Click on alert section in the left panel
	public void openAlertSection() {
		alertLeftPanelButton.click();
	}

	// Triggers and handles a JavaScript Prompt Alert by sending text and accepting
	// it
	public void handlePromptAlert() throws IOException {

		// click the alert prompt button
		promptAlertButton.click();
		// switch to alert
		Alert alert = driver.switchTo().alert();
		// send keys to alert
		alert.sendKeys("hello world");
		// accept the alert
		alert.accept();
	}

}

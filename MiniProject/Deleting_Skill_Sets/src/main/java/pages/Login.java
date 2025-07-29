/**
 * Page Class: Login.java
 * 
 * Purpose:
 * - Handles user authentication in the OrangeHRM application.
 * - Implements Page Object Model (POM) for better maintainability.
 * - Uses dynamic waits to ensure elements load before interacting.
 * - Captures screenshots to track login attempts.
 *
 * Methods:
 * - loginToWebsite(): Performs login action with predefined admin credentials.
 * - Validates successful login or captures errors if authentication fails.
 */


package pages;

import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ScreenShot;
import utils.WaitUtil;

public class Login {

	WebDriver driver;
	public Login(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
	}
	
	@FindBy(xpath = "//input[@name='username' and @placeholder='Username']")
	WebElement userName;
	
	@FindBy(xpath = "//input[@name='password' and @placeholder='Password']")
	WebElement password;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement submitBtn;
	
	@FindBy(xpath = "//div[contains(@class,'oxd-alert-content--error')]")
    WebElement errorAlert;
	
	public boolean loginToWebsite() throws IOException {
		WaitUtil.waitForOneElement(driver, userName);
		userName.sendKeys("Admin");
		password.sendKeys("admin123");
		ScreenShot.screenShotTC(driver, "Login Page");
		submitBtn.click();
		try {
			boolean isPresent = errorAlert.isDisplayed();
			return isPresent;
		}
		catch(NoSuchElementException e){
			return false;
		}
		
	}
}

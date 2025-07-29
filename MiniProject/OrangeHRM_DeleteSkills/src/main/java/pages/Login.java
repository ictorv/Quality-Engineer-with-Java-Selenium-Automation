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
	
	@FindBy(xpath = "//input[@name='password' and @placeholder='Passsword']")
	WebElement password;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement submitBtn;
	
	@FindBy(xpath = "//p[contains(@class,'oxd-alert-content-text')]")
    WebElement errorAlert;
	
	public boolean loginToWebsite() throws IOException {
		WaitUtil.waitForOneElement(driver, userName);
		userName.sendKeys("Admi");
		password.sendKeys("admin123");
		
		submitBtn.click();
		//WaitUtil.waitForOneElement(driver, errorAlert);
		ScreenShot.screenShotTC(driver, "LoginPage");
		try {
			boolean isPresent = errorAlert.isDisplayed();
			return isPresent;
		}
		catch(NoSuchElementException e){
			return false;
		}
		//return errorAlert.isDisplayed();	
	}
}

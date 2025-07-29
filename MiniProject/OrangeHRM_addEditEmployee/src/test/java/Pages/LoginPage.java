package Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath = "//div[contains(@class,'oxd-sheet')]/p[1]")
	WebElement username;
	
	@FindBy(xpath = "//div[contains(@class,'oxd-sheet')]/p[2]")
	WebElement password;
	
	@FindBy(name = "username")
	WebElement usernameInput;
	
	@FindBy(name = "password")
	WebElement passwordInput;
	
	@FindBy(xpath = "//button[contains(@class,'oxd-button')]")
	WebElement loginButton;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);		
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));

	}
	
	public void login() {
		wait.until(ExpectedConditions.visibilityOf(usernameInput));
		wait.until(ExpectedConditions.visibilityOf(passwordInput));
		String userNameString = username.getText().split(": ")[1];
		String passwordString = password.getText().split(": ")[1];
		
		usernameInput.sendKeys(userNameString);
		passwordInput.sendKeys(passwordString);
		
		loginButton.click();
	}
	
}

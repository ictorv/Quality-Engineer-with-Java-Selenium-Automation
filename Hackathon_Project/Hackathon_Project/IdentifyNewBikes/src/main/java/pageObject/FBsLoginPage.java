package pageObject;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

public class FBsLoginPage extends BasePage {
	private String main;
	private WebDriverWait wait;

	public FBsLoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.wait.pollingEvery(Duration.ofMillis(5000));
		PageFactory.initElements(driver, this);
	}

	// Locators
	@FindBy(xpath = "//div[@onclick='fb_login();']")
	WebElement faceBook;

	@FindBy(xpath = "//input[@name='email']")
	WebElement email;

	@FindBy(xpath = "//input[@name='login']")
	WebElement login;

	@FindBy(xpath = "//*[@id='error_box']/div[1]")
	WebElement errorMessage;

	@FindBy(xpath = "//input[@id='pass']")
	WebElement password;

	@FindBy(xpath = "//a//img[@alt='Home']")
	WebElement home;

	@FindBy(xpath = "//*[@id='content']/span")
	WebElement zigWheels;

	@FindBy(xpath = "//*[@id='content']/div/div/div[1]/div/div[2]/h2")
	WebElement wentWrong;

	// Wait for full page load
	private void waitForPageLoad() {
		wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
				.equals("complete"));
	}

	// 2) Enter invalid emailId
	public void invalidEmail(String emailId) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(email));
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
				email, emailId);
		
	}
	
	public void clickLogin() {
		login.click();
	}

	// 5) Print Error Message
	public String errorMessage() {
		wait.until(ExpectedConditions.visibilityOf(errorMessage));
		String errorText = errorMessage.getText();
		System.out.println("Error Message: " + errorText);
		return errorText;
	}

	// 7) Return to Main Window
	public void closeAndReturnToMain() {
		driver.close();
		driver.switchTo().window(main);
		waitForPageLoad();
	}

}

package pageObject;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleLoginPage extends BasePage {
	String main;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	int done=0;
	public GoogleLoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Locators
	// Locating Google Login
	@FindBy(xpath = "//div//span[text()='Google']")
	WebElement google;

	@FindBy(xpath = "//h1[@id='headingText']")
	WebElement signIn;

	// Locating Email
	@FindBy(xpath = "//div/input[@type='email']")
	WebElement email;

	// Locating Error Message
	@FindBy(xpath = "//div[@jsname='B34EJ']")
	WebElement errorMessage;

	// Reaching Home Page
	@FindBy(xpath = "//a//img[@alt='Home']")
	WebElement home;

	// Locating password
	@FindBy(xpath = "//input[@type='password']")
	WebElement password;
	// Actions
	
	// 1) navigate to Login window
	public String navigateToLoginWindow() {
		return signIn.getText();
	}
	
	public void googleLogin() {
		// Set up an explicit wait
		main = driver.getWindowHandle();
		// Wait until the Google login button is clickable, then click it
		wait.until(ExpectedConditions.elementToBeClickable(google)).click();
		google.click();
		wait.until(driver -> driver.getWindowHandles().size() > 1);
		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			if (!window.equals(main)) {
				driver.switchTo().window(window);
				done = 1;
				break;
			}
		}
	}
	
	// 3)Entering empty details
	public void emptyEmail(String emptyData) {
		System.out.println(emptyData);
		email.sendKeys(emptyData + Keys.ENTER);
	}

	public String errorMessage() {
		String WrongData1 = errorMessage.getText();
		return WrongData1;
	}

	// 4) Entering Invalid Data
	public void invalidEmail(String invalidEmail) {
		System.out.println(invalidEmail);
		if (!invalidEmail.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+/.[a-zA-Z]{2,}$")) {
			email.sendKeys(invalidEmail);
		} else {
			email.sendKeys(invalidEmail);
		}
	}
	
	// 5) Returning Error Message
	public String emailInvalidMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOf(errorMessage)); // WebElement already located																				// earlier
		String wrongPassword = errorMsg.getText();
		return wrongPassword;
	}
	
	// 6) Switch back to the original window
	public void closeAndReturnToMain() {
		driver.close();
		driver.switchTo().window(main); // switch back to the main tab
		// driver.close(); // close login tab
	}
}

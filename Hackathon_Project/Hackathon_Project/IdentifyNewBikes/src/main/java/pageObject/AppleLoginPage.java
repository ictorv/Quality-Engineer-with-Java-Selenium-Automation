package pageObject;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AppleLoginPage extends BasePage {

	String main;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	public AppleLoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Locator
	// Locating Apple Login
	@FindBy(xpath = "//div[@id='appleSignIn']")
	WebElement apple;

	// Locating Phone Number
	@FindBy(xpath = "//input[@type='text']")
	WebElement phoneNo;

	// Locating Sign-In
	@FindBy(id = "sign-in")
	WebElement signIn;

	// Locating Password Block
	@FindBy(xpath = "//input[@type='password']")
	WebElement password;

	// Reaching Home Page
	@FindBy(xpath = "//a//img[@alt='Home']")
	WebElement home;

	// Locating Error Message
	@FindBy(xpath = "//*[@id=\"errMsg\"]")
	WebElement errorMessage;

	@FindBy(xpath = "//div[@id='appleSignIn']/span[2]")
	WebElement appleLog;

	// Actions
	// 2)Clicking on Apple Login
	public void Apple() {
		
		waitForPageLoad();
		main = driver.getWindowHandle();
		wait.until(ExpectedConditions.elementToBeClickable(apple)).click();
		apple.click();

		if (driver.getWindowHandles().size() > 1) {
			Set<String> windows = driver.getWindowHandles();
			for (String window : windows) {
				if (!window.equals(main)) {
					driver.switchTo().window(window);
					break;
				}
			}
		}

	}

	// 3)Entering PhoneNo details
	public void PhoneNo(long phoneNum) {
		wait.until(ExpectedConditions.visibilityOf(phoneNo));
		System.out.println(phoneNo.isDisplayed());
		phoneNo.clear();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(
				"arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
				phoneNo, phoneNum);
		js.executeScript("arguments[0].removeAttribute('disabled'); arguments[0].click();", signIn);
	}

	// 4) Entering Password
	public void Password(String pass) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(password));
		password.clear();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(
				"arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
				password, pass);
		js.executeScript(
				"arguments[0].removeAttribute('disabled'); arguments[0].removeAttribute('aria-disabled'); arguments[0].click();",
				signIn);
	}

	// 5) Error Message
	public String ErrorMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(errorMessage));
		String WrongData2 = errorMessage.getText();
		return WrongData2;
	}

	// 5) //Moving to Parent Page
	public void closeAndReturnToMain() {
		driver.close();
		driver.switchTo().window(main); // switch back to the main tab
		// driver.close(); // close login tab
	}
	
	private void waitForPageLoad() {
        wait.until(webDriver ->
            ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
}

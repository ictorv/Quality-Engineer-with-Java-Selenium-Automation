package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class AddEmployeePage {

	WebDriver driver;
	WebDriverWait wait;

	public AddEmployeePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(name = "firstName")
	private WebElement firstNameInput;
	
	@FindBy(name = "middleName")
	private WebElement middleNameInput;
	
	@FindBy(name = "lastName")
	private WebElement lastNameInput;
	
	@FindBy(xpath = "//div[label[contains(text(),'Employee Id')]]/following-sibling::div/input")
	private WebElement EmployeeId;
	
	@FindBy(xpath = "//div[@class='oxd-switch-wrapper']/label")
	private WebElement loginToggle;
	
	@FindBy(xpath = "//div[label[text()='Username']]/following-sibling::div/input[contains(@class,'oxd-input')]")
	private WebElement usernameInput;
	
	@FindBy(xpath = "//div[label[text()='Password']]/following-sibling::div/input[@type='password']")
	private WebElement passwordInput;
	
	@FindBy(xpath = "//div[label[text()='Confirm Password']]/following-sibling::div/input[@type='password']")
	private WebElement confirmPasswordInput;
	
	@FindBy(xpath = "//button[normalize-space(.)='Save' and @type='submit']")
	private WebElement saveButton;
	
	public void waitForErrorScreenShot() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(@class, 'oxd-input-field-required')]")));
	}

	public void addEmployeeWithLogin(String firstName, String middleName, String lastName, String username, String password) {
		firstNameInput.sendKeys(firstName);
		middleNameInput.sendKeys(middleName);
		lastNameInput.sendKeys(lastName);
		
		loginToggle.click();
		wait.until(ExpectedConditions.visibilityOf(usernameInput));
		
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		confirmPasswordInput.sendKeys(password);
		
		saveButton.click();
	}
	
	public void addEmployeeWithoutLogin(String firstName, String middleName, String lastName) {
		firstNameInput.sendKeys(firstName);
		middleNameInput.sendKeys(middleName);
		lastNameInput.sendKeys(lastName);
		
		saveButton.click();
	}
	
	public String getEmployeeId() {
		return EmployeeId.getAttribute("value");
	}
}

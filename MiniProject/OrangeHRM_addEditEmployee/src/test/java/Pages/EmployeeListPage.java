package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class EmployeeListPage {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;

	public EmployeeListPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[label[text()='Employee Name']]//following-sibling::div //input")
	private WebElement nameSearchInput;

	@FindBy(xpath = "//div[label[text()='Employee Id']]//following-sibling::div //input")
	private WebElement idSearchInput;

	@FindBy(xpath = "//div[@class='oxd-table-filter-area']/form/div[2]/button[@type='submit']")
	private WebElement searchButton;

	@FindBy(xpath = "//div[@class='oxd-table-body']")
	private WebElement oldEmployeeList;

	@FindBy(xpath = "//div[@class='orangehrm-container']")
	private WebElement resultTabScrollInto;

	public void enterEmployeeDetails(String name, String empId) {
		wait.until(ExpectedConditions.visibilityOf(nameSearchInput)).sendKeys(name);
		wait.until(ExpectedConditions.visibilityOf(idSearchInput)).sendKeys(empId);
	}

	public void clickOnSearchedEmployee(String empId) {
		js.executeScript("arguments[0].scrollIntoView(false);", resultTabScrollInto);
		wait.until(ExpectedConditions.elementToBeClickable(searchButton));
		searchButton.click();
		WebElement resultClickable = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[contains(@class, 'oxd-table-card')]/div[descendant::div[normalize-space(text())='"+empId+"']]")));
		js.executeScript("arguments[0].click();", resultClickable);
	}
}

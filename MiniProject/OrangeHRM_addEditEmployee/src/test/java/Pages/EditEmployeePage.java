package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditEmployeePage {
	private WebDriver driver;
	private WebDriverWait wait;
	JavascriptExecutor js;

	public EditEmployeePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		js = (JavascriptExecutor) driver;
	}

	@FindBy(xpath = "//p[text()=' * Required']/following-sibling::button")
	private WebElement saveButton;

	@FindBy(xpath = "//label[text()='Male']")
	private WebElement maleRadioButton;

	@FindBy(xpath = "//label[text()='Female']")
	private WebElement femaleRadioButton;

	private void selectDropDownOption(String label, String optionText) {
		// open dropdown
		WebElement dropdown = driver.findElement(By.xpath("//label[text()='" + label
				+ "']/parent::div/following-sibling::div//i"));
		
		wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();
		//select from dropdown
		By optionLocator = By.xpath("//span[text()='"+optionText+"']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(optionLocator)).click();
	}
	
	public void waitForErrorScreenShot() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Full Name')]")));
	}
	
	public void assignDetails(String nationality, String maritalStatus, String gender) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Full')]")));
		js.executeScript("arguments[0].scrollIntoView(false);",saveButton);
		selectDropDownOption("Nationality", nationality);
		selectDropDownOption("Marital Status", maritalStatus);
		
		if (gender.equalsIgnoreCase("male")) {
			maleRadioButton.click();
		}
		else if(gender.equalsIgnoreCase("female")) {
			femaleRadioButton.click();
		}
		
		saveButton.click();
	}
	
	

}

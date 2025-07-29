package pageObject;
 
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class NewBikePage extends BasePage {
	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	
	// Constructor
	public NewBikePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
 
	
	// 3) Locating scooter brand dropdown
	@FindBy(id = "byBrandMake")
	WebElement bikeMakeDropdown;

	// 4) Locating the search button
	@FindBy(xpath = "//*[@id='byBrandSearchFrom']/div[3]/button")
	WebElement searchButton;

	// Action
	// 1) Select Honda from drop down
	public void selectBikeMake(String bMake) {
		WebElement bikeMake = wait.until(ExpectedConditions.elementToBeClickable(bikeMakeDropdown));
		Select sel = new Select(bikeMake);
		sel.selectByVisibleText(bMake); // Select the first item as a placeholder
		System.out.println("Selected scooter brand from dropdown.");
	}

	// 2) Click the search button using JavaScript
	public void clickSearchButton() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", searchButton);
		System.out.println("Clicked search button.");
	}

}
package pageObject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FilterUsedCarsPage extends BasePage {

	JavascriptExecutor js = (JavascriptExecutor) driver;
	List<String> filterUsedCars = new ArrayList<>();

	// 1) Constructor to initialize elements
	public FilterUsedCarsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// 2) Locating the "+ More Filter" button
	@FindBy(xpath = "/html/body/div[8]/div/div[1]/div[1]/div[1]/div[2]/span")
	WebElement more;

	// 3) Locating all filter options
	@FindBy(xpath = "/html/body//div[1]//ul/li/div[1]/span[2]")
	List<WebElement> filterList;

	// Action
	// 1) Clicking on the "More Filter" button
	public void clickMore() {
	        // Scroll into view
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", more);
	        // Wait until it's visible and clickable
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(more));
	        // Click using JavaScript to bypass interception
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", more);
	}


	// 2) Printing all displayed filter options
	public List<String> printDisplayedFilters() {
		System.out.println("Displayed Filter Options:");
		for (WebElement filter : filterList) {
			filterUsedCars.add(filter.getText());
		}
		return filterUsedCars;
	}
}

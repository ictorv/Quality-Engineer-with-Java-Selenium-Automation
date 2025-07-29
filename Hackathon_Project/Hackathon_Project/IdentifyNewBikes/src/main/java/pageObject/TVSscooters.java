package pageObject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TVSscooters extends BasePage {
	WebDriver driver;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	// Constructor
	public TVSscooters(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	// Locate the List of New Scooters
	@FindBy(xpath = "//*[@id='modelList']/li/div/div[3]/a[1]/h3")
	List<WebElement> newScooters;
	
	List<String> scooterList = new ArrayList<>();;
	// Actions
	public List<String> getNewScooters() {
		if (newScooters.isEmpty()) {
			System.out.println("No new scooters found or loaded yet.");
		} 
		else {
			for (WebElement text : newScooters) {
				scooterList.add(text.getText());
			}
		}
		return scooterList;
	}
}

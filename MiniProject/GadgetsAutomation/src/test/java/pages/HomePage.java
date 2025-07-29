package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	WebDriver driver;
	

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="inputValEnter")
	WebElement searchBox;
	@FindBy(className="searchTextSpan")
	WebElement searchButton;
	
	//clear push notification
	public void popup() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("pushDenied"))).click();
	}
	//Enter “Bluetooth headphone” in searchBox
	public void searchTextBox(String productname){
		searchBox.click();
        searchBox.sendKeys(productname);
	}
	//click search button
	public void searchButton() {
		searchButton.click();
	}
}

package pageObject;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ToyotaCommunityPage extends BasePage{
	
	WebDriverWait wait;
	public ToyotaCommunityPage(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.wait.pollingEvery(Duration.ofMillis(500));
		this.driver = driver;
		// TODO Auto-generated constructor stub
		
		PageFactory.initElements(driver, this);
	}
	
	// Locators
	// Locating Join Community
		@FindBy(xpath = "/html/body/main/div[1]/div/div[1]/div/div[6]/span")
		WebElement joinCommunity;
	
	// Community Page Validation
		@FindBy(xpath = "//h1")
		WebElement valid;
	
	// Actions
	// 1) Click Join Community
		public void JoinCommunity() {
			joinCommunity.click();
		}
		
	// 2) Validation Company
		public void validationCompany() {
			String validName = valid.getText();
			Assert.assertEquals("Toyota Fortuner Community", validName);
		}
}

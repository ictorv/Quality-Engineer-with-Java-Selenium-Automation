package Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HeaderPage {
	private WebDriverWait wait;
	
	public HeaderPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	@FindBy(xpath = "//span[@class='oxd-userdropdown-tab']")
	private WebElement userDropDown;
	
	@FindBy(xpath = "//a[text()='Logout']")
	private WebElement logoutButton;
	
	public void logout() {
		userDropDown.click();
		wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
	}
	
}

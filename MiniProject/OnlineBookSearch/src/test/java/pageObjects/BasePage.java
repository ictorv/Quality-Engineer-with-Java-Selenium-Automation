package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


/*BasePage serves as the parent class for all page objects.
 * It provides shared functionality, mainly initializing web elements using PageFactory.*/

public class BasePage {
	WebDriver driver;
	// Constructor accepts the WebDriver instance and initializes all @FindBy elements in the child page classes that extend BasePage.
	public BasePage(WebDriver driver) {
		this.driver = driver;
		// Initializes web elements annotated with @FindBy in the current class
		PageFactory.initElements(driver, this);
	}
}

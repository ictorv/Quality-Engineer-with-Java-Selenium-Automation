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

public class NewScooterPage extends BasePage {

    WebDriver driver;
    // 1) Create a WebDriverWait for synchronization
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    // 2) Constructor to initialize page elements
    public NewScooterPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // 3) Locating scooter brand dropdown
    @FindBy(id = "byBrandMake")
    WebElement scooterMakeDropdown;

    // 4) Locating the search button
    @FindBy(xpath = "//*[@id='byBrandSearchFrom']/div[3]/button")
    WebElement searchButton;
    
    
    // Action
    // 1) Select scooter make from dropdown (e.g., TVS)
    public void selectScooterMake(String sMake) {
        WebElement scooterMake = wait.until(ExpectedConditions.elementToBeClickable(scooterMakeDropdown));
        Select sel = new Select(scooterMake);
        sel.selectByVisibleText(sMake); // Select the first item as a placeholder
        System.out.println("Selected scooter brand from dropdown.");
    }

    // 2) Click the search button using JavaScript
    public void clickSearchButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", searchButton);
        System.out.println("Clicked search button.");
    }

}

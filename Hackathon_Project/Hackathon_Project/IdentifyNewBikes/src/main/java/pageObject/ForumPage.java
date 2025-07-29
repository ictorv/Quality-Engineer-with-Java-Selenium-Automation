package pageObject;

import java.time.Duration;
import java.util.Set;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForumPage extends BasePage {

	String main;
	private WebDriverWait wait;

	public ForumPage(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.wait.pollingEvery(Duration.ofMillis(500));
		this.driver = driver;
		// TODO Auto-generated constructor stub
		
		PageFactory.initElements(driver, this);
	}

	// Locators
	// Locating more element
	@FindBy(xpath = "//*[@id='headerNewVNavWrap']/nav/ul/li[5]/span")
	WebElement more;

	// Locating on Forum element
	@FindBy(xpath = "//*[@id='headerNewVNavWrap']/nav/ul/li[5]/ul/li[2]/a")
	WebElement forum;

	// Locating Car or Bike element
	@FindBy(id = "typeList")
	WebElement typeList;

	// Locating make element
	@FindBy(id = "makeList")
	WebElement makeModel;

	// Locating model element
	@FindBy(id = "modelList")
	WebElement makeMode;

	// Locating search element
	@FindBy(name = "search")
	WebElement search;

	// Locating FaceBook Login
	@FindBy(xpath = "//div[@onclick='fb_login();']")
	WebElement faceBook;

	// Locating Email
	@FindBy(xpath = "//input[@name='email']")
	WebElement email;

	// Locating Login
	@FindBy(xpath = "//input[@name='login']")
	WebElement login;

	// Locating Error Message
	@FindBy(xpath = "//form[@id='login_form']/div[1]/div[1]")
	WebElement errorMessage;

	// Locating password block
	@FindBy(xpath = "//input[@id='pass']")
	WebElement password;

	// Reaching Home Page
	@FindBy(xpath = "//a//img[@alt='Home']")
	WebElement home;

	// Community Page Validation
	@FindBy(xpath = "//h1")	
	WebElement valid;
	

	// Actions

	// 0) Validation of Froum Community page
	public void validationZigWheels() {
		String validName = valid.getText();
		Assert.assertEquals("Zigwheels Community", validName);
	}

	// 1) Clicking on forum
	public void Forum() {
		forum.click();
	}

	// 2) Select Car/Bike
	public void vehicleType(String car) {
		typeList.click();
		Select select = new Select(typeList);
		select.selectByVisibleText(car); // Excel dat
	}

	// 3) Select MakeModel
	public void company(String company) {
		makeModel.click();
		Select select = new Select(makeModel);
		select.selectByVisibleText(company); // Excel Data
	}

	// 4) Select MakeMode
	public void model(String model) {
		makeMode.click();
		Select select = new Select(makeMode);
		select.selectByVisibleText(model); // Excel Data
	}

	// 5) Click Search
	public void Search() {
		search.click();
	}
	
	
	private void waitForPageLoad() {
        wait.until(webDriver ->
            ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
	
	// 8)
	public void faceBook() {
        main = driver.getWindowHandle();
        wait.until(ExpectedConditions.elementToBeClickable(faceBook)).click();
        // Wait for new window and switch
        wait.until(d -> d.getWindowHandles().size() > 1);
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            if (!window.equals(main)) {
                driver.switchTo().window(window);
                break;
            }
        }
        // Wait for FB page to fully load
        waitForPageLoad();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
    }

}

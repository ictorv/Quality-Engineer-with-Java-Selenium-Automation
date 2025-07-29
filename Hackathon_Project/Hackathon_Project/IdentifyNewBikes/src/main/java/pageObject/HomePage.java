package pageObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	public static int total;
	// Constructors
	public HomePage(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.wait.pollingEvery(Duration.ofMillis(500));
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Locators
	// 1a) Locate the Search Box
	@FindBy(xpath = "//*[@id='headerSearch']")
	WebElement searchBox;

	// 1b) Element to get text "Result Not Found"
	@FindBy(xpath = "//*[@id='content_search_result_div']/div[2]")
	WebElement element1;

	// 1c)Get List of Honda Search
	@FindBy(xpath = "//*[@id='ui-id-1']/li/a/b")
	List<WebElement> searchList;
	
	@FindBy(css = "ul#ui-id-1 li.ui-menu-item")
    List<WebElement> dropdownItems;

	// *********Refresh*******
	@FindBy(xpath = "//a//img[@alt='Home']")
	WebElement refresh;

	// *****Testing Logo Function*****
	// 2a) Locating Logo element
	@FindBy(xpath = "//*[@id='Header']/div/div[1]/div[1]/a/img")
	WebElement logo;
	// 2b) Locating NewRewiew element
	@FindBy(xpath = "//*[@id='headerNewVNavWrap']/nav/ul/li[1]/a")
	WebElement newsReview;
	// 2c) Locating NewBikes element
	@FindBy(xpath = "//*[@id='headerNewVNavWrap']/nav/ul/li[2]/a")
	WebElement newCars;
	// 2d) Locating NewBikes element
	@FindBy(xpath = "//*[@id='headerNewVNavWrap']/nav/ul/li[3]/a")
	WebElement newBikes;
	// 2e) Locating Scooters element
	@FindBy(xpath = "//*[@id='headerNewVNavWrap']/nav/ul/li[4]/a")
	WebElement scooters;

	// 3) Locating on Login/SingIn
	@FindBy(xpath = "//div[@id='forum_login_wrap_lg']")
	WebElement login;

	// 4) Locating more element
	@FindBy(xpath = "//*[@id='headerNewVNavWrap']/nav/ul/li[5]/span")
	WebElement more;

	// 4a) Locating UsedCars element
	@FindBy(xpath = "//div//a[@title='Used Cars']")
	WebElement usedCars;

	// 4b) Locating on Forum element
	@FindBy(xpath = "//*[@id='headerNewVNavWrap']/nav/ul/li[5]/ul/li[2]/a")
	WebElement forum;

	// 5) Element of closing Login Page
	@FindBy(xpath = "//span[@id='report_submit_close_login']")
	WebElement close;

	// 6) Home Validation
	@FindBy(xpath = "//a//img[@title='ZigWheels Home']")
	WebElement home;
	
	//7) Top bike Brands
	@FindBy(xpath="//*[@id='zw-cmnSilder']/div[2]/a")
	WebElement topBikesElement;
	@FindBy(xpath="//*[@id='bikeBrands']/div/div/div/div[2]/span[2]")
	WebElement nextButton1;
	@FindBy(xpath="//*[@id='bikeBrands']/div/div/div/div[1]/ul/li/a")
	List<WebElement> bikeBrandList;
	
	
	// Actions
	// *********Refresh Clicking********
	public void refresh() {
		refresh.click();
	}

	// 1a)Sending Search Data
	public void invalidData(String Invalid) {
		searchBox.sendKeys(Invalid,Keys.ENTER);
	}

	// 1b) Printing error message
	public String errorMessage() {
		wait.until(ExpectedConditions.visibilityOf(element1));
		String errorMessage = element1.getText();
		return errorMessage;
	}

	// 2a) Click LOGO
	public void logo() {
		logo.click();
	}

	// 2b) Click New&Review
	public void clickNewsAndReviews() {
		wait.until(ExpectedConditions.elementToBeClickable(newsReview)).click();
		System.out.println("Clicked News & Reviews");
	}

	// 2c) Click newCars
	public void clickNewCars() {
		wait.until(ExpectedConditions.elementToBeClickable(newCars)).click();
		System.out.println("Clicked New Cars");
	}

	// 2d) Click NewBikes
	public void clickNewBikes() {
		wait.until(ExpectedConditions.elementToBeClickable(newBikes)).click();
		System.out.println("Clicked New Bikes");
	}

	// 2e) Click Scooters
	public void clickScooters() {
		wait.until(ExpectedConditions.elementToBeClickable(scooters)).click();
		System.out.println("Clicked Scooters");
	}

	// 3)Clicking on Login Button
	public void login() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(login)).click();
	}

	// 4) Go to More
	public void more() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement moreTab = wait.until(ExpectedConditions.visibilityOf(more));
		Actions actions = new Actions(driver);
		actions.moveToElement(moreTab).perform();
	}

	// 4a) Click on UsedCars
	public void usedCars() {
		usedCars.click();
	}

	// 4b) Click on forum
	public void forum() {
		forum.click();
	}

	// 5) Closing the login page
	public void close() {
		close.click();
	}

	// 6) HomePage Validation
	@SuppressWarnings("deprecation")
	public void homeValidation() throws MalformedURLException {
		String fullURL = driver.getCurrentUrl();
		URL url = new URL(fullURL); // java.net.URL
		String domain = url.getHost(); // Returns "www.zigwheels.com"
		Assert.assertEquals(domain, "www.zigwheels.com");
	}
	
	public void scrollToTopBikes() {
		js.executeScript("arguments[0].scrollIntoView(true);", topBikesElement);
		wait.until(ExpectedConditions.elementToBeClickable(nextButton1));
	}
	
	public void topBikeBrands() {
		System.out.println("Total:"+bikeBrandList.size());
		total = bikeBrandList.size()-1;
		for(int i=0;i<bikeBrandList.size()-1;i++) {
			js.executeScript("arguments[0].scrollIntoView(true);", nextButton1);
			if(!bikeBrandList.get(i).isDisplayed()) {
				js.executeScript("arguments[0].scrollIntoView(true);", topBikesElement);
				wait.until(ExpectedConditions.elementToBeClickable(nextButton1));
				js.executeScript("arguments[0].scrollIntoView(true);", topBikesElement);
				wait.until(ExpectedConditions.elementToBeClickable(nextButton1)); 
				js.executeScript("arguments[0].click();", nextButton1);
				wait.until(ExpectedConditions.elementToBeClickable(bikeBrandList.get(i)));
			}
			System.out.println(bikeBrandList.get(i).getText());
		}
	}
	
	public boolean validateTopBrands() {
		if(total==12) {
			return true;
		}
		return false;
	}
	
	
}
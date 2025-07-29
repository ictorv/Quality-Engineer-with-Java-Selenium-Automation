package pageObject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UpcomingBikePage extends BasePage {
	WebDriver driver;
	List<String> bikes = new ArrayList<String>();
	// 1) Constructor to initialize page elements
	public UpcomingBikePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// 3) Locating "Read More" button
	@FindBy(xpath = "//main//span[text()='Read More']")
	WebElement readMore;

	// 4) Locating list of upcoming bikes
	@FindBy(xpath = "//main//div[3]/table//tbody/tr")
	List<WebElement> upcomingBike;
	
	@FindBy(xpath = "//*[@id='make-faqs']/div[4]/div[1]")
	WebElement FAQ;

	// 5) Locating FAQs section arrow
	@FindBy(xpath = "//div[@id='make-faqs']/div[4]//span")
	WebElement FAQsArrow;

	// 6) Locating answer for upcoming models in FAQs
	@FindBy(xpath = "//div[@id='make-faqs']/div[4]//div[2]")
	WebElement FAQs;

	// 7) Locating header hover element (More section)
	@FindBy(xpath = "//div[@id='headerNewVNavWrap']//span")
	WebElement hoverElement;

	// 8) Locating homepage icon
	@FindBy(xpath = "//a//img[@alt='Home']")
	WebElement home;
	

	// 3) Click on "Read More" with wait
	public void readMore() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(readMore)).click();
	}
	
	public List<String> upcomingBikeList(){
		for (WebElement price1 : upcomingBike) {
			String price = price1.getText();
			String[] splitPrice = price.split(" ");
			bikes.add(splitPrice[0]+" "+splitPrice[1]+" "+splitPrice[2]);
		}
		return bikes;
	}

	// 5) Extract only bike less then or equal 4Lakh
	public Map<String, String> upcomingBike() {

		Map<String, String> map = new HashMap<>();
		for (WebElement price1 : upcomingBike) {
			String price = price1.getText();
			String[] splitPrice = price.split(" ");
			List<String> wordList = Arrays.asList(splitPrice);
			String bike1;
			if (wordList.contains("Lakh")) {
				bike1 = splitPrice[splitPrice.length - 2];
			} else {
				bike1 = splitPrice[splitPrice.length - 1];
			}
			int inRupees;
			if (bike1.contains(",")) {
				String amount = bike1.replace(",", "");
				inRupees = Integer.parseInt(amount);
			} else {
				float inLakhs = Float.parseFloat(bike1);
				inRupees = (int) (inLakhs * 100000);
			}
			if (inRupees <= 400000) {
				// upcomingBike1.add(price);
				map.put(splitPrice[0] + " " + splitPrice[1] + " " + splitPrice[2], price);
			}
		}

		// Print the Upcoming Honda Bike under 400000
		System.out.println("The New Upcoming Honda Bike Are");
		return map;

	}

	// 6) Interact with FAQs and print upcoming models information
	public String FQAss() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		while(!FAQ.isDisplayed()) {
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		}
		js.executeScript("arguments[0].click();", FAQsArrow);
		String text = FAQs.getText();
		return text;
	}
	
}

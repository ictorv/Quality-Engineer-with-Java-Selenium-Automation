package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.WaitUtil;

public class SearchResultMoviePage extends BasePage {

	public SearchResultMoviePage(WebDriver driver) {
		super(driver);
	}

	// locators
	@FindBy(xpath = "//a[@class='color111']")
	WebElement movieName;

	@FindBy(xpath = "//div[contains(@class,'movcard_name') and contains(@class,'font18')]")
	List<WebElement> theatresAvailable;

	@FindBy(xpath = "//div[contains(@class,'fw500 ') and contains(@class,'font14') and contains(@class,'color111 ')]")
	List<WebElement> locationsAvailable;

	@FindBy(xpath = "//ul[@class = 'movie_showlist']/descendant::span[1]")
	List<WebElement> showTimings;

	// actions
	public void waitUntilMovieNameToAppear() {
		WaitUtil.waitForOneElement(driver, movieName, 10);
	}

	public String getMovieName() {
		return movieName.getText();
	}

	public List<String> getMovieDetails() {
		List<String> details = new ArrayList<>();
		//int count = 0;
		for (int i = 0; i < Math.min(theatresAvailable.size(),5); i++) {
			details.add( theatresAvailable.get(i).getText() + " , "+ locationsAvailable.get(i).getText() + " , " + showTimings.get(i).getText());
			//count++;
		}
		return details;
	}

}

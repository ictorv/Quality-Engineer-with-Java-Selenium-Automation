package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.WaitUtil;

public class SearchResultPageCarWash extends BasePage {

	public SearchResultPageCarWash(WebDriver driver) {
		super(driver);
	}

	// locators
	@FindBy(xpath="//div[@role='navigation']/following::div[1]/h1")
	WebElement searchResultHeading;
	
	@FindBy(xpath="//button[starts-with(@class, 'jsx') and contains(@class, 'resfilter_item')]")
	List<WebElement> filterList;

	@FindBy(xpath = "//li[starts-with(@id, 'option')]")
	List<WebElement> ratingOptions;

	@FindBy(xpath = "//div[starts-with(@class, 'jsx') and contains(@class, 'resultbox_textbox')]")
	List<WebElement> searchResults;

	@FindBy(xpath = "//div[starts-with(@class, 'jsx') and contains(@class, 'resultbox_textbox')]//following::h3")
	List<WebElement> carNames;

	@FindBy(xpath = "//div[starts-with(@class, 'jsx') and contains(@class, 'resultbox_textbox')]//following::span[contains(@class, 'callcontent')]")
	List<WebElement> phoneNumbers;

	@FindBy(xpath = "//div[contains(@class, 'resultbox_textbox')]//li[starts-with(@class, 'resultbox_countrate')]")
	List<WebElement> peopleRatings;

	// actions
	public String getHeading() {
		return searchResultHeading.getText();
	}
	
	public void selectFilter(String filterOption) {
		for(WebElement x:filterList) {
			if(x.getText().contains(filterOption)) {
				x.click();
				break;
			}
		}
	}

	public void setRating(String rating) {
		for(WebElement x:ratingOptions) {
			if(x.getText().contains(rating)) {
				x.click();
				break;
			}
		}
	}

	public List<String> getSearchResults() {
		WaitUtil.waitForMultipleElement(driver, searchResults, 20);

		int count = 0;
		int size = Math.min(phoneNumbers.size(), Math.min(peopleRatings.size(), carNames.size()));

		List<String> result = new ArrayList<>();

		for (int i = 0; i < size; i++) {
			if (count == 5) {
				break;
			}

			String value = peopleRatings.get(i).getText().split(" ")[0].replace(",", "");
			String name = carNames.get(i).getText();
			String phoneNumber = phoneNumbers.get(i).getText();

			int rating = Integer.parseInt(value);

			if (rating > 20 && !phoneNumber.equals("Show Number")) {
				result.add(name+" / "+phoneNumber);
				count++;
			}
		}

		return result;

	}

}

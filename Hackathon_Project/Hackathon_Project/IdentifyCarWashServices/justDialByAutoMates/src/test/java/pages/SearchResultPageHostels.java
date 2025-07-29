package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultPageHostels extends BasePage {

	public SearchResultPageHostels(WebDriver driver) {
		super(driver);
	}

	// locators
	@FindBy(xpath="//button[starts-with(@class, 'jsx') and contains(@class, 'resfilter_item')]")
	List<WebElement> filterList;

	@FindBy(xpath = "//div[contains(@class, 'resultbox_textbox')]")
	List<WebElement> hotelSearchResults;

	@FindBy(xpath = "//div[contains(@class, 'resultbox_textbox')]//div[contains(@class, 'locatcity')]")
	List<WebElement> hotelLocationList;

	@FindBy(xpath = "//div[contains(@class, 'resultbox_textbox')]//h3")
	List<WebElement> hotelNameList;

	// actions
	public void selectFilter(String filterOption) {
		for(WebElement x:filterList) {
			if(x.getText().contains(filterOption)) {
				x.click();
				break;
			}
		}
	}

	public List<String> getHotelResultList() {
		int count = 0;
		
		List<String> result = new ArrayList<>();
		
		for (int i = 0; i < hotelSearchResults.size(); i++) {
			if (count == 5) {
				break;
			}
			result.add(hotelNameList.get(i).getText() + " , " + hotelLocationList.get(i).getText());
			count++;
		}
		return result;
	}

}

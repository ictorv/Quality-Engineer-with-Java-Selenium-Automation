package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultPageRestaurant extends BasePage{
		
	public SearchResultPageRestaurant(WebDriver driver) {
		super(driver);
	}
	
	//locators
	@FindBy(xpath="//button[starts-with(@class, 'jsx') and contains(@class, 'resfilter_item')]")
	List<WebElement> filterList;
	
	@FindBy(xpath = "//li[starts-with(@id, 'option')]")
	List<WebElement> cuisineOptions;
	
	@FindBy(xpath="//div[contains(@class, 'resultbox_textbox')]")
	List<WebElement> restaurantSearchResults;
	
	@FindBy(xpath="//div[contains(@class, 'resultbox_textbox')]//li[starts-with(@class, 'resultbox_countrate')]")
	List<WebElement> peopleRatings;
	
	@FindBy(xpath="//div[contains(@class, 'resultbox_textbox')]//h3")
	List<WebElement> hotelNames;
	
	@FindBy(xpath="//li[starts-with(@class, 'resultbox_totalrate')]")
	List<WebElement> ratings;
	
	//actions	
	public void selectFilter(String filterOption) {
		for(WebElement x:filterList) {
			if(x.getText().contains(filterOption)) {
				x.click();
				break;
			}
		}
	}
	
	public void setCuisine(String cuisine) {
		for(WebElement x:cuisineOptions) {
			if(x.getText().contains(cuisine)) {
				x.click();
				break;
			}
		}
	}
	
	public List<String> getRestaurantSearchResults() {
		List<String> results = new ArrayList<>();
		int count=0;
		for(int i=0;i<restaurantSearchResults.size();i++) {
			if (count == 5) {
				break;
			}
			results.add(hotelNames.get(i).getText()+" , "+ratings.get(i).getText()+" , "+peopleRatings.get(i).getText());
			count++;
		}
		return results;
	}
	
	public List<Integer> getNoOfRatings() {
		List<String> results= getRestaurantSearchResults();
		List<Integer> ratings = new ArrayList<>();
		
		for (String result : results) {
	        for (String line : result.split("\n")) {
	            if (line.startsWith("Number Of Ratings:")) {
	                String digits = line.replaceAll("[^0-9]", "");
	                if (!digits.isEmpty()) {
	                    ratings.add(Integer.parseInt(digits));
	                }
	            }
	        }
	    }
		
		return ratings;
	}
	
}
